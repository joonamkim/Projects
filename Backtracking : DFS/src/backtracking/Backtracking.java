package backtracking;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
/**
 * February 9th / 2015
 * @author Joo-nam Kim
 *
 */
public class Backtracking {

	/**
	 *  Finds adjacent nodes aka. possible moves from current position. 
	 * 
	 * @param puzzle
	 * @param current position
	 * @return ArrayList of possible move indices.
	 */
	private static ArrayList<Integer> findAdjacentEdges(int[] puzzle, int current) {
		
		ArrayList<Integer> adjacentEdges = new ArrayList<Integer>();
		
		if (puzzle.length == 1) return adjacentEdges; // if puzzle has only one element, there can't be adjacent edges. 
		
		//calculate possible moves. 
		int leftChild = current - puzzle[current]; 
		int rightChild = current + puzzle[current];

		if (leftChild >= 0 && current < puzzle.length - 1) { // if within legal boundaries
			adjacentEdges.add(leftChild);
		}
		if (rightChild < (puzzle.length) && current < puzzle.length - 1) {
			adjacentEdges.add(rightChild);
		}

		return adjacentEdges;

	}	
	/**
	 * Finds all paths of a given puzzle (a DAG)
	 * 
	 * Example : given a puzzle {3, 6, 4, 1, 3, 4, 2, 5, 3, 0}, 
	 * 			 provides paths in terms of indices {3, 2, 6, 8, 5, 9},...
	 * 
	 * @param current
	 * @param puzzle
	 * @param path
	 * @param allPaths
	 */
	private static void findAllPaths(int current, int[] puzzle, Stack<Integer> path, Set<ArrayList<Integer>> allPaths) {
		
		/*
		 * Uses recursion to find every possible path, and every time a path is found, 
		 * it gets added to the set of all paths. Does not return anything, but
		 * in another method, it changes a local or (global if wanted) "Set" variable that 
		 * a user could use after all paths have been added. 
		 * 
		 * Usage example : LOCAL SCOPE
		 * 
		 * Set<ArrayList<Integer>> setToBeUsed = new Set<ArrayList<Integer>>();
		 * findAllPaths(current, puzzle, path, setToBeUsed);
		 * 
		 * Now, "setToBeUsed" holds all possible paths. 
		 */
		
		int goal = puzzle.length - 1; 

		path.add(current);

		if (current == goal) { //when reached a goal, add to all paths and return;

			allPaths.add(new ArrayList<Integer>(path));
			path.pop();
			return;
		}
		ArrayList<Integer> edges = findAdjacentEdges(puzzle, current); // get adjacent edges (possible moves of 'current')

		
		for (int edge : edges) {
			if (!path.contains(edge)) {

				findAllPaths(edge, puzzle, path, allPaths);

			}


		}

		path.pop();


	}
	/**
	 * Turn a Set of ArrayList of Integers into ArrayList of ArrayLists of integers.
	 * 
	 * @param current
	 * @param puzzle
	 * @return an ArrayList
	 */
	private static ArrayList<ArrayList<Integer>> toArrayList(int current, int[] puzzle) {
		
		/*	For the purposes of situations where I would want to access a specific index
		 *  of a specific stack in a set, turn a set of Stack<Integer>s to ArrayLists.
		 */
		Set<ArrayList<Integer>> paths = new LinkedHashSet<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> turnToArray = new ArrayList<ArrayList<Integer>>();
		findAllPaths(current, puzzle, new Stack<Integer>(), paths);
	
		Iterator<ArrayList<Integer>> iter = paths.iterator(); 
		while (iter.hasNext()) {
			turnToArray.add(iter.next());
		}

		return turnToArray;

	}
	/**
	 * Given a puzzle with paths in numbers (indices), 
	 * generates a ('R' or 'L') move specific solution. 
	 * @param ArrayList, puzzle	
	 * @return stack of moves in characters. 
	 */
	private static Stack<Character> turnToCharacters(ArrayList<Integer> puzzle) {

		Stack<Character> moves = new Stack<Character>();

		moves.push('R');

		for (int i = 0; i < puzzle.size() - 1; i++) {
			if (puzzle.get(i) < puzzle.get(i + 1)) {
				moves.push('R');
			}
			else {

				moves.push('L');

			}

		}
		return moves; 

	}
	/**
	 * Given a set of solutions, finds the shortest path solution.
	 * 
	 * @param puzzle
	 * @return a shortest path solution / null if no solution exists.
	 */
	private static Stack<Character> shortestPath(int[] puzzle) {
		ArrayList<ArrayList<Integer>> turnToSet = new ArrayList<ArrayList<Integer>>();
		Stack<Character> newStack = new Stack<Character>();
		int min = 0; 

		turnToSet = toArrayList(puzzle[0], puzzle);
		
		
		if (!turnToSet.isEmpty() && !turnToSet.get(0).isEmpty()) { // make sure the Set is not empty && Stack is not empty.
			
			min = turnToSet.get(0).size();
			newStack = turnToCharacters(turnToSet.get(0));
			for (int i = 0; i < turnToSet.size(); i++) { //iterate till find the shortest path solution.

				if (turnToSet.get(i).size() < min) { 
					min = turnToSet.get(i).size();
					newStack = turnToCharacters(turnToSet.get(i));
				}

			}
			return newStack;

		}
		return null;

	}
	
	/**
	 * Solves a puzzle by finding the shortest path solution.
	 * 
	 * @param puzzle
	 * @return a solved, shortest path, solution / null if no solution exists
	 */
	public static Stack<Character> solve(int[] puzzle) {
		
		//puzzle itself is a solution
		if (puzzle.length == 1) {
			return new Stack<Character>();
		}

		return shortestPath(puzzle);



	}
	
	/**
	 * Given a puzzle, generates all possible solutions to the given puzzle. 
	 * Puzzles are solved so that the solutions are described in their 
	 * directions. 
	 * 
	 * Example : R -> L -> R -> R (R = right , L = left)
	 * 
	 * @param puzzle
	 * @return Set<Stack<Character>> all possible acyclic solutions
	 */
	public static Set<Stack<Character>> findAllSolutions(int[] puzzle) {

		ArrayList<ArrayList<Integer>> turnToSet = new ArrayList<ArrayList<Integer>>();
		Set<Stack<Character>> returnSet = new LinkedHashSet<Stack<Character>>();
		turnToSet = toArrayList(puzzle[0], puzzle); /*toArrayList method calls 'findAllPaths', so the returned ArrayList 
													  should hold all the solutions. */	
		// only one item, return a set with an empty stack
		if (puzzle.length == 1) {
			Stack<Character> empty = new Stack<Character>();
			returnSet.add(empty);
			return returnSet;
					
		}		
		// empty : return an empty set
		else if (turnToSet.isEmpty()) return new LinkedHashSet<Stack<Character>>();
		
		// else : convert index-based solutions to R/L (character) solutions and add to the return set.
		else {
			for (int i = 0; i < turnToSet.size(); i++) {

				returnSet.add(turnToCharacters(turnToSet.get(i)));
				
			}
			return returnSet;
			
		}
		
	}

}
