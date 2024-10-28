package package1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public static String[] gameReader(int counter) throws FileNotFoundException {
        File file = new File("files/game.txt");
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < counter; i++) {
            scanner.nextLine();
        }
        String line = scanner.nextLine();
        String[] elements = line.split("; ");
        return elements;
    }

    public static String[] tableReader(int counter) throws FileNotFoundException {
        File file = new File("files/table.txt");
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < counter; i++) {
            scanner.nextLine();
        }
        String line = scanner.nextLine();
        String[] elements = line.split("; ");
        return elements;
    }

    public static String[] playerReader(int counter) throws FileNotFoundException {
        File file = new File("files/player.txt");
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < counter; i++) {
            scanner.nextLine();
        }
        String line = scanner.nextLine();
        String[] elements = line.split("; ");
        return elements;
    }

}
