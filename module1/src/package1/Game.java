package package1;

public class Game {
    Integer id;
    Integer numberOfCopies;
    Integer minNumberOfPlayers;
    Integer maxNumberOfPlayers;

    public Game(Integer id, Integer numberOfCopies, Integer minNumberOfPlayers, Integer maxNumberOfPlayers) {
        this.id = id;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.minNumberOfPlayers = minNumberOfPlayers;
        this.numberOfCopies = numberOfCopies;
    }
}
