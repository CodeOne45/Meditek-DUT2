package aman.fr.persistantdata.modele.docs;

public class BorrowException extends RuntimeException{
    public BorrowException(){
        super("This item is not free, so you can't borrow this !");
    }
}
