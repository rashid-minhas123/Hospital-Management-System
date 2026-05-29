import java.util.Scanner;

class bank{
    private String accountNo;
    private double balance;
        bank(){ 
             this.accountNo="N/A";
             this.balance=0;
            }
         
        bank(String accountNo){
            this.accountNo = accountNo;
            this.balance=0;
            } 
            
        bank(String accountNo,double balance){
            this.accountNo=accountNo;
            this.balance=balance;
        }
        public String getAccountNumber(){
            return accountNo;
        }
        
        public void deposit(double amount){
        if(amount<0){
        System.out.println("You Entered an invalid amount.Try Agai!!");
        return;
    }
    balance+=amount;
    System.out.println("Your Deposit amount:"+amount);
    System.out.println("Your Current Balance:"+balance);
    }
    
    public void withdraw(double amount){
    if(amount>balance){
    System.out.println("Your entered invalid amount.try again by putting less values");
    return;
}
    balance-=amount;
    System.out.println("Your withdrawal amount;"+amount);
    System.out.println("Your current balance is:"+balance);
    }
    
    public void Balance(){
    System.out.print("Your Account Number is: "+accountNo);    
    System.out.print("Your Balance is:"+balance);
}
public static void main(String[] args){
    bank account1 = new bank();
        System.out.println("  [Initial Balance]");
        account1.Balance();
        System.out.println("  [Depositing 500.00]");
        account1.deposit(500.00);
        System.out.println("  [Withdrawing 200.00]");
        account1.withdraw(200.00);
        System.out.println("  [Final Balance]");
        account1.Balance();

        System.out.println("-----------------------------------------------");
        bank account2 = new bank("ACC-1001");
        System.out.println("  [Initial Balance]");
        account2.Balance();
        System.out.println("  [Depositing 1500.00]");
        account2.deposit(1500.00);
        System.out.println("  [Withdrawing 2000.00 - exceeds balance]");
        account2.withdraw(2000.00);
        System.out.println("  [Withdrawing 500.00]");
        account2.withdraw(500.00);
        System.out.println("  [Final Balance]");
        account2.Balance();
 
       System.out.println("--------------------------------------------------");
        
        bank account3 = new bank("ACC-2002", 3000.00);
        System.out.println("  [Initial Balance]");
        account3.Balance();
        System.out.println("  [Depositing 1000.00]");
        account3.deposit(1000.00);
        System.out.println("  [Withdrawing 750.00]");
        account3.withdraw(750.00);
        System.out.println("  [Withdrawing 5000.00 - exceeds balance]");
        account3.withdraw(5000.00);
        System.out.println("  [Final Balance]");
        account3.Balance();

       System.out.print("-------------------------------------------------");
    }
}
