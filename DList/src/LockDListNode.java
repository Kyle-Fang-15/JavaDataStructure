public class LockDListNode extends DListNode{

    protected boolean locked=false;

    public LockDListNode(Object item, DListNode pre, DListNode nex){
        super(item, pre, nex);
    }
}
