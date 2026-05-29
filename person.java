class person{
protected String name;
protected int age;

public person(String name,int age){
this.name=name;
this.age=age;}
public void displayInfo(){
    System.out.println("Name:"+name);
    System.out.println("Age:"+age);
}
public static void main(String[] args){
Student e1=new Student("RASHID MINHAS",19,013);
e1.displaydetails();
}
}
class Student extends person{
    private int StudentID;
    public Student(String name,int age,int StudentID){
    super(name,age);
    this.StudentID=StudentID;
}
     public void displaydetails(){
         displayInfo();
         System.out.println("StudentID:"+StudentID);
     }
}