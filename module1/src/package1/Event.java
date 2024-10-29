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
        FittingGamesToTables.fit(listOfGames, listOfTables);
    }

    public void wyniki(){
        int licznik1=0;
        int licznik2=0;
        int licznik3=0;
        int licznik4=0;
        int licznik5=0;
        int licznik6=0;
        int licznik7=0;
        int licznik8=0;
        int tyle_gra=0;

        //wyniki dopasowania
        for (Player player : listOfPlayers) {
            if(!player.atTheTable)
                licznik1++;
            else if(player.gameInPersonalRanking==1)
                licznik2++;
            else if(player.gameInPersonalRanking==2)
                licznik3++;
            else if(player.gameInPersonalRanking==3)
                licznik4++;
        }

        for(Table table: listOfTables){
            if(table.gamesOnTable.size()>1)
                licznik8++;
            if(table.full)
                licznik5++;
            else{
                if(table.freePlaces==table.places)
                    licznik7++;
                licznik6 += table.freePlaces;
            }
        }

        tyle_gra = licznik2+licznik3+licznik4;
        System.out.println("\nnie gra: " + licznik1 + " osób");
        System.out.println("gra: "+ tyle_gra + " osób\n");
        System.out.println("gra z pierwszego wyboru: "+ licznik2);
        System.out.println("gra z drugiego wyboru: "+ licznik3);
        System.out.println("gra z 3 wyboru: "+ licznik4);
        System.out.println("\npełnych stołów: " + licznik5 + " z "+ listOfTables.size());
        System.out.println("pozostawiono: "+ licznik6 + " wolnych miejsc przy wszystkich stołach");
        System.out.println("stoły puste: " + licznik7);
        System.out.println("więcej niż 1 gra na: " + licznik8 + " stołach");
    }
}
