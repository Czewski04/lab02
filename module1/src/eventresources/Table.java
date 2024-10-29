package eventresources;

import java.util.ArrayList;

public class Table {
    private Integer id;
    private Integer places;
    private Integer freePlaces;
    private ArrayList<GameCopy> gamesOnTable;
    private boolean full;

    public Table(Integer id, Integer places) {
        this.id = id;
        this.places = places;
        this.freePlaces = places;
        this.gamesOnTable = new ArrayList<>();
        this.full = false;
    }

    public Integer getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(Integer freePlaces) {
        this.freePlaces = freePlaces;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public ArrayList<GameCopy> getGamesOnTable() {
        return gamesOnTable;
    }

    public Integer getPlaces() {
        return places;
    }
}
