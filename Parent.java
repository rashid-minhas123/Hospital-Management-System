class Parent {
    protected void showMessage() {
        System.out.println("Message from Parent");
    }
      public static void main(String[] args) {
        Parent p = new Parent();
        p.showMessage();

        Child c = new Child();
        c.showMessage();

        
        Parent ref = new Child();
        ref.showMessage();
    }
}

class Child extends Parent {
    @Override
    protected void showMessage() {      
        System.out.println("Message from Child");
    }
}

   