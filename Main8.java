import java.util.ArrayList;
public class Main8 {
    public static void main(String[] args) {
        DataHolder<Integer> dh = new DataHolder<>();
        dh.add(1);
        dh.add(2); 
        dh.add(3);
        dh.display();  

        DataHolder<String> ds = new DataHolder<>();
        ds.add("Java"); ds.add("OOP");
        ds.display();   
        }
}