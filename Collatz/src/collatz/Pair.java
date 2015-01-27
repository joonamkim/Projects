package collatz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * @author Joo-nam Kim
 *
 */
class Pair<A, B> {
	
	private A first;
	private B second; 
	
	/**
	 * Constructor
	 * @param first
	 * @param second
	 */
	public Pair(A first, B second) {
		this.first = first; 
		this.second = second; 
	}
	/**
	 * @return first
	 */
	public A _1() { return first; }
	
	/**
	 * @return second
	 */
	public B _2() { return second; }
	
	@Override 
	public String toString() { return "(" + first + ", " + second + ")"; }
	

	/**
	 * This nonrecursive method makes just one step of the collatz sequence. 
	 * For example, given 7, the method returns 22; given 10, it returns 5; 
	 * given 1, it returns 1. Use long values rather than int values.
	 * @param n
	 * @return the next value of n in the sequence.
	 */
	static long collatz_1(long n) {
		
		assert(n > 0);
		
		if (n == 1) {
			
			return 1;
		}
		else if (n % 2 == 0) {
			
			return n / 2;
		
		} else {
			
			return n*3 + 1;
		
		}
	}
	/**
	 * Computes the Collatz sequence starting with n and ending with 1 
	 * @param n
	 * @return List<Long> list
	 */
	static List<Long> sequenceOf(long n) {
		
		List<Long> returnList = new ArrayList<Long>();
		long value = n;
		
		returnList.add(n);
		if (n <= 0) {
			return returnList;
		}

		while (value != 1) {
			returnList.add(collatz_1(value));
			value = collatz_1(value);
		}
		return returnList;
		
	}
	/**
	 * Computes the length of the Collatz sequence starting with n.
	 * @param n
	 * @return int length
	 */
	static int lengthOfSquence(long n) {
		
		return sequenceOf(n).size();
	}
	/**
	 * Computes the largest value in the Collatz sequence starting with n.
	 * @param n
	 * @return long max
	 */
	static long largestValueInSequence(long n) {
		
		List<Long> temp = sequenceOf(n);
		return Collections.max(temp);
	}
	/**
	 * Creates a list of pairs of (n, length) with equal lengths where lo <= n <= hi.
	 * @param lo
	 * @param hi
	 * @return List<Pair<Long, Integer>> returnList
	 */
	static List<Pair<Long, Integer>> equalLengthTwins(long lo, long hi) {
		
		List<Pair<Long, Integer>> returnList= new ArrayList<Pair<Long, Integer>>();
		
		for (long i = lo; i <= hi; i++) {
			if (lengthOfSquence(i) == lengthOfSquence(i + 1)) {
				returnList.add(new Pair<Long, Integer>(i, lengthOfSquence(i)));

			}
		}
		return returnList;
		
	}
	/**
	 * Creates a list of pairs of (n, length) with equal maximum values where lo <= n <= hi.
	 * @param lo
	 * @param hi
	 * @return List<Pair<Long, Long>> returnList
	 */
	static List<Pair<Long, Long>> equalMaxValueTwins(long lo, long hi) {

		List<Pair<Long, Long>> returnList = new ArrayList<Pair<Long, Long>>();
		
		for (long i = lo; i <= hi; i++) {
			if (largestValueInSequence(i) == largestValueInSequence(i + 1)) {
				returnList.add(new Pair<Long, Long>(i, largestValueInSequence(i)));
			}
		}
		return returnList;

	}
	/**
	 * Creates an integer array of size counts+1. 
	 * Array location i will contain the number of times 
	 * the number i occurs in the Collatz sequences from 1 to n, inclusive.
	 * @param n
	 * @param counts
	 * @return int[] array
	 */
	static int[] occurrences(long n, int counts) {
		
		int[] returnAry = new int[counts + 1];
		
		for (int i = (int) n; i > 0; i--) { // Count down from n
			
			List<Long> returnList = new ArrayList<Long>(); // Reset list each time
			returnList = sequenceOf(i);
			
			for (int j = 1; j < counts + 1; j++) { // count up
				if (returnList.contains((long) j)) { // match found
					returnAry[j] += 1;
				}
			}
		}
		return returnAry;
	}
		
}
