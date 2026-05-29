class Book{
String title,author;
int price;
Book(String title,String author,int price){
    this.title=title;
    this.author=author;
    this.price=price;
}
public void display(){
System.out.println("Title="+title);
System.out.println("Author="+author);
System.out.println("Price="+price);
}
public static void main(String[] args){
    Book b1=new Book("Prisoner of Geography","Tim Marshal",290);
    b1.display();
}


}