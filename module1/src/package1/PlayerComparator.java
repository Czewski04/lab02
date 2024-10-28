package package1;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    public int compare(Player p1, Player p2) {
        return p1.preferredGamesList.size() - p2.preferredGamesList.size();
    }
}
