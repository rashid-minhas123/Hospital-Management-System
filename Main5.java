abstract class Shape {
    String color;

    Shape(String color) {
        this.color = color;
    }

    public String toString() {
        return "Color: " + color;
    }

    abstract double getArea();
    abstract double getPerimeter();
}

class Rectangle extends Shape {
    double length, width;

    Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    public String toString() {
        return super.toString() + ", Shape: Rectangle, Length: " + length + ", Width: " + width;
    }

    public double getArea() {
        return length * width;
    }

    public double getPerimeter() {
        return 2 * (length + width);
    }
}

class Triangle extends Shape {
    double a, b, c;

    Triangle(String color, double a, double b, double c) {
        super(color);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String toString() {
        return super.toString() + ", Shape: Triangle, Sides: " + a + ", " + b + ", " + c;
    }

    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
     public double getPerimeter() {
        return a + b + c;
    }
}
public class Main5 {
    public static void main(String[] args) {

        Rectangle r = new Rectangle("Blue", 5, 10);
        Triangle t  = new Triangle("Red", 3, 4, 5);

        System.out.println(r);
        System.out.println("Area      : " + r.getArea());
        System.out.println("Perimeter : " + r.getPerimeter());
        System.out.println();
        System.out.println(t);
        System.out.println("Area      : " + t.getArea());
        System.out.println("Perimeter : " + t.getPerimeter());
    }
}