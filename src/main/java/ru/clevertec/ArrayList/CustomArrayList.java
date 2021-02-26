package ru.clevertec.ArrayList;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CustomArrayList<E> implements List<E>, Serializable {
    private E[] values;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();

    @SuppressWarnings("unchecked")
    public CustomArrayList() {
        values = (E[]) new Object[0];
    }

    @Override
    public int size() {
        readLock.lock();
        try {
            return values.length;
        } finally {
            readLock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        writeLock.lock();
        try {
            E[] temp = values;
            values = (E[]) new Object[temp.length + 1];
            for (int i = 0; i < temp.length; i++) {
                values[i] = temp[i];
            }
            values[values.length - 1] = e;
            return true;
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public E get(int index) {
        readLock.lock();
        try {
            if (index >= 0 && index < values.length) {
                return values[index];
            } else {
                throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + values.length);
            }
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean isEmpty() {
        readLock.lock();
        try {
            return (size() == 0);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public boolean contains(Object o) {
        readLock.lock();
        try {
            int counter = 0;
            for (int i = 0; i < size(); i++) {
                if (o == get(i)) {
                    counter++;
                }
            }
            return (counter > 0);
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public Object[] toArray() {
        readLock.lock();
        try {
            return Arrays.copyOf(values, size());
        } finally {
            readLock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    public void delete(int index) {
        writeLock.lock();
        try {
            if (index >= 0 && index < values.length) {
                E[] temp = values;
                values = (E[]) new Object[temp.length - 1];
                for (int i = 0; i < index; i++) {
                    values[i] = temp[i];
                }
                for (int i = index; i < values.length; i++) {
                    values[i] = temp[i + 1];
                }
            } else {
                throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + values.length);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public void update(int index, E e) {
        writeLock.lock();
        try {
            values[index] = e;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Iterator
     */

    public Iterator<E> iterator() {
        return new ArrayIterator<>(values);
    }

    static class ArrayIterator<E> implements Iterator<E> {
        private int index = 0;
        private final E[] values;
        private final ReadWriteLock lock = new ReentrantReadWriteLock();
        private final Lock readLock = lock.readLock();

        public ArrayIterator(E[] values) {
            this.values = values;
        }

        @Override
        public boolean hasNext() {
            return index < values.length;
        }

        @Override
        public E next() {
            readLock.lock();
            try {
                return values[index++];
            } finally {
                readLock.unlock();
            }
        }
    }

    /**
     * Unsupported list methods
     */

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method not supported!");
    }

    /**
     * toString
     */

    @Override
    public String toString() {
        readLock.lock();
        try {
            return new StringJoiner(", ", CustomArrayList.class.getSimpleName() + "[", "]")
                    .add("values=" + Arrays.toString(values))
                    .toString();
        } finally {
            readLock.unlock();
        }
    }
}
