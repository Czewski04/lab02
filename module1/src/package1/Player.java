package package1;

import java.util.ArrayList;

public class Player {
    Integer id;
    ArrayList<Integer> preferredGamesList;
    boolean fitted;
    Integer fittedGameId;
    Integer fittedCopyId;

    public Player(Integer id, ArrayList<Integer> gamesIdListStringType) {
        this.id = id;
        this.preferredGamesList = new ArrayList<>(gamesIdListStringType);
        this.fitted = false;
        this.fittedGameId = null;
        this.fittedCopyId = null;
    }
}
