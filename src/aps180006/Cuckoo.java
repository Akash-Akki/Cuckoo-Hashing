package aps180006;

/**
 * @author   1. Akash Akki apa190001
 *           2. Anant Srivastava aps180006
 */

import java.util.HashSet;

public class Cuckoo<T> {

  private int size;
  private int capacity;
  private T[] hashTable1;
  private T[] hashTable2;
  private float loadFactor;
  private int threshold;
  static final int DEFAULT_INITIAL_CAPACITY = 16;
  static final float DEFAULT_LOAD_FACTOR = (float) 0.9;
  HashSet<T> hset;

  /**
   *  Cuckko hashset constructor
   */

 public Cuckoo() {
    size = 0;
    this.capacity = DEFAULT_INITIAL_CAPACITY;
    this.loadFactor = DEFAULT_LOAD_FACTOR;
    this.threshold = (int) (Math.log(2 * this.capacity) / Math.log(2));
    this.hashTable1 = (T[]) new Object[this.capacity];
    this.hashTable2 = (T[]) new Object[this.capacity];
    hset = new HashSet<>();
  }


  /**
   * Hasfucntion1- Below function calculate the hash function of given element
   * @param x input element x
   * @return
   */
  private int hashCode1(T x) {
    //    System.out.println("hash 1 of"+x.hashCode()%5);
    //    System.out.println(x.hashCode());

    return x.hashCode() % 5;
  }

  /**
   * HashFunction2 - Below function calculate the hashfunction of the given element
   * @param x input element
   * @return
   */
  private int hashCode2(T x) {
    return x.hashCode() % 8;
  }

  /**
   *  Adds the given element to the Hashset
   * @param x input element
   * @return returns tru if added or return false
   */
  public boolean add(T x) {
    if (size / (float) (2 * capacity) > loadFactor) {
      resize();
    }

    if (contains(x)) return false;
    T y = null;
    if (hashTable1[hashCode1(x)] == null) {
      hashTable1[hashCode1(x)] = x;
      size++;
      return true;
    } else if (hashTable1[hashCode1(x)] != null) {
      y = (T) hashTable1[hashCode1(x)];

      hashTable1[hashCode1(x)] = x;
      for (int i = 1; i <= threshold; i++) {
        if (i % 2 == 1) {
          if (hashTable2[hashCode2(y)] == null) {
            hashTable2[hashCode2(y)] = y;
            size++;
            return true;
          } else {
            T z = (T) hashTable2[hashCode2(y)];
            hashTable2[hashCode2(y)] = y;
            y = z;
          }
        } else {
          if (hashTable1[hashCode1(y)] == null) {
            hashTable1[hashCode1(y)] = y;
            size++;
            return true;
          } else {
            T z = (T) hashTable1[hashCode1(y)];
            hashTable1[hashCode1(y)] = y;
            y = z;
          }
        }
      }
    }
    hset.add(y);
    size++;
    return true;
  }

  /**
   * Checks whether the element is there in the hashset
   * @param x input element
   * @return return true if added
   */
  public boolean contains(T x) {

    if (hashTable1[hashCode1(x)] == null) return false;
    else if (hashTable1[hashCode1(x)] == x) return true;
    else if (hashTable2[hashCode2(x)] == null) return false;
    else if (hashTable2[hashCode2(x)] == x) return true;
    else if (hset.contains(x)) return true;
    return false;
  }

  /**
   * Removes the element x
   * @param x inpput element x
   * @return
   */
  public T remove(T x) {
    if (contains(x)) {
      T e = null;
      if (hashTable1[hashCode1(x)] != null) {
        if (hashTable1[hashCode1(x)] == x) {
          e = (T) hashTable1[hashCode1(x)];
          hashTable1[hashCode1(x)] = null;
          size--;
          return e;
        }
      } else if (hashTable2[hashCode2(x)] != null) {
        if (hashTable2[hashCode2(x)] == x) {
          e = (T) hashTable2[hashCode2(x)];

          hashTable2[hashCode2(x)] = null;
          size--;
          return e;
          }

      }
      if(hset.contains(x))
      {
        hset.remove(x);
      }
    }
    return null;
  }

  /**
   * Resixes the hashset the when the loadfactor of the hashset exceeds the given loadfactor
   */
  public void resize() {

    T[] temp1 = (T[]) new Object[size];
    T[] temp2 = (T[]) new Object[size];
    for (int i = 0, j = 0; i < hashTable1.length; i++) {
      if (hashTable1[i] != null) {
        temp1[j++] = hashTable1[i];
        hashTable1[i] = null;
      }
    }
    for (int i = 0, j = 0; i < hashTable2.length; i++) {
      if (hashTable2[i] != null) {
        temp2[j++] = hashTable2[i];
        hashTable2[i] = null;
      }
    }

    capacity = 2 * capacity;
    size = 0;
    hashTable1 = (T[]) new Object[capacity];
    hashTable2 = (T[]) new Object[capacity];

    for (int i = 0; i < temp1.length; i++) {
      if (temp1[i] != null) add((T) temp1[i]);
    }
    for (int i = 0; i < temp2.length; i++) {
      if (temp2[i] != null) add((T) temp2[i]);
    }
    this.threshold = (int) (Math.log(2 * this.capacity) / Math.log(2));
    return;
  }

  /**
   *  Prints the HashTable elements
   */
  public void printTable() {
    System.out.println("\n\nHash Table1:  elements");

    for (int i = 0; i < hashTable1.length; i++) {
      if (hashTable1[i] == null) System.out.println("" + i + " -> " + "null");
      else System.out.println("" + i + " -> " + hashTable1[i]);
    }
    System.out.println("Hash table 2 elements are :");
    for (int i = 0; i < hashTable2.length; i++) {
      if (hashTable2[i] == null) System.out.println("" + i + " -> " + "null");
      else System.out.println("" + i + " -> " + hashTable2[i]);
    }

    if (hset.size() > 0) {
      System.out.println("Java Hashset Elements");
      for (T i : hset) System.out.println(i);
     }
     else
       System.out.println("No elements");
  }

  public static void main(String[] args) {

    Cuckoo<Integer> ck = new Cuckoo<Integer>();
    ck.add(1);
    ck.add(10);
    ck.add(30);

    ck.add(40);

    ck.add(80);
    ck.add(120);
    ck.add(3);
    ck.add(160);
    ck.remove(160);
    ck.printTable();



  }
}


