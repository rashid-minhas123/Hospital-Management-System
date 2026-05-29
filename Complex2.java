class Complex2{
    int real;
    int imag;
    
    Complex2(int real,int imag){
        this.real=real;
        this.imag=imag;
    }
    Complex2(){
        real=0;
        imag=0;
    }
    int  add(int l,int m){
     return l+m;
    }
    int add(Complex2 c){
     int newreal=real+c.real;
     int newimag=imag+c.imag;
     return new Complex2(newreal,newimag);
    }
    void display(){
        System.out.println("The results are:"+real+"+"+imag+"i");
    }
    public static void main(String[] args){
    Complex2 c1=new Complex2(4,5);
    Complex2 c2=new Complex2();
    Complex2 sum=c1.add(c2);
    sum.display();
}
}