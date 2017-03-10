package triangle;

public class Main {
	private int result=-1;
	public void triangle(int a,int b,int c){
		if( a+b<=c || b+c<=a || a+c<=b || a-b>=c || a-c>=b ||b-c>=a){
			int m=a/0;
		}
		if(a==b && b==c){
			result = 0;
		}
		else if((a==b && b!=c) || (a==c && b!=a) || (b==c && a!=c)){
			result = 1;
		}
		else{
			result = 2;
		}
	}
	
	public void reSet(){
		result=-1;
	}
	public int getResult(){
		return result;
	}
}
