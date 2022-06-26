package javaTester;

import java.util.Random;
public class Topic_03_Random {
	
	public static void main(String[] args) {
       Random rand = new Random();
	   System.out.println(rand.nextInt(99999));
	   System.out.println(rand.nextInt(99999));
	   System.out.println(rand.nextInt(99999));
	   System.out.println(rand.nextInt(99999));	
	   System.out.println(rand.nextInt(99999));
	   System.out.println(rand.nextInt(99999));
	   System.out.println(rand.nextInt(99999));
	   System.out.println(rand.nextInt(99999));
	
	}  
	
}

//ham random

   public int generateRandomNumber() {
	Random rand = new Random();
	return rand.nextInt(99999);
}