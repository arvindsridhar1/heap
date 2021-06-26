package heap;

import com.sun.xml.internal.bind.v2.model.core.Element;
import net.datastructures.*;

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

	 private NodeDeque<Position<E>> _positionNodeDeque;
	
	/**
	 * Default constructor. The tree begins empty.
	 */
	public MyLinkedHeapTree() {
		_positionNodeDeque = new NodeDeque<Position<E>>();
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
		if (isEmpty()) {
			Position<E> root = this.addRoot(element);

			_positionNodeDeque.addLast(root);
			return _positionNodeDeque.getLast();
		}
		Position<E> parent = _positionNodeDeque.getFirst();
		if(hasLeft(_positionNodeDeque.getFirst()) == false){
			_positionNodeDeque.addLast(this.insertLeft(parent, element));

		}
		else if(hasLeft(_positionNodeDeque.getFirst()) == true){
			_positionNodeDeque.addLast(this.insertRight(parent,element));
			_positionNodeDeque.removeFirst();
		}

		return _positionNodeDeque.getLast();
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
		if (isEmpty()) {
			throw new EmptyTreeException("Cannot remove from an empty tree");
		}
		Position<E> last = _positionNodeDeque.getLast();

		if(isRoot(last)){
			_positionNodeDeque.removeLast();
			return remove(last);
		}
		Position<E> parent = parent(last);

		if(hasLeft(parent)){
			if(left(parent) == last) {
				_positionNodeDeque.removeLast();
			}
		}

		if(hasRight(parent)){
			if(right(parent) == last) {
				_positionNodeDeque.removeLast();
				_positionNodeDeque.addFirst(parent(last));
			}
		}
		remove(last);
		return last.element();
	}
	
	/*
	 * Feel free to add helper methods here.
	 */
	public Position<E> returnLast(){
		return _positionNodeDeque.getLast();
	}

}
