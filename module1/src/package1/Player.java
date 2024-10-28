package package1;

import java.util.ArrayList;
import java.util.List;

public class Player {
    Integer id;
    ArrayList<Integer> gamesIdList;

    public Player(Integer id, ArrayList<Integer> gamesIdListStringType) {
        this.id = id;
        this.gamesIdList = new ArrayList<>(gamesIdListStringType);
    }
}
