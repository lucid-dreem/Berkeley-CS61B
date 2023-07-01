import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(sentinel, null, sentinel);
    }

    @Override
    public void addFirst(T x) {
        if (this.isEmpty()) {
            Node newFirst = new Node(sentinel, x, sentinel);
            sentinel.prev = newFirst;
            sentinel.next = newFirst;
        }
        else {
            Node newFirst = new Node(sentinel, x, sentinel.next);
            sentinel.next.prev = newFirst;
            sentinel.next = newFirst;
        }
        size++;
    }

    @Override
    public void addLast(T x) {
        if (this.isEmpty()) {
            Node newFirst = new Node(sentinel, x, sentinel);
            sentinel.prev = newFirst;
            sentinel.next = newFirst;
        }
        else {
            Node newFirst = new Node(sentinel.prev, x, sentinel);
            sentinel.prev.next = newFirst;
            sentinel.prev = newFirst;
        }
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> lst = new ArrayList<>();

        // initialise currentNode pointer
        Node currentNode;
        if (size != 0) {
            currentNode = sentinel.next;
            while (currentNode != sentinel) {
                lst.add(currentNode.item);
                currentNode = currentNode.next;
            }
        }
        return lst;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) { return true; }
        else { return false; }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T toBeReturned = this.get(0);
        if (size < 2) {
            sentinel.next = null;
            sentinel.prev = null;
            size = 0;
        }
        else {
            // forward reference
            sentinel.next = sentinel.next.next;
            // reverse reference
            sentinel.next.prev = sentinel;
            size--;
        }
        return toBeReturned;
    }

    @Override
    public T removeLast() {
        T toBeReturned = this.get(size - 1);
        if (size < 2) {
            sentinel.next = null;
            sentinel.prev = null;
            size = 0;
        }
        else {
            // forward reference
            sentinel.prev = sentinel.prev.prev;
            // reverse reference
            sentinel.prev.next = sentinel;
            size--;
        }
        return toBeReturned;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < size) {
            Node currentNode = sentinel.next;
            for (int i = 1; i <= index; i++) {
                currentNode = currentNode.next;
            }
            return currentNode.item;
        }
        else { return null; }
    }

    @Override
    public T getRecursive(int index) {
        if (index >= 0 && index < size) {
            return getRecursive(sentinel.next, index);
        }
        else { return null; }
    }

    private T getRecursive(Node startNode, int index){
        if (index != 0) {
            return getRecursive(startNode.next, index - 1);
        }
        else { return startNode.item; }
    }

    public static void main(String[] args) {
        Deque<Integer> lld = new LinkedListDeque<>();
        Object alpha = lld.get(0);
        System.out.println(alpha);
    }
}
