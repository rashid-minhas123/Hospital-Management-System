class Employee{
    private String name;
    private float salary;
    private float hour_of_work;
   
    public Employee(String name,float salary,float hour_of_work){
        this.name=name;
        this.salary=salary;
        this.hour_of_work=hour_of_work;
    }
    public void showinfo(){
        System.out.println("Name="+name);
        System.out.println("Salary="+salary);
        System.out.println("hour_of_work="+hour_of_work);
        System.out.println("---------------------");
        
        
    }
    public void addtosalary(){
        if(hour_of_work>8){
            salary+=salary*0.20;
        }
         }
    public void deductfromsalary(){
        if(hour_of_work<5){
            salary-=salary*0.05;
        }
    }
    public static void main(String[] args){
        Employee e1=new Employee("Rashid Minhas",20000,8);
        Employee e2=new Employee("Muhammad Baqir",11000,4);
        Employee e3=new Employee("Chaudary shakoor",15000,16);
        e1.addtosalary();
        e1.deductfromsalary();
        
         e2.addtosalary();
        e2.deductfromsalary();
        
         e3.addtosalary();
        e3.deductfromsalary();
        
        e1.showinfo();
        e2.showinfo();
        e3.showinfo();
        
        
    }    
}