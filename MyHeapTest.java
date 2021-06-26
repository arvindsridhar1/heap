package heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.datastructures.*;
import org.junit.Ignore;
import org.junit.Test;

/**
 * This class can be used to test the functionality of your MyHeap implementation.
 * You will find a few examples to guide you through the syntax of writing test cases.
 * Each test case uses its own heap instance to ensure that the test cases are independent 
 * of each other. All of the given examples should pass once you've implemented your heap.
 * 
 *
 * The annotation @Test before each test case is JUnit syntax. It basically lets the compiler know
 * that this is a unit test method. Use this annotation for *every* test method. This class is 
 * also like any other java class, so should you need to add private helper methods to use in your 
 * tests, you can do so, simply without the @Test annotation.
 * The general framework of a test case is:
 * 		- Name the test method descriptively, mentioning what is being tested (it is ok to have 
 * 		  slightly verbose method names here)
 * 		- Set-up the program state (ex: instantiate a heap and insert K,V pairs into it)
 * 		- Use assertions to validate that the program is in the state you expect it to be
 * 
 * We've given you four example of test cases below that should help you understand syntax and the
 * general structure of tests.
 */

public class MyHeapTest {

	/**
	 * A simple test to ensure that insert() works.
	 */
	@Test
	public void testInsertOneElement() {
		// set-up
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");

		// Assert that your data structure is consistent using 
		// assertThat(actual, is(expected))
		assertThat(heap.size(), is(1));
		assertThat(heap.min().getKey(), is(1));
	}


	/**
	 * This is an example to check that the order of the heap is sorted as per the keys
	 * by comparing a list of the actual and expected keys.
	 */
	@Test
	public void testRemoveMinHeapOrderUsingList() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "A");
		heap.insert(13, "B");
		heap.insert(64, "C");
		heap.insert(16, "D");
		heap.insert(44, "E");

		// the expected ordering that keys come in
		List<Integer> expectedKeys = Arrays.asList(11, 13, 16, 44, 64);

		// the actual ordering of keys in the heap
		List<Integer> actualKeys = new ArrayList<Integer>();
		while (!heap.isEmpty()) {
			actualKeys.add(heap.removeMin().getKey());
		}


		// check that the actual ordering matches the expected ordering by using one assert
		// Note that assertThat(actual, is(expected)), when used on lists/ arrays, also checks that the
		// ordering is the same.
		assertThat(actualKeys, is(expectedKeys));
	}

	/**
	 * This is an example of testing heap ordering by ensuring that the min key is always at the root
	 * by checking it explicitly each time, using multiple asserts rather than a list.
	 */
	@Test
	public void testRemoveMinHeapOrder() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(11, "A");
		heap.insert(13, "B");
		heap.insert(64, "C");
		heap.insert(16, "D");
		heap.insert(44, "E");


		// test the heap ordering by asserting on all elements
		assertThat(heap.removeMin().getKey(), is(11));
		assertThat(heap.removeMin().getKey(), is(13));
		assertThat(heap.removeMin().getKey(), is(16));
		assertThat(heap.removeMin().getKey(), is(44));
		assertThat(heap.removeMin().getKey(), is(64));
	}


	/**
	 * This is an example of how to test whether an exception you expect to be thrown on a certain line of code
	 * is actually thrown. As shown, you'd simply add the expected exception right after the @Test annotation.
	 * This test will pass if the exception expected is thrown by the test and fail otherwise.
	 * <p>
	 * Here, we're checking to see if an IllegalStateException is being correctly thrown after we try to
	 * call setComparator on a non-empty heap.
	 */
	@Test(expected = IllegalStateException.class)
	public void testSetComparatorThrowsIllegalStateException() {
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(1, "A");
		heap.setComparator(new IntegerComparator());
	}


	/**
	 * TODO: add your tests below!
	 * Think of edge cases and testing for exceptions (if applicable) for insert, remove, min, removeMin, size and
	 * your helper methods (if applicable).
	 */

	/**
	 * Tests for the sizing of the heap when entries are added and removed
	 */
	@Test
	public void sizeTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertThat(heap.size(), is(0));
		Entry<Integer, String> entry = heap.insert(25, "ABC");
		assertThat(heap.size(), is(1));
		heap.remove(entry);
		assertThat(heap.size(), is(0));
	}

	/**
	 * Tests that the heap is properly recognized as empty/not empty
	 */
	@Test
	public void isEmptyTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		assertTrue(heap.isEmpty());
		Entry<Integer, String> entry = heap.insert(25, "ABC");
		assertTrue(!heap.isEmpty());
		heap.remove(entry);
		assertTrue(heap.isEmpty());
	}

	/**
	 * Tests that an Empty Priority Queue exception is properly raised in an invalid min call
	 */
	@Test(expected = EmptyPriorityQueueException.class)
	public void minExceptionTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.min();
	}

	/**
	 * Tests that the minimum of the heap is properly recognized
	 */
	@Test
	public void minStandardTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry = heap.insert(1, "ABC");
		Entry<Integer, String> entry2 = heap.insert(10, "ABC");
		Entry<Integer, String> entry3 = heap.insert(20, "ABC");

		assertTrue(heap.min() == entry);
		heap.replaceKey(entry, 99);
		assertTrue(heap.min().getKey() == 10);
	}

	/**
	 * Test that an Invalid Key Exception is properly raised in an invalid insert call
	 */
	@Test(expected = InvalidKeyException.class)
	public void insertExceptionTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.insert(null, "abc");
	}

	/**
	 * Tests that the insert function works as intended
	 */
	@Test
	public void insertStandardTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());

		Entry<Integer, String> entry = heap.insert(1, "ABC");
		Entry<Integer, String> entry2 = heap.insert(2, "DEF");
		Entry<Integer, String> entry3 = heap.insert(3, "GHI");
		Entry<Integer, String> entry4 = heap.insert(4, "JKL");

		assertTrue(entry.getKey() == 1);
		assertTrue(entry.getValue() == "ABC");
		assertTrue(entry4.getKey() == 4);
		assertTrue(entry4.getValue() == "JKL");
	}

	/**
	 * Tests that an Empty Priority Queue Execption is raised when the heap is empty on a removeMin call
	 */
	@Test(expected = EmptyPriorityQueueException.class)
	public void removeMinExceptionTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		heap.removeMin();
	}

	/**
	 * Tests that the removeMin methods works are intended
	 */
	@Test
	public void removeMinStandardTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());

		Entry<Integer, String> entry = heap.insert(1, "ABC");
		Entry<Integer, String> entry2 = heap.insert(2, "DEF");
		Entry<Integer, String> entry3 = heap.insert(3, "GHI");
		Entry<Integer, String> entry4 = heap.insert(4, "JKL");

		assertTrue(heap.removeMin().getKey() == 1);
		assertTrue(heap.removeMin() == entry3);
		assertTrue(heap.removeMin() == entry2);
		assertTrue(heap.removeMin() == entry);
	}

	/**
	 * Tests that an Invalid Entry Exception is raised when trying to removed from an entry not in the tree
	 */
	@Test(expected = InvalidEntryException.class)
	public void removeExceptionTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry = null;
		heap.remove(entry);
	}

	/**
	 * Tests that the tree holds when removing entries, along with testing swapping and heaping up/down
	 */
	@Test
	public void removeStandardTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());

		Entry<Integer, String> entry = heap.insert(12, "ABC");
		Entry<Integer, String> entry2 = heap.insert(6, "DEF");
		Entry<Integer, String> entry3 = heap.insert(18, "GHI");
		Entry<Integer, String> entry4 = heap.insert(29, "JKL");
		Entry<Integer, String> entry5 = heap.insert(99, "MNO");
		Entry<Integer, String> entry6 = heap.insert(1, "PQR");
		Entry<Integer, String> entry7 = heap.insert(55, "STU");

		//These are testing the helper swapping method, as the key value should switch during the reordering of the heap
		assertTrue(heap.remove(entry2).getValue() == "ABC");
		assertTrue(heap.remove(entry3) == entry6);
		assertTrue(heap.remove(entry5).getKey() == 99);
		assertTrue(heap.remove(entry4).getValue() == "STU");
	}

	/**
	 * Tests that an Invalid Entry Exception is properly raised in an invalid replace key call
	 */
	@Test(expected= InvalidEntryException.class)
	public void replaceKeyInvalidEntryTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry = null;
		heap.replaceKey(entry, 4);
	}

	/**
	 * Tests that an Invalid Key Exception is properly raised in an invalid replace key call
	 */
	@Test(expected= InvalidKeyException.class)
	public void replaceKeyInvalidKeyTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry = heap.insert(null, "DOI");
		heap.replaceKey(entry, 4);
	}

	/**
	 * Tests that the tree holds its properties when replacing keys (also tests up/downheaping)
	 */
	@Test
	public void replaceKeyStandardTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());

		Entry<Integer, String> entry = heap.insert(12, "ABC");
		Entry<Integer, String> entry2 = heap.insert(6, "DEF");
		Entry<Integer, String> entry3 = heap.insert(18, "GHI");
		Entry<Integer, String> entry4 = heap.insert(29, "JKL");
		Entry<Integer, String> entry5 = heap.insert(99, "MNO");
		Entry<Integer, String> entry6 = heap.insert(1, "PQR");
		Entry<Integer, String> entry7 = heap.insert(55, "STU");

		assertTrue(entry2.getKey() == 12);
		heap.replaceKey(entry2, 30);
		//This is testing the downheap, as the 30 key should shift down the properties of entry2 to entry4
		assertTrue(entry2.getKey() == 29);
		assertTrue(entry4.getKey() == 30);
		assertTrue(entry.getKey() == 1);
		heap.replaceKey(entry, 9);
		assertTrue(entry.getKey() == 6);
		//Testing 0 and negative numbers
		heap.replaceKey(entry, 0);
		assertTrue(entry.getKey() == 0);
		heap.replaceKey(entry, -50);
		assertTrue(entry.getKey() == -50);
	}

	/**
	 * Tests that an Invalid Entry Exception is properly raised in an invalid replace value call
	 */
	@Test(expected = InvalidEntryException.class)
	public void replaceValueExceptionTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());
		Entry<Integer, String> entry = null;
		heap.replaceValue(entry, "DEF");
	}

	/**
	 * Tests that replacing the value of entries works properly and doesn't affect tree properties
	 */
	@Test
	public void replaceValueStandardTest(){
		MyHeap<Integer, String> heap = new MyHeap<Integer, String>(new IntegerComparator());

		Entry<Integer, String> entry = heap.insert(6, "ABC");
		Entry<Integer, String> entry2 = heap.insert(12, "DEF");

		assertTrue(entry.getValue().equals("ABC"));
		heap.replaceValue(entry, "POA");
		assertTrue(entry.getValue() == "POA");
		heap.replaceValue(entry, "ZOZ");
		assertTrue(entry.getValue() == "ZOZ");
		//empty string check
		heap.replaceValue(entry, "");
		assertTrue(entry.getValue() == "");
	}

}


