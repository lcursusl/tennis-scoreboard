package com.scoreboard.tennis.service;

import com.scoreboard.tennis.model.Match;
import com.scoreboard.tennis.repository.MatchRepository;

import java.util.List;

public class MatchesService {
    private final MatchRepository matchRepository = new MatchRepository();

    public List<Match> getMatches(int currentPage, String filterByPlayerName) {
        if (filterByPlayerName == null || filterByPlayerName.isEmpty()) {
            return matchRepository.findAll(currentPage);
        }
        return matchRepository.findByName(currentPage, filterByPlayerName);
    }

    public Long getTotalPages(String filterByPlayerName) {
        if (filterByPlayerName == null || filterByPlayerName.isEmpty()) {
            return matchRepository.findTotalPages();
        }
        return matchRepository.findTotalPages(filterByPlayerName);
    }
}
