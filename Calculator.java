public class Calculator {

    public int multiply(int a, int b) {
        return a * b;
    }

    public int multiply(int a, int b, int c) {
        return a * b * c;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        int result1 = calc.multiply(4, 5);
        System.out.println("Product of two integers (4, 5): " + result1);

        int result2 = calc.multiply(2, 3, 6);
        System.out.println("Product of three integers (2, 3, 6): " + result2);

        double result3 = calc.multiply(2.5, 4.0);
        System.out.println("Product of two doubles (2.5, 4.0): " + result3);
    }
}