import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stage1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] list = scanner.nextLine().split(" ");
        String word = scanner.nextLine();
        Optional<Integer> op = Stream.iterate(0,idx -> idx + 1).limit(list.length).filter(idx->
        word.equals(list[idx])).findFirst();
        op.ifPresentOrElse((x) -> System.out.println(x + 1),()->System.out.println("Not found"));
    }
}
