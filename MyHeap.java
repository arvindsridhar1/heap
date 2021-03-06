package heap;

import java.util.Comparator;

import net.datastructures.*;
import support.heap.HeapWrapper;

/**
 * An implementation of an adaptable priority queue by 
 * means of a heap. Be certain that your running times 
 * match those specified in the program documentation, 
 * and remember that the running time of a "called" 
 * method sets the minimum running time of the "calling" 
 * method. Feel free to add additional comments. 
 */

public class MyHeap<K,V> implements HeapWrapper<K,V>, AdaptablePriorityQueue<K,V> {
	
	// This the underlying data structure of your heap
	private Comparator<K> _comparator;
	private MyLinkedHeapTree<MyHeapEntry<K,V>> _tree;

	/** 
	 * Creates an empty heap with the given comparator. 
	 * 
	 * @param comparator to be used for heap keys
	 */
	public MyHeap(Comparator<K> comparator) {
		_comparator = comparator;
		_tree = new MyLinkedHeapTree<MyHeapEntry<K,V>>();
	}

	/**
	 * Sets the comparator used for comparing items in the heap to the
	 * comparator passed in.
	 * 
	 * @param comparator, the comparator to be used for heap keys
	 * @throws IllegalStateException if priority queue is not empty
	 * @throws IllegalArgumentException if null comparator is passed in
	 */
	public void setComparator(Comparator<K> comparator)
			throws IllegalStateException, IllegalArgumentException {
		if(!isEmpty()){
			throw new IllegalStateException("Not Empty");
		}
		if(comparator == null) {
			throw new IllegalArgumentException("comparator is null");
		}
	}

	/**
	 * Returns a CompleteBinaryTree that will allow the visualizer 
	 * access to private members, shattering encapsulation, but 
	 * allowing visualization of the heap. This is the only method 
	 * needed to satisfy HeapWrapper interface implementation.
	 *
	 * Do not modify or call this method. It is solely
	 * necessary for the visualizer to work properly.
	 * 
	 * @return the underlying binary tree on which the heap is based
	 */
	public CompleteBinaryTree<MyHeapEntry<K,V>> getTree() {
		return _tree;
	}
	
	/** 
	 * Returns the size of the heap.
	 * This method must run in O(1) time.
	 *
	 * @return an int representing the number of entries stored
	 */
	public int size() {
		return _tree.size();
	}

	/** 
	 * Returns whether the heap is empty.
	 * This method must run in O(1) time.
	 * 
	 * @return true if the heap is empty; false otherwise
	 */
	public boolean isEmpty() {
		return (_tree.isEmpty());
	}

	/** 
	 * Returns but does not remove the entry with minimum key.
	 * This method must run in O(1) time.
	 * 
	 * @return the entry with the minimum key in the heap
	 * @throws EmptyPriorityQueueException if the heap is empty
	 */
	public Entry<K,V> min() throws EmptyPriorityQueueException {
		if (isEmpty()){
			throw new EmptyPriorityQueueException("Empty Heap");
		}
		return _tree.root().element();
	}

	/** 
	 * Inserts a key-value pair and returns the entry created.
	 * This method must run in O(log n) time.
	 *
	 * @param key to be used as the key the heap is sorting with
	 * @param value stored with the associated key in the heap
	 * @return the entry created using the key/value parameters
	 * @throws InvalidKeyException if the key is not suitable for this heap
	 */
	public Entry<K,V> insert(K key, V value) throws InvalidKeyException {
		if (key == null || !(key instanceof Integer)) {
			throw new InvalidKeyException("Key is not a valid object type");
		}
		MyHeapEntry<K,V> insertableEntry = new MyHeapEntry<K,V>(key, value);
		insertableEntry.setKey(key);
		insertableEntry.setValue(value);
		insertableEntry.setPosition(_tree.add(insertableEntry));
		this.upHeap(insertableEntry.getPosition());
		return insertableEntry.getPosition().element();
	}

	/** 
	 * Removes and returns the entry with the minimum key.
	 * This method must run in O(log n) time.
	 * 
	 * @return the entry with the with the minimum key, now removed 
	 * @throws EmptyPriorityQueueException if the heap is empty
	 */
	public Entry<K,V> removeMin() throws EmptyPriorityQueueException {
		if(_tree.isEmpty()){
			throw new EmptyPriorityQueueException("The Heap is Empty");
		}
		MyHeapEntry<K,V> lastEntry = _tree.returnLast().element();
		if(!_tree.isEmpty()) {
			this.swapElement(_tree.root().element(), lastEntry);
			_tree.remove();
			if(!_tree.isEmpty()) {
				this.downHeap(_tree.root());
			}
		}
		return lastEntry;
	}

	/** 
	 * Removes and returns the given entry from the heap.
	 * This method must run in O(log n) time.
	 *
	 * @param entry to be removed from the heap
	 * @return the entry specified for removal by the parameter, now removed
	 * @throws InvalidEntryException if the entry cannot be removed from this heap
	 */
	public Entry<K,V> remove(Entry<K,V> entry) throws InvalidEntryException {
		MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry);
		Position<MyHeapEntry<K,V>> position = checkedEntry.getPosition();
		if(_tree.isEmpty() || position == null || entry == null){
			throw new InvalidEntryException("Not in the tree");
		}
		Position<MyHeapEntry<K,V>> check = _tree.returnLast();
		this.swapElement(checkedEntry, check.element());
		MyHeapEntry<K,V> lastEntry = _tree.remove();
		if(!_tree.isEmpty()) {
			this.upHeap(position);
			this.downHeap(position);
		}
		return lastEntry;
	}

	/** 
	 * Replaces the key of the given entry.
	 * This method must run in O(log n) time.
	 *
	 * @param entry within which the key will be replaced
	 * @param key to replace the existing key in the entry
	 * @return the old key formerly associated with the entry
	 * @throws InvalidEntryException if the entry is invalid
	 * @throws InvalidKeyException if the key is invalid
	 */

	public K replaceKey(Entry<K,V> entry, K key) throws InvalidEntryException, InvalidKeyException {
		MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry);
		if(_tree.isEmpty() || entry == null){
			throw new InvalidEntryException("Entry is not in tree");
		}
		if(!_tree.isEmpty()) {
			if (key == null || !(key instanceof Integer)) {
				throw new InvalidKeyException("Key is not a valid object type");
			}
		}

		Position<MyHeapEntry<K,V>> position = checkedEntry.getPosition();
		K oldKey = checkedEntry.getKey();
		checkedEntry.setKey(key);
		this.downHeap(position);
		this.upHeap(position);

		return oldKey;
	}

	/** 
	 * Replaces the value of the given entry.
	 * This method must run in O(1) time.
	 *
	 * @param entry within which the value will be replaced
	 * @param value to replace the existing value in the entry
	 * @return the old value formerly associated with the entry
	 * @throws InvalidEntryException if the entry cannot have its value replaced
	 */
	public V replaceValue(Entry<K,V> entry, V value) throws InvalidEntryException {		
		MyHeapEntry<K,V> checkedEntry = this.checkAndConvertEntry(entry);

		V oldValue = checkedEntry.getValue();
		checkedEntry.setValue(value);

		return oldValue;
	}
	

	/**
	 * Determines whether a given entry is valid and converts it to a
	 * MyHeapEntry. Don't change this method.
	 *
	 * @param entry to be checked for validity with respect to the heap
	 * @return the entry cast as a MyHeapEntry if considered valid 
	 *
	 * @throws InvalidEntryException if the entry is not of the proper class
	 */
	public MyHeapEntry<K,V> checkAndConvertEntry(Entry<K,V> entry)
			throws InvalidEntryException {
		if (entry == null || !(entry instanceof MyHeapEntry)) {
			throw new InvalidEntryException("Invalid entry");
		}
		return (MyHeapEntry<K, V>) entry;
	}

	/*
	 * You may find it useful to add some helper methods here.
	 * Think about actions that may be executed often in the 
	 * rest of your code. For example, checking key  
	 * validity, upheaping and downheaping, swapping or 
	 * replacing elements, etc. Writing helper methods instead 
	 * of copying and pasting helps segment your code, makes 
	 * it easier to understand, and avoids problems in keeping 
	 * each occurrence "up-to-date."
	 */

	/**
	 * @param pos which is the position whose contents are checked to be upHeaped
	 * Handles the recognition and calls the upward swapping of entry details when a key is smaller than its parents
	 */
	public void upHeap(Position<MyHeapEntry<K,V>> pos){
		Position<MyHeapEntry<K,V>> position = pos;
		while(!_tree.isRoot(position) && _comparator.compare(position.element().getKey(),
				_tree.parent(position).element().getKey()) < 0){
			this.swapElement(position.element(), _tree.parent(position).element());
			position = _tree.parent(position);
		}
	}

	/**
	 * @param pos which is the position whose contents are checked to be downHeaped
	 * Handles the recognition and calls the downward swapping of entry details when a key is larger than its children
	 */
	public void downHeap(Position<MyHeapEntry<K,V>> pos){
		Position<MyHeapEntry<K,V>> position = pos;
			while (_tree.hasLeft(position)) {
				Position<MyHeapEntry<K, V>> swapChild = _tree.left(position);
				if (_tree.hasRight(position)) {
					if (_comparator.compare(_tree.right(position).element().getKey(),
							_tree.left(position).element().getKey()) < 0) {
						swapChild = _tree.right(position);
					}
				}
				if (_comparator.compare(swapChild.element().getKey(), position.element().getKey()) < 0) {
					this.swapElement(swapChild.element(), position.element());
					position = swapChild;
				} else {
					break;
				}
		}
	}

	/**
	 * @param elementOne which is the first element whose contents are swapped
	 * @param elementTwo which is the second element whose contents are swapped
	 * Handles the actual mechanics of swapping entry details by switching keys and values of the entries
	 */
	public void swapElement(MyHeapEntry<K,V> elementOne, MyHeapEntry<K,V> elementTwo){
		K oneKey = elementOne.getKey();
		V oneValue = elementOne.getValue();

		elementOne.setKey(elementTwo.getKey());
		elementOne.setValue(elementTwo.getValue());
		elementTwo.setKey(oneKey);
		elementTwo.setValue(oneValue);
	}
}

