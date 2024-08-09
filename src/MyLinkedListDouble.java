public class MyLinkedListDouble {

    private Node head;
    private Node tail;
    private int size;

    static class Node {
        String value;
        Node next;

        Node previous;

        public Node(String value, Node next, Node previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    public MyLinkedListDouble() {
        head = null;
        tail = null;
    }

    public void checkIndex(int index) {
        if (index > size || index < 0) {
            throw new RuntimeException("Введенный индекс больше/меньше чем размер");
        }
    }

    public void add(String element) {

        Node tmp = new Node(element, null, null);

        if (head == null) {
            head = tmp;
            tail = head;
            return;
        }

        Node oldTail = tail;
        tail = tmp;
        tail.previous = oldTail;
        oldTail.next = tail;
        size++;
    }

    public void add(String element, int index) {

        checkIndex(index);

        if (index == 0) {
            Node tmpHead = head;
            head = new Node(element, tmpHead, null);
            tmpHead.previous = head;
            size++;
            return;
        }

        Node tmpHead = head;
        Node tmpPrev = null;

        for (int i = 0; i < index; i++) {
            tmpPrev = tmpHead;
            tmpHead = tmpHead.next;
        }

        Node node = new Node(element, tmpHead, tmpHead.previous);
        tmpPrev.next = node;
        tmpHead.previous = node;
        node.previous = tmpPrev;
        size++;
    }

    public String get(int index) {

        checkIndex(index);

        if(index == 0) {
            return head.value;
        }

        if (index == size) {
            return tail.value;
        }

        Node tmpHead = head;

        for (int i = 0; i < index; i++) {
            tmpHead = tmpHead.next;
        }

        return tmpHead.value;
    }

    public String delete(int index) {

        checkIndex(index);

        Node deletedNode;

        if(index == size) {

            tail = tail.previous;
            deletedNode = tail.next;
            tail.next = null;

            size--;
            return deletedNode.value;
        }

        if(index == 0) {

            Node tmpHead = head;
            head = tmpHead.next;

            size--;
            return tmpHead.value;
        }

        Node currentNode = head;

        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }

        Node prevNode = currentNode.previous;
        prevNode.next = currentNode.next;
        currentNode.next.previous = prevNode;

        size--;
        return currentNode.value;
    }

    public void print() {
        Node tmpHead = head;

        while (tmpHead != null) {
            System.out.print("[" + tmpHead.value + "] -> ");
            tmpHead = tmpHead.next;
        }
    }

    public static void main(String[] args) {
        MyLinkedListDouble list = new MyLinkedListDouble();
        list.add("Joe");
        list.add("Biden");

        list.print();
        System.out.println();

        list.add("Element1", 0);
        list.add("NewElement2", 2);

        list.print();

        System.out.println("\n" + list.get(1));

        list.delete(2);
        System.out.println(list.get(2));
        list.print();

    }

}


