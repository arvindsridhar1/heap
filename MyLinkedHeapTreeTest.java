package heap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import net.datastructures.Position;
import org.junit.Test;

import net.datastructures.EmptyTreeException;

/**
 * This class should be used to test the functionality of your MyLinkedHeapTree implementation.
 * You will find a few examples to guide you through the syntax of writing test cases.
 * Each test case uses its own tree instance to ensure that the test cases are independent 
 * of each other. All of the given examples should pass once you've implemented your tree methods.
 * 
 *
 * The annotation @Test before each test case is JUnit syntax, it basically lets the compiler know
 * that this is a unit test method. Use this annotation for every test method. This class is also like
 * any other java class, so should you need to add private helper methods to use in your tests, 
 * you can do so, simply without the annotations as you usually would write a method.
 * The general framework of a test case is:
 * 		- Name the test method descriptively, mentioning what is being tested (it is ok to have slightly verbose method names here)
 * 		- Set-up the program state (ex: instantiate a heap and insert K,V pairs into it)
 * 		- Use assertions to validate that the progam is in the state you expect it to be
 */
public class MyLinkedHeapTreeTest {
	
	/**
	 * A simple example of checking that the add() method adds the first element to the tree.
	 */
	@Test
	public void testAddOneElement() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		tree.add(1);
		
		/* These are two ways of asserting the same thing
		 * Use whichever you find more convenient out of
		 * assertThat(actual, is(expected)) and
		 * assertTrue(boolean)
		 * Take a look at the JUnit docs for more assertions you might want to use.
		 */
		assertThat(tree.size(), is(1));
		assertTrue(tree.size() == 1);
	}
	
	/**
	 * This is an example of how to test whether an exception you expect to be thrown on a certain line of code
	 * is actually thrown. As shown, you'd simply add the expected exception right after the @Test annotation.
	 * This test will pass if the exception expected is thrown by the test and fail otherwise.
	 */
	@Test(expected = EmptyTreeException.class)
	public void testRemoveThrowsEmptyTreeException() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		tree.remove();
	}
	
	/**
	 * TODO: Write your own tests below!
	 * Think of edge cases for add/remove and try to test your helper methods (if applicable).
	 */

	/**
	 * Checks that when adding elements to the tree, size is properly calculated
	 */
	@Test
	public void testSize() {
		MyLinkedHeapTree<Integer> tree = new MyLinkedHeapTree<Integer>();
		tree.add(1);
		tree.add(2);
		tree.add(3);

		assertTrue(tree.size() == 3);
	}


	/**
	 * Checks the removed entry from a tree, and checks the size after having removed an entry
	 */
	@Test
	public void sizeAfterRemove() {
		MyLinkedHeapTree tree = new MyLinkedHeapTree();
		tree.add(12);
		tree.add(18);
		tree.add(20);

		assertEquals(20, tree.remove());
		assertTrue(tree.size() == 2);
	}

	/**
	 * Tests that the getKey and getValue methods work as intended
	 */
	@Test
	public void testGetters() {
		MyHeapEntry tree = new MyHeapEntry(25, "abc");

		assertTrue(tree.getKey().equals(25));
		assertTrue(tree.getValue().equals("abc"));
	}

	/**
	 * Tests that the setKey and setValue methods work as intended
	 */
	@Test
	public void testSetters() {
		MyHeapEntry entry = new MyHeapEntry(25, "abc");
		MyHeapEntry entry2 = new MyHeapEntry(26, 4);

		entry.setKey(12);
		entry2.setKey(35);
		assertTrue(entry.getKey().equals(12));
		assertTrue(entry2.getKey().equals(35));

		entry.setValue("def");
		entry2.setValue(6);
		assertTrue(entry.getValue().equals("def"));
		assertTrue(entry2.getValue().equals(6));
	}

	/**
	 * Checks that the relationship between a position and an element in a tree holds properly
	 */
	@Test
	public void positionTest() {
		MyLinkedHeapTree tree = new MyLinkedHeapTree();

		MyHeapEntry entry = new MyHeapEntry(25, "abc");
		MyHeapEntry secondEntry = new MyHeapEntry(26, "def");

		Position firstPosition = tree.add(entry);
		Position secondPosition = tree.add(secondEntry);

		assertTrue(firstPosition.element() == entry);
		assertTrue(secondPosition.element() == secondEntry);
	}

}
