class student1{
    private String name;
    private String ID;
    private int age;
    private double GPA;

    student1(String name,String ID,int age,double GPA){
        this.name=name;
        this.ID=ID;
        this.age=age;
        this.GPA=GPA;
    }
    student1( String ID){
        this.ID=ID;
        
    }
    student1(String name,String ID){
        this.name=name;
        this.ID=ID;
    }
    void display(){
        System.out.println("Student Name:"+name);
        System.out.println("Student ID:"+ID);
        System.out.println("Student Age:"+age);
        System.out.println("Student GPA:"+GPA);
    }
    void display(String ID){
        System.out.println("Student ID:"+ID);
    }
    void display(String name,String ID){
        System.out.println("Student Name:"+name);
        System.out.println("Student ID:"+ID);
    }
    public static void main(String[] args){
        student1 s1=new student1("Rashid Minhas","F25608013",18,3.333);
        student1 s2=new student1("F25608005");
        student1 s3=new student1("Faheem","F25608024");
        
        s1.display();
        System.out.println("========================");
        s2.display("F25608013");
        System.out.println("========================");
        s3.display("Faheem","F25608024");
        
    }
}