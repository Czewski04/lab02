package comparators;

import eventresources.GameCopy;

import java.util.Comparator;

public class GameComparator implements Comparator<GameCopy> {
    public int compare(GameCopy g1, GameCopy g2) {
        return Float.compare(g2.getTmpSatisfaction(), g1.getTmpSatisfaction());
    }
}
