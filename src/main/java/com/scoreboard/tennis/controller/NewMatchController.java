package com.scoreboard.tennis.controller;

import com.scoreboard.tennis.service.PlayerService;
import com.scoreboard.tennis.util.ExceptionHandler;
import com.scoreboard.tennis.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {
    private PlayerService playerService;

    @Override
    public void init() {
        playerService = new PlayerService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String player1Name = req.getParameter("player1");
            String player2Name = req.getParameter("player2");
            Validator.validatePlayers(player1Name, player2Name);

            UUID matchId = playerService.createNewMatch(player1Name, player2Name);
            resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + matchId);
        } catch (Exception e) {
            ExceptionHandler.handle(e, req, resp);
        }
    }
}
