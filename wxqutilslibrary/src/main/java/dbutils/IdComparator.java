package dbutils;

import java.io.Serializable;
import java.util.Comparator;

public class IdComparator implements Comparator<Msg>, Serializable {

    public int compare(Msg o1, Msg o2) {
        // TODO Auto-generated method stub
        int val1 = Integer.parseInt(o1.id);
        int val2 = Integer.parseInt(o2.id);
        return (val1 < val2 ? -1 : (val1 == val2 ? 0 : 1));
    }

}
