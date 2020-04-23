package rcm.model;

public class WrongInputException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public WrongInputException() {
        super("Unexpected behaviour");
    }
    
    public WrongInputException(String msg) {
        super(msg);
    }
}