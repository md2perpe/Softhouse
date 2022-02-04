import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConditionalFilter<T> {

    private Iterator<T> iterator;

    public ConditionalFilter(Iterator<T> iterator)
    {
        this.iterator = iterator;
    }


    private T current;

    public T nextIf(Predicate<T> condition)
    {
        if (current == null)
        {
            if (! iterator.hasNext())
            {
                return null;
            }

            current = iterator.next();
        }

        if (condition.test(current))
        {
            T result = current;
            current = null;
            return result;
        }

        return null;
    }
}
