import java.util.Scanner;

public class AreaCalculator {
    
    public double calculateArea(double sides) {
        return sides * sides;
    }
    public double calculateArea(double Length, double width) {
        return Length * width;
    }

    public double calculateArea(double radius, boolean isCircle) {
        return (3.14) * radius * radius;
    }

    public double calculateArea(double side1, double side2, double side3, boolean isTriangle) {
        double s = (side1 + side2 + side3) / 2;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    public double calculateArea(double base, double height, boolean isParallelogram) {
        return base * height;
    }

    public double calculateArea(double Base1, double Base2, double Height) {
        return ((Base1 + Base2) / 2) * Height;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AreaCalculator calc = new AreaCalculator();

        System.out.println("========================================");
        System.out.println("         AREA CALCULATOR");
        System.out.println("========================================");
        System.out.println("Choose a shape:");
        System.out.println("  1. Square");
        System.out.println("  2. Rectangle");
        System.out.println("  3. Circle");
        System.out.println("  4. Triangle");
        System.out.println("  5. Parallelogram");
        System.out.println("  6. Trapezoid");
        System.out.println("========================================");
        System.out.print("Enter your choice (1-6): ");

        int choice = scanner.nextInt();
        double area = 0;

        switch (choice) {
            case 1: // For Square
                System.out.print("Enter the side length: ");
                double side = scanner.nextDouble();
                area = calc.calculateArea(side);
                System.out.printf("%nArea of Square = %.2f%n", area);
                break;

            case 2: // For Rectangle
                System.out.print("Enter the length: ");
                double length = scanner.nextDouble();
                System.out.print("Enter the width: ");
                double width = scanner.nextDouble();
                area = calc.calculateArea(length, width);
                System.out.printf("%nArea of Rectangle = %.2f%n", area);
                break;

            case 3: // For Circle
                System.out.print("Enter the radius: ");
                double radius = scanner.nextDouble();
                area = calc.calculateArea(radius, true);
                System.out.printf("%nArea of Circle = %.2f%n", area);
                break;

            case 4: //  ForTriangle
                System.out.print("Enter side 1: ");
                double s1 = scanner.nextDouble();
                System.out.print("Enter side 2: ");
                double s2 = scanner.nextDouble();
                System.out.print("Enter side 3: ");
                double s3 = scanner.nextDouble();
                area = calc.calculateArea(s1, s2, s3, true);
                System.out.printf("%nArea of Triangle = %.2f%n", area);
                break;

            case 5: // For Parallelogram
                System.out.print("Enter the base: ");
                double base = scanner.nextDouble();
                System.out.print("Enter the height: ");
                double height = scanner.nextDouble();
                area = calc.calculateArea(base, height, true);
                System.out.printf("%nArea of Parallelogram = %.2f%n", area);
                break;

            case 6: // For Trapezoid
                System.out.print("Enter base 1: ");
                double base1 = scanner.nextDouble();
                System.out.print("Enter base 2: ");
                double base2 = scanner.nextDouble();
                System.out.print("Enter the height: ");
                double trapHeight = scanner.nextDouble();
                area = calc.calculateArea(base1, base2, trapHeight);
                System.out.printf("%nArea of Trapezoid = %.2f%n", area);
                break;

            default:
                System.out.println("Invalid choice! Please enter a number between 1 and 6.");
        }

        scanner.close();
    }
}