package deque;

import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.comparator = c;
    }

    public T max() {
        if (this.isEmpty()) { return null;}
        T maxItem = get(0);
        for (T item : this) {
            if (comparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) { return null;}
            T maxItem = get(0);
        for (T item : this) {
            if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this) { return true; }
        if (o == null) { return false; }
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if (other.size() != this.size()) { return false; }
            Iterator<T> i = other.iterator();
            Iterator<T> ti = this.iterator();
            while (i.hasNext()) {
                if (i.next() != ti.next()) { return false; }
            }
            return true;
        }
        return false;
    }
}
