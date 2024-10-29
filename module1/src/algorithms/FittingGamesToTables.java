package algorithms;

import eventresources.Game;
import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;

import java.util.ArrayList;
import java.util.HashMap;

public class FittingGamesToTables {

    public static void fit(HashMap<Integer, Game> games, ArrayList<Table> tables) {
        int i, fullCounter, maxPlaces=0;

        for(Table table : tables) {
            if(maxPlaces<table.getPlaces())
                maxPlaces = table.getPlaces();
        }

        for (Game game : games.values()) {
            for(GameCopy gameCopy : game.getCopiesList()){
                if(gameCopy.getFittedPlayers().size()>=game.getMinNumberOfPlayers()){
                    i=0;
                    while(!gameCopy.isOnTable() && i<maxPlaces){
                        fullCounter=0;
                        for(Table table : tables){
                            if(!table.isFull()){
                                if(gameCopy.getFittedPlayers().size()+i==table.getFreePlaces()){
                                    table.getGamesOnTable().add(gameCopy);
                                    gameCopy.setOnTable(true);
                                    table.setFreePlaces(table.getFreePlaces()-gameCopy.getFittedPlayers().size());
                                    for(Player player : gameCopy.getFittedPlayers()){
                                        player.setAtTheTable(true);
                                        player.setSatisfaction((float) 1/player.getGameInPersonalRanking());
                                    }
                                    if(table.getFreePlaces()<2)
                                        table.setFull(true);
                                    break;
                                }
                            }
                            else    fullCounter ++;
                        }
                        i++;
                        if(fullCounter==tables.size()){
                            return;
                        }
                    }
                }
            }
        }
    }
}
