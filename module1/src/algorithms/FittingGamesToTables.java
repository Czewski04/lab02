package algorithms;

import eventresources.Game;
import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;
import othermechanics.GameComparator;

import java.util.ArrayList;
import java.util.HashMap;

public class FittingGamesToTables {

    public static void fit1(HashMap<Integer, Game> games, ArrayList<Table> tables) {
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

    public static void fit2(HashMap<Integer, Game> games, ArrayList<Table> tables) {
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
                                    if(!table.getGamesOnTable().isEmpty())
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

    public static void fit3_testing(ArrayList <GameCopy> games, ArrayList<Table> tables) {
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
                            table.getGamesOnTable().add(gameCopy);
                            gameCopy.setOnTable(true);
                            table.setFreePlaces(table.getFreePlaces()-gameCopy.getFittedPlayers().size());
                            for(Player player : gameCopy.getFittedPlayers()){
                                player.setAtTheTable(true);
                                player.setSatisfaction((float) 1/player.getGameInPersonalRanking());
                            }
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

    public static void refittingTest(ArrayList <GameCopy> games, ArrayList<Table> tables) {
        games.sort(new GameComparator());
        boolean done = false;
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
                                    gameCopy.getFittedPlayers().getLast().setTmpSatisfaction(0);
                                    gameCopy.getFittedPlayers().getLast().setFittedGameId(null);
                                    gameCopy.getFittedPlayers().getLast().setFitted(false);
                                    gameCopy.getFittedPlayers().getLast().setSatisfaction(0);
                                    gameCopy.getFittedPlayers().getLast().setGameInPersonalRanking(0);
                                    gameCopy.getFittedPlayers().getLast().setAtTheTable(false);
                                    gameCopy.getFittedPlayers().removeLast();
                                }
                                while (!table.getGamesOnTable().isEmpty()) {
                                    table.getGamesOnTable().getLast().setOnTable(false);
                                    for(Player player: table.getGamesOnTable().getLast().getFittedPlayers()){
                                        player.setAtTheTable(false);
                                    }
                                    table.getGamesOnTable().removeLast();
                                }
                                table.setFreePlaces(table.getPlaces());
                                table.getGamesOnTable().add(gameCopy);
                                gameCopy.setOnTable(true);
                                table.setFreePlaces(table.getFreePlaces() - gameCopy.getFittedPlayers().size());
                                for (Player player : gameCopy.getFittedPlayers()) {
                                    player.setAtTheTable(true);
                                    player.setSatisfaction((float) 1 / player.getGameInPersonalRanking());
                                }
                                if(table.getFreePlaces()>0)
                                    table.setFull(false);
                                else
                                    table.setFull(true);
                                done = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void refitting2Test(ArrayList <GameCopy> games, ArrayList<Table> tables) {
        games.sort(new GameComparator());
        boolean done = false;
        int sumOfFreePlaces = 0;

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
                                        gameCopy.getFittedPlayers().getLast().setTmpSatisfaction(0);
                                        gameCopy.getFittedPlayers().getLast().setFittedGameId(null);
                                        gameCopy.getFittedPlayers().getLast().setFitted(false);
                                        gameCopy.getFittedPlayers().getLast().setSatisfaction(0);
                                        gameCopy.getFittedPlayers().getLast().setGameInPersonalRanking(0);
                                        gameCopy.getFittedPlayers().getLast().setAtTheTable(false);
                                        gameCopy.getFittedPlayers().removeLast();
                                    }
                                    table.getGamesOnTable().add(gameCopy);
                                    gameCopy.setOnTable(true);
                                    table.setFreePlaces(table.getFreePlaces() - gameCopy.getFittedPlayers().size());
                                    for (Player player : gameCopy.getFittedPlayers()) {
                                        player.setAtTheTable(true);
                                        player.setSatisfaction((float) 1 / player.getGameInPersonalRanking());
                                    }
                                    if(table.getFreePlaces()>0)
                                        table.setFull(false);
                                    else
                                        table.setFull(true);
                                    done = true;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            sumOfFreePlaces = 0;
            for(Table table: tables){
                sumOfFreePlaces += table.getFreePlaces();
            }
        }while(sumOfFreePlaces>0);
    }
}
