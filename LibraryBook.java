public class LibraryBook{
    
private String bookTitle;
private String author;
private int ISBN;
private static int count=0;
LibraryBook(String book,String author,int isbn){
    this.bookTitle=book;
    this.author=author;
    this.ISBN=isbn;
}

public void displaydetail(){
    System.out.println("bookTitle:"+bookTitle);
    System.out.println("Author:"+author);
    System.out.println("ISBN:"+ISBN);
    System.out.println("--------------------------");
    count++;
}
public static void showtotal(){
System.out.println("Total Books:"+count);
}
public static void main(String[] args){
LibraryBook l1=new LibraryBook("Prisioners of Geography","Tom Marshall",12345);
LibraryBook l2=new LibraryBook("Physics","Raheem",123456);
LibraryBook l3=new LibraryBook("urdu","Professor Rana",654321);
l1.displaydetail();
l2.displaydetail();
l3.displaydetail();
LibraryBook.showtotal();
}
}