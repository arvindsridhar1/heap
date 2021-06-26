package heap;

import net.datastructures.Entry;
import net.datastructures.Position;

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

	private K _key;
	private V _value;
	private Position<MyHeapEntry<K,V>> _position;
	/** 
	 * Default constructor. You may wish to modify the parameters.
	 */
	public MyHeapEntry(K key, V value) {
		_key = key;
		_value = value;
	}

	/**
	 * @return the key stored in this entry 
	 */
	public K getKey() {
		return _key;
	}

	/**
	 * @return the value stored in this entry
	 */
	public V getValue() {
		return _value;
	}

	/**
	 * set the key stored in this entry
	 */
	public void setKey(K key) {
		_key = key;
	}

	/**
	 * set the value stored in this entry
	 */
	public void setValue(V value) {
		_value = value;
	}

	/**
	 * set the position of the entry
	 */
	public void setPosition(Position<MyHeapEntry<K,V>> position) {
		_position = position;
	}

	/**
	 * @return the position that the entry is contained in
	 */
	public Position<MyHeapEntry<K, V>> getPosition() {
		return _position;
	}
}
