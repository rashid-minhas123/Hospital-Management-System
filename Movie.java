class Movie{
String title;
int year;
double rating;
 
Movie(){
    title="Untitled";
    year=2000;
    rating=0.0;
}
Movie(String title,int year){
this.title=title;
this.year=year;
    rating=5.0;
}
Movie(String title,int year,double rating){
this.title=title;
this.year=year;
this.rating=rating;
}
void display(){
    System.out.println("Title= "+title);
    System.out.println("Year= "+year);
    System.out.println("Rating= "+rating);
}
public static void main(String[] args){
    Movie m1=new Movie();
    Movie m2=new Movie("Teefa in Trouble",2018);
    Movie m3=new Movie("Smile",2012,4.5);
    System.out.println("---------------------------");
    m1.display();
    System.out.println("----------------------------");
    m2.display();
    System.out.println("----------------------------");
    m3.display();
    
    
}
}