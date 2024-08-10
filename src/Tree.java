public class Tree {

    private Node root;

    static class Node {
        String key;
        String value;

        Node left;
        Node right;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public Node(String key, String value, Node left, Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public Tree() {
        root = null;
    }

    public void insert(String key, String value) {
        root = insert(key, value, root);
    }

    private Node insert(String key, String value, Node node) {
        if (node == null) {
            return new Node(key, value);
        }

        if (node.key.equals(key)) {
            node.value = value;
            return node;
        }

        if (node.key.compareTo(key) > 0) {
            node.left = insert(key, value, node.left);
        }
        else {
            node.right = insert(key, value, node.right);
        }

        return node;
    }

//    рекурсия
//    Почитать в интернете про деревья и балансировку
//    balance() - почитать и реализовать
//    delete(key)
//    get(key)
//    print()

}
