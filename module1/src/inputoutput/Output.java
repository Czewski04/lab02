package inputoutput;

import eventresources.GameCopy;
import eventresources.Player;
import eventresources.Table;

import java.util.ArrayList;
import java.util.HashMap;

public class Output {
    public static void showTableArrangement(ArrayList<Table> tables) {
        System.out.println("\n=========== Table layout ============\n");
        for (Table table : tables) {
            System.out.println("\n---------------" + "Table" + table.getId() +  "----------------");
            System.out.printf("%-10s %-10s%n", "Game", "Players");
            System.out.println("-------------------------------------");

            for (GameCopy gameOnTable : table.getGamesOnTable()) {
                System.out.printf("%-10d ", gameOnTable.getMainGameId());
                for (Player playerAtTheTable : gameOnTable.getFittedPlayers()) {
                    System.out.print(playerAtTheTable.getId() + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void showBestResult(float bestScore) {
        String scoreStr = " " + bestScore + " ";
        int frameWidth = 15;

        System.out.print("          ");
        for (int i = 0; i < frameWidth + 2; i++) {
            System.out.print("•");
        }
        System.out.println();

        System.out.print("          ");
        System.out.print("•");
        System.out.print("  BEST RESULT  ");
        System.out.println("•");

        int space = (frameWidth - scoreStr.length()) / 2;
        System.out.print("          ");
        System.out.print("•");
        for (int i = 0; i < space; i++) {
            System.out.print(" ");
        }
        System.out.print(scoreStr);
        for (int i = 0; i < (frameWidth - scoreStr.length() - space); i++) {
            System.out.print(" ");
        }
        System.out.println("•");

        System.out.print("          ");
        for (int i = 0; i < frameWidth + 2; i++) {
            System.out.print("•");
        }
        System.out.println();
    }

    public static void showStatistics(HashMap<String, Object> statistics, int numberOfTables) {
        System.out.println("\n---------------------------------------------------");
        System.out.println("---------------- Statistics Table -----------------");
        System.out.println("+---------------------------+---------------------+");
        System.out.printf("| %-25s | %-19s |%n", "Category", "Result");
        System.out.println("+---------------------------+---------------------+");
        System.out.printf("| %-25s | %-19s |%n", "Players play", statistics.get("players play") + " players");
        System.out.printf("| %-25s | %-19s |%n", "Players don't play", statistics.get("players don't play") + " players");
        System.out.printf("| %-25s | %-19s |%n", "First choice game", statistics.get("first choice game"));
        System.out.printf("| %-25s | %-19s |%n", "Second choice game", statistics.get("second choice game"));
        System.out.printf("| %-25s | %-19s |%n", "Third choice game", statistics.get("third choice game"));
        System.out.printf("| %-25s | %-19s |%n", "Fourth choice game", statistics.get("fourth choice game"));
        System.out.printf("| %-25s | %-19s |%n", "Full tables", statistics.get("full tables") + " of " + numberOfTables);
        System.out.printf("| %-25s | %-19s |%n", "Free places", statistics.get("free places") + " free places");
        System.out.printf("| %-25s | %-19s |%n", "Empty tables", statistics.get("empty tables"));
        System.out.printf("| %-25s | %-19s |%n", "More than one game on", statistics.get("more than one game on tables") + " tables");
        System.out.printf("| %-25s | %-19s |%n", "Games on tables", statistics.get("games on tables"));
        System.out.printf("| %-25s | %-19s |%n", "Satisfaction", statistics.get("satisfaction"));
        System.out.printf("| %-25s | %-19s |%n", "Penalty", statistics.get("penalty"));
        System.out.println("+---------------------------+---------------------+");
    }
}
