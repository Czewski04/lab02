package mainpackage;

import eventresources.Event;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Event event = new Event();
        attempt1(event);
        attempt2(event);
        attempt3(event);
        attempt4(event);
        //attempt5(event);
    }

    public static void attempt1(Event event) throws FileNotFoundException {
        event.fillWeights();
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting1BaseFitting();
        event.calculateScore();
        event.copyToListst1();
        event.clearLists();
    }

    public static void attempt2(Event event) throws FileNotFoundException {
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting2ForHighPenalty();
        event.calculateScore();
        event.copyToListst2();
        event.clearLists();
    }

    public static void attempt3(Event event) throws FileNotFoundException {
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting3PlayedGamesPriorityForHighPenalty();
        event.calculateScore();
        event.copyToListst3();
        event.clearLists();
    }

    public static void attempt4(Event event) throws FileNotFoundException {
        event.fillListOfGames();
        event.fillListOfTables();
        event.fillListOfPlayers();
        event.fitting4PlayedGamesPriority();
        event.calculateScore();
        event.compareResults();
    }

//    public static void attempt5(Event event) throws FileNotFoundException {
//        event.fillWeights();
//        event.fillListOfGames();
//        event.fillListOfTables();
//        event.fillListOfPlayers();
//        //event.fitting8PlayedGamesPriority();
//        //event.fitting7PlayedGamesPriority();
//        event.fitting1BaseFitting();
//        event.showTestResults();
//    }
}