import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * A simple program that reads two lines:
 *          a line of words and a line containing the search word.
 * The program searches in the
 *          first line for a word specified in the second one.
 * The program outputs the index of the specified word.
 * If there is no such word in the first line, the program prints Not Found.
 */

public class Stage1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] userInput = scanner.nextLine().split(" ");
        String toSearch = scanner.nextLine();
        Optional<Integer> op = Stream
                .iterate(0, index -> index + 1)
                .limit(userInput.length)
                .filter(index -> toSearch.equals(userInput[index]))
                .findFirst();
        op.ifPresentOrElse(index -> System.out.println(index + 1),
                () -> System.out.println("Not found"));}
}
