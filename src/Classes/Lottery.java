package Classes;

public class Lottery {

	private static int num1,num2,num3,num4,num5,powerball;
	
	public static void main(String[] args) {
		
		for(int i = 0; i <10000; i++) {
			
			num1 += (int)Math.random() * 61;
			num2 += (int)Math.random() * 61;
			num3 += (int)Math.random() * 61;
			num4 += (int)Math.random() * 61;
			num5 += (int)Math.random() * 61;
			powerball += (int)Math.random() * 6;
			
		}
		
		num1 = num1/10000;
		num2 = num1/10000;
		num3 = num1/10000;
		num4 = num1/10000;
		num5 = num1/10000;
		
		System.out.println(num1 + " " + num2 + " " + num3 + " " + num4 + " " +num5 );

	}

}
