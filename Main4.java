abstract class Shape {
    abstract void area();
}

class Circle extends Shape {
    double radius = 7;

    void area() {
        System.out.println("Area of Circle:"+(3.14 * radius * radius));  
    }
}

class Rectangle extends Shape {
    double length = 5;
    double width  = 10;

    void area() {
        System.out.println("Rectangle area="+( length * width));             
    }
}

public class Main4 {
    public static void main(String[] args) {
        Shape circle    = new Circle();
        Shape rectangle = new Rectangle();

        circle.area();
        rectangle.area();
    }
}