package rcm.model;

public class WrongInputException extends Exception {

    private static final long serialVersionUID = -7350310191056291666L;

    /**
     * Exception constructor for a wrong input
     * 
     * @param msg message to be displayed when an exception is thrown
     */
    public WrongInputException(String msg) {
        super(msg);
    }
}