package dict;

public class SList {
    public SNode head;


    public static class SNode{
        public Object item;
        public SNode next;

        public SNode(Object item, SNode next){
            this.item=item;
            this.next=next;
        }

    }

    public SList(){
        this.head=new SNode(null, null);

    }

    public void addFirst(Object item){
        this.head.next=new SNode(item, this.head.next);


    }

    public void deleteFirst(){
        this.head.next=this.head.next.next;
    }
    public  SNode currentNode(){
        return head.next;

    }


}
