/* HashTableChained.java */

package dict;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

   private ArrayList<LinkedList> hashArray;
   private int size;//number of key-value pairs;
  /**
   *  Place any data fields here.
   **/



  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  //a method to generate a prime number that is at least sizeEstimate;
  public int primeGenator(int sizeEstimate){
    if (sizeEstimate<7) return 7;
    while (true){
      int i=sizeEstimate;
      int j=0;
      for (j=2; j<i/2+1; j++){
        if (i%j==0)
          break;
      }
      if (j==i/2+1) return i;
      i++;
    }
  }

  public HashTableChained(int sizeEstimate) {
    int primeNum=primeGenator(sizeEstimate);

    this.hashArray=new ArrayList<LinkedList>(primeNum);
    // Your solution here.
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    this.hashArray=new ArrayList<LinkedList>(101);
    // Your solution here.
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    int arrayListSize=this.hashArray.size();

    // Replace the following line with your solution.
    return Math.abs(code)/arrayListSize;
    //return (arrayListSize*(code-Integer.MIN_VALUE))/(Integer.MAX_VALUE-Integer.MIN_VALUE+1);
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size==0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    Entry newEntry=new Entry();
    newEntry.key=key;
    newEntry.value=value;
    int hash=compFunction(key.hashCode());
    if (hashArray.get(hash)==null){
    LinkedList<Entry> newList= new LinkedList<Entry>();
    newList.addFirst(newEntry);
    size++;
    }else{
      hashArray.get(hash).addFirst(newEntry);
      size++;
    }
    // Replace the following line with your solution.
    return newEntry;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    int hash=compFunction(key.hashCode());
    if(hashArray.get(hash)==null)
      return null;
    for (int i=0; i<hashArray.get(hash).size();i++){
        if (((Entry) hashArray.get(hash).get(i)).key.equals(key))
          return (Entry) hashArray.get(hash).get(i);
    }
    // Replace the following line with your solution.
    return null;
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int hash=compFunction(key.hashCode());
    if(hashArray.get(hash)==null)
      return null;
    Entry temp=null;
    for (int i=0; i<hashArray.get(hash).size();i++){
      if (((Entry) hashArray.get(hash).get(i)).key.equals(key))
         temp = (Entry) hashArray.get(hash).get(i);
            hashArray.get(hash).remove(i);
      size--;
      return temp;
    }
    // Replace the following line with your solution.
    return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for (int i=0; i<size; i++){
      hashArray.set(i,null);
    }
    size=0;
    // Your solution here.
  }

}
