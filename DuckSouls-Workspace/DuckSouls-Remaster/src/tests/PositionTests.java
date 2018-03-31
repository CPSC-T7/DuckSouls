package tests;

import static org.junit.Assert.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

import world.Room;

public class PositionTests {
	
	/*
	 * 
	 * TESTS
	 * 
	 */
	
	/**
	 * Asserts that a point is within the range of a room.
	 * 
	 * @param room
	 *            The room the point should be in.
	 * @param point
	 *            The point to test.
	 */
	@Test
	public static void testPointInRoom(Room room, Point point) {
		
		int maxX = room.getInternalWidth() + 1;
		int maxY = room.getInternalHeight() + 1;
		
//		assertTrue("X out of range: X should be between 0 and " + maxX + ". Got " + point.x + ".",
//				point.x >= 0 && point.x <= maxX);
//		assertTrue("Y out of range: Y should be between 0 and " + maxY + ". Got " + point.y + ".",
//				point.y >= 0 && point.y <= maxY);
		
		assert point.x >= 0 && point.x <= maxX;
		assert point.y >= 0 && point.y <= maxY;
		
	}
	
}
