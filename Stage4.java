import helper.Help;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Modified version of Stage3.
 * In this stage,
 *      the program reads
 *          data from a text file instead of the standard input.
 * The file structure will depend on your text data type
 *      (personal information,
 *      address information, book information, and so on).
 */

public class Stage4 {
    /**
     * @param fileName, @throws Exception
     * @return
     * it helps in retrieving the list of people,
     * from a file.
     */
    public static String readFile(final String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    /**
     * displays menu to user to choose from.
     */
    public static void print() {
        System.out.println("==Menu==");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }

    /**
     * @throws Exception, @param args
     * main method to display info according to choice,
     * made by user from menu.
     */
    public static void main(String[] args) throws Exception {
        String fileName = args[1];
        String people = readFile(fileName);
        ArrayList<String> listOfPerson = new ArrayList<>();
        Scanner sc = new Scanner(people);
        while (sc.hasNext()) {
            listOfPerson.add(sc.nextLine());
        }
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            print();
            int choice = Help.getChoice(scanner);
            switch (choice) {
                case 0 :
                    exit = Help.choice0();
                    break;
                case 2 :
                    Help.choice2(listOfPerson);
                    break;
                case 1 :
                    Help.choice1(scanner, listOfPerson);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
        scanner.close();
    }
}