package lab01;
import java.util.Scanner; 

/*
 * Montana Singer
 * CS 160-06 Fall 2018
 * 
 * Lab 1
 * 
 */

public class NumericTypes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		byte byteNumber = 127;
		short shortNumber = 127;
		int intNumber = 127;
		long longNumber = 127;
		float floatNumber = 127;
		double doubleNumber = 127;
		
		//Problem 2A
		String output = "\n\tbyteNumber = " + byteNumber +
				"\n\tshortNumber = " + shortNumber +
				"\n\tintNumber = " + intNumber +
				"\n\tlongNumber = " + longNumber +
				"\n\tfloatNumber = " + floatNumber +
				"\n\tdoubleNumber = " + doubleNumber;
		System.out.println("For problem 2(A): " + output);
		
		//Problem 2B
		Scanner keyboardInput = new Scanner(System.in);
			System.out.println("Enter the same integer 127 six times: ");
			byteNumber = keyboardInput.nextByte();
			shortNumber = keyboardInput.nextShort();
			intNumber = keyboardInput.nextInt();
			longNumber = keyboardInput.nextLong();
			floatNumber = keyboardInput.nextFloat();
			doubleNumber = keyboardInput.nextDouble();
		System.out.println("For problem 2(B): " + output);
		
		/*The differences between A and B is that A displays the values of the variables immediately,
		*whereas B requires the user to input 127 six times in order to display the text of the assigned variables
		*/
		
		//Problem 3 is the same as 2A and 2B
		
		//Problem 4
		byteNumber = (byte)128;
		byteNumber = -128;
		byteNumber = (byte)-129;
		byteNumber = (byte)32767;
		byteNumber = (byte)32768;
		byteNumber = (byte)-32768;
		byteNumber = (byte)-32769;
		byteNumber = (byte)2147483647;
//		byteNumber = (byte)2147483648;
		System.out.println("byteNumber is " + byteNumber);
//A byte cannot display the value 2147483648 because it is out of int range
		
		shortNumber = 128;
		shortNumber = -128;
		shortNumber = -129;
		shortNumber = 32767;
		shortNumber = (short)32768;
		shortNumber = (short)-32768;
		shortNumber = (short)-32769;
		shortNumber = (short)2147483647;
//		shortNumber = (short)2147483648;
		System.out.println("shortNumber is " + shortNumber);
//A short cannot display the value 2147483648 because it is out of int range
		
		intNumber = 128;
		intNumber = -128;
		intNumber = -129;
		intNumber = 32767;
		intNumber = 32768;
		intNumber = -32768;
		intNumber = -32769;
		intNumber = 2147483647;
//		intNumber = 2147483648;
		System.out.println("intNumber is " + intNumber);
//An int cannot display the value 2147483648 because it is out of int range
		
		longNumber = 128;
		longNumber = -128;
		longNumber = -129;
		longNumber = 32767;
		longNumber = 32768;
		longNumber = -32768;
		longNumber = -32769;
		longNumber = 2147483647;
		longNumber = 2147483648L;
		System.out.println("longNumber is " + longNumber);
		
		floatNumber = 128;
		floatNumber = -128;
		floatNumber = 129;
		floatNumber = 32767;
		floatNumber = 32768;
		floatNumber = -32768;
		floatNumber = -32769;
		floatNumber = 2147483647;
		floatNumber = 2147483648F;
		System.out.println("longNumber is " + floatNumber);
		
		doubleNumber = 128;
		doubleNumber = -128;
		doubleNumber = 32767;
		doubleNumber = 32768;
		doubleNumber = -32768;
		doubleNumber = -32769;
		doubleNumber = -32769;
		doubleNumber = 2147483647;
		doubleNumber = 2147483648D;
		System.out.println("doubleNumber is " + doubleNumber);
		
	Scanner keyboardInput2 = new Scanner(System.in);
		System.out.println("Enter the values (a-i) in order for byte");
		byteNumber = (byte) keyboardInput2.nextShort();
		byteNumber = keyboardInput2.nextByte();
		byteNumber = (byte) keyboardInput2.nextShort();
		byteNumber = (byte) keyboardInput2.nextShort();
		byteNumber = (byte) keyboardInput2.nextInt();
		byteNumber = (byte) keyboardInput2.nextShort();
		byteNumber = (byte) keyboardInput2.nextInt();
		byteNumber = (byte) keyboardInput2.nextInt();
		byteNumber = (byte) keyboardInput2.nextDouble();
	System.out.println("byteNumber is " + byteNumber);
	
	Scanner keyboardInput3 = new Scanner(System.in);
	System.out.println("Enter the values (a-i) in order for short");
		shortNumber = keyboardInput3.nextShort();
		shortNumber = keyboardInput3.nextShort();
		shortNumber = keyboardInput3.nextShort();
		shortNumber = keyboardInput3.nextShort();
		shortNumber = (short) keyboardInput3.nextInt();
		shortNumber = (short) keyboardInput3.nextInt();
		shortNumber = (short) keyboardInput3.nextInt();
		shortNumber = (short) keyboardInput3.nextInt();
		shortNumber = (short) keyboardInput3.nextDouble();
	System.out.println("shortNumber is " + shortNumber);
		
	Scanner keyboardInput4 = new Scanner(System.in);
	System.out.println("Enter the values (a-i) in order for int");
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = keyboardInput4.nextInt();
		intNumber = (int) keyboardInput4.nextDouble();
	System.out.println("intNumber is " + intNumber);

	Scanner keyboardInput5 = new Scanner(System.in);
	System.out.println("Enter the values (a-i) in order for long");
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();
		longNumber = keyboardInput5.nextLong();	
	System.out.println("longNumber is " + longNumber);
		
	Scanner keyboardInput6 = new Scanner(System.in);
	System.out.println("Enter the values (a-i) in order for float");
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = keyboardInput6.nextFloat();
		floatNumber = (float) keyboardInput6.nextDouble();
	System.out.println("floatNumber is " + floatNumber);
		
	Scanner keyboardInput7 = new Scanner(System.in);
	System.out.println("Enter the values (a-i) in order for double");
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
		doubleNumber = keyboardInput7.nextDouble();
	System.out.println("doubleNumber is " + doubleNumber);
		
	//Problem 5
	floatNumber = 6;
	floatNumber = (float) 6.79;
	//When assigning floatNumber the value 6.79 there was a mismatch error
	//This is solved by adding a cast to the re-assignment, which I did
	
	//Problem 6
	intNumber = 15;
	doubleNumber = 6;
	
	double ratio;
	ratio = intNumber/6;
	System.out.println(ratio);
	
	ratio = intNumber/doubleNumber;
	System.out.println(ratio);
	//The first printing of the output using "6" caused 2.0 to be displayed because 6 was a integer.
	//The second printing of the output using 6 although declared as a double displayed 2.5 because 
	//a double is able to display to the tenth's place.
	
	//Problem 7
	System.out.println('a' + 'b');
	System.out.println(" " + 'a' + 'b');
	System.out.println(" " +('a' + 'b'));
	//The first and third lines caused 195 to be displayed whereas the second line displayed ab together
	
	//Problem 8
	System.out.println("These are numbers: " + intNumber + doubleNumber);
	System.out.println("These are numbers: " + (intNumber + doubleNumber));
	//The first line displayed the 2 separate numbers right next to each other
	//whereas the second line actually displayed the sum of the 2 numbers
	
	//Problem 9 
	System.out.println("Formula1: " + (25 +5/intNumber*doubleNumber + 1));
	System.out.println("Formula2: " + ((25+5)/intNumber*(doubleNumber + 1)));
	//Parentheses affect what the computation is; order of operations
	
	}

}
