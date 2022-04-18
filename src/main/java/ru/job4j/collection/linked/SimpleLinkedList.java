package ru.job4j.collection.linked;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private int size = 0;
    private final Node<E> head = new Node<>(null, null, null);
    private Node<E> last;
    private int modCount = 0;

    @Override
    public void add(E value) {
        final Node<E> lastNode = head.next == null ? head : last;
        final Node<E> newNode = new Node<>(lastNode, value, null);
        lastNode.next = newNode;
        last = newNode;
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> find = null;
        Node<E> l = head.next;
        for (int i = 0; i <= index; i++) {
            find = l;
            l = find.next;
        }
        return find.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int expectedModCount = modCount;
            private Node<E> iterNode = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return iterNode.next != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                iterNode = iterNode.next;
                return iterNode.item;
            }
        };
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E item() {
            return item;
        }
    }
}
