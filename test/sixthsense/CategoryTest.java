/**
 * Authors: Aroop Ganguly
 */

package sixthsense;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CategoryTest
{
	@Test
	public void testCtorForNameMemberInit()
	{
		Category c = new Category("c1");
		if (!c.Name().equals("c1"))
		{
			fail("Category constructor Name value incorrect");
		}
	}

	@Test
	public void testCtorForNameAndObjectMembersInit()
	{
		Category c = null;
		try
		{
			c = new Category("c1", new Gesture());
		}
		catch (Exception e)
		{
			fail("failed to add example, " + e.getMessage());
		}
		if (c == null || c.Examples() == 0)
		{
			fail("no examples added in constructor");
		}
		else if (c.Examples() > 1)
		{
			fail("garbge values for examples inside Category");
		}
	}

	@Test
	public void testCtorForGenericParamsInit()
	{
		Category c = null;
		ArrayList<String> data = new ArrayList<String>(5);
		for (@SuppressWarnings("unused") String s : data)
		{
			s = "t";
		}
		try
		{
			c = new Category("c1", data);
		}
		catch (Exception e)
		{
			fail("failed to add example, " + e.getMessage());
		}

		if (c == null || c.Examples() != data.size())
		{
			fail("no examples added incorrectly in constructor");
		}
		else
		{
			for (int i = 0; i < 5; i++)
			{
				if (!c.getGesture(i).getClass().equals(Gesture.class))
				{
					fail("Examples not added properly as Gestures in Contructor");
					break;
				}
			}
		}
	}

	@Test
	public void testName()
	{
		Category c = new Category("test");
		if (!c.Name().equals("test"))
		{
			fail("Name not retrived correctly");
		}
	}

	@Test
	public void testExample()
	{

		ArrayList<String> data = new ArrayList<String>(5);
		for (@SuppressWarnings("unused")
		String s : data)
		{
			s = "t";
		}

		Category c = new Category("test", data);
		if (!(c.Examples() == data.size()))
		{
			fail("Example() not working properly");
		}
	}

	@Test
	public void testAddExample()
	{
		Category c = new Category("test");
		try
		{
			c.AddExample(new Gesture());
		}
		catch (Exception e)
		{
			fail("error in adding examples." + e.getMessage());
		}
		if (c.Examples() != 1)
		{
			fail("error in adding examples");
		}
	}

	@Test
	public void testParseName()
	{
		String testStr = "abc1bcd";
		if (Category.ParseName(testStr) != "abc1b")
		{
			fail("ParseName is incorrect");
		}
	}
}
