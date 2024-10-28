package package1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Event {
    //ArrayList<Game> listOfGames;
    ArrayList<Table> listOfTables;
    ArrayList<Player> listOfPlayers;
    HashMap<Integer, Game> listOfGames;

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

    public void fitting(){
        FittingPlayersToGames.fit(listOfPlayers, listOfGames);
        for (Player player : listOfPlayers) {
            if(player.fitted)
                System.out.println(player.id);
        }
    }
}
