package com.drdivago.list;

import java.util.*;
import java.util.function.Consumer;

public class SortedLinkedList<E extends Comparable<E>> implements List<E> {

    private static class Node<E extends Comparable<E>> {
        private E value;
        private Node<E> next;
        private Node<E> previous;

        public Node(E value, Node<E> next, Node<E> previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SortedLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIteratorImpl(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl(0);
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    @Override
    public boolean contains(Object o) {
        return this.stream().anyMatch(e -> e.equals(o));
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIteratorImpl(0);
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (E e : this) {
            array[i++] = e;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }


    @Override
    public boolean add(E value) {
        if (isEmpty() || head.value.compareTo(value) >= 0) {
            insertHead(value);
        }
        else {
            Node<E> node = findPrevious(value);
            insertAfter(node, value);

        }
        size++;
        return true;
    }

    private Node<E> findPrevious(E value) {
        Node<E> current = head;
        while (current.next != null && current.next.value.compareTo(value) < 0) {
            current = current.next;
        }
        return current;
    }

    private void insertAfter(Node<E> node, E value) {
        node.next = new Node<>(value, node.next, node);
        if (node.next.next != null) {
            node.next.next.previous = node.next;
        } else {
            tail = node.next;
        }
    }
    private void insertHead(E value) {
        head = new Node<>(value, head, null);
        if (tail == null) {
            tail = head;
        }
        else {
            head.next.previous = head;
        }
    }

    @Override
    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        if (head.value.equals(o)) {
            removeHead();
        }
        else {
            Node<E> current = head;
            while (current.next != null && !current.value.equals(o)) {
                current = current.next;
            }
            removeNode(current);
        }
        return true;
    }

    private E removeHead() {
        E value = head.value;
        head = head.next;
        if (head != null) {
            head.previous = null;
        }
        else {
            tail = null;
        }
        size--;
        return value;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
        for (Node<E> node = head; node != null; ) {
            Node<E> next = node.next;
            node.value = null;
            node.next = null;
            node.previous = null;
            node = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        return nodeAt(index).value;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = nodeAt(index);
        E oldValue = node.value;
        node.value = element;
        return oldValue;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        Node<E> node = nodeAt(index);
        return (node==  head) ? removeHead() : removeNode(node);
    }

    private E removeNode(Node<E> node) {
        E value = node.value;
        node.previous.next = node.next;
        if (node.next != null) {
            node.next.previous = node.previous;
        }
        else {
            tail = node.previous;
        }
        size--;
        return value;
    }

    @Override
    public int indexOf(Object o) {
         return (int)this.stream().takeWhile(e -> !e.equals(o)).count();
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> last = tail;
        int index = size-1;
        while (last != null) {
            if (last.value.equals(o)) {
                return index;
            }
            last = last.previous;
            index--;
        }
        return -1;
    }

    private Node<E> nodeAt(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private class ListIteratorImpl implements ListIterator<E> {
        private Node<E> current;
        private Node<E> next;
        private int nextIndex;
        ListIteratorImpl(int index) {
            next = (index == size) ? null : nodeAt(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();

            current = next;
            next = next.next;
            nextIndex++;
            return current.value;
        }

        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        public E previous() {
            current = next = (next == null) ? tail : next.previous;
            nextIndex--;
            return current.value;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex--;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        public void add(E e) {
            throw new UnsupportedOperationException();
        }

        public void forEachRemaining(Consumer<? super E> action) {
            Objects.requireNonNull(action);
            while (nextIndex < size) {
                action.accept(next.value);
                current = next;
                next = next.next;
                nextIndex++;
            }
        }
    }
}
