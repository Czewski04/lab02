package algorithms;

import eventresources.Game;
import eventresources.Player;
import othermechanics.PlayerComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FittingPlayersToGames {

    public static void fit1(ArrayList<Player> players, HashMap<Integer, Game> games) {
        boolean found;
        int i;
        Collections.sort(players, new PlayerComparator());
        for (Player player : players) {
            i=0;
            found=false;
            while(!found && i<player.getPreferredGamesList().size()) {
                int key = player.getPreferredGamesList().get(i);
                if(games.containsKey(key)){
                    for(int j=0; j<games.get(key).getNumberOfCopies(); j++){
                        if(games.get(key).getCopiesList().get(j).getFittedPlayers().size() < games.get(key).getMaxNumberOfPlayers())
                        {
                            games.get(key).getCopiesList().get(j).getFittedPlayers().add(player);
                            found = true;
                            player.setFitted(true);
                            player.setGameInPersonalRanking(i+1);
                            player.setFittedGameId(key);
                            break;
                        }
                    }
                }
                i++;
            }
        }
    }

    public static void fit2(ArrayList<Player> players, HashMap<Integer, Game> games) {
        HashMap<Integer, Game> tmpGameList = new HashMap<>();
        boolean found;
        Collections.sort(players, new PlayerComparator());
        for (Player player : players) {
            found=false;
            while(!found) {
                for(int h=0; h<player.getPreferredGamesList().size() && !found; h++) {
                    int key = player.getPreferredGamesList().get(h);
                    if(tmpGameList.containsKey(key)){
                        for(int j=0; j<tmpGameList.get(key).getNumberOfCopies(); j++){
                            if(tmpGameList.get(key).getCopiesList().get(j).getFittedPlayers().size() < tmpGameList.get(key).getMaxNumberOfPlayers())
                            {
                                tmpGameList.get(key).getCopiesList().get(j).getFittedPlayers().add(player);
                                found = true;
                                player.setFitted(true);
                                player.setGameInPersonalRanking(h+1);
                                player.setFittedGameId(key);
                                break;
                            }
                        }
                    }
                }
            for(int h=0; h<player.getPreferredGamesList().size() && !found; h++) {
                    int key = player.getPreferredGamesList().get(h);
                    if(games.containsKey(key)){
                        for(int j=0; j<games.get(key).getNumberOfCopies(); j++){
                            if(games.get(key).getCopiesList().get(j).getFittedPlayers().size() < games.get(key).getMaxNumberOfPlayers())
                            {
                                games.get(key).getCopiesList().get(j).getFittedPlayers().add(player);
                                tmpGameList.put(key, games.get(key));
                                found = true;
                                player.setFitted(true);
                                player.setGameInPersonalRanking(h+1);
                                player.setFittedGameId(key);
                                break;
                            }
                        }
                    }
                }
            if(!player.isFitted())
                break;
            }
        }
    }
}
