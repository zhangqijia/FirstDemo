package sheffield2;

public class List<T> {

    private T head;
    private List<T> tail;

    public List() {
        head = null;
        tail = null;

    }

    private List(T h, List<T> t) {
        head = h;
        tail = t;
    }

    public List<T> add(T s) {
        return new List<T>(s, this);
    }

    public T getHead() {
        return head;
    }

    public List<T> getTail() {
        return tail;
    }

    public String toString() {
        List h = this;
        String s = "";
        while (h.head != null) {
            s = s + h.head + " ";
            h = h.tail;
        }
        return s;
    }

    public boolean member(T s) {
        if (head == null)
            return false;
        else if (s.equals(head))
            return true;
        else {
            return tail.member(s);
        }
    }

    public static int length(List list) {
        if (list.head == null) {
            return 0;
        } else {
            return 1 + List.length(list.tail);
        }
    }

    public static <V> List<V> reverse(List<V> list) {
        V head = list.getHead();
        List<V> tail = list.getTail();
        if (head == null) {
            return list;
        } else {
            List<V> reserved = List.reverse(tail);
            reserved.append(head);
            return reserved;
        }
    }

    public void append(T s) {
        if (head == null) {
            head = s;
            tail = new List<T>();
        } else
            tail.append(s);
    }
}
