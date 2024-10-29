package othermechanics;

import eventresources.Game;
import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;

import java.util.ArrayList;
import java.util.HashMap;

public class Scoring {

    public static float calculateFinalScore(HashMap<Integer, Game> games, ArrayList<Player> players, ArrayList<Table> tables, float[] weights) {
        float sumOfSat=0;
        int activePlayers=0;
        int cane=0;

        int licznik7=0;
        int licznik9=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                activePlayers++;
                sumOfSat += player.getSatisfaction();
            }
        }

        for(Table table: tables){
            if(table.getFreePlaces()==table.getPlaces())
                    licznik7++;
        }

        for(Game game: games.values()){
            for (GameCopy gameCopy: game.getCopiesList()){
                if(gameCopy.isOnTable())
                    licznik9++;
            }
        }

        if(tables.size()-licznik7-licznik9<0)
            cane = tables.size()-licznik7-licznik9;

        return weights[0]*activePlayers+weights[1]*sumOfSat+weights[2]*cane;
    }

}
