package eventresources;

import java.util.ArrayList;

public class Player {
    private final Integer id;
    private final ArrayList<Integer> preferredGamesList;
    private boolean fitted;
    private boolean atTheTable;
    private Integer gameInPersonalRanking;
    private float satisfaction;
    private float tmpSatisfaction;


    public Player(Integer id, ArrayList<Integer> gamesIdListStringType) {
        this.id = id;
        this.preferredGamesList = new ArrayList<>(gamesIdListStringType);
        this.fitted = false;
        this.atTheTable = false;
        this.gameInPersonalRanking = null;
        this.satisfaction = 0;
    }

    public boolean isAtTheTable() {
        return atTheTable;
    }

    public void setAtTheTable(boolean atTheTable) {
        this.atTheTable = atTheTable;
    }

    public boolean isNotFitted() {
        return !fitted;
    }

    public void setFitted(boolean fitted) {
        this.fitted = fitted;
    }

    public Integer getGameInPersonalRanking() {
        return gameInPersonalRanking;
    }

    public void setGameInPersonalRanking(Integer gameInPersonalRanking) {
        this.gameInPersonalRanking = gameInPersonalRanking;
    }

    public ArrayList<Integer> getPreferredGamesList() {
        return preferredGamesList;
    }

    public float getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(float satisfaction) {
        this.satisfaction = satisfaction;
    }

    public float getTmpSatisfaction() {
        return tmpSatisfaction;
    }

    public void setTmpSatisfaction(float tmpSatisfaction) {
        this.tmpSatisfaction = tmpSatisfaction;
    }

    public Integer getId() {
        return id;
    }
}
