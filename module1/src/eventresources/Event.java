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
                mapOfGames.put(Integer.valueOf(Reader.gameReader(i)[0]), new Game(Integer.valueOf(Reader.gameReader(i)[1]),Integer.valueOf(Reader.gameReader(i)[2]),Integer.valueOf(Reader.gameReader(i)[3])));
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
        //Scoring.testCalculateParametrs(listOfGames, listOfPlayers, listOfTables);
        scores[3] = Scoring.calculateFinalScore(mapOfGames, listOfPlayers, listOfTables, weights);
        System.out.println(scores[3]);
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
        bestResultIndex +=1;
        System.out.println("Wygrywa alogrytm: "+ bestResultIndex + " z wynikiem: "+ result);
        Scoring.testCalculateParametrs(mapOfGames1, listOfPlayers1, listOfTables1);
    }

//    public void showTestResults(){
//        System.out.println(Scoring.calculateFinalScore(listOfGames, listOfPlayers, listOfTables, weights));
//        Scoring.testCalculateParametrs(listOfGames, listOfPlayers, listOfTables);
//    }
}
