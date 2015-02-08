package squaresort;

import java.util.Comparator;
/**
 * 
 * @author Joo-nam Kim
 * 
 */
public class Squaresort {
	
	public static void main(String[] args) {
		
		/**
		 * If wanted to check timings, please uncomment below :
		 * 
		 * 1) m x n variables
		 * 2) a comparator you want to use
		 * 3) Creating a 2D array for loops
		 * 4) Finally, the timing code. 
		 * 
		 */
		
		/**1) Variables to hold m x n size to test with.**/ 
//		int m = 20;
//		int n = 20;
	
		/**2) Comparators**/ 
//		NameComparator nc = new NameComparator();
//		PayGradeComparator pgc = new PayGradeComparator();
//		EmployeeIdComparator eic = new EmployeeIdComparator();
		
		/**3) Create m x n size array**/
//		Person[][] testPersonTwo = new Person[m][n];
//		for (int i = 0; i < testPersonTwo.length; i++) {
//			for (int j = 0; j < testPersonTwo[i].length; j++) {
//				
//				testPersonTwo[i][j] = new Person();
//			}
//		}
		
		/**4) =====Timing=====**/
//		System.gc();
//		long startTime = System.nanoTime();
//		squaresort(testPersonTwo, pgc);
//		squaresort(testPersonTwo, eic);
//		squaresort(testPersonTwo, nc);
//		long elapsedTime = System.nanoTime() - startTime;
//		System.out.println("took " + elapsedTime / 1000000000.0 + " seconds for " + m + " rows and " + n + " columns"); // print timings in seconds
		
	}
	
	/**
	 * This method simply sorts the array nums in place 
	 * @param nums
	 */
	public static void linearSort(int[] nums) {
		
		int outer, inner, min;
				
		for (outer = 0; outer < nums.length - 1; outer++) {
			min = outer;
			/** Beginning Invariant : for all j <= outer, min == outer */
			for (int j = 0; j < outer; j++) { assert min == outer;}
			
			for(inner = outer + 1; inner < nums.length; inner++) {
				if(nums[inner] < nums[min]) {
					min = inner;
				}
				/** End Invariant : for all i, if outer <= i <= inner, then nums[min] <= nums[i]*/
				for (int i = outer; i <= inner; i++) {assert nums[min] <= nums[i];}
				
			}
			// nums[min] is least among nums[outer] .. nums[nums.length - 1]
			int temp = nums[outer];
			nums[outer] = nums[min];
			nums[min] = temp;
			/** End Invariant : for all i <= outer, if i < j then nums[i] <= nums[j]*/
			for (int i = 0; i <= outer; i++) {assert nums[i] <= nums[outer];}
		}
		/**Exit Condition */
		assert outer >= nums.length - 1;
	}
	/**
	 * this method sorts Person objects according to the given Comparator. 
	 * @param people
	 * @param comp
	 */
	public static void linearSort(Person[] people, Comparator<Person> comp) {
		
		int outer, inner, min; 
		
		for (outer = 0; outer < people.length - 1; outer++) {
			min = outer; 
			for (inner = outer + 1; inner < people.length; inner++) {
				if (comp.compare(people[inner], people[min]) < 0) {
					min = inner;
				}
				/** Invariant : for all i, if outer <= i <= inner, then people[min] <= people[i]*/
				for (int i = outer; i <= inner; i++) { assert (comp.compare(people[min], people[i]) < 0 || comp.compare(people[min], people[i]) == 0);}
			}
			// people[min] is least among people[outer] .. people[nums.length - 1]
			Person temp = people[outer];
			people[outer] = people[min];
			people[min] = temp;
			
			/** Invariant : for all i <= outer, if i < j then people[i] <= people[j] */
			for (int i = 0; i <= outer; i++) { assert (comp.compare(people[i], people[outer]) < 0 || comp.compare(people[i], people[outer]) == 0); }
		}
		/** Exit Condition */
		assert outer >= people.length - 1;
	}
	
	/**
	 * This method sorts a rectangular (not ragged) array in place. 
	 * By "sorted", every row and every column is sorted in ascending order.
	 * @param people
	 * @param comp
	 */
	public static void squaresort(Person[][] people, Comparator<Person> comp) {
	
		int i = 0;
		int k = 0;
		int j = 0;
		
		for (i = 0; i < people.length; i++){
			for (j = 0; j < people[0].length - 1; j++){
				for(k = 0; k < people[0].length - 1; k++){
					
					if (comp.compare(people[i][k], people[i][k + 1]) > 0){ //check if element to the right is greater
						Person temp = people[i][k];						   //If so, swap. 
						people[i][k] = people[i][k + 1];
						people[i][k+1] = temp;
					}
				} // end of innermost loop
				/** First loop invariant : for all indices (first) < i and for all indices (second) < k, 
										  people[first][second] <= people[first][second + 1] */
				for (int first = 0; first < i; first++) {
					for (int second = 0; second < k; second++) {
						assert comp.compare(people[first][second], people[first][second + 1]) <= 0;
					}
				}
			} // end of second loop
			/** Second loop invariant : for all l < i, m < j, every element is sorted; people[l][m] < people[l][m + 1] */
			for (int l = 0; l < i; l++) {
				for (int m = 0; m < j; m++) {
					assert comp.compare(people[l][m], people[l][m + 1]) <= 0;
				}
			}
		} //end of outer loop
		/**Exit condition**/
		assert i >= people.length;
		/**Last loop invariant : for all i < people.length, for all j < people[i].length, every row is sorted;
						    	 people[i][j] <= people[i][j + 1]**/
		for (i = 0; i < people.length; i++) {
			for (j = 0; j < people[i].length - 1; j++) {
				assert comp.compare(people[i][j], people[i][j + 1]) <= 0; 
			}
		}
		
		// Start of the second block of loops
		for (i = 0; i < people[0].length; i++){
			for (j = 0; j < people.length; j++){
				for(k = 0; k < people.length - 1; k++){

					if (comp.compare(people[k][i], people[k + 1][i]) > 0){ //if element below is greater,
						Person temp = people[k][i];						   //swap
						people[k][i] = people[k + 1][i];
						people[k + 1][i] = temp;
						
					}
				} //end of innermost loop
				/** First loop invariant : for all indices (first) < k and for all indices (second) < i, 
				  people[first][second] <= people[first + 1][second]
				  */
				for (int first = 0; first < k; first++){
					for (int second = 0; second < i; second++){
						assert comp.compare(people[first][second], people[first + 1][second]) <= 0;
					}
				}
			}// end of second loop
			/** Second loop invariant : We are guaranteed to have the columns up to this point to be sorted; 
			 * for all m < k, l < i then people[m][l] <= people[m + 1][l] 
			 */
			for (int m = 0; m < k; m++) {
				for (int l = 0; l < i; l++) {
					assert comp.compare(people[m][l], people[m + 1][l]) <= 0;
				}
			}
		}//end of outer loop
		/**Exit Condition*/
		assert i >= people[0].length;
		/**Last loop invariant : for all i < columns, for all k < rows, EVERY column is sorted;
	    people[k][i] <= people[k + 1][i]**/
		for (i = 0; i < people[0].length; i++) {
			for (j = 0; j < people.length; j++) {
				for(k = 0; k < people.length - 1; k++) {
					assert comp.compare(people[k][i], people[k + 1][i]) <= 0; 
				}
			}
		}
	}	
}
