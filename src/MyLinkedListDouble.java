public class MyLinkedListDouble {

    private Node head;
    private Node tail;
    private int size;

    static class Node {
        String value;
        Node next;
        public Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    public MyLinkedListDouble() {
        head = null;
        tail = null;
    }

    public void add(String element) {
        Node tmp = new Node(element, null);

        if (head == null) {
            head = tmp;
            tail = head;
            return;
        }

        Node oldTail = tail;
        tail = tmp;
        oldTail.next = tail;
        size++;
    }

    public void add(String element, int index) {

        if (index > size || index < 0) {
            throw new RuntimeException("Введенный индекс больше/меньше чем размер");
        }

        if (index == 0) {
            Node tmpHead = head;
            head = new Node(element, tmpHead);

            size++;
            return;
        }

        Node tmpHead = head;
        Node tmpPrev = null;

        for (int i = 0; i <= index; i++) {
            tmpPrev = tmpHead;
            tmpHead = tmpHead.next;
        }

        Node node = new Node(element, tmpHead);
        tmpPrev.next = node;
        size++;
    }

    public String get(int index) {

        if (index > size || index < 0) {
            throw new RuntimeException("Введенный индекс больше/меньше чем размер");
        }

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

        if (index > size || index < 0) {
            throw new RuntimeException("Введенный индекс больше/меньше чем размер");
        }

        Node deletedNode;

        if(index == size) {

            Node tmpHead = head;

            for (int i = 0; i < size - 1; i++) {
                tmpHead = tmpHead.next;
            }

            deletedNode = tmpHead.next;
            tmpHead.next = null;
            tail = tmpHead;

            size--;
            return deletedNode.value;
        }

        if(index == 0) {

            Node tmpHead = head;
            Node tmpNext = tmpHead.next;

            head = tmpNext;

            size--;
            return tmpHead.value;
        }

        Node currentNode = head;
        Node prevNode = null;

        for (int i = 0; i < index; i++) {
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        prevNode.next = currentNode.next;

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
        list.add("Ivan");
        list.add("Dan");

        list.print();
        System.out.println();

        list.add("Mom", 0);
        list.add("Dad", 0);

        list.print();

        System.out.println("\n" + list.get(2));

        list.delete(2);
        list.print();

    }

}


