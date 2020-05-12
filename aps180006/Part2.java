package aps180006;


/**
 * @author   1. Akash Akki apa190001
 *           2. Anant Srivastava aps180006
 */

import java.util.HashSet;
import java.util.Random;

public class Part2 {

  public static void main(String[] args){

    System.out.println(
      "Cuckoo hashing's performance on 10 millions of operations: add, contains, and remove.");

    Cuckoo<Integer> ck = new Cuckoo<Integer>();
    Random random = new Random();
    int n = 10000000; // Large n
    if (args.length > 0) {
      n = Integer.parseInt(args[0]);
    }
    int[] arr = new int[n];

    for (int i = 0; i < n; i++)
      arr[i] = random.nextInt(10000000); // Generating a random number till 100


    Timer timer = new Timer();
    for(int i=0;i<arr.length;i++){
      ck.add(arr[i]);
    }
    timer.end();
    System.out.println("Add "+timer);

    timer = new Timer();
    for(int i=0;i<arr.length;i++)
      ck.remove(arr[i]);
    timer.end();
    System.out.println("Remove "+timer);

    timer =new Timer();
    for(int i=0;i<arr.length;i++)
      ck.contains(arr[i]);
    timer.end();
    System.out.println("Contains "+timer);

    System.out.println(
        "Java's hashing's performance on 10 millions of operations: add, contains, and remove.");

    HashSet<Integer> hashSet = new HashSet<>();
    timer = new Timer();
    for(int i=0;i<arr.length;i++){
      hashSet.add(arr[i]);
    }
    timer.end();
    System.out.println("Add "+timer);

    timer = new Timer();
    for(int i=0;i<arr.length;i++)
      hashSet.remove(arr[i]);
    timer.end();
    System.out.println("Remove "+timer);

    timer=new Timer();
    for(int i=0;i<arr.length;i++)
      hashSet.contains(arr[i]);
    timer.end();
    System.out.println("Contains "+timer);

  }
}
