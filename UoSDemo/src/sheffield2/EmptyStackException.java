package sheffield2;

public class EmptyStackException extends Exception
{

    public EmptyStackException()
    {
        super();
    }
    public EmptyStackException(String desc)
    {
        super(desc);
    }
}
