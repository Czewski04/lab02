package package1;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Event event = new Event();
        attempt1(event);
        attempt2(event);
    }

    public static void attempt1(Event event) throws FileNotFoundException {
        event.fillWeights();
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting1();
        event.calculateScore();
        event.copyLists();
        event.clearLists();
    }

    public static void attempt2(Event event) throws FileNotFoundException {
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting2();
        event.calculateScore();
        event.compareResults();
    }
}