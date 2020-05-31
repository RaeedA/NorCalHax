package backend;

import java.sql.Time;
import java.util.Comparator;

public class TimeComparator implements Comparator<Recipe>
{
    @Override
    public int compare( Recipe o1, Recipe o2 )
    {
        Time t1 = o1.getCookingTime();
        Time t2 = o2.getCookingTime();
        if (t1.getHours() > t2.getHours())
        {
            return 1;
        }
        else if (t2.getHours() < t1.getHours())
        {
            return -1;
        }
        else
        {
            if (t1.getMinutes() > t2.getMinutes())
            {
                return 1;
            }
            else if (t2.getMinutes() < t1.getMinutes())
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

}
