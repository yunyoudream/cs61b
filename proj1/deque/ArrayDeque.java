package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = items.length - 1;
        this.nextLast = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size >= items.length) {
            resizing(size * 2);
        }
        this.items[nextFirst] = item;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        this.size += 1;
    }

    private void resizing(int capacity) {
        T[] t = (T[]) new Object[capacity];
        int first = (nextFirst + 1) % items.length;
        for (int pos = 0; pos < this.size; pos ++) {
            t[pos] = items[first];
            first = (first + 1) % items.length;
        }
        items = t;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    public void addLast(T item) {
        if (size >= items.length) {
            resizing(size * 2);
        }
        this.items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        this.size += 1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (T item : items) {
            System.out.print(item + " ");
        }
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (size < items.length / 4 && items.length >= 16) {
            resizing(items.length / 4);
        }
        T returnItem;
        nextFirst = (nextFirst + 1) % items.length;
        returnItem = items[nextFirst];
        items[nextFirst] = null;
        this.size -= 1;
        return returnItem;
    }

    @Override
    public T removeLast() {
        if (size < items.length / 4 && items.length >= 16) {
            resizing(items.length / 4);
        }
        T returnItem;
        nextLast = (nextLast - 1 + items.length) % items.length;
        returnItem = items[nextLast];
        items[nextLast] = null;
        this.size -= 1;
        return returnItem;
    }

    @Override
    public T get(int index) {
        if (index >= size) { return null; }
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int first;
        private int total;

        public ArrayDequeIterator() {
            first = (nextFirst + 1) % items.length;
            total = 0;
        }

        @Override
        public boolean hasNext() {
            return total < size;
        }

        @Override
        public T next() {
            T returnItem = items[first];
            first = (first + 1) % items.length;
            return returnItem;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if (other.size != this.size) { return false; }
            int pos = (nextFirst + 1) % items.length;
            for (T item : other) {
                if (!item.equals(items[pos])) { return false; }
                pos = (pos + 1) % items.length;
            }
            return true;
        }
        return false;
    }
}
