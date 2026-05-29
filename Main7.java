class Employee {

    private String name;
    private double salary;
    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    void display() {

        System.out.println("Name: " + name);
        System.out.println("Salary: " + salary);
    }
}
class Teacher extends Employee {
    private String subject;
    Teacher(String name, double salary, String subject) {
        super(name, salary);
        this.subject = subject;
    }
    @Override
    void display() {
        super.display();
        System.out.println("Subject: " + subject);
    }
    void conductClass() {
        System.out.println("The teacher is conducting a lecture of Math.");
      }
    }
    class AdminStaff extends Employee {

    private String department;
     AdminStaff(String name, double salary, String department) {

        super(name, salary);

        this.department = department;

    }

     @Override
    void display() {

        super.display();

        System.out.println("Department: " + department);

    }

}
public class Main7 {
    public static void main(String[] args) {

        Employee emp1 = new Teacher("Rashid Minhas", 500000, "Mathematics");
        Employee emp2 = new AdminStaff("Ali Zafar", 400000, "Registrar Office");
        emp1.display();
        emp2.display();
        if (emp1 instanceof Teacher) {

            Teacher specificTeacher = (Teacher) emp1;
            specificTeacher.conductClass();
          }
       }
    }

 