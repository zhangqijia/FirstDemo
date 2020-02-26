package sheffield2;

public class GreaterThan<T> extends AbstractPredicate1<T>
{
    GreaterThan(T o)
    {
        super(o);
    }

    public boolean pred(T obj)
    {
        return ((Comparable<T>) obj).compareTo(data) > 0;
    }
}
