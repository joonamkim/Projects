package squaresort;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Joo-nam Kim
 *
 */
public class PayGradeComparatorTest {
	@Test
	public void testCompare() {
		
		PayGradeComparator pgc = new PayGradeComparator();
		Person one = new Person("Alice", "Abe", 1);
		Person two = new Person("Bob", "Boobley", 1);
		Person three = new Person("Chris", "Catering", 3);
		Person four = new Person("Dan", "Dearson", 4);
	
		assertTrue(pgc.compare(one, two) == 0);
		assertTrue(pgc.compare(two, three) < 0);
		assertTrue(pgc.compare(four, two) > 0);
		
		
		
	}

}
