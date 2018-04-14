public class BadTransactionException extends Exception {
//int depositAmount;

    public BadTransactionException(int depositAmount){
super("Invalid deposit amount: " +depositAmount);

    }
}
