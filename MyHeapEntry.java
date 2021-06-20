package heap;

import net.datastructures.Entry;

/**
 * Represents a key/value pair to be stored in a data 
 * structure, such as a heap. Entry<K,V> is a very 
 * limited accessing interface, so you may wish to add 
 * additional methods. In particular, think about the 
 * relationship of the Entry<K,V> to its location in 
 * the heap's binary tree. All methods must run in O(1)
 * time.
 *
 * Feel free to add additional comments. 
 */

public class MyHeapEntry<K,V> implements Entry<K,V> {

	/** 
	 * Default constructor. You may wish to modify the parameters.
	 */
	public MyHeapEntry() {

	}
	
	/**
	 * @return the key stored in this entry 
	 */
	public K getKey() {
		return null;
	}

	/** 
	 * @return the value stored in this entry 
	 */
	public V getValue() {
		return null;
	}
	
	/* Add any additional methods here */

}
