package com.xelorium.soccerleaguetable;

import com.xelorium.soccerleaguetable.model.MatchModel;

import java.util.LinkedList;
import java.util.List;

public class FixtureGenerator<T extends Object> {

    public List<List<MatchModel<T>>> getFixtures(List<T> teams, boolean includeReverseFixtures) {


        int numberOfTeams = teams.size();

        boolean ghost = false;
        if (numberOfTeams % 2 != 0) {
            numberOfTeams++;
            ghost = true;
        }

        int totalRounds = numberOfTeams - 1;
        int matchesPerRound = numberOfTeams / 2;
        List<List<MatchModel<T>>> rounds = new LinkedList<List<MatchModel<T>>>();

        for (int round = 0; round < totalRounds; round++) {
            List<MatchModel<T>> fixtures = new LinkedList<MatchModel<T>>();
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (numberOfTeams - 1);
                int away = (numberOfTeams - 1 - match + round) % (numberOfTeams - 1);

                if (match == 0) {
                    away = numberOfTeams - 1;
                }

                fixtures.add(new MatchModel<T>(teams.get(home), teams.get(away)));
            }
            rounds.add(fixtures);
        }

        List<List<MatchModel<T>>> interleaved = new LinkedList<List<MatchModel<T>>>();

        int evn = 0;
        int odd = (numberOfTeams / 2);
        for (int i = 0; i < rounds.size(); i++) {
            if (i % 2 == 0) {
                interleaved.add(rounds.get(evn++));
            } else {
                interleaved.add(rounds.get(odd++));
            }
        }

        rounds = interleaved;


        for (int roundNumber = 0; roundNumber < rounds.size(); roundNumber++) {
            if (roundNumber % 2 == 1) {
                MatchModel fixture = rounds.get(roundNumber).get(0);
                rounds.get(roundNumber).set(0, new MatchModel(fixture.getAwayTeamName(), fixture.getHomeTeamName()));
            }
        }

        if (includeReverseFixtures) {
            List<List<MatchModel<T>>> reverseFixtures = new LinkedList<List<MatchModel<T>>>();
            for (List<MatchModel<T>> round : rounds) {
                List<MatchModel<T>> reverseRound = new LinkedList<MatchModel<T>>();
                for (MatchModel<T> fixture : round) {
                    reverseRound.add(new MatchModel<T>(fixture.getAwayTeamName(), fixture.getHomeTeamName()));
                }
                reverseFixtures.add(reverseRound);
            }
            rounds.addAll(reverseFixtures);
        }

        return rounds;
    }

}
