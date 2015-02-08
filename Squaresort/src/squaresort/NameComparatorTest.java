package squaresort;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class NameComparatorTest {

	@Test
	public void testCompare() {
		
		Person one = new Person("Alice", "Abe", 1);
		Person two = new Person("Bob", "Boobley", 2);
		
		Person three = new Person("Chris", "Catering", 3);
		Person four = new Person("Dan", "Catering", 4);
		
		Person five = new Person("Kao", "KIM", 5);
		Person six = new Person("Koo", "Kim", 6);
		Person seven = new Person("LAN", "LAN", 7);
		Person eight = new Person("lan", "lan", 8);
		
		NameComparator nc = new NameComparator();
		
		assertTrue(nc.compare(one, two) < 0);
		assertTrue(nc.compare(one, one) == 0);
		assertTrue(nc.compare(two, one) > 0);
		
		// same surname but different given names
		assertEquals(-1, nc.compare(three, four));
		assertEquals(1, nc.compare(four, three));
		
		// First letters are equal for both sur/given names then compare by 
		// the next letter that follows. 
//		int a = five.givenName - six.givenName;
		assertTrue(nc.compare(five, six) < 0);
		
		// Compare UPPER vs. LOWER with same names. 
		assertTrue(nc.compare(seven, eight) == 0);
		
		
	}

}
