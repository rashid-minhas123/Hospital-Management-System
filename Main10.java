import java.util.*;

// Child Class
class Employee {
    private String name;
    Employee(String name) { this.name = name; }
    public String getName() { return name; }
}

// Parent Class
class Bank {
    private String bankName;
    private List<Employee> employees; // Bank "has" employees

    Bank(String bankName, List<Employee> employees) {
        this.bankName = bankName;
        this.employees = employees;
    }

    public void displayEmployees() {
        System.out.println("Employees at " + bankName + ":");
        for (Employee emp : employees) {
            System.out.println("- " + emp.getName());
        }
    }
}

public class Main10 {
    public static void main(String[] args) {
        Employee e1 = new Employee("Alice");
        Employee e2 = new Employee("Bob");

        List<Employee> empList = new ArrayList<>();
        empList.add(e1);
        empList.add(e2);

        Bank myBank = new Bank("Global Trust", empList);
        myBank.displayEmployees();
    }
}