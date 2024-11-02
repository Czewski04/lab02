package eventresources;

import algorithms.FittingGamesToTables;
import algorithms.FittingPlayersToGames;
import othermechanics.Reader;
import othermechanics.Scoring;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Event {

    ArrayList<Table> listOfTables;
    ArrayList<Player> listOfPlayers;
    HashMap<Integer, Game> mapOfGames;
    ArrayList<GameCopy> listOfGames;

    ArrayList<Table> listOfTables1;
    ArrayList<Player> listOfPlayers1;
    HashMap<Integer, Game> mapOfGames1;
    ArrayList<GameCopy> listOfGames1;

    ArrayList<Table> listOfTables2;
    ArrayList<Player> listOfPlayers2;
    HashMap<Integer, Game> mapOfGames2;
    ArrayList<GameCopy> listOfGames2;

    ArrayList<Table> listOfTables3;
    ArrayList<Player> listOfPlayers3;
    HashMap<Integer, Game> mapOfGames3;
    ArrayList<GameCopy> listOfGames3;

    float[] weights = new float[3];
    float[] scores = new float[4];

    public void fillListOfGames() throws FileNotFoundException {
        mapOfGames = new HashMap<>();
        listOfGames = new ArrayList<>();

        int i=0;
        try{
            while(true){
                mapOfGames.put(Integer.valueOf(Reader.gameReader(i)[0]), new Game(Integer.valueOf(Reader.gameReader(i)[0]), Integer.valueOf(Reader.gameReader(i)[1]), Integer.valueOf(Reader.gameReader(i)[2]), Integer.valueOf(Reader.gameReader(i)[3])));
                i++;
            }
        }
        catch (NoSuchElementException e){
            e.getMessage();
        }
    }

    public void fillListOfTables() throws FileNotFoundException {
        listOfTables = new ArrayList<>();
        int i=0;
        try {
            while(true){
                listOfTables.add(new Table(Integer.valueOf(Reader.tableReader(i)[0]), Integer.valueOf(Reader.tableReader(i)[1])));
                i++;
            }
        }
        catch (NoSuchElementException e) {
            e.getMessage();
        }
    }

    public void fillListOfPlayers() throws FileNotFoundException {
        listOfPlayers = new ArrayList<>();
        int i=0;
        try{
            while(true){
                ArrayList<String> gamesIdListStringType = new ArrayList<>(Arrays.asList(Reader.playerReader(i)[1].split(", ")));

                ArrayList<Integer> gamesIdList = new ArrayList<>();

                for (String s : gamesIdListStringType) {
                    gamesIdList.add(Integer.parseInt(s));
                }
                listOfPlayers.add(new Player(Integer.valueOf(Reader.playerReader(i)[0]), gamesIdList));
                i++;
            }
        }
        catch (NoSuchElementException e){
            e.getMessage();
        }
    }

    public void fillWeights() {
        weights = Reader.weightReader();
    }


    public void fitting1BaseFitting(){
        FittingPlayersToGames.fit1GamesReadyToPlayMethod(listOfPlayers, mapOfGames, listOfGames);
        FittingGamesToTables.fit1GameSatisfactionPriority(listOfGames, listOfTables);
        FittingGamesToTables.clearOverloadedTabelsAndRefitting(listOfGames, listOfTables);
        FittingGamesToTables.clearNotFullTables(listOfGames, listOfTables);
        FittingPlayersToGames.fit1GamesReadyToPlayMethod(listOfPlayers, mapOfGames, listOfGames);
        FittingGamesToTables.complementFreePlaces(listOfGames, listOfTables);
    }

    public void fitting2ForHighPenalty(){
        FittingPlayersToGames.fit1GamesReadyToPlayMethod(listOfPlayers, mapOfGames, listOfGames);
        FittingGamesToTables.fit1GameSatisfactionPriority(listOfGames, listOfTables);
        FittingGamesToTables.clearOverloadedTabelsAndRefitting(listOfGames, listOfTables);
    }

    public void fitting3PlayedGamesPriorityForHighPenalty(){
        FittingPlayersToGames.fit2PlayedGamePiority(listOfPlayers, mapOfGames, listOfGames);
        FittingGamesToTables.fit1GameSatisfactionPriority(listOfGames, listOfTables);
        FittingGamesToTables.clearOverloadedTabelsAndRefitting(listOfGames, listOfTables);
    }

    public void fitting4PlayedGamesPriority(){
        FittingPlayersToGames.fit2PlayedGamePiority(listOfPlayers, mapOfGames, listOfGames);
        FittingGamesToTables.fit1GameSatisfactionPriority(listOfGames, listOfTables);
        FittingGamesToTables.clearOverloadedTabelsAndRefitting(listOfGames, listOfTables);
        FittingGamesToTables.clearNotFullTables(listOfGames, listOfTables);
        FittingPlayersToGames.fit2PlayedGamePiority(listOfPlayers, mapOfGames, listOfGames);
        FittingGamesToTables.complementFreePlaces(listOfGames, listOfTables);
    }


    public void calculateScore(){
        scores[3] = Scoring.calculateFinalScore(mapOfGames, listOfPlayers, listOfTables, weights);
    }

    public void clearLists(){
        listOfTables.clear();
        listOfPlayers.clear();
        mapOfGames.clear();
        listOfGames.clear();
    }

    public void copyToListst1(){
        listOfTables1 = new ArrayList<>(listOfTables);
        listOfPlayers1 = new ArrayList<>(listOfPlayers);
        listOfGames1 = new ArrayList<>(listOfGames);
        mapOfGames1 = new HashMap<>(mapOfGames);
        scores[0] = scores[3];
    }

    public void copyToListst2(){
        listOfTables2 = new ArrayList<>(listOfTables);
        listOfPlayers2 = new ArrayList<>(listOfPlayers);
        listOfGames2 = new ArrayList<>(listOfGames);
        mapOfGames2 = new HashMap<>(mapOfGames);
        scores[1] = scores[3];
    }

    public void copyToListst3(){
        listOfTables3 = new ArrayList<>(listOfTables);
        listOfPlayers3 = new ArrayList<>(listOfPlayers);
        listOfGames3 = new ArrayList<>(listOfGames);
        mapOfGames3 = new HashMap<>(mapOfGames);
        scores[2] = scores[3];
    }


    public void compareResults(){
        float result = 0;
        int bestResultIndex = 0;
        for(int i=0; i<scores.length; i++){
            if(scores[i]>result){
                result = scores[i];
                bestResultIndex = i;
            }
        }
        showResults(bestResultIndex, result);
    }

    public void showResults(int bestResultIndex, float bestScore){
        showBestResult(bestScore);

        switch(bestResultIndex){
            case 0:
                showTableArrangement(listOfTables1);
                Scoring.testCalculateParametrs(mapOfGames1, listOfPlayers1, listOfTables1);
                break;
            case 1:
                showTableArrangement(listOfTables2);
                Scoring.testCalculateParametrs(mapOfGames2, listOfPlayers2, listOfTables2);
                break;
            case 2:
                showTableArrangement(listOfTables3);
                Scoring.testCalculateParametrs(mapOfGames3, listOfPlayers3, listOfTables3);
                break;
            case 3:
                showTableArrangement(listOfTables);
                Scoring.testCalculateParametrs(mapOfGames, listOfPlayers, listOfTables);
                break;
        }

    }

    public void showTableArrangement(ArrayList<Table> tables) {
        System.out.println("\n=========== Table layout ==========\n");
        for (Table table : tables) {
            System.out.println("Table " + table.getId() + ":");
            System.out.println("-------------------------------------");
            System.out.printf("%-10s %-10s%n", "Game", "Players");
            System.out.println("-------------------------------------");

            for (GameCopy gameOnTable : table.getGamesOnTable()) {
                System.out.printf("%-10d ", gameOnTable.getMainGameId());
                for (Player playerAtTheTable : gameOnTable.getFittedPlayers()) {
                    System.out.print(playerAtTheTable.getId() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public void showBestResult(float bestScore) {
        String scoreStr = " " + bestScore + " ";
        int frameWidth = 15;

        for (int i = 0; i < frameWidth + 2; i++) {
            System.out.print("•");
        }
        System.out.println();

        System.out.print("•");
        System.out.print("  BEST RESULT  ");
        System.out.println("•");

        int padding = (frameWidth - scoreStr.length()) / 2;
        System.out.print("•");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(scoreStr);
        for (int i = 0; i < (frameWidth - scoreStr.length() - padding); i++) {
            System.out.print(" ");
        }
        System.out.println("•");

        for (int i = 0; i < frameWidth + 2; i++) {
            System.out.print("•");
        }
        System.out.println();
    }

}
