package backtracking;

import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

/**
 * February 9th / 2015
 * @author Joo-nam Kim
 *
 */
public class BacktrackingTest {

	@Test
	public void testSolve() {
		
		int[] one = new int[] {3, 6, 4, 1, 3, 4, 2, 5, 3, 0};
		int[] two = new int[] {2, 3, 1, 2, 0};
		int[] three = new int[] {2, 2, 0};
		int[] four = new int[] {2, 2, 1, 1, 0};
		int[] longPuzzle = new int[]{3,6,4,1,3,4,2,5,3,1,2,9,2};
		
		int[] cycle = new int[] {3, 1, 2, 3, 0};
		
		int[] oneItem = new int[] {1};
		
		Stack<Character> solution1 = new Stack<Character>();
		solution1.push('R');
		solution1.push('L');
		solution1.push('R');
		solution1.push('R');
		solution1.push('L');
		solution1.push('R');
		
		assertEquals(solution1, Backtracking.solve(one));
		
		Stack<Character> solution2 = new Stack<Character>();
		solution2.push('R');
		solution2.push('L');
		solution2.push('R');
		
		assertEquals(solution2, Backtracking.solve(two));

		Stack<Character> solution3 = new Stack<Character>();
		solution3.push('R');
		
		assertEquals(solution3, Backtracking.solve(three));
		
		Stack<Character> solution4 = new Stack<Character>();
		solution4.push('R');
		solution4.push('R');
		solution4.push('R');
		
		assertEquals(solution4, Backtracking.solve(four));
		
		// test cyclic graphs. 
		assertEquals(null, Backtracking.solve(cycle));
		
		// test one item
		Stack<Character> empty = new Stack<Character>();
		assertEquals(empty, Backtracking.solve(oneItem));
		
		// test a long puzzle
		Stack<Character> longPuzzleStack = new Stack<Character>();
		longPuzzleStack.push('R');
		longPuzzleStack.push('R');
		longPuzzleStack.push('R');
		longPuzzleStack.push('R');
		assertEquals(longPuzzleStack, Backtracking.solve(longPuzzle));
		
	}

	@Test
	public void testFindAllSolutions() {
		
		int[] one = new int[] {3, 6, 4, 1, 3, 4, 2, 5, 3, 0};
		
		int[] nullPuzzle = new int[] {3, 6, 0, 0, 0};
		
		int[] longPuzzle = new int[]{3,6,4,1,3,4,2,5,3,1,2,9,2};
		
		int[] cycle = new int[] {3, 1, 2, 3, 0};
		
		int[] oneItem = new int[] {1};
		
		Set<Stack<Character>> longPuzzleSolutions = new LinkedHashSet<Stack<Character>>();
		Stack<Character> stack1 = new Stack<Character>();
		stack1.push('R');
		stack1.push('L');
		stack1.push('R');
		stack1.push('R');
		stack1.push('L');
		stack1.push('R');
		stack1.push('R');
		stack1.push('R');
		Stack<Character> stack2 = new Stack<Character>();
		stack2.push('R');
		stack2.push('R');
		stack2.push('R');
		stack2.push('R');
		Stack<Character> stack3 = new Stack<Character>();
		stack3.push('R');
		stack3.push('R');
		stack3.push('L');
		stack3.push('R');
		stack3.push('L');
		stack3.push('R');
		stack3.push('R');
		stack3.push('L');
		stack3.push('R');
		stack3.push('R');
		stack3.push('R');
		Stack<Character> stack4 = new Stack<Character>();
		stack4.push('R');
		stack4.push('L');
		stack4.push('R');
		stack4.push('L');
		stack4.push('L');
		stack4.push('R');
		stack4.push('R');
		Stack<Character> stack5 = new Stack<Character>();
		stack5.push('R');
		stack5.push('R');
		stack5.push('L');
		stack5.push('R');
		stack5.push('R');
		Stack<Character> stack6 = new Stack<Character>();
		stack6.push('R');
		stack6.push('L');
		stack6.push('R');
		stack6.push('R');
		stack6.push('L');
		stack6.push('L');
		stack6.push('R');
		stack6.push('R');
		Stack<Character> stack7 = new Stack<Character>();
		stack7.push('R');
		stack7.push('L');
		stack7.push('R');
		stack7.push('L');
		stack7.push('R');
		stack7.push('R');
		
		Stack<Character> stack8 = new Stack<Character>();
		stack8.push('R');
		stack8.push('R');
		stack8.push('R');
		stack8.push('L');
		stack8.push('R');
		stack8.push('R');
		stack8.push('L');
		stack8.push('R');
		stack8.push('R');
		stack8.push('R');
		
		longPuzzleSolutions.add(stack1);
		longPuzzleSolutions.add(stack2);
		longPuzzleSolutions.add(stack3);
		longPuzzleSolutions.add(stack4);
		longPuzzleSolutions.add(stack5);
		longPuzzleSolutions.add(stack6);
		longPuzzleSolutions.add(stack7);
		longPuzzleSolutions.add(stack8);
		
		assertEquals(longPuzzleSolutions, Backtracking.findAllSolutions(longPuzzle));
		
		//NULL PUZZLE || NO SOLUTION PUZZLE
		assertEquals(new LinkedHashSet<Stack<Character>>(), Backtracking.findAllSolutions(nullPuzzle));
		Set<Stack<Character>> cyclePuzzle = new LinkedHashSet<Stack<Character>>();
		cyclePuzzle.add(null);
		assertEquals(new LinkedHashSet<Stack<Character>>(), Backtracking.findAllSolutions(cycle));
		
	
		Set<Stack<Character>> puzzleOneSolution = new LinkedHashSet<Stack<Character>>();
		Stack<Character> stack9 = new Stack<Character>();
		Stack<Character> stack10 = new Stack<Character>();
		Stack<Character> stack11 = new Stack<Character>();
		stack9.push('R');
		stack9.push('R');
		stack9.push('R');
		stack9.push('L');
		stack9.push('R');
		stack9.push('R');
		stack9.push('L');
		stack9.push('R');
		
		stack10.push('R');
		stack10.push('R');
		stack10.push('L');
		stack10.push('R');
		stack10.push('L');
		stack10.push('R');
		stack10.push('R');
		stack10.push('L');
		stack10.push('R');
		
		stack11.push('R');
		stack11.push('L');
		stack11.push('R');
		stack11.push('R');
		stack11.push('L');
		stack11.push('R');
		
		puzzleOneSolution.add(stack9);
		puzzleOneSolution.add(stack10);
		puzzleOneSolution.add(stack11);
		assertEquals(puzzleOneSolution, Backtracking.findAllSolutions(one));
		
		
		//ONE ELEMENT
		Set<Stack<Character>> oneElement = new LinkedHashSet<Stack<Character>>();
		Stack<Character> oneElementStack = new Stack<Character>();
		oneElement.add(oneElementStack);
		
		assertEquals(oneElement, Backtracking.findAllSolutions(oneItem));
		
		
		
		
	}

}
