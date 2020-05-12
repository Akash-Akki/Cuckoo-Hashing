package aps180006;

/**
 * @author   1. Akash Akki apa190001
 *           2. Anant Srivastava aps180006
 */
import java.util.Scanner;

public class Part1 {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    Cuckoo<Integer> ck = new Cuckoo<Integer>();
    whileloop:
    while (sc.hasNext()) {
      int x;
      switch (sc.nextInt()) {
        case 1: // Add
          System.out.println("insert element");
          x = sc.nextInt();
          ck.add(x);
          break;
        case 2: // Remove
          System.out.println("remove element");
          x = sc.nextInt();
          ck.remove(x);
          break;
        case 3: // Contains
          System.out.println("Check for contains ");
          x = sc.nextInt();
          if(ck.contains(x))
            System.out.println("Cuckoo Hashset contains "+x);
          else
            System.out.println("Cuckoo Hashset does not contain"+x);
          break;
        case 4:
             ck.printTable();
        default:
          break whileloop;
      }

    }
    ck.printTable();
  }
}
