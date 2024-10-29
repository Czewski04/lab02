package package1;

import java.util.ArrayList;

public class Table {
    Integer id;
    Integer places;
    Integer freePlaces;
    ArrayList<GameCopy> gamesOnTable;
    boolean full;

    public Table(Integer id, Integer places) {
        this.id = id;
        this.places = places;
        this.freePlaces = places;
        this.gamesOnTable = new ArrayList<>();
        this.full = false;
    }
}
