import java.util.*;

public class Stage2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> arr = new ArrayList<>();
        System.out.println("Enter all people:");
        while(n-- > 0){
            arr.add(scanner.nextLine());
        }
        System.out.println("Enter the number of search queries:");
        int q = Integer.parseInt(scanner.nextLine());
        while(q-- > 0){
            System.out.println("Enter data to search people:");
            String word = scanner.nextLine();
            List<String> find = getString(word.toLowerCase(), arr);
            if(find.isEmpty()){
                System.out.println("No matching people found.");
            }
            else{
                System.out.println("Found people:");
                find.forEach(System.out::println);
            }
        }
    }
    private static List<String> getString(String word, ArrayList<String> arr) {
        List<String> find = new ArrayList<>();
        for (String s : arr) {
            if (s.contains(word)) {
                find.add(s);
            }
        }
        return find;
    }
}
