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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uuidParam = req.getParameter("uuid");
            Validator.validateUuid(uuidParam);

            MatchDto match = ongoingMatchesService.getMatch(uuidParam);
            req.setAttribute("match", match);
            req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
        } catch (Exception e) {
            ExceptionHandler.handle(e, req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uuidParam = req.getParameter("uuid");
            Long winnerId = Long.valueOf(req.getParameter("winnerId"));
            Validator.validateUuid(uuidParam);
            Validator.validatePlayerId(winnerId);

            MatchDto match = ongoingMatchesService.getMatch(uuidParam);
            MatchDto updatedMatch = matchScoreCalculationService.updateMatchScore(match, winnerId);

            if (matchScoreCalculationService.matchIsOver(updatedMatch)) {
                finishedMatchesPersistenceService.persist(updatedMatch, winnerId);
            } else {
                ongoingMatchesService.saveMatch(uuidParam, updatedMatch);
            }
            req.setAttribute("match", updatedMatch);
            req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
        } catch (Exception e) {
            ExceptionHandler.handle(e, req, resp);
        }
    }
}
