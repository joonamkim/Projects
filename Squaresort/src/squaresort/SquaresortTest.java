package squaresort;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * 
 * @author Joo-nam Kim
 *
 */
public class SquaresortTest {

	@Test
	public void testLinearSortIntArray() {
		
		int[] test = new int[] {4, 5, 7 ,13, 12, 2, 3, 1, 6, 8, 9, 10, 11};
		int[] testSorted = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		
		int[] test2 = new int[] {-5, 4, 2 ,1};
		int[] test2Sorted = new int[] {-5, 1, 2 ,4};
		
		int[] test3 = new int[] {1, 1, 1 ,1};
		int[] test3Sorted = new int[] {1, 1, 1 ,1};
		
		Squaresort.linearSort(test);
		Squaresort.linearSort(test2);
		Squaresort.linearSort(test3);
		assertArrayEquals(testSorted, test);
		assertArrayEquals(test2Sorted, test2);
		assertArrayEquals(test3Sorted, test3);
			
	}

	@Test
	public void testLinearSortPersonArrayComparatorOfPerson() {
		
		Person five = new Person("Joo", "Jim", 5);
		Person four = new Person("Dan", "Dearson", 4);
		Person one = new Person("Alice", "Abe", 1);
		Person two = new Person("Bob", "Boobley", 2);
		Person three = new Person("Chris", "Catering", 3);
		
		NameComparator nameComp = new NameComparator();
		PayGradeComparator payComp = new PayGradeComparator();
		
		Person[] test = new Person[] {four, five, two, three, one};
		Person[] testAnswer = new Person[] {one, two, three, four, five};
		
		Squaresort.linearSort(test, nameComp);
		assertArrayEquals(testAnswer, test);
		
		Person[] test1 = new Person[] {four, five, two, three, one};
		Person[] testAnswer1 = new Person[] {one, two, three, four, five};
		
		Squaresort.linearSort(test1, payComp);
		assertArrayEquals(testAnswer1, test1);
		
		Person[] test2 = new Person[] {one, one, one, one, one};
		Person[] testAnswer2 = new Person[] {one, one, one, one, one};
		
		Squaresort.linearSort(test2, payComp);
		assertArrayEquals(testAnswer2, test2);
		
		Squaresort.linearSort(test2, nameComp);
		assertArrayEquals(testAnswer2, test2);
		
		
		Person[] test3 = new Person[] {one, one, one, two, one};
		Person[] testAnswer3 = new Person[] {one, one, one, one, two};
		
		Squaresort.linearSort(test3, nameComp);
		assertArrayEquals(testAnswer3, test3);
		
	}

	@Test
	public void testSquaresort() {
		
		Person one = new Person("Alice", "Abe", 1);
		Person two = new Person("Bob", "Boobley", 2);
		Person three = new Person("Chris", "Catering", 3);
		Person four = new Person("Dan", "Dearson", 4);
		Person five = new Person("Joo", "Jim", 5);
		Person six = new Person("Koo", "Kim", 6);
		Person seven = new Person("Lan", "Learson", 7);
		Person eight = new Person("Mlice", "Mabe", 8);
		Person nine = new Person("Ob", "Oobley", 9);
		Person ten = new Person("Pris", "Patering", 10);
		
		Person eleven = new Person("Qlice", "Qbe", 11);
		Person twelve = new Person("Rob", "Roobley", 12);
		Person thirteen = new Person("Shris", "Satering", 13);
		Person fourteen = new Person("Tan", "Tearson", 14);
		Person fifteen = new Person("Uoo", "Uim", 15);
		Person sixteen = new Person("Woo", "Uim", 16);
		Person seventeen = new Person("Xan", "Xearson", 17);
		Person eighteen = new Person("Ylice", "Yabe", 18);
		Person nineteen = new Person("Zb", "Zabley", 19);
		Person twenty = new Person("Zris", "Zabley", 20);
		
		Person[][] random = new Person[][]{{twenty, one, sixteen, six, two}, 
										   {fourteen, three, eighteen, five, thirteen}, 
										   {seventeen, four, seven, nineteen, eight},
										   {nine, fifteen, ten, twelve,eleven}};
		Person[][] random2 = new Person[][]{{twenty, one, sixteen, six, two}, 
				   {fourteen, three, eighteen, five, thirteen}, 
				   {seventeen, four, seven, nineteen, eight},
				   {nine, fifteen, ten, twelve,eleven}};
		
		Person[][] randomAnswer = new Person[][]{{one, two, six, twelve, fifteen}, 
												 {three, five, eight, fourteen, eighteen},
												 {four, seven, eleven, sixteen, nineteen},
												 {nine, ten, thirteen, seventeen, twenty}};
		NameComparator nc = new NameComparator();
		PayGradeComparator pgc = new PayGradeComparator();
		
		Squaresort.squaresort(random, nc);
		Squaresort.squaresort(random2, pgc);
		
		for (int i = 0; i < random.length; i++) {
			for (int j = 0; j < random[i].length; j++) {
				assertEquals(randomAnswer[i][j].surname, random[i][j].surname);
				assertEquals(randomAnswer[i][j].givenName, random[i][j].givenName);
				assertEquals(randomAnswer[i][j].payGrade, random2[i][j].payGrade);
			}
		}
		// test single array
		Person[][] singleValue = new Person[][]{{one}};
		Person[][] singleValueAnswer = new Person[][]{{one}};
		Squaresort.squaresort(singleValue, nc);
		assertArrayEquals(singleValueAnswer, singleValue);
		
		
		Person[][] singleValue2 = new Person[][]{{two, one}};
		Person[][] singleValueAnswer2 = new Person[][]{{one, two}};
		Squaresort.squaresort(singleValue2, nc);
		assertArrayEquals(singleValueAnswer2, singleValue2);
		
		// test empty
		Person[][] emptyArray = new Person[][]{{}};
		Person[][] emptyArrayAnswer = new Person[][]{{}};
		Squaresort.squaresort(emptyArray, nc);
		assertArrayEquals(emptyArrayAnswer, emptyArray);
		
	}

}
