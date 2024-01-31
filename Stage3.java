import java.util.*;

public class Stage3 {
    public static void print(){
        System.out.println("=== Menu ===");
        System.out.println("1. Find a person");
        System.out.println("2. Print all people");
        System.out.println("0. Exit");
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of people:");
        int n = Integer.parseInt(scanner.nextLine());
        ArrayList<String> arr = new ArrayList<>();
        System.out.println("Enter all people:");
        while(n-- > 0){
            arr.add(scanner.nextLine());
        }
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
                    for(String w : arr){
                        if(w.toLowerCase().contains(word.toLowerCase())){
                            System.out.println(w);
                        }
                    }
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
            }
        }

    }
}
