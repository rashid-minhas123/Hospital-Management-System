class Employee{
String name;
int employeeID;
static String companyName ="NUTECH Solutions";
Employee(String name,int employeeID){
    this.name=name;
    this.employeeID= employeeID;
}
void displayEmployee(){
System.out.println("Employee Name:"+name);
System.out.println("Employee ID:"+employeeID);
System.out.println("Company Name:"+companyName);
System.out.println("----------------------------");
}
public static void main(String[] args){
Employee e=new Employee("Rashid",123);
Employee e1=new Employee("Abdulllllah",1243);
Employee e2=new Employee("Mustafa",143);
e.displayEmployee();
e1.displayEmployee();
e2.displayEmployee();


}
}