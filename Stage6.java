
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Stage6 {
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
    public static void print(){
        System.out.println("==Menu==");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
    public static Set<String> find(String word,String strategy, Map<String,Set<Integer>> inv_idx,ArrayList<String>arr){
        Set<Integer> ans = new HashSet<>();
        if(strategy.equals("ALL")){
            boolean first = true;
            for(String w : word.split(" ")){
                if(inv_idx.get(w) == null) continue;
                w = w.toLowerCase();
                if(first){
                    first = false;
                    ans.addAll(inv_idx.get(w));
                }
                else{
                    Set<Integer> c = new HashSet<>();
                    for(int idx : inv_idx.get(w)){
                        if(ans.contains(idx)){
                            c.add(idx);
                        }
                    }
                    ans = c;
                }
            }
        }
        else if(strategy.equals("ANY")){
            for(String w : word.split(" ")){
                w = w.toLowerCase();
                if(inv_idx.get(w) == null) continue;
                ans.addAll(inv_idx.get(w));
            }
        }
        else{
            Set<Integer> s = new HashSet<>();
            for(String w : word.split(" ")){
                w = w.toLowerCase();
                if(inv_idx.get(w) == null){
                   continue;
                }
                s.addAll(inv_idx.get(w));
            }
            for(int i = 0; i < arr.size(); i++){
                if(!s.contains(i)){
                    ans.add(i);
                }
            }
        }
        if(ans.isEmpty()){
            return new HashSet<>(List.of("No matching person found."));
        }
        else{
            return ans.stream().map(arr::get).collect(Collectors.toSet());
        }
    }
    public static Map<String,Set<Integer>> create_inverted_index(ArrayList<String> arr){
        Map<String,Set<Integer>> inverted_index = new HashMap<>();
        for(int i = 0; i < arr.size(); i++){
            String[] values = arr.get(i).split(" ");
            for(String val : values){
                val = val.toLowerCase();
                inverted_index.putIfAbsent(val, new HashSet<>());
                inverted_index.get(val).add(i);
            }
        }
        return inverted_index;
    }
    public static void main(String[] args) throws Exception{
        String fileName = args[1];
        String txt = readFile(fileName);
        ArrayList<String> arr = new ArrayList<>();
        Scanner sc = new Scanner(txt);
        while(sc.hasNext()){
            arr.add(sc.nextLine());
        }
        Map<String,Set<Integer>> inverted_index
                = create_inverted_index(arr);

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
                    System.out.println("Select a matching strategy: ALL, ANY, NONE");
                    String strategy = scanner.nextLine();
                    System.out.println("Enter a name or email to search all suitable people.");
                    String word = scanner.nextLine();
                    find(word,strategy,inverted_index,arr).forEach(System.out::println);
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }
        scanner.close();

    }
}
