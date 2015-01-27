package collatz;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class Collatz {

	public static void main(String[] args) {
		
		long testValue = 100000;
		int n = 101;
		
		//System.out.println(maximumLength(100000000)); // Test to see the maximum length of the sequences from 1 to 100,000,000. 
		//Number 63728127 had the maximum length : 950
		
		while (testValue <= 1000000) {
			
			doTimings(testValue);
			testValue += 100000;
			System.out.println();
			
		}
		for (int i = 1; i < n; i++) {
			System.out.println(i + ": length   " + Pair.lengthOfSquence(i) + ", max    " + Pair.largestValueInSequence(i) + ": " + Pair.sequenceOf(i));
		}
		System.out.println();
		System.out.println("Equal Length Twins are : " + Pair.equalLengthTwins(1, 100));
		System.out.println();
		System.out.println("Equal Max Value Twins are : " + Pair.equalMaxValueTwins(1, 100));
		System.out.println();
		
		System.out.println("Here is the list of occurrences in the range 1 to 100, starting from 1 to 1,000,000 : ");
		System.gc();
		long startTime = System.nanoTime();
		
		
		printArray(Pair.occurrences(1000000, 100));
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println();
		System.out.println("Occurrences took " + elapsedTime / 1000000000.0 + " seconds for (1000000, 100)");
		
		System.out.println();
		System.out.println("Number " + 63728127 + " had the maximum length : " + getLength(63728127) + " out of 1 to 100,000,000");
		
	}
	/**
	 * Finds the maximum sequence length from 1 to n
	 * @param n
	 * @return the maximum length
	 */
	private static String maximumLength(long n) {
		//Own method to see how far Collatz sequences can become.
		
		int value = 0;
		long count = 0;
		
		for (long i = 1; i < n; i++) {
			if (getLength(i) > getLength(count)) {

				count = i; 
				value = getLength(i);
			}
		}
		return "Number " + count + " had the maximum length : " + value; 
	}
	
	
	private static void printArray(int[] inputArray) {
		
		int spaceCount = 0;
		
		for (int i = 1; i < inputArray.length; i++) {
			
			System.out.print(i + ": "+ inputArray[i] + "    ");
			spaceCount++;
			
			if (spaceCount == 10) {
				System.out.println();
				System.out.println();
				spaceCount = 0;
			}
		}
	}
	/**
	 * Compute the length of each sequence separately, by repeatedly applying the definition, 
	 * and counting the number of steps required to reach 1.
	 * @param n
	 * @return int[] array
	 */
	static int[] simpleComputeSequenceLengths(long n){
		
		int[] returnAry = new int[(int) (n + 1)];
		int len = (int) n + 1;
	
		if (n <= 0) {
			return returnAry;
		}		
		if (n == 1) return new int[]{0, 1};
		
		for (int i = 1; i < len; i++) {
			
			returnAry[i] = getLength(i);
		}
		return returnAry;

	}
	/**
	 * computes the length of a Collatz sequence of a given value
	 * @param value
	 * @return int length
	 */
	static int getLength(long value) {
		
		int count = 0;
		
		if (value == 0) return 0;
		
		while (value != 1) {

			if (value % 2 == 0) {

				count += 1;
				value = value / 2; 
			} 
			else {
				
				count += 1;
				value = 3*value + 1;
			}
		}
		return count + 1; 
		
	}
	/**
	 * Compute the length of each sequence separately, by retrieving lengths that 
	 * we have already found previously. A faster method than the simpleComputeSequenceLengths
	 * @param n
	 * @return int[] array
	 */
	static int[] memoizedComputeSequenceLengths(long n) {
		
		int[] returnArray = new int[(int) n + 1];
		if (n <= 0) {
			return returnArray;
		} 
		if (n == 1) {
			return new int[] {0, 1};
		}
		
		returnArray[1] = 1;
		returnArray[2] = 2;
		
		// start from 2. 
		for (int i = 3; i < n + 1; i++) {
			
			long value = i;
			int count = 1;
			
			while (value >= i) { // when we hit a value we have already found, we just go retrieve it. 
					
				if (value % 2 == 0) {
					value = value / 2;
					count++;
				
				} else {
					value = 3*value + 1;
					count++;	
				}	
			}
			returnArray[i] = count + returnArray[(int)value] - 1; // retrieve and save it after computation.
		}
		return returnArray;		
	}
	/**
	 * Run timings for memoized and non-memoized methods.
	 * @param long n
	 */
	static void doTimings(long n) {
		
		System.gc();
		long startTime = System.nanoTime();
		
		memoizedComputeSequenceLengths(n);
		
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("Memoized Compute Seq took " + elapsedTime / 1000000000.0 + " seconds for value, " + n);
		
		System.gc();
		startTime = System.nanoTime();
		
		simpleComputeSequenceLengths(n);
		
		elapsedTime = System.nanoTime() - startTime;
		System.out.println("Simple Compute Seq took " + elapsedTime / 1000000000.0 + " seconds for value, " + n);
		
	}
}
