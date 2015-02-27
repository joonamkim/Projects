package tree;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test cases for Expression Class. 
 * @author Joo-nam Kim
 *
 */
public class ExpressionTest {
	
	@Test
	public void testExpression() {
		//These don't throw exceptions. 
		Expression test = new Expression( "+ (5 10 -( *(15 20) 25) 30)");
		Expression test1 = new Expression( "+ (5 10 25)");
		Expression test2 = new Expression( "+ (5 10 + ( *(15 20) *(20 15) 25) 30)");
		Expression test3 = new Expression(" - (50 / (- (200 100) /(6 3)))");
		Expression test4 = new Expression(" - (50 - (50 - (50 - (50 40))))");
		test.evaluate();
		test1.evaluate();
		test2.evaluate();
		test3.evaluate();
		test4.evaluate();
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidExpressions() {
		Expression test = new Expression( "- (5 10 -( *(15 20) 25) 30)"); // - more than 2
		Expression test1 = new Expression( "+ (5 (10 25)"); // not matching parentheses
		Expression test2 = new Expression( "+ (5)"); //one argument
		Expression test3 = new Expression(" - (50 / (- (200 100) & (6 3)))"); //invalid op
		Expression test4 = new Expression(" - (-50 - (50 - (50 - (50 40))))"); //singed integer
		
		test.evaluate();
		test1.evaluate();
		test2.evaluate();
		test3.evaluate();
		test4.evaluate();
		
		
	}

	@Test
	public void testEvaluate() {
		
		Expression test = new Expression( "+ (5 10 -( *(15 20) 25) 30)");
		Expression test1 = new Expression( "+ (5 10 25)");
		Expression test2 = new Expression( "+ (5 10 + ( *(15 20) *(20 15) 25) 30)");
		Expression test3 = new Expression(" - (50 / (- (200 100) /(6 3)))");
		Expression test4 = new Expression(" - (50 - (50 - (50 - (50 40))))");
		
		assertEquals(320, test.evaluate());
		assertEquals(40, test1.evaluate());
		assertEquals(670, test2.evaluate());
		assertEquals(0, test3.evaluate());
		assertEquals(40, test4.evaluate());
		


	}
	@Test(expected = ArithmeticException.class)
	public void testDivisionByZeroException() {
		Expression test4 = new Expression(" / (50 0)");
		test4.evaluate();
	}

	@Test
	public void testToString() {
		Expression test = new Expression( "+ (5 10 -( *(15 20) 25) 30)");
		Expression test1 = new Expression( "+ (5 10 25)");
		Expression test2 = new Expression( "+ (5 10 + ( *(15 20) *(20 15) 25) 30)");
		Expression test3 = new Expression(" - (50 / (- (100 200) /(6 3)))");
		Expression test4 = new Expression(" - (50 - (50 - (50 -(50 40))))");
		
		assertEquals("(5 + 10 + ((15 * 20 )- 25 )+ 30 )", test.toString());
		assertEquals("(5 + 10 + 25 )", test1.toString());
		assertEquals("(5 + 10 + ((15 * 20 )+ (20 * 15 )+ 25 )+ 30 )", test2.toString());
		assertEquals("(50 - ((100 - 200 )/ (6 / 3 )))", test3.toString());
		assertEquals("(50 - (50 - (50 - (50 - 40 ))))", test4.toString());
		
		
	}

}
