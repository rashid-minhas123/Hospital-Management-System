import java.util.Scanner;
class Account{
    private String name;
    private float balance;
    
    public Account( String name, float balance){
        this.name=name;
        this.balance=balance;
    }
    public void displayinfo(){
    System.out.println("Account Holder name="+name); 
    System.out.println(" Current Balance="+balance);    
    }
    public void deposit(float amount){
        if(amount>0){ balance+=amount;
        System.out.println("Deposit successfully.");
    }
    else{
        System.out.println("Invalid Amount.");
    }
        System.out.println("----------------------------------");
    }
        public void withdrawal(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println(amount + " withdrawn successfully.");
            System.out.println("Remaining Balance: " + balance);
        } else {
            System.out.println("Insufficient funds! Withdrawal failed.");
        }
        System.out.println("----------------------------");
    }


    
    public static void main(String[] args){
        Scanner input=new Scanner(System.in);
        System.out.print("Enter your Holder name:");
        String name=input.nextLine();
        
        System.out.print("Enter Initial Balance:");
        float balance=input.nextFloat();
        
        Account a1= new Account(name,balance);
        a1.displayinfo();
        System.out.print("Enter your deposit amount:");
        int add=input.nextInt();
        a1.deposit(add);
        System.out.print("Enter your widrawal amount.");
        int widraw=input.nextInt();
        a1.withdrawal(widraw);
        input.close();
        }
}
