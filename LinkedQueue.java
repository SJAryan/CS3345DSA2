import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<E> implements QueueInterface<E>, List<E> {

    // class for the doubly-linked node
    private class Node {
        E data;
        Node next;
        Node prev;

        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node head;
    private Node tail;
    private int count;

    public LinkedQueue() {
        head = null;
        tail = null;
        count = 0;
    }

   

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void enqueue(E e) throws IllegalStateException, NullPointerException {
        if (e == null) throw new NullPointerException("Element cannot be null");
        addLast(e); 
    }

    @Override
    public E peek() {
        if (isEmpty()) return null;
        return head.data;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        E data = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null; 
        }
        count--;
        return data;
    }

    @Override
    public E dequeue(int index) throws NoSuchElementException {
        if (index < 0 || index >= count) {
            throw new NoSuchElementException("Invalid index");
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        
        count--;
        return current.data;
    }

    @Override
    public void removeAll() {
        head = null;
        tail = null;
        count = 0;
    }

    // --- List Interface Methods ---

    @Override
    public void addLast(E item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        count++;
    }

    @Override
    public void addFirst(E item) {
        Node newNode = new Node(item);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        count++;
    }

    @Override
    public E get(int position) {
        if (position < 0 || position >= count) return null;
        Node current = head;
        for (int i = 0; i < position; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public void printBackwards() {
        Node current = tail;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.prev;
        }
        System.out.println();
    }

    @Override
    public boolean remove(E item) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(item)) {
                if (current.prev != null) current.prev.next = current.next;
                else head = current.next;
                
                if (current.next != null) current.next.prev = current.prev;
                else tail = current.prev;
                
                count--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public int getLength() {
        return count;
    }

    // --- Iterable Method ---
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) throw new NoSuchElementException();
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }
}