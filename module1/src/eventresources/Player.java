package eventresources;

import java.util.ArrayList;

public class Player {
    private Integer id;
    private ArrayList<Integer> preferredGamesList;
    private boolean fitted;
    private boolean atTheTable;
    private Integer gameInPersonalRanking;
    private Integer fittedGameId;
    private float satisfaction;
    private float tmpSatisfaction;


    public Player(Integer id, ArrayList<Integer> gamesIdListStringType) {
        this.id = id;
        this.preferredGamesList = new ArrayList<>(gamesIdListStringType);
        this.fitted = false;
        this.atTheTable = false;
        this.gameInPersonalRanking = null;
        this.fittedGameId = null;
        this.satisfaction = 0;
    }

    public boolean isAtTheTable() {
        return atTheTable;
    }

    public void setAtTheTable(boolean atTheTable) {
        this.atTheTable = atTheTable;
    }

    public boolean isFitted() {
        return fitted;
    }

    public void setFitted(boolean fitted) {
        this.fitted = fitted;
    }

    public void setFittedGameId(Integer fittedGameId) {
        this.fittedGameId = fittedGameId;
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
