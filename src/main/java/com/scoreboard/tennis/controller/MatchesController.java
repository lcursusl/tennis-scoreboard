package com.scoreboard.tennis.controller;

import com.scoreboard.tennis.model.Match;
import com.scoreboard.tennis.service.MatchesService;
import com.scoreboard.tennis.util.ExceptionHandler;
import com.scoreboard.tennis.util.Validator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesController extends HttpServlet {
    private MatchesService matchesService;

    @Override
    public void init() {
        matchesService = new MatchesService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String page = request.getParameter("page");
            String filter_by_player_name = request.getParameter("filter_by_player_name");
            int currentPage = Validator.validatePage(page);

            List<Match> matches = matchesService.getMatches(currentPage, filter_by_player_name);
            Long totalPages = matchesService.getTotalPages(filter_by_player_name);

            request.setAttribute("matches", matches);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("totalPages", totalPages);
            request.getRequestDispatcher("matches.jsp").forward(request, response);
        } catch (Exception e) {
            ExceptionHandler.handle(e, request, response);
        }
    }
}
