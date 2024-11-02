package othermechanics;

import eventresources.Game;
import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Scoring {

    public static float calculateFinalScore(HashMap<Integer, Game> games, ArrayList<Player> players, ArrayList<Table> tables, float[] weights) {
        float sumOfSat=0;
        int activePlayers=0;
        int penalty=0;

        int fullTables=0;
        int gamesOnTables=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                activePlayers++;
                sumOfSat += player.getSatisfaction();
            }
        }

        for(Table table: tables){
            if(Objects.equals(table.getFreePlaces(), table.getPlaces()))
                    fullTables++;
        }

        for(Game game: games.values()){
            for (GameCopy gameCopy: game.getCopiesList()){
                if(gameCopy.isOnTable())
                    gamesOnTables++;
            }
        }

        if(tables.size()-fullTables-gamesOnTables<0)
            penalty = tables.size()-fullTables-gamesOnTables;

        return weights[0]*activePlayers+weights[1]*sumOfSat+weights[2]*penalty;
    }

    public static HashMap<String, Object> calculateStatistics(HashMap<Integer, Game> games, ArrayList<Player> players, ArrayList<Table> tables, float[] weights) {

        HashMap<String, Object> statistics = new HashMap<>();

        int dontPlay=0;
        int firstChoice=0;
        int secondChoice=0;
        int thirdChoice=0;
        int fullTables=0;
        int freePlaces=0;
        int emptyTables=0;
        int moreThanOneGameOnTable=0;
        int gamesOnTables=0;
        int fourthChoice=0;
        float sumOfSat=0;
        int activePlayers=0;
        int penalty=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                sumOfSat += player.getSatisfaction();
                activePlayers++;
            }

            if(!player.isAtTheTable())
                dontPlay++;
            else if(player.getGameInPersonalRanking()==1)
                firstChoice++;
            else if(player.getGameInPersonalRanking()==2)
                secondChoice++;
            else if(player.getGameInPersonalRanking()==3)
                thirdChoice++;
            else if(player.getGameInPersonalRanking()==4)
                fourthChoice++;
        }

        for(Table table: tables){
            if(table.getGamesOnTable().size()>1)
                moreThanOneGameOnTable++;
            if(table.isFull())
                fullTables++;
            else{
                if(Objects.equals(table.getFreePlaces(), table.getPlaces()))
                    emptyTables++;
                freePlaces += table.getFreePlaces();
            }
        }

        for(Game game: games.values()){
            for (GameCopy gameCopy: game.getCopiesList()){
                if(gameCopy.isOnTable())
                    gamesOnTables++;
            }
        }

        if(tables.size()-emptyTables-gamesOnTables<0)
            penalty = tables.size()-emptyTables-gamesOnTables;


        statistics.put("players don't play", dontPlay);
        statistics.put("players play", activePlayers);
        statistics.put("first choice game", firstChoice);
        statistics.put("second choice game", secondChoice);
        statistics.put("third choice game", thirdChoice);
        statistics.put("fourth choice game", fourthChoice);
        statistics.put("full tables", fullTables);
        statistics.put("more than one game on tables", moreThanOneGameOnTable);
        statistics.put("games on tables", gamesOnTables);
        statistics.put("free places", freePlaces);
        statistics.put("empty tables", emptyTables);
        statistics.put("satisfaction", sumOfSat*weights[1]);
        statistics.put("penalty", penalty*weights[2]);

        return statistics;
    }
}
