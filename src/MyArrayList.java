public class MyArrayList {

    private int capacity;
    private float loadFactor;
    private int size;
    private static final int DEFAULT_CAPACITY = 8;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private String[] arrayList;

    public MyArrayList() {
        capacity = DEFAULT_CAPACITY;
        loadFactor = DEFAULT_LOAD_FACTOR;
        arrayList = new String[capacity];
    }

    public MyArrayList(int capacity, float loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        arrayList = new String[capacity];
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        loadFactor = DEFAULT_LOAD_FACTOR;
        arrayList = new String[capacity];
    }

    private void increaseArrayList() {

        capacity = capacity * 2;
        updateArrayList();
        System.out.println("Updated Capacity: " + capacity / 2 + " -> " + capacity);
    }

    private void decreaseArrayList() {

        capacity = capacity / 2;
        updateArrayList();
        System.out.println("Updated Capacity: " + capacity * 2 + " -> " + capacity);
    }

    private void updateArrayList() {

        String[] tmpArrayList = new String[capacity];

        if (size == 0) {
            return;
        }

        for(int i = 0; i <= size; i++) {
            tmpArrayList[i] = arrayList[i];
        }

        arrayList = new String[capacity];

        for(int i = 0; i <= size; i++) {
            arrayList[i] = tmpArrayList[i];
        }

    }

    public void add(String element) {

        if(size == 0) {
            arrayList[size] = element;
            size++;
            return;
        }

        arrayList[size] = element;
        size++;

        if (size == capacity * loadFactor) {
            increaseArrayList();
        }

    }

    public void add(String element, int index) {

        checkIndex(index);

        for(int i = size; i >= index; i--) {
            arrayList[i + 1] = arrayList[i];
        }

        arrayList[index] = element;
        size++;

        if (size == capacity * loadFactor) {
            increaseArrayList();
        }
    }

    public String get(int index) {

        checkIndex(index);
        return arrayList[index];
    }

    public String delete(int index) {

        checkIndex(index);
        String deletedValue = arrayList[index];

        String[] tmpArrayList = new String[capacity];

        for (int i = 0; i < size; i++) {
            if (i < index) {
                tmpArrayList[i] = arrayList[i];
                continue;
            }

            tmpArrayList[i] = arrayList[i+1];
        }

        arrayList = tmpArrayList.clone();

        size--;
        if (capacity != DEFAULT_CAPACITY && size < capacity / 2 * loadFactor) {
            decreaseArrayList();
        }
        return deletedValue;
    }

    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println("["+ i + "]:" + arrayList[i]);
        }
        System.out.println();
    }

    private void checkIndex(int index) {

        if(index > size || index < 0) {
            throw new RuntimeException("Некорректный индекс");
        }

    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList();

        list.add("Barack");
        list.add("Obama");
        list.add("Joe");
        list.print();

        System.out.println();
        list.add("Biden", 3);
        list.print();
        list.add("Donald",0);
        list.print();

        System.out.println(list.delete(1));
        list.print();

        list.add("Trump");
        list.add("Kamala");
        list.add("Harris", 5);
        list.print();
        System.out.println(list.delete(5));
        System.out.println(list.delete(5));
        list.print();
    }

}