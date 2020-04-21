package rcm.model;

public class WrongInputException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Exception constructor for a wrong input
     */
    public WrongInputException() {
        super("Unexpected behaviour");
    }

    /**
     * Exception constructor for a wrong input
     * 
     * @param msg message to be displayed when an exception is thrown
     */
    public WrongInputException(String msg) {
        super(msg);
    }
}