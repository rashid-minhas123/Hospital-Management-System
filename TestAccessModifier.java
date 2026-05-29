class Parent {
    // protected method
    protected void showMessage() {
        System.out.println("Hello from Parent class");
    }
}

// Child class
class Child extends Parent {

    /*
    // ❌ INVALID OVERRIDE (will cause COMPILE-TIME ERROR)
    // Cannot reduce visibility from protected to private

    private void showMessage() {
        System.out.println("Hello from Child class (private)");
    }
    */

    // ✅ VALID OVERRIDE (same access level: protected)
    @Override
    protected void showMessage() {
        System.out.println("Hello from Child class (protected)");
        }/*
    // ✅ ALSO VALID (less restrictive: public)
    @Override
    public void showMessage() {
        System.out.println("Hello from Child class (public)");
    }
    */
}// Main class
public class TestAccessModifier {
    public static void main(String[] args) {
        Parent obj = new Child();  // runtime polymorphism
        obj.showMessage();         // calls Child's version
    }
}
