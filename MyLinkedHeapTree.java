package heap;

import net.datastructures.CompleteBinaryTree;
import net.datastructures.EmptyTreeException;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

/**
 * An implementation of a complete binary tree by means 
 * of a linked structure (LinkedBinaryTree). The LinkedBinaryTree class 
 * takes care of most of the mechanics of modifying 
 * the tree (you should read through the NDS4 documentation 
 * in order to fully understand how this class works. There's a link on
 * the website), but you will need 
 * to think about how to implement a CompleteBinaryTree such that
 * additions and removals operate *only* on the last node (hint: think
 * about other useful data structures). You must also ensure that you do not
 * violate the assignment runtime requirements when deciding how you will
 * track nodes within the tree.
 *  
 */

public class MyLinkedHeapTree<E> extends LinkedBinaryTree<E> 
		implements CompleteBinaryTree<E> {
	
	/**
	 * Default constructor. The tree begins empty.
	 */
	public MyLinkedHeapTree() {
		
	}

	/**
	 * Adds an element to the tree just after the last node. Returns the newly
	 * created position for the element.
	 *
	 * Note: You don't need to instantiate a new Position<E> as a local variable.
	 * Look at the NDS4 documentation for LinkedBinaryTree for how to add a
	 * new Position<E> to the tree.
	 * 
	 * This method must run in constant O(1) worst-case time.
	 * 
	 * @param element to be added to the tree as the new last node
	 * @return the Position of the newly inserted element
	 */
	@Override
	public Position<E> add(E element) {
		return null;
	}

	/**
	 * Removes and returns the element stored in the last node of the tree.
	 * 
	 * This method must run in constant O(1) worst-case time.
	 * 
	 * @return the element formerly stored in the last node (prior to its removal)
	 * @throws EmptyTreeException if the tree is empty and no last node exists
	 */
	@Override
	public E remove() throws EmptyTreeException {
		return null;
	}
	
	/*
	 * Feel free to add helper methods here.
	 */ 
	
}
