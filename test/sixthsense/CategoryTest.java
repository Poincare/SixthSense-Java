/**
 * Authors: Aroop Ganguly, Dhaivat Pandya
 */
package sixthsense;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CategoryTest {
//	@Test
//	public void testCtorForNameMemberInit() {
//		Category c = new Category("c1");
//		if (!c.getName().equals("c1")) {
//			fail("Category constructor Name value incorrect");
//		}
//	}
//	
//	@Test
//<<<<<<< HEAD
//	public void testParseName() {
//		String testStr = "abc1121bcd";
//		if (!Category.parseName(testStr).equals("abc")) {
//			fail("ParseName is incorrect");
//		}
//	}
//	
//	@Test
//	public void testName() {
//		Category c = new Category("test");
//		if (!c.getName().equals("test")) {
//			fail("Name not retrived correctly");
//=======
//	public void testCtorForNameAndObjectMembersInit() {
//		Category c = null;
//		try {
//			c = new Category("c1", new Gesture());
//		}
//		catch (Exception e) {
//			fail("failed to add example, " + e.getMessage());
//		}
//		if (c == null || c.getExamples() == 0) {
//			fail("no examples added in constructor");
//		}
//		else if (c.getExamples() > 1) {
//			fail("garbge values for examples inside Category");
//>>>>>>> 11214033c57b725188d412a17ba6534d7329ed3f
//		}
//	}
//	
//	@Test
//	public void testExample() {
//		ArrayList<String> data = new ArrayList<String>(5);
//		for (@SuppressWarnings("unused")
//		String s : data) {
//			s = "t";
//		}
//		Category c = null;
//		try {
//			c = new Category("test", data);
//		}
//		catch (Exception e) {
//			if (e.getMessage().indexOf("Prototypes name") != -1) {
//				return;
//			}
//		}
//<<<<<<< HEAD
//		if (!(c.getExamples() == data.size())) {
//			fail("Example() not working properly");
//=======
//
//		if (c == null || c.getExamples() != data.size()) {
//			fail("no examples added incorrectly in constructor");
//>>>>>>> 11214033c57b725188d412a17ba6534d7329ed3f
//		}
//	}
//	
//	@Test
//	public void testAddExample() {
//		Category c = new Category("test");
//		try {
//			c.addExample(new Gesture("test1", null));
//		}
//		catch (Exception e) {
//			if (e.getMessage().indexOf("Prototype name") != -1) {
//				return;
//			}
//			else
//				fail("error in adding examples." + e.getMessage());
//		}
//		if (c.getExamples() != 1) {
//			fail("error in adding examples");
//		}
//	}
//	
//	@Test
//<<<<<<< HEAD
//	public void testCtorForNameAndObjectMembersInit() {
//		Category c = null;
//		try {
//			Gesture g = new Gesture("c1", null);
//			c = new Category("c1", g);
//		}
//		catch (Exception e) {
//			if (e.getMessage().indexOf("Prototype name") != -1) {
//				return;
//			}
//			else
//				fail("failed to add example, " + e.getMessage());
//		}
//		if (c == null || c.getExamples() == 0) {
//			fail("no examples added in constructor");
//		}
//		else if (c.getExamples() > 1) {
//			fail("garbage values for examples inside Category");
//=======
//	public void testName() {
//		Category c = new Category("test");
//		if (!c.getName().equals("test")) {
//			fail("Name not retrived correctly");
//>>>>>>> 11214033c57b725188d412a17ba6534d7329ed3f
//		}
//	}
//	
//	@Test
//	public void testCtorForGenericParamsInit() {
//		Category c = null;
//		ArrayList<String> data = new ArrayList<String>(5);
//		for (@SuppressWarnings("unused")
//		String s : data) {
//			s = "t";
//		}
//<<<<<<< HEAD
//		try {
//			c = new Category("c1", data);
//=======
//
//		Category c = new Category("test", data);
//		if (!(c.getExamples() == data.size())) {
//			fail("Example() not working properly");
//		}
//	}
//
//	@Test
//	public void testAddExample() {
//		Category c = new Category("test");
//		try {
//			c.addExample(new Gesture());
//>>>>>>> 11214033c57b725188d412a17ba6534d7329ed3f
//		}
//		catch (Exception e) {
//			fail("failed to add example, " + e.getMessage());
//		}
//<<<<<<< HEAD
//		if (c == null || c.getExamples() != data.size()) {
//			fail("no examples added incorrectly in constructor");
//		}
//		else {
//			for (int i = 0; i < 5; i++) {
//				try {
//					if(c.getGesture(i)==null)
//						return;
//					if (!c.getGesture(i).getClass().equals(Gesture.class)) {
//						fail("Examples not added properly as Gestures in Contructor");
//						break;
//					}
//				}
//				catch (Exception e) {
//					if (e.getMessage().indexOf("prototypes not initialised") != -1) {
//						return;
//					}
//					else
//						fail("Examples not added properly as Gestures in Contructor");
//				}
//			}
//=======
//		if (c.getExamples() != 1) {
//			fail("error in adding examples");
//		}
//	}
//
//	@Test
//	public void testParseName() {
//		String testStr = "abc1bcd";
//		if (Category.parseName(testStr) != "abc1b") {
//			fail("ParseName is incorrect");
//>>>>>>> 11214033c57b725188d412a17ba6534d7329ed3f
//		}
//	}
}
