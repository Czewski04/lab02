package eventresources;

import java.util.ArrayList;

public class GameCopy {
    private ArrayList<Player> fittedPlayers;
    private boolean onTable;

    public GameCopy() {
        this.fittedPlayers = new ArrayList<>();
        this.onTable = false;
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
}
