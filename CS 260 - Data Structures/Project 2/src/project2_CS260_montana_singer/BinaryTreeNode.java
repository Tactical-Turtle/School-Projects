package project2_CS260_montana_singer;

import java.io.Serializable;

/**
 * This class represents a Node that will be added to the BinaryTree.
 * The class is generic, and in this instance it holds Dog objects.
 * @author Montana Singer
 * @param <E> The generic data that the class can receive.
 */
public class BinaryTreeNode<E extends Comparable<E>> implements Serializable {

	private E data;
	private BinaryTreeNode<E> leftChild, rightChild;
	
	
	/**
	 * Constructor to initialize a BinaryTreeNode with generic data.
	 * @param data Generic data, in this case a Dog object.
	 */
	public BinaryTreeNode (E data)
	{
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	/** Default constructor. */
	public BinaryTreeNode() 
	{
		this.data = null;
		this.leftChild = null;
		this.rightChild = null;
	}
	
	
	/** 
	 * Returns data in the node.
	 * @return Generic E data (in this case a Dog object which holds int data).
	 */
	public E getData()
	{
		return data;
	}
	
	
	/** 
	 * Initializes the data in the node. 
	 * @param data Generic data (in this case a Dog object).
	 */
	public void setData(E data)
	{
		this.data = data;
	}
	
	
	/**
	 * Returns the left child.
	 * @return The BinaryTreeNode to the left of the current node.
	 */
	public BinaryTreeNode<E> getLeftChild()
	{
		return leftChild;
	}
	
	/**
	 * Initializes the left child of the current node.
	 * @param Node The node that will be the left child of the current node.
	 */
	public void setLeftChild(BinaryTreeNode<E> Node)
	{
		leftChild = Node;
	}
	
	/**
	 * Returns the right child.
	 * @return The BinaryTreeNode to the right of the current node.
	 */
	public BinaryTreeNode<E> getRightChild()
	{
		return rightChild;
	}
	
	/**
	 * Initializes the right child of the current node.
	 * @param Node The node that will be the right child of the current node.
	 */
	public void setRightChild(BinaryTreeNode<E> Node)
	{
		rightChild = Node;
	}
	
} //End BinaryTreeNode class.
