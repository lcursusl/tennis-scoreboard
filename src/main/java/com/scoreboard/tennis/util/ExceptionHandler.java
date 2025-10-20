package com.scoreboard.tennis.util;

import com.scoreboard.tennis.exception.InvalidIdException;
import com.scoreboard.tennis.exception.InvalidPlayerException;
import com.scoreboard.tennis.exception.SamePlayersException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ExceptionHandler {
    private static final String INDEX_JSP = "/index.jsp";
    private static final String NEW_MATCH_JSP = "/new-match.jsp";
    private static final String MATCH_SCORE_JSP = "/match-score.jsp";
    private static final String MATCHES_JSP = "/matches.jsp";

    public static void handle(Exception e, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String view;

        if (e instanceof InvalidPlayerException || e instanceof SamePlayersException) {
            view = NEW_MATCH_JSP;
        } else if (e instanceof InvalidIdException) {
            view = MATCH_SCORE_JSP;
        }
        else {
            view = INDEX_JSP;
        }

        req.setAttribute("error", e.getMessage());
        req.getRequestDispatcher(view).forward(req, resp);
    }
}
