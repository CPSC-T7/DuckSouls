package tests;

import world.Room;
import world.RoomIO;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import ai.NotAvaliablePathExecption;
import ai.Path;


public class PathTests {

	private static final Room[] roomArray = new Room[4];

	static {
		for(int index = 0; index<roomArray.length; index++) {
			roomArray[index] = RoomIO.loadTestRoom(index);
		}
	}
	

	@Test
	public void testPathlength() {
		Path test;
		try {
			test = new Path(roomArray[0], roomArray[0].getEnemyList().get(0).getPosition(), roomArray[0].getPlayer().getPosition());
			assertEquals("Path length should be 74", 74, test.getPathLength());
			test = new Path(roomArray[1], roomArray[1].getEnemyList().get(0).getPosition(), roomArray[1].getPlayer().getPosition());
			assertEquals("Path length should be 2", 2, test.getPathLength());
			test = new Path(roomArray[2], roomArray[2].getEnemyList().get(0).getPosition(), roomArray[2].getPlayer().getPosition());
			assertEquals("Path length should be 80", 80, test.getPathLength());
			
			/*This is known to fail, current algorithm has the issue where if two paths to the goal they have points that for
			 * some time have the same H cost and F cost and both only move closer to the goal from this point on,
			 * then the algorithm will pick one of these paths and not check the other.
			 * 
			 * This means that it still produces a path, however is not the optimal path.
			 * 
			 * Would require reworking the algorithm to fix and does not seem worth the time investment at the moment for a minor
			 * improvement which is unlikely to affect the game given the limit map size able to be displayed.
			 */
			test = new Path(roomArray[3], roomArray[3].getEnemyList().get(0).getPosition(), roomArray[3].getPlayer().getPosition());
			assertEquals("Path length should be 58", 58, test.getPathLength());
			
		} catch (NotAvaliablePathExecption e) {
			assertEquals("Should not be empty", 1, 0);
		}
	}

}
