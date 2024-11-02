package comparators;

import eventresources.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    public int compare(Player p1, Player p2) {
        return p1.getPreferredGamesList().size() - p2.getPreferredGamesList().size();
    }
}
