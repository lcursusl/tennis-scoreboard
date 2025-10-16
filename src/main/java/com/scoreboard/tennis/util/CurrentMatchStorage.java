package com.scoreboard.tennis.util;

import com.scoreboard.tennis.dto.GameScore;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class CurrentMatchStorage {
    private static final Map<UUID, GameScore> currentMatches = new ConcurrentHashMap<>();

    public void save(UUID uuid, GameScore gameScore) {
        currentMatches.put(uuid, gameScore);
    }

    public GameScore get(UUID uuid) {
        return currentMatches.get(uuid);
    }
}
