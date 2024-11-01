package eventresources;

import java.util.ArrayList;

public class GameCopy {
    private ArrayList<Player> fittedPlayers;
    private boolean onTable;
    private float tmpSatisfaction;
    private int minNumbersOfPlayers;

    public GameCopy(int minNumbersOfPlayers) {
        this.fittedPlayers = new ArrayList<>();
        this.onTable = false;
        this.tmpSatisfaction = 0;
        this.minNumbersOfPlayers = minNumbersOfPlayers;
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
}
