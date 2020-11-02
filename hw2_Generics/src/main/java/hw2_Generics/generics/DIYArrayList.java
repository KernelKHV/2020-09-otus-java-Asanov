package hw2_Generics.generics;

import java.util.*;

public class DIYArrayList<E> implements List<E> {

    private Object[] resData;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public DIYArrayList() {
        resData = new Object[DEFAULT_CAPACITY];
    }

    private boolean setArrayListSize(int minSize) {
        if (minSize > DEFAULT_CAPACITY) {
            int newCapacity = minSize + (DEFAULT_CAPACITY * 3) / 2 + 1;
            this.resData = new Object[newCapacity];
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        return new DIYIterator();
    }

    private class DIYIterator implements Iterator<E> {
        private int index = 0;

        public boolean hasNext() {
            return index != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            if (index >= size)
                throw new NoSuchElementException();
            if (index >= resData.length)
                throw new ConcurrentModificationException();
            return (E) DIYArrayList.this.resData[index++];
        }
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[size];
        System.arraycopy(this.resData, 0, res, 0, size);
        return res;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) {
        Object[] a = this.resData;
        if (setArrayListSize(size + 1)) {
            System.arraycopy(a, 0, this.resData, 0, size);
        }
        this.resData[this.size] = e;
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        /*Object[] a = c.toArray();
        int initLen = a.length;
        this.setArrayListSize(initLen + c.size());
        System.arraycopy(a, 0, resData, 0, initLen);
        System.arraycopy(c.toArray(), 0, resData, initLen, c.size());
        size = initLen + c.size();
        return true;*/
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
       /* Object[] a = c.toArray();
        int initLenght = a.length;
        this.setArrayListSize(initLenght);
        int mov = initLenght - index;
        if (mov > 0)
            System.arraycopy(resData, index, resData, index + initLenght, mov);
        System.arraycopy(a, 0, resData, index, initLenght);
        size = initLenght;
        return true;*/
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return (E) this.resData[index];
    }

    @SuppressWarnings("unchecked")
    @Override
    public E set(int index, E element) {
        Objects.checkIndex(index, size);
        this.resData[index] = element;
        return (E) this.resData;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new DIYListItr();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new DIYListItr();
    }

    private class DIYListItr implements ListIterator<E> {
        private int index = 0;

        public boolean hasPrevious() {
            return index != 0;
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            rangeCheckForAdd(index + 1);
            return (E) resData[index++];
        }

        @SuppressWarnings("unchecked")
        @Override
        public E previous() {
            rangeCheckForAdd(index - 1);
            return (E) resData[index--];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();

        }

        @Override
        public void set(E e) {
            if (index == 0)
                throw new IllegalStateException();
            try {
                resData[index - 1] = e;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }

        }

        @Override
        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}