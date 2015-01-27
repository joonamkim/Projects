package collatz;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class PairTest {


	@Test
	public void testCollatz_1() {
		
		
		assertEquals(0, Pair.collatz_1(0));
		assertEquals(1, Pair.collatz_1(1));
		assertEquals(10, Pair.collatz_1(3));
		assertEquals(5, Pair.collatz_1(10));
		assertEquals(16, Pair.collatz_1(5));
		assertEquals(8, Pair.collatz_1(16));
		assertEquals(4, Pair.collatz_1(8));
		assertEquals(29998, Pair.collatz_1(9999));
		
		//Did not test for negatives as bad inputs are not allowed for this assignment. 

	}

	@Test
	public void testSequenceOf() {
		
		List<Long> returnList = new ArrayList<Long>();
		// test 0
		returnList.add((long) 0);
		assertEquals(returnList, Pair.sequenceOf(0));
		returnList.remove(0);
		
		// test 1
		returnList.add((long) 1);
		assertEquals(returnList, Pair.sequenceOf(1));
		returnList.remove(0);
		
		// test 2
		returnList.add((long) 2);
		returnList.add((long) 1);
		assertEquals(returnList, Pair.sequenceOf(2));
		returnList.removeAll(returnList);
		
		// test 3
		returnList.add((long) 3);
		returnList.add((long) 10);
		returnList.add((long) 5);
		returnList.add((long) 16);
		returnList.add((long) 8);
		returnList.add((long) 4);
		returnList.add((long) 2);
		returnList.add((long) 1);
		assertEquals(returnList, Pair.sequenceOf(3));
		returnList.removeAll(returnList);
		
	}

	@Test
	public void testLengthOfSquence() {
		
		assertEquals(1, Pair.lengthOfSquence(0));
		assertEquals(1, Pair.lengthOfSquence(1));
		assertEquals(2, Pair.lengthOfSquence(2));
		assertEquals(8, Pair.lengthOfSquence(3));
		assertEquals(3, Pair.lengthOfSquence(4));
	}

	@Test
	public void testLargestValueInSequence() {
		
		assertEquals(16, Pair.largestValueInSequence(3));
		assertEquals(9232, Pair.largestValueInSequence(27));
		assertEquals(1, Pair.largestValueInSequence(1));
		assertEquals(4, Pair.largestValueInSequence(4));
		assertEquals(16, Pair.largestValueInSequence(10));
		
	}

	@Test
	public void testEqualLengthTwins() {
		
		List<Pair<Long, Integer>> returnList= new ArrayList<Pair<Long, Integer>>();
		returnList.add(new Pair<Long, Integer>((long) 28, 19));
		returnList.add(new Pair<Long, Integer>((long) 29, 19));
		
		// test 28 ~ 29
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(returnList.get(i)._1(), Pair.equalLengthTwins(28, 29).get(i)._1());
			assertEquals(returnList.get(i)._2(), Pair.equalLengthTwins(28, 29).get(i)._2());
		}
		
		//test 100 ~ 102
		returnList.removeAll(returnList);
		returnList.add(new Pair<Long, Integer>((long) 100, 26));
		returnList.add(new Pair<Long, Integer>((long) 101, 26));
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(returnList.get(i)._1(), Pair.equalLengthTwins(100, 102).get(i)._1());
			assertEquals(returnList.get(i)._2(), Pair.equalLengthTwins(100, 102).get(i)._2());
		}
		
		returnList.removeAll(returnList);
		
		//Empty list test
		assertEquals(returnList, Pair.equalLengthTwins(4, 4));
		assertEquals(returnList, Pair.equalLengthTwins(4, 4));
		
	}

	@Test
	public void testEqualMaxValueTwins() {

		List<Pair<Long, Long>> returnList= new ArrayList<Pair<Long, Long>>();
		returnList.add(new Pair<Long, Long> ((long) 5,(long) 16));
		returnList.add(new Pair<Long, Long> ((long) 17,(long) 52));
		
		for (int i = 0; i < returnList.size(); i++) {
			assertEquals(returnList.get(i)._1(), Pair.equalMaxValueTwins(1, 30).get(i)._1());
			assertEquals(returnList.get(i)._2(), Pair.equalMaxValueTwins(1, 30).get(i)._2());
		}
		
		returnList.removeAll(returnList);
		returnList.add(new Pair<Long, Long> ((long) 5,(long) 16));
		assertEquals(returnList.get(0)._1(), Pair.equalMaxValueTwins(1, 30).get(0)._1());
		assertEquals(returnList.get(0)._2(), Pair.equalMaxValueTwins(1, 30).get(0)._2());
		
		returnList.removeAll(returnList);
		returnList.add(new Pair<Long, Long> ((long) 5,(long) 16));
		assertEquals(returnList.get(0)._1(), Pair.equalMaxValueTwins(5, 6).get(0)._1());
		assertEquals(returnList.get(0)._2(), Pair.equalMaxValueTwins(5, 6).get(0)._2());
		
		//Empty list test
		returnList.removeAll(returnList);
		assertEquals(returnList, Pair.equalMaxValueTwins(4, 4));
		assertEquals(returnList, Pair.equalMaxValueTwins(4, 4));
		
	}

	@Test
	public void testOccurrences() {
		
		int[] test = new int[] {0, 10, 9, 2, 8, 6, 1};
		int[] test1 = new int[] {0, 0, 0, 0, 0, 0, 0};
		int[] test2 = new int[] {0};
		int[] test3 = new int[] {0};
		int[] test4 = new int[] {0, 4, 3, 1, 2, 1, 0};
		int[] test5 = new int[] {0, 4, 3, 1, 2};
		
		
		assertArrayEquals(test, Pair.occurrences(10, 6));
		assertArrayEquals(test1, Pair.occurrences(0, 6));
		assertArrayEquals(test2, Pair.occurrences(10, 0));
		assertArrayEquals(test3, Pair.occurrences(0, 0));
		assertArrayEquals(test4, Pair.occurrences(4, 6));
		assertArrayEquals(test5, Pair.occurrences(4, 4));
		
	}

}
