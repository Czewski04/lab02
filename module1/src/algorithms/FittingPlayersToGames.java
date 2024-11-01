package algorithms;

import eventresources.Game;
import eventresources.GameCopy;
import eventresources.Player;
import othermechanics.PlayerComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FittingPlayersToGames {
    public static void fit2PlayedGamePiority(ArrayList<Player> players, HashMap<Integer, Game> games, ArrayList<GameCopy> gamesReadyToPlay) {
        HashMap<Integer, Game> tmpGameList = new HashMap<>();
        Collections.sort(players, new PlayerComparator());
        for (Player player : players) {
            while(!player.isFitted()) {
                for(int h=0; h<player.getPreferredGamesList().size() && !player.isFitted(); h++) {
                    int key = player.getPreferredGamesList().get(h);
                    if(tmpGameList.containsKey(key)){
                        for(int j=0; j<tmpGameList.get(key).getNumberOfCopies(); j++){
                            if(tmpGameList.get(key).getCopiesList().get(j).getFittedPlayers().size() < tmpGameList.get(key).getMaxNumberOfPlayers())
                            {
                                tmpGameList.get(key).getCopiesList().get(j).getFittedPlayers().add(player);
                                player.setFitted(true);
                                player.setGameInPersonalRanking(h+1);
                                player.setFittedGameId(key);
                                break;
                            }
                        }
                    }
                }
                for(int h=0; h<player.getPreferredGamesList().size() && !player.isFitted(); h++) {
                    int key = player.getPreferredGamesList().get(h);
                    if(games.containsKey(key)){
                        for(int j=0; j<games.get(key).getNumberOfCopies(); j++){
                            if(games.get(key).getCopiesList().get(j).getFittedPlayers().size() < games.get(key).getMaxNumberOfPlayers())
                            {
                                games.get(key).getCopiesList().get(j).getFittedPlayers().add(player);
                                tmpGameList.put(key, games.get(key));
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
        for(Game g : games.values()){
            for(GameCopy gameCopy: g.getCopiesList()){
                if(gameCopy.getFittedPlayers().size()>=gameCopy.getMinNumbersOfPlayers()){
                    gamesReadyToPlay.add(gameCopy);
                }
            }
        }
    }

    public static void fit1GamesReadyToPlayMethod(ArrayList<Player> players, HashMap<Integer, Game> games, ArrayList<GameCopy> gamesReadyToPlay) {
        players.sort(new PlayerComparator());
        for (Player player : players) {
            for(int i=0; i<player.getPreferredGamesList().size() && !player.isFitted(); i++) {
                int key = player.getPreferredGamesList().get(i);
                if (games.containsKey(key)) {
                    for (int j = 0; j < games.get(key).getNumberOfCopies(); j++) {
                        if (games.get(key).getCopiesList().get(j).getFittedPlayers().size() < games.get(key).getMaxNumberOfPlayers()&& !games.get(key).getCopiesList().get(j).isOnTable()) {
                            games.get(key).getCopiesList().get(j).getFittedPlayers().add(player);
                            player.setFitted(true);
                            player.setGameInPersonalRanking(i + 1);
                            player.setFittedGameId(key);

                            calculateTmpSatisfaction(player);
                            games.get(key).getCopiesList().get(j).addTmpSatisfaction(player.getTmpSatisfaction());
                            if (games.get(key).getCopiesList().get(j).getFittedPlayers().size() >= games.get(key).getMinNumberOfPlayers() && !gamesReadyToPlay.contains(games.get(key).getCopiesList().get(j)))
                                gamesReadyToPlay.add(games.get(key).getCopiesList().get(j));
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void calculateTmpSatisfaction(Player player) {
        player.setTmpSatisfaction((float) 1/player.getGameInPersonalRanking());
    }


}
