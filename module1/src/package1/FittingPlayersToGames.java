package package1;

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
            while(!found && i<player.preferredGamesList.size()) {
                int key = player.preferredGamesList.get(i);
                if(games.containsKey(key)){
                    for(int j=0; j<games.get(key).numberOfCopies; j++){
                        if(games.get(key).CopiesList.get(j).fittedPlayers.size() < games.get(key).maxNumberOfPlayers)
                        {
                            games.get(key).CopiesList.get(j).fittedPlayers.add(player);
                            found = true;
                            player.fitted = true;
                            player.gameInPersonalRanking = i+1;
                            player.fittedGameId = key;
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
                for(int h=0; h<player.preferredGamesList.size() && !found; h++) {
                    int key = player.preferredGamesList.get(h);
                    if(tmpGameList.containsKey(key)){
                        for(int j=0; j<tmpGameList.get(key).numberOfCopies; j++){
                            if(tmpGameList.get(key).CopiesList.get(j).fittedPlayers.size() < tmpGameList.get(key).maxNumberOfPlayers)
                            {
                                tmpGameList.get(key).CopiesList.get(j).fittedPlayers.add(player);
                                found = true;
                                player.fitted = true;
                                player.gameInPersonalRanking = h+1;
                                player.fittedGameId = key;
                                break;
                            }
                        }
                    }
                }
            for(int h=0; h<player.preferredGamesList.size() && !found; h++) {
                    int key = player.preferredGamesList.get(h);
                    if(games.containsKey(key)){
                        for(int j=0; j<games.get(key).numberOfCopies; j++){
                            if(games.get(key).CopiesList.get(j).fittedPlayers.size() < games.get(key).maxNumberOfPlayers)
                            {
                                games.get(key).CopiesList.get(j).fittedPlayers.add(player);
                                tmpGameList.put(key, games.get(key));
                                found = true;
                                player.fitted = true;
                                player.gameInPersonalRanking = h+1;
                                player.fittedGameId = key;
                                break;
                            }
                        }
                    }
                }
            if(!player.fitted)
                break;
            }
        }
    }
}
