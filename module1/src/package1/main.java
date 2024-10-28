package package1;

import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        Event event = new Event();
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting();
    }
}
