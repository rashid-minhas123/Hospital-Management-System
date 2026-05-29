import java.util.Arrays;
import java.util.List;
class Student {
// -----------------------------------
// DATA MEMBERS (State of Object)
// -----------------------------------
 private String name;
private int marks;
private char grade;
// -----------------------------------
// CONSTRUCTOR
// -----------------------------------
// Automatically called when object is created
public Student(String name, int marks) {
this.name = name;
this.marks = marks;
calculateGrade(); // calling member function inside constructor
}
// -----------------------------------
// MEMBER FUNCTION - Calculate Grade
// ----------------------------------- 
public void calculateGrade() {
if (marks >= 80)
grade = 'A';
else if (marks >= 60)
grade = 'B';
else if (marks >= 50)
grade = 'C';
else
grade = 'F';
}
// -----------------------------------
// MEMBER FUNCTION - Display Info
// ----------------------------------- 
public void displayInfo() {
System.out.println("Name: " + name);
System.out.println("Marks: " + marks);
System.out.println("Grade: " + grade);
System.out.println("---------------------");
}
// -----------------------------------
// DESTRUCTOR CONCEPT (Garbage Collection)
// -----------------------------------
 @Override
protected void finalize() throws Throwable {
System.out.println("Object is destroyed by Garbage Collector");
}
// -----------------------------------
// MAIN METHOD
// -----------------------------------
 public static void main(String[] args) {
// Creating Objects
Student s1 = new Student("Aimen", 85);
Student s2 = new Student("Mahrukh", 67);
Student s3 = new Student("Ali", 45);
System.out.println("Student Details:");
// Calling member functions normally
s1.displayInfo();
s2.displayInfo();
s3.displayInfo();
// -----------------------------------

// SCOPE RESOLUTION OPERATOR (::)
// -----------------------------------
 System.out.println("Using Scope Resolution Operator:");
List<Student> students = Arrays.asList(s1, s2, s3);
// Method reference using ::
students.forEach(Student::printStudent);
}
// Static method used with scope resolution
public static void printStudent(Student s) {
s.displayInfo();
}
}
