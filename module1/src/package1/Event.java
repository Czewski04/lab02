package package1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Event {

    ArrayList<Table> listOfTables;
    ArrayList<Player> listOfPlayers;
    HashMap<Integer, Game> listOfGames;
    float score;
    float score1;
    ArrayList<Table> listOfTables1;
    ArrayList<Player> listOfPlayers1;
    HashMap<Integer, Game> listOfGames1;
    float[] weights = new float[3];

    public void fillListOfGames() throws FileNotFoundException {
        listOfGames = new HashMap<>();
        int i=0;
        try{
            while(true){
                listOfGames.put(Integer.valueOf(Reader.gameReader(i)[0]), new Game(Integer.valueOf(Reader.gameReader(i)[1]),Integer.valueOf(Reader.gameReader(i)[2]),Integer.valueOf(Reader.gameReader(i)[3])));
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

                for(int j=0; j<gamesIdListStringType.size(); j++){
                    gamesIdList.add(Integer.parseInt(gamesIdListStringType.get(j)));
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

    public void fitting1(){
        FittingPlayersToGames.fit1(listOfPlayers, listOfGames);
        FittingGamesToTables.fit(listOfGames, listOfTables);
    }

    public void fitting2(){
        FittingPlayersToGames.fit2(listOfPlayers, listOfGames);
        FittingGamesToTables.fit(listOfGames, listOfTables);
    }

    public void calculateScore(){
        //Scoring.testCalculateParametrs(listOfGames, listOfPlayers, listOfTables);
        score = Scoring.calculateFinalScore(listOfGames, listOfPlayers, listOfTables, weights);
        System.out.println(score);
    }

    public void clearLists(){
        listOfTables.clear();
        listOfPlayers.clear();
        listOfGames.clear();
    }

    public void copyLists(){
        listOfTables1 = new ArrayList<>(listOfTables);
        listOfPlayers1 = new ArrayList<>(listOfPlayers);
        listOfGames1 = new HashMap<>(listOfGames);
        score1 = score;
    }

    public void compareResults(){
        if(score>score1){
            //Scoring.testCalculateParametrs(listOfGames, listOfPlayers, listOfTables);
            System.out.println("2nd win " + score);
        }
        else{
            //Scoring.testCalculateParametrs(listOfGames1, listOfPlayers1, listOfTables1);
            System.out.println("1st win " + score1);
        }
    }
}
