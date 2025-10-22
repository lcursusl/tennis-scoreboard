package com.scoreboard.tennis.controller;

import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.service.FinishedMatchesPersistenceService;
import com.scoreboard.tennis.service.MatchScoreCalculationService;
import com.scoreboard.tennis.service.OngoingMatchesService;
import com.scoreboard.tennis.util.ExceptionHandler;
import com.scoreboard.tennis.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private OngoingMatchesService ongoingMatchesService;
    private MatchScoreCalculationService matchScoreCalculationService;
    private FinishedMatchesPersistenceService finishedMatchesPersistenceService;

    @Override
    public void init() {
        ongoingMatchesService = new OngoingMatchesService();
        matchScoreCalculationService = new MatchScoreCalculationService();
        finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uuidParam = request.getParameter("uuid");
            Validator.validateUuid(uuidParam);

            MatchDto match = ongoingMatchesService.getMatch(uuidParam);
            request.setAttribute("match", match);
            request.getRequestDispatcher("/match-score.jsp").forward(request, response);
        } catch (Exception e) {
            ExceptionHandler.handle(e, request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String uuidParam = request.getParameter("uuid");
            Long winnerId = Long.valueOf(request.getParameter("winnerId"));
            Validator.validateUuid(uuidParam);
            Validator.validatePlayerId(winnerId);

            MatchDto match = ongoingMatchesService.getMatch(uuidParam);
            MatchDto updatedMatch = matchScoreCalculationService.updateMatchScore(match, winnerId);

            if (matchScoreCalculationService.matchIsOver(updatedMatch)) {
                finishedMatchesPersistenceService.persist(updatedMatch, winnerId);
                response.sendRedirect( request.getContextPath() + "/matches?page=1&filter_by_player_name=");
            } else {
                ongoingMatchesService.saveMatch(uuidParam, updatedMatch);
                request.setAttribute("match", updatedMatch);
                request.getRequestDispatcher("/match-score.jsp").forward(request, response);
            }
        } catch (Exception e) {
            ExceptionHandler.handle(e, request, response);
        }
    }
}
