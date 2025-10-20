package com.scoreboard.tennis.controller;

import com.scoreboard.tennis.dto.MatchDto;
import com.scoreboard.tennis.service.MatchService;
import com.scoreboard.tennis.util.ExceptionHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {
    private MatchService matchService;

    @Override
    public void init() {
        matchService = new MatchService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String uuidParam = req.getParameter("uuid");
            MatchDto matchDto = matchService.getScore(uuidParam);
            req.setAttribute("match", matchDto);
            req.getRequestDispatcher("/match-score.jsp").forward(req, resp);
        } catch (Exception e) {
            ExceptionHandler.handle(e, req, resp);
        }
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        UUID matchId = matchService.updateMatchScore(uuid);
        resp.sendRedirect(req.getContextPath() + "/match-score.jsp?uuid=" + matchId);
    }*/
}
