package project2_CS260_montana_singer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Scanner;


/**
 * This class represents the way to interact with the tree.
 * It also saves and loads the tree in a file.
 * @author Montana Singer
 */
public class DatabaseAccess implements Serializable {
	
	public static BinaryTree<Dog> tree = new BinaryTree<>();
	
	/** Default constructor */
	public DatabaseAccess() {}
	
	/**
	 * Loads the tree from a file.
	 * @throws FileNotFoundException
	 */
	public static void loadDatabaseFromFile() throws FileNotFoundException
	{
		Scanner scanner = new Scanner(new File("BSTData.txt"));
		scanner.useDelimiter(",");
		
		while (scanner.hasNext())
		{
			System.out.println(scanner.next() + "\n");
		}
		
		scanner.close();
	}
	
	
	/**
	 * Saves the tree to a file.
	 * @throws FileNotFoundException
	 */
	public static void saveDatabaseToFile() throws FileNotFoundException
	{
		PrintWriter out = new PrintWriter("BSTData.txt");
		out.println(tree.toString());
		out.close();
	}
	
	
	/**
	 * Adds a node to the tree, the node consists of a Dog object
	 * @param e Dog object with data to be added to the tree.
	 */
	public void addEntry(Dog e)
	{
		BinaryTreeNode<Dog> node = new BinaryTreeNode<Dog>(e);
		tree.addNode(node);
	}
	
	
	/**
	 * Removes a node from the tree, the node consists of a Dog object.
	 * @param e Dog object with data to be removed from the tree.
	 */
	public void removeEntry(Dog e)
	{
		BinaryTreeNode<Dog> node = new BinaryTreeNode<Dog>(e);
		tree.removeNodeWithData(node.getData());
	}
	
	
	/**
	 * Finds a node within the tree, the node consists of a Dog object.
	 * @param e Dog object with data to be searched for in the tree.
	 */
	public void findEntry(Dog e)
	{
		BinaryTreeNode<Dog> node = tree.findNodeWithData(e);
		System.out.println(node.getData().toString());
	}
	
	
	/**
	 * Main method to test the functions of the binary search tree.
	 * @param args Commands to test the methods
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		DatabaseAccess database = new DatabaseAccess();
		
		System.out.println("Loading database");
		DatabaseAccess.loadDatabaseFromFile();
		
		Dog root6 = new Dog(6);
		Dog dog1 = new Dog(1);
		Dog dog2 = new Dog(2);
		Dog dog3 = new Dog(3);
		Dog dog4 = new Dog(4);
		Dog dog5 = new Dog(5);
		Dog dog6 = new Dog(6);
		Dog dog7 = new Dog(7);
		Dog dog8 = new Dog(8);
		Dog dog9 = new Dog(9);
		Dog dog10 = new Dog(10);
		
		database.addEntry(root6); 
		database.addEntry(dog4);
		database.addEntry(dog5);
		database.addEntry(dog3);
		database.addEntry(dog2);
		database.addEntry(dog7);
		database.addEntry(dog8);

		System.out.println(tree.toString());
		System.out.println("\nTop down view of the tree (by level)");
		tree.printTreeDiagram();
		
		System.out.println("\nRemove 2");
		database.removeEntry(dog2);
		
		System.out.println(tree.toString());
		System.out.println("\nTop down view of the tree (by level)");
		tree.printTreeDiagram();
		
		System.out.println("\nRemove 7");
		database.removeEntry(dog7);
		
		System.out.println(tree.toString());
		System.out.println("\nTop down view of the tree (by level)");
		tree.printTreeDiagram();
		
		System.out.println("\nRemove 4");
		database.removeEntry(dog4);
		
		System.out.println(tree.toString());
		System.out.println("\nTop down view of the tree (by level)");
		tree.printTreeDiagram();
		
		System.out.println("\nSearching for 3");
		database.findEntry(dog3);
		
		System.out.println("\nSearching for 8");
		database.findEntry(dog8);
		
		DatabaseAccess.saveDatabaseToFile();
		System.exit(0);
			
	} //End main method.
	
} //End DatabaseAccess class.
