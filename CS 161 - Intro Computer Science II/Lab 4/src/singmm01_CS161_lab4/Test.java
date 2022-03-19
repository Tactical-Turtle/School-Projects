package singmm01_CS161_lab4;


/*
* Montana Singer
* CS161
* Spring 2019
* Lab 4
*
*/

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Testing Person Class
		Person mama = new Person("Leona", 1982);
		Person papa = new Person("Leo", 1980);
		Person ditto = new Person("Leo", 1980);
		
		System.out.println("TESTING THE Person CLASS\n");
		System.out.println("Data of papa:\n" + papa.toString());
		
		System.out.println("\nTesting papa equals mama???\n" + papa.equals(mama));
		
		System.out.println("\nTesting papa equals ditto???\n" + papa.equals(ditto));
		
		System.out.println("\nTesting papa == ditto???\n" + (papa == ditto));
		
		
		//Testing Employee Class
		Person mailman = new Employee(238, 13.2);
		
		System.out.println("\nTESTING the Employee CLASS:");
		
		System.out.println("Print mailman's name: " + mailman.getName());
		System.out.println("\nCommented out the Person default constructor and and the error is: " + "\n" +
						   "Implicit super constructor Person() is undefined. Must explicitly invoke another constructor");
		
		mailman.setName("Martin");
		mailman.setYearOfBirth(1990);
		
		System.out.println("\nTesting toString( ) for mailman\nmailman data:");
		System.out.println(mailman.toString());
		
		System.out.println("\nTesting instanceof for mailman");
		
			if (mailman instanceof Person)
			{
				System.out.println("Person: true");
			}
			else
			{
				System.out.println("Person: false");
			}
			
			if (mailman instanceof Employee)
			{
				System.out.println("Employee: true");
			}
			else
			{
				System.out.println("Employee: false");
			}
		
		System.out.println("\nTesting instanceof for mailman with getClass()\n" + 
							mailman.getClass());
		System.out.println("\nTesting instanceof for mailman with getClass().getname()\n" + 
							mailman.getClass().getName());
		
//		mailman.earnings();
		System.out.println("\nMailman calling the earnings() method created this error:\n" +
						   "The method earnings() is undefined for the type Person");
		
		System.out.println("\nAfter type cast mailman calls earnings: " + ((Employee) mailman).earnings());
		
		System.out.println("\nMailman calls displayAccess()");
		mailman.displayAccess();
		
		System.out.println("\nAfter commenting out the displayAcces() method in the Person class, the error message was as follows:\n" + 
						   "The method displayAccess() is undefined for the type Person\n" + 
						   "This is the same message that calling the earnings() method above yielded\n");
		
		System.out.println("Mailman calls displayAccess() after it is commented out in the Employee class");
		mailman.displayAccess();
		
		
		Employee supervisor = new Employee(238, 13.2);
		
		System.out.println("\nequality of mailman and supervisor\n" + 
							supervisor.equals(mailman));
		
		System.out.println("\nsupervisor calls earnings: " + supervisor.earnings());
		System.out.println("The call works because supervisor is an object of the employee class");
		
		
		
		
		

	} //End main method

} //End Test Class


/*
 * a) code where polymorphic types apply
 * 
 * The first instance of mailmain calling displayAccess() line 79 is an example
 * of when polymorphic types apply
 * 
 * b) method calls where dynamic binding applies
 * 
 * toString method as it is overridden and called by mailman
 * 
 * 
 */
