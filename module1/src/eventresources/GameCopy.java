package eventresources;

import java.util.ArrayList;

public class GameCopy {
    private final ArrayList<Player> fittedPlayers;
    private boolean onTable;
    private float tmpSatisfaction;
    private final int minNumbersOfPlayers;
    private final int mainGameId;

    public GameCopy(int mainGameId, int minNumbersOfPlayers) {
        this.fittedPlayers = new ArrayList<>();
        this.onTable = false;
        this.tmpSatisfaction = 0;
        this.minNumbersOfPlayers = minNumbersOfPlayers;
        this.mainGameId = mainGameId;
    }

    public ArrayList<Player> getFittedPlayers() {
        return fittedPlayers;
    }

    public boolean isOnTable() {
        return onTable;
    }

    public void setOnTable(boolean onTable) {
        this.onTable = onTable;
    }

    public float getTmpSatisfaction() {
        return tmpSatisfaction;
    }

    public void addTmpSatisfaction(float tmpSatisfaction) {
        this.tmpSatisfaction += tmpSatisfaction;
    }

    public void subtractTmpSatisfaction(float tmpSatisfaction) {
        this.tmpSatisfaction -= tmpSatisfaction;
    }

    public int getMinNumbersOfPlayers() {
        return minNumbersOfPlayers;
    }

    public int getMainGameId(){
        return mainGameId;
    }
}
