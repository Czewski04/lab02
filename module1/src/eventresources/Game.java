package eventresources;

import java.util.ArrayList;

public class Game {
    private Integer numberOfCopies;
    private Integer minNumberOfPlayers;
    private Integer maxNumberOfPlayers;
    private ArrayList<GameCopy> CopiesList;

    public Game(Integer gameId, Integer numberOfCopies, Integer minNumberOfPlayers, Integer maxNumberOfPlayers) {
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.minNumberOfPlayers = minNumberOfPlayers;
        this.numberOfCopies = numberOfCopies;
        this.CopiesList = new ArrayList<>();
        for(int i=0; i<numberOfCopies; i++) {
            this.CopiesList.add(new GameCopy(gameId, minNumberOfPlayers));
        }
    }

    public ArrayList<GameCopy> getCopiesList() {
        return CopiesList;
    }

    public Integer getMaxNumberOfPlayers() {
        return maxNumberOfPlayers;
    }

    public Integer getMinNumberOfPlayers() {
        return minNumberOfPlayers;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }
}
