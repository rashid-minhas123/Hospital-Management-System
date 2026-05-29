class BaseClass{
public String name;
protected int id;
BaseClass(String name,int id){
    this.name=name;
    this.id=id;
}
 public void displayInfo(){
    System.out.println("Name:"+name);
    System.out.println("Id :"+id);
}
 public static void main(String[] args) {
        derivedClass emp = new derivedClass("Rashid Minhas", 013, "Software Engineering");
        emp.showDetails();
    }
}
class derivedClass extends BaseClass{
private String Department;
derivedClass(String name, int id,String Department){
    super(name,id);
    this.Department=Department;
}
 public void showDetails(){
     displayInfo();
     System.out.print("Department:"+Department);
 }
}

