package package1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FittingPlayersToGames {
    public static void fit(ArrayList<Player> players, HashMap<Integer, Game> games) {
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
                            //player.fittedCopyId = j;
                            break;
                        }
                    }
                }
                i++;
            }
//            if(!found){
//                for(int k=players.indexOf(player)-1; k>=0; k-=1){
//                    if(player.preferredGamesList.contains(players.get(k).preferredGamesList.getLast())){
//                        players.get(k).fitted = false;
//                        games.get(players.get(k).fittedGameId).CopiesList.get(players.get(k).fittedCopyId).fittedPlayers.remove(players.get(k));
//                    }
//                }
//            }
        }
    }
}
