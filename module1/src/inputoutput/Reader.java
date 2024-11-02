package inputoutput;

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
        return line.split("; ");
    }

    public static String[] tableReader(int counter) throws FileNotFoundException {
        File file = new File("files/table.txt");
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < counter; i++) {
            scanner.nextLine();
        }
        String line = scanner.nextLine();
        return line.split("; ");
    }

    public static String[] playerReader(int counter) throws FileNotFoundException {
        File file = new File("files/player.txt");
        Scanner scanner = new Scanner(file);
        for (int i = 0; i < counter; i++) {
            scanner.nextLine();
        }
        String line = scanner.nextLine();
        return line.split("; ");
    }

    public static float[] weightReader() {
        float[] weights = new float[3];
        for(int i=0; i<3; i++){
            weights[i] = readAndCheckWeights(i);
        }
        return weights;
    }

    public static float readAndCheckWeights(int i){
        Scanner scanner = new Scanner(System.in);
        float weight;

        switch(i){
            case 0:
                System.out.println("Enter first weight (W1). Weight of players:");
                break;
            case 1:
                System.out.println("Enter second weight (W2). Weight of players satisfaction:");
                break;
            case 2:
                System.out.println("Enter third weight (W3). Weight of penalty:");
                break;
        }

        try{
            weight = scanner.nextFloat();
            if(weight <= 0){
                throw new Exception();
            }
        }catch (Exception e){
            System.out.println("Your weight cannot be a letter, must be a number greater than 0! Try enter it again.");
            return readAndCheckWeights(i);
        }

        return weight;
    }
}
