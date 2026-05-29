class Rectangle {

    private float length;
    private float width;

    // Constructor
    public Rectangle(float length, float width) {
        this.length = length;
        this.width = width;
    }

    // Method to calculate area
    public void calArea() {
        System.out.println("The Area of Rectangle: " + (length * width));
    }
    public void calPerimeter(){
        System.out.println("The Perimeter of Rectangle:"+(2*(length+width)));
    }

    public static void main(String[] args) {
        Rectangle s1 = new Rectangle(12, 10);
        s1.calArea();
        s1.calPerimeter();
    }
}