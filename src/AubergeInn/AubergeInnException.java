package AubergeInn;

/**
 * L'exception IFT287Exception est levee lorsqu'une transaction est inadequate.
 */
public final class AubergeInnException extends Exception
{
    private static final long serialVersionUID = 1L;

    public AubergeInnException(String message)
    {
        super(message);
    }
}