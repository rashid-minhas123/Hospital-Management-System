import java.util.Scanner;
public class TemperatureConverter{
    private float temperature;
    TemperatureConverter(float value){
    this.temperature=value;
}
    public void Ferenheit(float temperature ){
     double celsius = (temperature - 32) * 5 / 9;
     System.out.print("Tempetaure in celcius is:"+celsius);
    }
    public void Celcius(float temperature){
        double fahrenheit = (temperature * 9 / 5) + 32;
        System.out.print("Temperature in Ferenheit is:"+fahrenheit);

    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("\n===================================");
        System.out.println("Press 1 for Ferenheit to Celcius");
        System.out.println("Press 2 for Celcius to Ferhenheit");
        System.out.println("Enter your choice 1 or 2");
        int choice=sc.nextInt();
        TemperatureConverter t1=new TemperatureConverter(0);
        if(choice==1){
        System.out.println("Enter the temperatue in Ferenheit:");
        float fa=sc.nextFloat();
        t1.Ferenheit(fa);
    } 
    else if(choice==2){
        System.out.println("Enter the temperature in Celcius:");
        float cs=sc.nextFloat();
        t1.Celcius(cs);
    }
    else {
    System.out.print("You Entered Invalid Input!");
}
}
}