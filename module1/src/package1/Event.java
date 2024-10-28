package package1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Event {
    ArrayList<Game> listOfGames;
    ArrayList<Table> listOfTables;
    ArrayList<Player> listOfPlayers;

    public void fillListOfGames() throws FileNotFoundException {
        listOfGames = new ArrayList<>();
        int i=0;
        try{
            while(true){
                listOfGames.add(new Game(Integer.valueOf(Reader.gameReader(i)[0]),Integer.valueOf(Reader.gameReader(i)[1]),Integer.valueOf(Reader.gameReader(i)[2]),Integer.valueOf(Reader.gameReader(i)[3])));
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
        System.out.println(listOfTables.get(0).id);
        System.out.println(listOfTables.get(1).id);
    }

    public void fillListOfPlayers() throws FileNotFoundException {
        listOfPlayers = new ArrayList<>();
        int i=0;
        try{
            while(true){
                ArrayList<String> gamesIdListStringType = new ArrayList<>(Arrays.asList(Reader.playerReader(i)[1].split(", ")));

                ArrayList<Integer> gamesIdList = new ArrayList<>();

                for(int j=0; i<gamesIdListStringType.size(); j++){
                    gamesIdList.add(Integer.parseInt(gamesIdListStringType.get(j)));
                }
                listOfPlayers.add(new Player(Integer.valueOf(Reader.playerReader(i)[0]), gamesIdList));
                i++;
            }
        }
        catch (NoSuchElementException e){
            e.getMessage();
        }


        System.out.println(listOfPlayers.get(0).id);
    }


}
