package othermechanics;

import eventresources.Game;
import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;

import java.util.ArrayList;
import java.util.HashMap;

public class Scoring {

    public static float calculateFinalScore(HashMap<Integer, Game> games, ArrayList<Player> players, ArrayList<Table> tables, float[] weights) {
        float sumOfSat=0;
        int activePlayers=0;
        int cane=0;

        int licznik7=0;
        int licznik9=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                activePlayers++;
                sumOfSat += player.getSatisfaction();
            }
        }

        for(Table table: tables){
            if(table.getFreePlaces()==table.getPlaces())
                    licznik7++;
        }

        for(Game game: games.values()){
            for (GameCopy gameCopy: game.getCopiesList()){
                if(gameCopy.isOnTable())
                    licznik9++;
            }
        }

        if(tables.size()-licznik7-licznik9<0)
            cane = tables.size()-licznik7-licznik9;

        return weights[0]*activePlayers+weights[1]*sumOfSat+weights[2]*cane;
    }

    public static void testCalculateParametrs(HashMap<Integer, Game> games, ArrayList<Player> players, ArrayList<Table> tables){
        int licznik1=0;
        int licznik2=0;
        int licznik3=0;
        int licznik4=0;
        int licznik5=0;
        int licznik6=0;
        int licznik7=0;
        int licznik8=0;
        int licznik9=0;
        int licznik10=0;
        float sumOfSat=0;
        int tyle_gra=0;
        int kara=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                sumOfSat += player.getSatisfaction();
                tyle_gra++;
            }

            if(!player.isAtTheTable())
                licznik1++;
            else if(player.getGameInPersonalRanking()==1)
                licznik2++;
            else if(player.getGameInPersonalRanking()==2)
                licznik3++;
            else if(player.getGameInPersonalRanking()==3)
                licznik4++;
            else if(player.getGameInPersonalRanking()==4)
                licznik10++;
        }

        for(Table table: tables){
            if(table.getGamesOnTable().size()>1)
                licznik8++;
            if(table.isFull())
                licznik5++;
            else{
                if(table.getFreePlaces()==table.getPlaces())
                    licznik7++;
                licznik6 += table.getFreePlaces();
            }
        }

        for(Game game: games.values()){
            for (GameCopy gameCopy: game.getCopiesList()){
                if(gameCopy.isOnTable())
                    licznik9++;
            }
        }

        if(tables.size()-licznik7-licznik9<0)
            kara = tables.size()-licznik7-licznik9;


        System.out.println("\nnie gra: " + licznik1 + " osób");
        System.out.println("gra: "+ tyle_gra + " osób\n");
        System.out.println("gra z pierwszego wyboru: "+ licznik2);
        System.out.println("gra z drugiego wyboru: "+ licznik3);
        System.out.println("gra z 3 wyboru: "+ licznik4);
        System.out.println("gra z 4 wyboru: "+ licznik10);
        System.out.println("\npełnych stołów: " + licznik5 + " z "+ tables.size());
        System.out.println("pozostawiono: "+ licznik6 + " wolnych miejsc przy wszystkich stołach");
        System.out.println("stoły puste: " + licznik7);
        System.out.println("więcej niż 1 gra na: " + licznik8 + " stołach");
        System.out.println("gier na stołach: " + licznik9);

        System.out.println("\nsatysfakcja: " + sumOfSat);
        System.out.println("kara: " + kara);
        float wynik=tyle_gra+sumOfSat+kara;
        System.out.println("wynik: " + wynik);
    }

    public static float calculateFinalScore(ArrayList<GameCopy> games, ArrayList<Player> players, ArrayList<Table> tables, float[] weights) {
        float sumOfSat=0;
        int activePlayers=0;
        int cane=0;

        int licznik7=0;
        int licznik9=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                activePlayers++;
                sumOfSat += player.getSatisfaction();
            }
        }

        for(Table table: tables){
            if(table.getFreePlaces()==table.getPlaces())
                licznik7++;
        }

        for(GameCopy game: games){
            if(game.isOnTable())
                licznik9++;
        }

        if(tables.size()-licznik7-licznik9<0)
            cane = tables.size()-licznik7-licznik9;

        return weights[0]*activePlayers+weights[1]*sumOfSat+weights[2]*cane;
    }

    public static void testCalculateParametrs(ArrayList<GameCopy> games, ArrayList<Player> players, ArrayList<Table> tables){
        int licznik1=0;
        int licznik2=0;
        int licznik3=0;
        int licznik4=0;
        int licznik5=0;
        int licznik6=0;
        int licznik7=0;
        int licznik8=0;
        int licznik9=0;
        int licznik10=0;
        float sumOfSat=0;
        int tyle_gra=0;
        int kara=0;

        for (Player player : players) {
            if(player.isAtTheTable()) {
                sumOfSat += player.getSatisfaction();
                tyle_gra++;
            }

            if(!player.isAtTheTable())
                licznik1++;
            else if(player.getGameInPersonalRanking()==1)
                licznik2++;
            else if(player.getGameInPersonalRanking()==2)
                licznik3++;
            else if(player.getGameInPersonalRanking()==3)
                licznik4++;
            else if(player.getGameInPersonalRanking()==4)
                licznik10++;
        }

        for(Table table: tables){
            licznik6 += table.getFreePlaces();
            if(table.getGamesOnTable().size()>1)
                licznik8++;
            if(table.isFull())
                licznik5++;
            else{
                if(table.getFreePlaces()==table.getPlaces())
                    licznik7++;

            }
        }

        for (GameCopy game:games){
            if(game.isOnTable())
                licznik9++;
        }

        if(tables.size()-licznik7-licznik9<0)
            kara = tables.size()-licznik7-licznik9;


        System.out.println("\nnie gra: " + licznik1 + " osób");
        System.out.println("gra: "+ tyle_gra + " osób\n");
        System.out.println("gra z pierwszego wyboru: "+ licznik2);
        System.out.println("gra z drugiego wyboru: "+ licznik3);
        System.out.println("gra z 3 wyboru: "+ licznik4);
        System.out.println("gra z 4 wyboru: "+ licznik10);
        System.out.println("\npełnych stołów: " + licznik5 + " z "+ tables.size());
        System.out.println("pozostawiono: "+ licznik6 + " wolnych miejsc przy wszystkich stołach");
        System.out.println("stoły puste: " + licznik7);
        System.out.println("więcej niż 1 gra na: " + licznik8 + " stołach");
        System.out.println("gier na stołach: " + licznik9);

        System.out.println("\nsatysfakcja: " + sumOfSat);
        System.out.println("kara: " + kara);
        float wynik=tyle_gra+sumOfSat+kara;
        System.out.println("wynik: " + wynik);
    }
}
