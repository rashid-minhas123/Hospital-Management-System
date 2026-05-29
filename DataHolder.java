import java.util.ArrayList;

class DataHolder<T> {
    private ArrayList<T> list = new ArrayList<>();

    public void add(T item) {
        list.add(item);
    }

    public void display() {
        System.out.println("Elements: " + list);
    }
}

