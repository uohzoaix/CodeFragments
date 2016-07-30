package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */
public class RootException extends RuntimeException {

    private final static long serialVersionUID = 1L;

    public RootException(String message, Throwable cause) {
        super(message, cause);
    }

    public RootException(String message) {
        super(message);
    }

    public RootException(Throwable cause) {
        super(cause);
    }

    public RootException() {
        super();
    }

}
