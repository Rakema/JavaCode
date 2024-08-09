public class MyArrayList {
    private int capacity = 8;
    private int size = 0;
    private float loadFactor = 0.75f;
    private String[] arrayList = new String[capacity];

    private void checkIndex(int index) {

        if(index > size || index < 0) {
            throw new RuntimeException("Некорректный индекс");
        }

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

    private void updateCapacity() {

        if (size == capacity * loadFactor) {
            capacity = capacity * 2;
            updateArrayList();
            System.out.println("Updated Capacity: " + capacity / 2 + " -> " + capacity );
        }

        if (capacity != 8 && size < capacity / 2 * loadFactor) {
            capacity = capacity / 2;
            updateArrayList();
            System.out.println("Updated Capacity: " + capacity * 2 + " -> " + capacity );
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
        updateCapacity();
    }

    public void add(String element, int index) {

        checkIndex(index);

        for(int i = size; i >= index; i--) {
            arrayList[i + 1] = arrayList[i];
        }

        arrayList[index] = element;
        size++;
        updateCapacity();
    }

    public void get(int index) {

        checkIndex(index);
        System.out.println("Index: " + index + " | " + "Value: " + arrayList[index]);
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
        updateCapacity();
        return deletedValue;
    }

    public void print() {
        for(int i = 0; i < size; i++) {
            System.out.println("["+ i + "]:" + arrayList[i]);
        }
        System.out.println();
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

        list.delete(1);
        list.print();

        list.add("Trump");
        list.add("Kamala");
        list.add("Harris", 5);
        list.print();
        list.delete(5);
        list.delete(5);
        list.print();
    }

}