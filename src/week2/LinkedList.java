package week2;

public class LinkedList<T> {
    private Node head;
    private Node last;

    class Node {
        T element;
        Node next;

        Node(T e) {
            this.element = e;
        }
    }

    public void add(T element) {
        Node node = new Node(element);
        if (last == null) {
            last = node;
            head = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public T get(int index) {
        T e = null;
        Node next = head;
        if (index == 0) {
            return head.element;
        }
        for (int i = index; i > 0; i--) {
            if (next == null) {
                return null;
            }
            next = next.next;
            if (next == null)
                return null;
            else
                e = next.element;
        }
        return e;
    }

    private Node after;

    public int length() {
        if (head == null) {
            return 0;
        } else {
            after = head.next;
            if (after == null) {
                return 0;
            } else {
                return 1 + length();
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> integerLinkedList = new LinkedList<>();
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.add(4);
        integerLinkedList.add(5);
        integerLinkedList.add(6);
        integerLinkedList.add(7);
        integerLinkedList.add(8);
        integerLinkedList.add(9);
        Integer integer = integerLinkedList.get(0);
        System.out.println(integer);
        System.out.println(integerLinkedList.length());
    }
}
