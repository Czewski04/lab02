package package1;

import java.util.ArrayList;

public class Player {
    Integer id;
    ArrayList<Integer> preferredGamesList;
    boolean fitted;
    boolean atTheTable;
    Integer gameInPersonalRanking;
    Integer fittedGameId;
    float satisfaction;


    public Player(Integer id, ArrayList<Integer> gamesIdListStringType) {
        this.id = id;
        this.preferredGamesList = new ArrayList<>(gamesIdListStringType);
        this.fitted = false;
        this.atTheTable = false;
        this.gameInPersonalRanking = null;
        this.fittedGameId = null;
        this.satisfaction = 0;
    }
}
