package serder;

/**
 * Created by 周小强 on 2016/7/30.
 */

public class SerializerException extends RootException {

    private static final long serialVersionUID = 1L;

    public SerializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SerializerException(String message) {
        super(message);
    }

    public SerializerException(Throwable cause) {
        super(cause);
    }

    public SerializerException() {
        super();
    }

    /* avoid the expensive and useless stack trace for serialization exceptions */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
