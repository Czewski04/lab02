package package1;

import java.util.ArrayList;

public class Game {
    Integer numberOfCopies;
    Integer minNumberOfPlayers;
    Integer maxNumberOfPlayers;
    ArrayList<GameCopy> CopiesList;

    public Game( Integer numberOfCopies, Integer minNumberOfPlayers, Integer maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.minNumberOfPlayers = minNumberOfPlayers;
        this.numberOfCopies = numberOfCopies;
        this.CopiesList = new ArrayList<>();
        for(int i=0; i<numberOfCopies; i++) {
            this.CopiesList.add(new GameCopy());
        }
    }
}
