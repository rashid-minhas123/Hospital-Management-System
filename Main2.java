class Mobile{
    String Brand;
    String Model;
    double price;
    
    Mobile(){
    Brand="unknown";
    Model="unknown";
    price=0.0;
    }
    Mobile(String Brand,String Model,double price){
    this.Brand=Brand;
    this.Model=Model;
    this.price=price;
    }
    void display(){
    System.out.println("Brand="+Brand);
    System.out.println("Model="+Model);
    System.out.println("price="+price);
    }
    }
    class BankAccount{
    int accountNumber;
    double balance;
    String ownerName;

    BankAccount(){
    accountNumber=0;
    balance=0.0;
    ownerName="Unverified";
    }
    BankAccount(int accountNumber,double balance,String ownerName){
    this.accountNumber=accountNumber;
    this.balance=balance;
    this.ownerName=ownerName;
    }
    void display(){ 
    System.out.println("Account Number="+accountNumber);
    System.out.println("Balance="+balance);
    System.out.println("Owner Name="+ownerName);
    }
    }

    public class Main2{
    public static void main(String[] args){
    Mobile m1=new Mobile();
    Mobile m2=new Mobile("Samsung","S24 Ultra",400000);
    m1.display();
    System.out.println("------------------------------");
    m2.display();
    System.out.println("-------------------------------");
    BankAccount ba1=new BankAccount();
    BankAccount ba2=new BankAccount(1234556,500000,"Rashid Minhas");
    ba1.display();
    System.out.println("-----------------------");
    ba2.display();
    }

    }

