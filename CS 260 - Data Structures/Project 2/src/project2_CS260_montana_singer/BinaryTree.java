package project2_CS260_montana_singer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * This class represents the actual binary tree, and is generic.
 * The tree is made up of BinaryTreeNodes, which contain Dog objects.
 * @author Montana Singer
 * @param <E> Generic data that the tree can receive.
 */
public class BinaryTree<E extends Comparable<E>> implements Serializable {

	BinaryTreeNode<E> root;
	DatabaseAccess database;
	
	
	/** Default constructor. */
	public BinaryTree() 
	{
		root = null;
	}
	
	
	/**
	 * Constructor that initializes a binary search tree with
	 * data that will be the root of the tree.
	 * @param data
	 */
	public BinaryTree(E data)
	{
		root = new BinaryTreeNode<E>(data);
	}
	
	
	/**
	 * Adds a node to the tree.
	 * @param node The node to be added to the tree.
	 */
	public void addNode(BinaryTreeNode<E> node)
	{
		if (root == null)
		{
			root = node;
			return;
		}
		
		BinaryTreeNode<E> current = root;
		BinaryTreeNode<E> parent = null;
		
		while(true)
		{
			parent = current;
			
			//If current is > the node to be added, go left.
			if (current.getData().compareTo(node.getData()) > 0)
			{
				current = current.getLeftChild();
				
				if (current == null)
				{
					parent.setLeftChild(node);
					return;
				}
			
			}
			
			//Else current is < the node to be added, go right
			else
			{
				current = current.getRightChild();
				
				if (current == null)
				{
					parent.setRightChild(node);
					return;
				}
			}
			
		} //End while loop.
		
	}
	
	
	/**
	 * Removes a node with the given data from the tree.
	 * @param data The data contained in the node to be removed.
	 */
	public void removeNodeWithData(E data)
	{
		BinaryTreeNode<E> parent = root;
		BinaryTreeNode<E> current = root;
		boolean isLeftChild = false;
		
		while (current.getData() != data)
		{
			parent = current;
			
			//Current node is greater than data to be deleted.
			if (current.getData().compareTo(data) > 0)
			{
				isLeftChild = true;
				current = current.getLeftChild();
			}
			
			//Current node is less than data to be deleted.
			else
			{
				isLeftChild = false;
				current = current.getRightChild();
			}
			
			if (current == null)
			{
				return;
			}
			
		} //End while loop.
		
		//Case 1: if node to be deleted has no children.
		if (current.getLeftChild() == null && current.getRightChild() == null)
		{
			if (current == root)
			{
				root = null;
			}
			
			if (isLeftChild ==true)
			{
				parent.setLeftChild(null); 
			}
			
			else
			{
				parent.setRightChild(null);
			}
		}
		
		//Case 2: The node to be deleted has only one child (left case).
		else if (current.getRightChild() == null)
		{
			if (current == root)
			{
				root = current.getLeftChild();
			}
			
			else if (isLeftChild)
			{
				parent.setLeftChild(current.getLeftChild());
			}
			
			else
			{
				parent.setRightChild(current.getLeftChild());
			}
		}
		
		//Case 2 : The node to be deleted has only one child (right case).
		else if (current.getLeftChild() == null)
		{
			if (current == root)
			{
				root = current.getRightChild();
			}
			
			else if (isLeftChild)
			{
				parent.setLeftChild(current.getRightChild());
			}
			
			else
			{
				parent.setRightChild(current.getRightChild());
			}
			
		}
		
		//Case 3: Node to be deleted has 2 children.
		else if (current.getLeftChild() != null && current.getRightChild() != null)
		{
			//Minimum element in the right sub tree.
			BinaryTreeNode<E> successor	= getSuccessor(current);
			
			if (current==root)
			{
				root = successor;
			}
			
			else if (isLeftChild)
			{
				parent.setLeftChild(successor);
			}
			
			else
			{
				parent.setRightChild(successor);
			}	
			
			successor.setLeftChild(current.getLeftChild());
		}			
		
	}
	

	/**
	 * Helper method for the remove method.
	 * @param deleteNode Node to be deleted.
	 * @return Successor node for the node to be deleted.
	 */
	public BinaryTreeNode<E> getSuccessor(BinaryTreeNode<E> deleteNode)
	{
		BinaryTreeNode<E> successor = null;
		BinaryTreeNode<E> successorParent = null;
		BinaryTreeNode<E> current = deleteNode.getRightChild();
		
		while (current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.getLeftChild();
		}

		if (successor != deleteNode.getRightChild())
		{
			successorParent.setLeftChild(successor.getRightChild());
			successor.setRightChild(deleteNode.getRightChild());
		}
		
		return successor;
	}
	
	
	/**
	 * Searches for a node with given data.
	 * @param targetData Data that is contained in the node being searched for.
	 * @return BinaryTreeNode that was being searched for.
	 */
	public BinaryTreeNode<E> findNodeWithData(E targetData)
	{
		//Uses binarySearch method
		return binarySearch(root, targetData); 
	}
	
	
	/**
	 * Recursively searches through the tree to find a node.
	 * @param root root of the tree.
	 * @param targetData Data being searched for.
	 * @return Node being searched for.
	 */
	public BinaryTreeNode<E> binarySearch(BinaryTreeNode<E> root, E targetData)
	{
		if (root == null)
		{
			return null;
		}
		
		else if (root.getData().compareTo(targetData) > 0)
		{
			return binarySearch(root.getLeftChild(), targetData);
		}
		
		else if (root.getData().compareTo(targetData) < 0)
		{
			return binarySearch(root.getRightChild(), targetData);
		}
		
		else
		{
			return root;
		}
		
	}
	
	
	/** Prints the tree diagram top down (level order). */
	public void printTreeDiagram()
	{
        if (root == null) 
        {
        	return;
        } 
          
        //Create an empty queue for level order traversal. 
        Queue<BinaryTreeNode<E>> q = new LinkedList<BinaryTreeNode<E>>(); 
          
        q.add(root); 
    
        while (true) 
        { 
            //Number of nodes at current level. 
            int nodeCount = q.size(); 
            
            if (nodeCount == 0) 
            {
            	break;
            }
              
            //Dequeue nodes on current level.
            //Enqueue nodes of next level.
            while (nodeCount > 0) 
            { 
                BinaryTreeNode<E> node = q.peek(); 
                System.out.print(node.getData() + " "); 
                q.remove(); 
                
                if(node.getLeftChild() != null) 
                {
                	q.add(node.getLeftChild());
                }
                   
                if(node.getRightChild() != null)
                {
                	q.add(node.getRightChild()); 
                }
                 
                nodeCount--; 
                
            } //End inner while loop.
            
            System.out.println(); 
            
        } //End outer while loop.
        
	} //End printTreeDiagram method.
	
	
	/**
	 * Builds a string containing the elements of the tree using in-order traversal.
	 * @param root Root of the tree.
	 * @return String containing the elements of the tree.
	 */
	public String inOrderTraversal(BinaryTreeNode<E> root)
	{
	    Stack<BinaryTreeNode<E>> nodes = new Stack<>();
	    BinaryTreeNode<E> current = root;
	    StringBuilder result = new StringBuilder();

	    while (!nodes.isEmpty() || current != null) 
	    {

	      if (current != null) 
	      {
	        nodes.push(current);
	        current = current.getLeftChild();
	      } 
	      
	      else 
	      {
	        BinaryTreeNode<E> node = (BinaryTreeNode<E>) nodes.pop();
	        result.append(node.getData() + " ");
	        current = node.getRightChild();
	      }

	    }
	    
	    return result.toString();
		
	}
	
	
	/**
	 * Uses the method inOrderTraversal to represent the tree as a sorted list.
	 * @Override
	 * @return String representation of the tree in a list.
	 */
	public String toString()
	{
		return "In order traversal of the tree: " + inOrderTraversal(root);
	}
	
} //End BinaryTree class.
