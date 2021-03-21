package aman.fr.services.auth;

public class IncorectPwdException  extends RuntimeException{
    private static final long serialVersionUID = -5837660773782816145L;

    public IncorectPwdException(String arg0) {
        super(arg0);
    }
}
