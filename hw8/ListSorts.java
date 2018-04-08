/* ListSorts.java */

import list.*;

public class ListSorts {

  private final static int SORTSIZE = 1000;

  /**
   *  makeQueueOfQueues() makes a queue of queues, each containing one item
   *  of q.  Upon completion of this method, q is empty.
   *  @param q is a LinkedQueue of objects.
   *  @return a LinkedQueue containing LinkedQueue objects, each of which
   *    contains one object from q.
   **/
  public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
    // Replace the following line with your solution.
  LinkedQueue queues=new LinkedQueue();
  LinkedQueue tempQueue=new LinkedQueue();
  while (!q.isEmpty()){
  try {

    tempQueue.enqueue(q.dequeue());
  }catch (QueueEmptyException e){
    System.out.println("empty queue exception");
  }
    queues.enqueue(tempQueue);
    tempQueue=new LinkedQueue();

  }
    return queues;
  }

  /**
   *  mergeSortedQueues() merges two sorted queues into a third.  On completion
   *  of this method, q1 and q2 are empty, and their items have been merged
   *  into the returned queue.
   *  @param q1 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @param q2 is LinkedQueue of Comparable objects, sorted from smallest 
   *    to largest.
   *  @return a LinkedQueue containing all the Comparable objects from q1 
   *   and q2 (and nothing else), sorted from smallest to largest.
   **/
  public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
    // Replace the following line with your solution.
    LinkedQueue newQueue= new LinkedQueue();
    while(!q1.isEmpty() &&!q2.isEmpty()){

      try {
        Comparable temp1=(Comparable) q1.front();
        Comparable temp2=(Comparable) q2.front();
        //
       if (temp1.compareTo(temp2) <= 0){
         newQueue.enqueue(q1.dequeue());
       }else{
         newQueue.enqueue(q2.dequeue());
       }

      }catch(QueueEmptyException e){
        System.out.println("merge sort queue exception");
      }
    }
    if (!q1.isEmpty())
      newQueue.append(q1);
    if(!q2.isEmpty())
      newQueue.append(q2);
    return newQueue;
  }

  /**
   *  partition() partitions qIn using the pivot item.  On completion of
   *  this method, qIn is empty, and its items have been moved to qSmall,
   *  qEquals, and qLarge, according to their relationship to the pivot.
   *  @param qIn is a LinkedQueue of Comparable objects.
   *  @param pivot is a Comparable item used for partitioning.
   *  @param qSmall is a LinkedQueue, in which all items less than pivot
   *    will be enqueued.
   *  @param qEquals is a LinkedQueue, in which all items equal to the pivot
   *    will be enqueued.
   *  @param qLarge is a LinkedQueue, in which all items greater than pivot
   *    will be enqueued.  
   **/   
  public static void partition(LinkedQueue qIn, Comparable pivot, 
                               LinkedQueue qSmall, LinkedQueue qEquals, 
                               LinkedQueue qLarge) {
    // Your solution here.
    while (!qIn.isEmpty()){

      try {
       Comparable  tempObj = (Comparable) qIn.dequeue();
        if(tempObj.compareTo(pivot)<0){
          qSmall.enqueue(tempObj);
        }else if(tempObj.compareTo(pivot)==0){
          qEquals.enqueue(tempObj);
        }else{
          qLarge.enqueue(tempObj);
        }
      }catch(QueueEmptyException e){
        System.out.println("partition empty queue exception");
      }

    }


  }

  /**
   *  mergeSort() sorts q from smallest to largest using mergesort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void mergeSort(LinkedQueue q) {
    // Your solution here.
    if (q.size()<2) return;

    LinkedQueue queue=makeQueueOfQueues(q);


    while(queue.size()>1) {
      try {
        LinkedQueue q1 = (LinkedQueue) queue.dequeue();

        LinkedQueue q2 = (LinkedQueue) queue.dequeue();
        queue.enqueue(mergeSortedQueues(q1, q2));

      } catch (QueueEmptyException e) {
        System.out.println("mergeSort exception 1");
      }
    }



    try {
      q.append((LinkedQueue)queue.dequeue());
    }catch(QueueEmptyException e){
      System.out.println("merge exception");
    }



  }

  /**
   *  quickSort() sorts q from smallest to largest using quicksort.
   *  @param q is a LinkedQueue of Comparable objects.
   **/
  public static void quickSort(LinkedQueue q) {
    // Your solution here.
    if (q.size()<2) return;
    int n=(int) ((q.size())*Math.random())+1;
    Comparable pivot=(Comparable) q.nth(n);
LinkedQueue small=new LinkedQueue();
LinkedQueue equal=new LinkedQueue();
LinkedQueue large=new LinkedQueue();
partition(q,pivot,small,equal,large);




if (small.size()>1) quickSort(small);
if (large.size()>1) quickSort(large);
 q.append(small);
    q.append(equal);
   q.append(large);

  }

  /**
   *  makeRandom() builds a LinkedQueue of the indicated size containing
   *  Integer items.  The items are randomly chosen between 0 and size - 1.
   *  @param size is the size of the resulting LinkedQueue.
   **/
  public static LinkedQueue makeRandom(int size) {
    LinkedQueue q = new LinkedQueue();
    for (int i = 0; i < size; i++) {
      q.enqueue(new Integer((int) (size * Math.random())));
    }
    return q;
  }

  /**
   *  main() performs some tests on mergesort and quicksort.  Feel free to add
   *  more tests of your own to make sure your algorithms works on boundary
   *  cases.  Your test code will not be graded.
   **/
  public static void main(String [] args) {

    LinkedQueue q = makeRandom(20);
    //LinkedQueue q5 = makeRandom(10);
    System.out.println(q.toString());
   // System.out.println(q5.toString());
   // System.out.println(mergeSortedQueues(q,q5).toString());
    mergeSort(q);
    System.out.println(q.toString());

    q = makeRandom(20);
    System.out.println(q.toString());
    quickSort(q);
    System.out.println(q.toString());

    /* Remove these comments for Part III.
    Timer stopWatch = new Timer();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    mergeSort(q);
    stopWatch.stop();
    System.out.println("Mergesort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");

    stopWatch.reset();
    q = makeRandom(SORTSIZE);
    stopWatch.start();
    quickSort(q);
    stopWatch.stop();
    System.out.println("Quicksort time, " + SORTSIZE + " Integers:  " +
                       stopWatch.elapsed() + " msec.");
    */
  }

}
