package tree;

import static org.junit.Assert.*;

import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for Test<V> class
 * 
 * @author Joo-nam Kim
 *
 */

public class TreeTest {

	Tree<String> root, child2, child3, child4, child5, child6, child7, child8, child9;

	@Before
	public void setUp() throws Exception {


		child2 = new Tree<String>("two");
		child5 = new Tree<String>("five");
		child7 = new Tree<String>("seven");
		child8 = new Tree<String>("eight");
		child9 = new Tree<String>("nine");
		child6 = new Tree<String>("six", child8);
		child4 = new Tree<String>("four", child6, child7);
		child3 = new Tree<String>("three", child5);
		root = new Tree<String>("one", child2, child3, child4, child9);
	}

	@Test
	public void testHashCode() {

		Tree<String> rootA = new Tree<String>("one");
		Tree<String> nodeA1 = new Tree<String>("two");
		Tree<String> nodeA2 = new Tree<String>("three");

		rootA.addChild(nodeA1);
		rootA.addChild(nodeA2);

		Tree<String> nodeA3 = new Tree<String>("four");
		Tree<String> nodeA4 = new Tree<String>("five");

		nodeA2.addChild(nodeA3);
		nodeA2.addChild(nodeA4);

		//right
		Tree<String> nodeA5 = new Tree<String>("six");
		Tree<String> nodeA6 = new Tree<String>("seven");
		Tree<String> nodeA7 = new Tree<String>("eight");

		nodeA4.addChild(nodeA5);
		nodeA4.addChild(nodeA6);
		nodeA4.addChild(nodeA7);
		rootA.hashCode();

		assertEquals(322807725, rootA.hashCode());
		rootA = new Tree<String>("one");
		root = new Tree<String>("one");
		assertEquals(root.hashCode(), rootA.hashCode());

	}
	@Test
	public void testTree() {
		Tree<String> tree1 = new Tree<String>("what", child2, child3, child4);
		Tree<String> tree2 = new Tree<String>("what", child2, child3, child4);
		assertEquals(tree1, tree2);
		assertEquals(tree1.getValue(), "what");
		assertNotEquals(2, tree2.getNumberOfChildren());

	}

	@Test
	public void testSetValue() {
		root.setValue("what");
		assertEquals(root.getValue(), "what");
	}

	@Test
	public void testGetValue() {

		assertEquals(root.getValue(), "one");
		assertFalse(root.getValue().equals("what"));
	}

	@Test
	public void testAddChildIntTreeOfV() {

		Tree<String> tree1 = new Tree<String>("tree", child8);
		tree1.addChild(0, child2);
		Tree<String> tree2 = new Tree<String>("tree", child2, child8);
		assertEquals(tree1, tree2);

	}

	@Test
	public void testAddChildTreeOfV() {

		Tree<String> tree1 = new Tree<String>("tree");
		tree1.addChild(child2);
		Tree<String> tree2 = new Tree<String>("tree", child2);
		assertEquals(tree1, tree2);
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testAddChildException1() {
		child2.addChild(root);
	}

	@Test
	@SuppressWarnings("unchecked")
	public final void testAddChildren() {
		Tree<String> tree1 = new Tree<String>("tree");
		Tree<String> tree2 = new Tree<String>("tree", child2, child9);

		tree1.addChildren(child2, child9);
		assertEquals(tree1, tree2);
	}

	@SuppressWarnings("unchecked")
	@Test(expected = IllegalArgumentException.class)
	public final void testAddChildrenException() {
		child8.addChildren(child2, child4);
	}

	@Test
	public final void testGetNumberOfChildren() {
		assertEquals(root.getNumberOfChildren(), 4);
		assertEquals(child3.getNumberOfChildren(), 1);
		child3.addChild(new Tree<String>(""));
		assertEquals(child3.getNumberOfChildren(), 2);
		assertEquals(child7.getNumberOfChildren(), 0);
	}
	@Test
	public final void testGetChild() {

		assertEquals(root.getChild(0), child2);
		assertFalse(root.getChild(1).equals(child8));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public final void testGetChildException() {
		root.getChild(1231237);
	}


	@SuppressWarnings("unchecked")
	@Test
	public void testIterator() {
		Tree<String> test1 = Tree.parse("+ (3 12 -( 5 *(15 20)))");
		Tree<String> test2 = Tree.parse("*(1 2 3 4 5 7 68 93)");
		Tree<String> test3 = Tree.parse("-(6 66666)");
		test1.addChildren(test2, test3);
		Iterator<Tree<String>> iterator = test1.iterator(); 
		assertTrue(iterator.hasNext());
		assertEquals("3", iterator.next().getValue());
		assertEquals("12", iterator.next().getValue());

	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public final void testContains() {
		assertTrue(root.contains(child8));
		assertTrue(root.contains(child2));
		assertTrue(root.contains(child7));
		assertTrue(root.contains(root));
		assertFalse(child5.contains(root));

		Tree rootA = new Tree("one");
		Tree nodeA1 = new Tree("two");
		Tree nodeA2 = new Tree("three");

		rootA.addChild(nodeA1);
		rootA.addChild(nodeA2);

		//left 
		Tree nodeA3 = new Tree("four");
		Tree nodeA4 = new Tree("five");

		nodeA2.addChild(nodeA3);
		nodeA2.addChild(nodeA4);

		//right
		Tree nodeA5 = new Tree("six");
		Tree nodeA6 = new Tree("seven");
		Tree nodeA7 = new Tree("eight");

		nodeA4.addChild(nodeA5);
		nodeA4.addChild(nodeA6);
		nodeA4.addChild(nodeA7);

		assertTrue(rootA.contains(nodeA4));
		assertTrue(rootA.contains(nodeA6));
		assertTrue(rootA.contains(nodeA5));
		assertTrue(rootA.contains(nodeA7));
	}

	@Test
	public final void testToString() {
		assertEquals(root.toString(), "one(two three(five)four(six(eight)seven)nine)");
		Tree<String> rootA = new Tree<String>("one");
		Tree<String> nodeA1 = new Tree<String>("two");
		Tree<String> nodeA2 = new Tree<String>("three");

		rootA.addChild(nodeA1);
		rootA.addChild(nodeA2);

		Tree<String> nodeA3 = new Tree<String>("four");
		Tree<String> nodeA4 = new Tree<String>("five");

		nodeA2.addChild(nodeA3);
		nodeA2.addChild(nodeA4);

		//right
		Tree<String> nodeA5 = new Tree<String>("six");
		Tree<String> nodeA6 = new Tree<String>("seven");
		Tree<String> nodeA7 = new Tree<String>("eight");

		nodeA4.addChild(nodeA5);
		nodeA4.addChild(nodeA6);
		nodeA4.addChild(nodeA7);
		
		assertEquals("one(two three(four five(six seven eight)))", rootA.toString());
	}


	@SuppressWarnings({ "rawtypes", "unchecked"})
	@Test
	public void testParseString() {

		assertEquals(Tree.parse("one ( two three(five)four  ( six(eight ) seven) nine)  "), root);
		assertFalse(Tree.parse("one ( two three(five)four  ( six(eight ) ) nine)  ").equals(root));
		Tree rootA = new Tree("one");
		Tree nodeA1 = new Tree("two");
		Tree nodeA2 = new Tree("three");

		rootA.addChild(nodeA1);
		rootA.addChild(nodeA2);

		//left 
		Tree nodeA3 = new Tree("four");
		Tree nodeA4 = new Tree("five");

		nodeA2.addChild(nodeA3);
		nodeA2.addChild(nodeA4);

		assertEquals(Tree.parse("one(two three(four five))"), rootA);

		//right
		Tree nodeA5 = new Tree("six");
		Tree nodeA6 = new Tree("seven");
		Tree nodeA7 = new Tree("eight");

		nodeA4.addChild(nodeA5);
		nodeA4.addChild(nodeA6);
		nodeA4.addChild(nodeA7);

		assertEquals(Tree.parse("one(two three(four five(six seven eight)))"), rootA);

	}


}
