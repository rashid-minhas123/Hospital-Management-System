import java.util.ArrayList;

class Employee {
    String firstName;
    String lastName;
    int employeeId;
    String status;
    String position;
    double salary;

    Employee(String firstName, String lastName, int employeeId, String position, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeId = employeeId;
        this.position = position;
        this.salary = salary;
        this.status = "Active";
    }

    void promote(String newPosition) {
        System.out.println(firstName + " promoted to " + newPosition);
        position = newPosition;
    }

    void terminate() {
        status = "Terminated";
        System.out.println(firstName + " has been terminated.");
    }

    void displayInfo() {
        System.out.println("Name     : " + firstName + " " + lastName);
        System.out.println("ID       : " + employeeId);
        System.out.println("Position : " + position);
        System.out.println("Salary   : " + salary);
        System.out.println("Status   : " + status);
    }
}

class Manager extends Employee {
    ArrayList<String> projects;

    Manager(String firstName, String lastName, int employeeId, double salary) {
        super(firstName, lastName, employeeId, "Manager", salary);
        projects = new ArrayList<>();
    }

    void assignProject(String project) {
        projects.add(project);
        System.out.println("Project added: " + project);
    }

    void evaluatePerformance() {
        System.out.println(firstName + " is evaluating team performance.");
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Projects : " + projects);
    }
}

class TeamLead extends Manager {
    String teamName;
    ArrayList<String> tasks;

    TeamLead(String firstName, String lastName, int employeeId, String teamName, double salary) {
        super(firstName, lastName, employeeId, salary);
        this.teamName = teamName;
        this.position = "Team Lead";
        tasks = new ArrayList<>();
    }

    void holdMeeting() {
        System.out.println(firstName + " is holding a meeting for " + teamName);
    }

    void addTask(String task) {
        tasks.add(task);
        System.out.println("Task added: " + task);
    }

    void showTasks() {
        System.out.println("Tasks for " + teamName + " : " + tasks);
    }

    @Override
    void displayInfo() {
        super.displayInfo();
        System.out.println("Team     : " + teamName);
        System.out.println("Tasks    : " + tasks);
    }
}

public class Main1 {
    public static void main(String[] args) {

        Employee emp = new Employee("Alice", "Smith", 101, "Junior Developer", 55000);
        Manager mgr = new Manager("Bob", "Johnson", 201, 90000);
        TeamLead tl = new TeamLead("Carol", "Williams", 301, "Alpha Team", 75000);

        System.out.println("--- Employee ---");
        emp.displayInfo();
        emp.promote("Senior Developer");

        System.out.println("\n--- Manager ---");
        mgr.assignProject("Project Phoenix");
        mgr.evaluatePerformance();
        mgr.displayInfo();

        System.out.println("\n--- Team Lead ---");
        tl.holdMeeting();
        tl.addTask("Fix bugs");
        tl.addTask("Code review");
        tl.displayInfo();
        tl.showTasks();

        System.out.println("\n--- Terminate ---");
        emp.terminate();
    }
}
