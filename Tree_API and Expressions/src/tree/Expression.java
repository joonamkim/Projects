package tree;

import java.util.Iterator;

/**
 * Class for representing simple arithmetic expressions.
 * @author Joo-nam Kim
 * @version Feb 10, 2015
 */
public class Expression {
	Tree<String> expressionTree;

	/**
	 * Constructs a Tree<String> representing the given arithmetic expression,
	 * then verifies that the newly created Tree is valid as an expression.
	 * If the Tree is invalid, throws an IllegalArgumentException.<br>
	 * Here are the validity rules:<ul>
	 * <li>The value of each node must be one of "+", "-", "*", "/",
	 *     or a String representing an unsigned integer.</li>
	 * <li>If a node has value "+" or "*", it must have two or more children.</li>
	 * <li>If a node has value "-" or "/", it must have exactly two children.</li>
	 * <li>If a node contains a numeric string, it must be a leaf.</li></ul>
	 * Note that the input parameter uses prefix notation, for example:
	 * "+ (5 10 -( *(15 20) 25) 30)"
	 * @param expression The String representation of the expression to be constructed.
	 */
	public Expression(String expression) {

		expressionTree = Tree.parse(expression);

		if (!valid(expressionTree)) {
			throw new IllegalArgumentException("Invalid expression: " + expression);
		}
	}

	/**
	 * Tests whether the given Tree represents a valid Expression.
	 * @param tree The input tree.
	 * @return <code>true</code> iff the Tree is a valid Expression.
	 */
	private boolean valid(Tree<String> tree) {

		// parentheses match
		if (!Tree.doParenthesesMatch(tree.toString())) {
			return false; 
		}
		if (!isValidOps(tree)) {
			return false; 
		}
		if (((tree.getValue().equals("+")) || (tree.getValue().equals("*"))) && (tree.getNumberOfChildren() < 2)){
			return false;
		}
		if (((tree.getValue().equals("-")) || (tree.getValue().equals("/"))) && (tree.getNumberOfChildren() > 2)){
			return false;
		}
		if (isNumeric(tree.getValue()) && (tree.getNumberOfChildren() != 0)) {
			return false;
		}
		if (tree.getValue().equals(")")) {
			return true;
		}
		for (Iterator<Tree<String>> children = tree.iterator(); children.hasNext();){
			if (!valid(children.next())){
				return false;
			}
		}
		return true;

	}
	/**
	 * 
	 * @param op
	 * @return
	 */
	private boolean isValidOps(Tree<String> op) {

		String value = op.getValue();
		return ((value.equals("+")) || (value.equals("-")) || (value.equals("*")) || (value.equals("/")) || (value.matches("[0-9]+")));

	}

	/**
	 * Evaluates this Expression.
	 * @return The value of this Expression.
	 */
	public int evaluate() {

		return evaluate(expressionTree);
	}

	/**
	 * Evaluates the given Tree, which must represent a valid Expression.
	 * @return The value of this Expression.
	 */
	private int evaluate(Tree<String> tree) {
		// Helper method for evaluate()

		String value = tree.getValue();

		if (isNumeric(value)) {
			return Integer.parseInt(value);
		}
		if ((value.equals("+"))) {

			int runningTotal = 0;
			for (int i = 0; i < tree.getNumberOfChildren(); i++) {

				int currentTotal = evaluate(tree.getChild(i));
				runningTotal += currentTotal;
			}
			return runningTotal;
		}
		else if ((value.equals("-"))) {
			int runningSub = 0;
			int first = evaluate(tree.getChild(0));
			int second = evaluate(tree.getChild(1));
			runningSub = first - second;
			return runningSub; 
		}
		else if ((value.equals("*"))) {

			int runningTotal = 1;
			for (int i = 0; i < tree.getNumberOfChildren(); i++) {
				int currentTotal = evaluate(tree.getChild(i));
				runningTotal *= currentTotal;
			}
			return runningTotal;
		}
		else if ((value.equals("/"))) {
			int runningDiv = 1 ;
			int first = evaluate(tree.getChild(0)) ;
			int second = evaluate(tree.getChild(1)) ;
			runningDiv = first / second ;
			return runningDiv;
		}
		else {
			return Integer.parseInt(value);
		}

	}
	/**
	 * Check if string is numeric.
	 * @param str
	 * @return true if numeric false otherwise. 
	 */
	@SuppressWarnings("unused")
	private static boolean isNumeric(String str)  
	{  
		try {  
			double d = Double.parseDouble(str);  
		}  
		catch (NumberFormatException nfe)  
		{  
			return false;  
		}  
		return true;  
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return toString(expressionTree, expressionTree.getValue(), new String());
		
	} 
	/**
	 * Helper for toString method
	 * @param tree
	 * @param operator (initial operator) ex. + (50 ... - (20 10)) ==> "+" 
	 * @param finalString
	 * @return
	 */
	private static String toString(Tree<String> tree, String operator, String finalString) {

		finalString += "(";

		for (int i = 0; i < tree.getNumberOfChildren(); i++) {
			if (tree.getChild(i).getNumberOfChildren() > 0) {
				finalString = toString(tree.getChild(i), tree.getChild(i).getValue(), finalString);
			} 
			else {
				finalString += tree.getChild(i);

			}
			if (i != tree.getNumberOfChildren() - 1) { //insert operators at correct places.
				finalString += operator + " ";
			}

		}
		return finalString + ")";  
	}
}
