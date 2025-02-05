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
        if (o instanceof Deque) {
            Deque<T> other = (Deque<T>) o;
            if (other.size() != this.size()) { return false; }

            for (int i = 0; i < this.size(); i++) {
                if (!other.get(i).equals(this.get(i))) { return false; }
            }
            return true;
        }
        return false;
    }
}
