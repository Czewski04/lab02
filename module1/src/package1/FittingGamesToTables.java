package package1;

import java.util.ArrayList;
import java.util.HashMap;

public class FittingGamesToTables {
    public static void fit(HashMap<Integer, Game> games, ArrayList<Table> tables) {
        int i, fullCounter, maxPlaces=0;
        for(Table table : tables) {
            if(maxPlaces<table.places)
                maxPlaces = table.places;
        }
        for (Game game : games.values()) {
            for(GameCopy gameCopy : game.CopiesList){
                if(gameCopy.fittedPlayers.size()>=game.minNumberOfPlayers){
                    i=0;
                    while(!gameCopy.onTable && i<maxPlaces){
                        fullCounter=0;
                        for(Table table : tables){
                            if(!table.full){
                                if(gameCopy.fittedPlayers.size()+i==table.freePlaces){
                                    table.gamesOnTable.add(gameCopy);
                                    gameCopy.onTable = true;
                                    table.freePlaces -= gameCopy.fittedPlayers.size();
                                    for(Player player : gameCopy.fittedPlayers){
                                        player.atTheTable = true;
                                        player.satisfaction = (float) 1/player.gameInPersonalRanking;
                                    }
                                    if(table.freePlaces<2)
                                        table.full = true;
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
