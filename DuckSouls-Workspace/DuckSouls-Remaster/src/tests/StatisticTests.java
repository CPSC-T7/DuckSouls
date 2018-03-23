package tests;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

public class StatisticTests {
	
	/*
	 * 
	 * TESTS
	 * 
	 */
	
	/**
	 * Asserts that an integer statistic is between 0 and 100.
	 * 
	 * @param stat
	 *            The statistic to test.
	 */
	@Test
	public static void testIntStatRange(String name, int stat) {
		
		assertTrue("Statistic out of range: " + name + " should be between 0 and 100. Got " + stat + ".",
				stat >= 0 && stat <= 100);
		
	}
	
}
