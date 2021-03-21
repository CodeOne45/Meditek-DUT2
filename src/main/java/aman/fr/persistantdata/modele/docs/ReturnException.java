package aman.fr.persistantdata.modele.docs;

public class ReturnException extends RuntimeException{
    public ReturnException(){
        super("This item is free, so you can't return this !");
    }
}
