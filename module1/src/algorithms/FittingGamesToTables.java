package algorithms;

import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;
import comparators.GameComparator;

import java.util.ArrayList;

public class FittingGamesToTables {
    public static void fit1GameSatisfactionPriority(ArrayList <GameCopy> games, ArrayList<Table> tables) {
        int i, fullCounter, maxPlaces=0;

        games.sort(new GameComparator());

        for(Table table : tables) {
            if(maxPlaces<table.getPlaces())
                maxPlaces = table.getPlaces();
        }

        for(GameCopy gameCopy : games){
            i=0;
            while(!gameCopy.isOnTable() && i<maxPlaces){
                fullCounter=0;
                for(Table table : tables){
                    if(!table.isFull()){
                        if(gameCopy.getFittedPlayers().size()+i==table.getFreePlaces()){
                            addingGameToTable(gameCopy, table);
                            if(table.getFreePlaces()<1)
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

    private static void addingGameToTable(GameCopy gameCopy, Table table) {
        table.getGamesOnTable().add(gameCopy);
        gameCopy.setOnTable(true);
        table.setFreePlaces(table.getFreePlaces()-gameCopy.getFittedPlayers().size());
        for(Player player : gameCopy.getFittedPlayers()){
            player.setAtTheTable(true);
            player.setSatisfaction((float) 1/player.getGameInPersonalRanking());
        }
    }

    public static void clearOverloadedTabelsAndRefitting(ArrayList <GameCopy> games, ArrayList<Table> tables) {
        games.sort(new GameComparator());
        boolean done;
        for(Table table : tables) {
            done = false;
            if(table.getGamesOnTable().size()>1 || table.getFreePlaces()>1){
                for(int i=0; i<table.getPlaces(); i++) {
                    if(done)
                        break;
                    for (GameCopy gameCopy : games) {
                        if (!gameCopy.isOnTable()) {
                            if (table.getPlaces() - i <= gameCopy.getFittedPlayers().size() && table.getPlaces() - i >= gameCopy.getMinNumbersOfPlayers()) {
                                while (table.getPlaces() - i != gameCopy.getFittedPlayers().size()) {
                                    gameCopy.subtractTmpSatisfaction(gameCopy.getFittedPlayers().getLast().getTmpSatisfaction());
                                    clearPlayer(gameCopy);
                                }
                                removeGameFromTable(table);
                                addingGameToTable(gameCopy, table);
                                table.setFull(table.getFreePlaces() <= 0);

                                done = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void removeGameFromTable(Table table) {
        while (!table.getGamesOnTable().isEmpty()) {
            table.getGamesOnTable().getLast().setOnTable(false);
            for(Player player: table.getGamesOnTable().getLast().getFittedPlayers()){
                player.setAtTheTable(false);
            }
            table.getGamesOnTable().removeLast();
        }
        table.setFreePlaces(table.getPlaces());
    }

    private static void clearPlayer(GameCopy gameCopy) {
        gameCopy.getFittedPlayers().getLast().setTmpSatisfaction(0);
        gameCopy.getFittedPlayers().getLast().setFitted(false);
        gameCopy.getFittedPlayers().getLast().setSatisfaction(0);
        gameCopy.getFittedPlayers().getLast().setGameInPersonalRanking(0);
        gameCopy.getFittedPlayers().getLast().setAtTheTable(false);
        gameCopy.getFittedPlayers().removeLast();
    }

    public static void complementFreePlaces(ArrayList <GameCopy> games, ArrayList<Table> tables) {
        games.sort(new GameComparator());
        boolean done;
        int sumOfFreePlaces;
        int playersInGames;
        int playersAtTheTable;
        int gamesOnTable;

        do{
            for(Table table : tables) {
                done = false;
                if(table.getFreePlaces() > 0){
                    for(int i=0; i<table.getFreePlaces(); i++) {
                        if(done)
                            break;
                        for (GameCopy gameCopy : games) {
                            if (!gameCopy.isOnTable()) {
                                if (table.getFreePlaces() - i <= gameCopy.getFittedPlayers().size() && table.getFreePlaces() - i >= gameCopy.getMinNumbersOfPlayers()) {
                                    while (table.getFreePlaces() - i != gameCopy.getFittedPlayers().size()) {
                                        gameCopy.subtractTmpSatisfaction(gameCopy.getFittedPlayers().getLast().getTmpSatisfaction());
                                        clearPlayer(gameCopy);
                                    }
                                    addingGameToTable(gameCopy, table);
                                    table.setFull(table.getFreePlaces() <= 0);
                                    done = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            sumOfFreePlaces = 0;
            playersInGames = 0;
            playersAtTheTable = 0;
            gamesOnTable = 0;
            for(Table table: tables){
                sumOfFreePlaces += table.getFreePlaces();
            }
            for(GameCopy game: games){
                for(Player player : game.getFittedPlayers()){
                    if(player.isAtTheTable()){
                        playersAtTheTable++;
                    }
                    playersInGames++;
                }
                if(game.isOnTable()){
                    gamesOnTable++;
                }
            }
        }while(sumOfFreePlaces>0 && playersInGames!=playersAtTheTable && gamesOnTable<games.size());
    }

    public static void clearNotFullTables(ArrayList<GameCopy> gamesReadyToPlay, ArrayList<Table> tables) {
        for(Table table : tables){
            if(!table.isFull()){
                removeGameFromTable(table);
            }
        }

        for(int i=0; i<gamesReadyToPlay.size(); i++){
            if(!gamesReadyToPlay.get(i).isOnTable()){
                while(!gamesReadyToPlay.get(i).getFittedPlayers().isEmpty()){
                    clearPlayer(gamesReadyToPlay.get(i));
                }
                gamesReadyToPlay.remove(i);
                i-=1;
            }
        }

    }
}
