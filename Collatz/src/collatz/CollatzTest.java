package collatz;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class CollatzTest {

	@Test
	public void testSimpleComputeSequenceLengths() {
		
		int[] test = new int[] {0, 1};
		int[] test1 = new int[] {0};
		int[] test2 = new int[] {0, 1, 2, 8};
		int[] test3 = new int[] {0, 1, 2, 8, 3};
		
		assertArrayEquals(test, Collatz.simpleComputeSequenceLengths(1));
		assertArrayEquals(test1, Collatz.simpleComputeSequenceLengths(0));
		assertArrayEquals(test2, Collatz.simpleComputeSequenceLengths(3));
		assertArrayEquals(test3, Collatz.simpleComputeSequenceLengths(4));
		
	}

	@Test
	public void testGetLength() {
		
		assertEquals(0, Collatz.getLength(0));
		assertEquals(1, Collatz.getLength(1));
		assertEquals(2, Collatz.getLength(2));
		assertEquals(8, Collatz.getLength(3));
		assertEquals(6, Collatz.getLength(5));

	}

	@Test
	public void testMemoizedComputeSequenceLengths() {
		
		int[] test = new int[] {0, 1};
		int[] test1 = new int[] {0};
		int[] test2 = new int[] {0, 1, 2, 8};
		int[] test3 = new int[] {0, 1, 2, 8, 3};
		
		assertArrayEquals(test, Collatz.memoizedComputeSequenceLengths(1));
		assertArrayEquals(test1, Collatz.memoizedComputeSequenceLengths(0));
		assertArrayEquals(test2, Collatz.memoizedComputeSequenceLengths(3));
		assertArrayEquals(test3, Collatz.memoizedComputeSequenceLengths(4));
	}

}
