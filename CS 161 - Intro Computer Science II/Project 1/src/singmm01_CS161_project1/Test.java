package singmm01_CS161_project1;

import java.util.ArrayList;

/*
* Montana Singer
* CS161 - 01
* Spring 2019
* Project 1
*
*/

//The Test class tests the methods listed in the FlexibleString class
//to ensure they work properly
public class Test {

	//Main method to test FlexibleString methods
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Testing the Character ArrayList constructor
		ArrayList<Character> testCharacterList = new ArrayList<Character>();
		testCharacterList.add('e');
		testCharacterList.add('d');
		testCharacterList.add('c');
		
		System.out.println("Testing FlexibleString(ArrayList<Character>)");
		FlexibleString testA = new FlexibleString(testCharacterList);
		System.out.println(testA.toString());
		System.out.println("Size of field: " + testA.list.size());
		
		
		
		//Testing the String constructor
		System.out.println("\nTesting FlexibleString(String)");
		String testString = "Testing";
		FlexibleString testB = new FlexibleString(testString);
		System.out.println(testB.toString());
		System.out.println("Size of field: " + testB.list.size());


		
		//Testing the char[] constructor
		System.out.println("\nTesting FlexibleString(char[])");
		char[] testCharArray = {'a', 'b', 'c', 'd', 'e'};
		FlexibleString testC = new FlexibleString(testCharArray);
		System.out.println(testC.toString());
		System.out.println("Size of field: " + testC.list.size());
		
		
		
		//Testing copy constructor
		System.out.println("\nTesting FlexibleString(FlexibleString)");
		FlexibleString testD = new FlexibleString(testA);
		System.out.println(testD.toString());
		System.out.println("Size of field: " + testD.list.size());
		
		
		
		//Testing int constructor
		System.out.println("\nTesting FlexibleString(int)");
		int testInt = 10;
		
		FlexibleString testE = new FlexibleString(testInt);
		System.out.println(testE.toString());
		System.out.println("Size of field: " + testE.list.size());
		
		
		
		//Testing toArray method
		System.out.println("\nTesting toArray()");
		testCharacterList.toArray();
		System.out.println(testCharacterList.toString());
		
		
		
		System.out.println("\nTesting equals()");
		System.out.println("testA = testB: " + testA.equals(testB));
		System.out.println("testA = testA: " + testA.equals(testA));
		
		
		
		//Testing the append group
		System.out.println("\nTesting the append group");
		FlexibleString testF = new FlexibleString(testString);
		testF.append('c');
		System.out.println("Append char to Testing:\n" + testF);
		
		FlexibleString testG = new FlexibleString(testString);
		testG.append(testCharArray);
		System.out.println("\nAppend char array to Testing:\n" + testG);
		
		FlexibleString testH = new FlexibleString(testString);
		testH.append(testA);
		System.out.println("\nAppend FlexibleString object to Testing:\n" + testH);
		
		FlexibleString testI = new FlexibleString(testString);
		testI.append(testString);
		System.out.println("\nAppend String to Testing:\n" + testI);
		
		
		
		//Testing the insert group
		System.out.println("\nTesting the insert group");
		FlexibleString testJ = new FlexibleString(testString);
		testJ.insert(2, testCharacterList);
		System.out.println("Insert ArrayList to Testing:\n" + testJ);
	
		FlexibleString testK = new FlexibleString(testString);
		testK.insert(1, 'Y');
		System.out.println("\nInsert char to Testing:\n" + testK);
		
		FlexibleString testL = new FlexibleString(testString);
		testL.insert(2, testCharArray);
		System.out.println("\nInsert char array to Testing:\n" + testL);
		
		FlexibleString testM = new FlexibleString(testString);
		testM.insert(2, testB);
		System.out.println("\nInsert FlexibleString to Testing:\n" + testM);
		
		FlexibleString testN = new FlexibleString(testString);
		testN.insert(2, testString);
		System.out.println("\nInsert String to Testing:\n" + testN);
		
		
		
		
		//Testing the delete group
		System.out.println("Testing the delete group");
		FlexibleString testO = new FlexibleString(testString);
		testO.delete(2, 4);
		System.out.println("\nDelete elements in list:\n" + testO);
		
		FlexibleString testP = new FlexibleString(testString);
		testP.deleteCharAt(3);
		System.out.println("\nDelete char at in list:\n" + testP);
		
		FlexibleString testQ = new FlexibleString("attbtttc");
		testQ.deleteAll(0, 2, 't');
		System.out.println("\nDelete deleteAll 't' in list:\n" + testQ);
		
		
		
		//Testing the replace group
		System.out.println("\nTesting the replace group");
		FlexibleString testR = new FlexibleString(testString);
		testR.replace(2, 'G');
		System.out.println("Replace char in Testing:\n" + testR);
		
		FlexibleString testS = new FlexibleString(testString);
		testS.replace(2, 4, testCharacterList);
		System.out.println("\nReplace char in Testing with ArrayList:\n" + testS);
		
		FlexibleString testT = new FlexibleString(testString);
		testT.replace(2, 5, testCharArray);
		System.out.println("\nReplace char in Testing with char array:\n" + testT);
		
		FlexibleString testU = new FlexibleString(testString);
		testU.replace(2, 4, testB);
		System.out.println("\nReplace char in Testing with FlexibleString object:\n" + testU);
		
		FlexibleString testV = new FlexibleString(testString);
		testV.replace(2, 4, testString);
		System.out.println("\nReplace char in Testing with String:\n" + testV);
		FlexibleString testW = new FlexibleString(testString);
		testW.replace(10, 90, testString);
		System.out.println("Replace char in Testing with String: (append logic test)\n" + testW);
		
		
		
		//Testing the String group
		System.out.println("\nTesting the String group");
		FlexibleString testX = new FlexibleString(testString);
		System.out.println("charAt index 3 in Testing is: " + testX.charAt(3));
		System.out.println("charAt index 50 in Testing is: " + testX.charAt(50));
		
		FlexibleString testY = new FlexibleString("test");
		System.out.println("\nindexOf t in 'Testing' is:\n" + testY.indexOf("e"));
		
		FlexibleString testZ = new FlexibleString("testing");
		System.out.println("\nindexOf est in 'Testing' is:\n" + testZ.indexOf("testing"));
		
		System.out.println("\nEnd testing");
		
	} //End main method

} //End Test class


/*
 * 
 * Testing FlexibleString(ArrayList<Character>)
 *	edc
 *	Size of field: 3
 *
 *	Testing FlexibleString(String)
 *	Testing
 *	Size of field: 7
 *
 *	Testing FlexibleString(char[])
 *	abcde
 *	Size of field: 5
 *
 *	Testing FlexibleString(FlexibleString)
 *	edcedc
 *	Size of field: 6
 *
 *	Testing FlexibleString(int)
 *
 *	Size of field: 10
 *
 *	Testing toArray()
 *	[e, d, c, e, d, c]
 *
 *	Testing equals()
 *	testA = testB: false
 *	testA = testA: true
 *
 *	Testing the append group
 *	Append char to Testing:
 *	Testingc
 *
 *	Append char array to Testing:
 *	Testingabcde
 *
 *	Append FlexibleString object to Testing:
 *	Testingedcedc
 *
 *	Append String to Testing:
 *	TestingTesting
 *
 *	Testing the insert group
 *	Insert ArrayList to Testing:
 *	Teedcedcsting
 *
 *	Insert char to Testing:
 *	TYesting
 *
 *	Insert char array to Testing:
 *	Teabcdesting
 *
 *	Insert FlexibleString to Testing:
 *	TeTestingTestingsting
 *
 *	Insert String to Testing:
 *	TeTestingsting
 *	Testing the delete group
 *
 *	Delete elements in list:
 *	Teng
 *
 *	Delete char at in list:
 *	Tesing
 *
 *	Delete deleteAll 't' in list:
 *	abtttc
 *
 *	Testing the replace group
 *	Replace char in Testing:
 *	TeGting
 *
 *	Replace char in Testing with ArrayList:
 *	Teedcedcng
 *
 *	Replace char in Testing with char array:
 *	Teabcdeg
 *
 *	Replace char in Testing with FlexibleString object:
 *	TeTestingTestingTestingTestingng
 *
 *	Replace char in Testing with String:
 *	TeTestingng
 *	Replace char in Testing with String: (append logic test)
 *	TestingTesting
 *
 *	Testing the String group
 *	charAt index 3 in Testing is: t
 *	charAt index 50 in Testing is: 0
 *
 *	indexOf t in 'Testing' is:
 *	3
 *
 *	indexOf est in 'Testing' is:
 *	-1
 *
 *	End testing
 * 
 * 
*/