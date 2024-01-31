import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Stage5 {
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void print(){
        System.out.println("==Menu==");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
    public static void main(String[] args) throws Exception{
        String fileName = args[1];
        String txt = readFile(fileName);
        ArrayList<String> arr = new ArrayList<>();
        Scanner sc = new Scanner(txt);
        while(sc.hasNext()){
            arr.add(sc.nextLine());
        }
        Map<String,ArrayList<Integer>> inverted_index
                = new HashMap<>();
        for(int i = 0; i < arr.size(); i++){
            String[] values = arr.get(i).split(" ");
            for(String val : values){
                inverted_index.putIfAbsent(val, new ArrayList<>());
                inverted_index.get(val).add(i);
            }
        }
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            print();
            int choice = -1;
            try{
                choice = Integer.parseInt(scanner.nextLine());
            }
            catch(Exception e){
                System.out.println("Incorrect option! Try again.");
                continue;
            }
            switch(choice){
                case 0:
                    System.out.println("Bye!");
                    exit = true;
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    arr.forEach(System.out::println);
                    break;
                case 1 :
                    System.out.println("Enter a name or email to search all suitable people.");
                    String word = scanner.nextLine();
                    if(inverted_index.get(word) != null){
                        ArrayList<Integer> indices = inverted_index.get(word);
                        for(int index : indices){
                            System.out.println(arr.get(index));
                        }
                    }
                    else{
                        System.out.println("No matching people found.");
                    }
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
        scanner.close();

    }
}
