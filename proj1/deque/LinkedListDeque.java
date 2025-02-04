package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private static class LinkNode<T> {
        public T element;
        public LinkNode<T> before;
        public LinkNode<T> next;

        public LinkNode(T e, LinkNode<T> b, LinkNode<T> n) {
            this.element = e;
            this.before = b;
            this.next = n;
        }
    }

    private LinkNode<T> Sentinel;
    private int size;

    public LinkedListDeque() {
        this.Sentinel = new LinkNode<>(null, null, null);
        this.Sentinel.before = this.Sentinel;
        this.Sentinel.next = this.Sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T item) {
        LinkNode<T> p = new LinkNode<>(item, this.Sentinel, this.Sentinel.next);
        p.before.next = p;
        p.next.before = p;
        this.size += 1;
    }
    @Override
    public void addLast(T item) {
        LinkNode<T> p = new LinkNode<>(item, this.Sentinel.before, this.Sentinel);
        p.before.next = p;
        p.next.before = p;
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
        LinkNode<T> p = this.Sentinel;
        while (p.next != this.Sentinel) {
            p = p.next;
            System.out.print(p + " ");
        }
        System.out.println();
    }
    @Override
    public T removeFirst() {
        LinkNode<T> p = this.Sentinel.next;
        if (p == this.Sentinel) {
            return null;
        }
        p.next.before = p.before;
        p.before.next = p.next;

        this.size -= 1;
        return p.element;
    }
    @Override
    public T removeLast() {
        LinkNode<T> p = this.Sentinel.before;
        if (p == this.Sentinel) {
            return null;
        }
        p.next.before = p.before;
        p.before.next = p.next;

        this.size -= 1;
        return p.element;
    }
    @Override
    public T get(int index) {
        if (index >= this.size) {
            return null;
        }
        LinkNode<T> p = this.Sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.element;
    }

    private T getRecursiveHelper(int index, LinkNode<T> p) {
        if (index == 0) {
            return p.element;
        }
        return getRecursiveHelper(index - 1, p.next);
    }
    public T getRecursive(int index) {
        if (index >= this.size) {
            return null;
        }

        return getRecursiveHelper(index, this.Sentinel.next);
    }
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private LinkNode<T> pos;

        public LinkedListDequeIterator() {
            this.pos = Sentinel;
        }
        public boolean hasNext() {
            return pos.next != Sentinel;
        }

        public T next() {
            pos = pos.next;
            return  pos.element;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null) { return false; }
        if (o.getClass() != this.getClass()) { return false; }

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        LinkNode<T> pos = Sentinel;
        if (other.size != this.size) { return false; }
        for (T item : other) {
            if (!item.equals(pos.next)) {
                return false;
            }
            pos = pos.next;
        }
        return true;
    }
}
