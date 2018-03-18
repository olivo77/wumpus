package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.constant.Orientation;
import es.david.wumpus.element.impl.Gold;
import es.david.wumpus.element.impl.Hunter;

/**
 * 
 */

/**
 * @author David Doña Corrales
 * 
 */
public class HunterTest extends TestCase {
	
	@Test
	public void testTurnLeft() {
		Hunter hunter = new Hunter(1,1,Orientation.NORTH);
		assertEquals(hunter.getOrientation(), Orientation.NORTH);
		hunter.turnLeft();
		assertEquals(hunter.getOrientation(), Orientation.WEST);
		hunter.turnLeft();
		assertEquals(hunter.getOrientation(), Orientation.SOUTH);
		hunter.turnLeft();
		assertEquals(hunter.getOrientation(), Orientation.EAST);
		hunter.turnLeft();
		assertEquals(hunter.getOrientation(), Orientation.NORTH);
	}
	
	@Test
	public void testTurnRight() {
		Hunter hunter = new Hunter(1,1,Orientation.NORTH);
		assertEquals(hunter.getOrientation(), Orientation.NORTH);
		hunter.turnRight();
		assertEquals(hunter.getOrientation(), Orientation.EAST);
		hunter.turnRight();
		assertEquals(hunter.getOrientation(), Orientation.SOUTH);
		hunter.turnRight();
		assertEquals(hunter.getOrientation(), Orientation.WEST);
		hunter.turnRight();
		assertEquals(hunter.getOrientation(), Orientation.NORTH);
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegative() {
		try {
			Hunter hunter = new Hunter(-1,1,Orientation.NORTH);
            fail();
        } catch (Exception e) {
        	//
        }
		
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegative2() {
		try {
			Hunter hunter = new Hunter(0,-1,Orientation.NORTH);
            fail();
        } catch (Exception e) {
        	//
        }
		
	}
	
	@Test
	public void testHasGold() {
		Hunter hunter = new Hunter(0,0,Orientation.NORTH);
		assertFalse(hunter.hasGold());
		hunter.setGold(new Gold());
		assertTrue(hunter.hasGold());
	}
	
	@Test
	public void testArrows() {
		Hunter hunter = new Hunter(0,0,Orientation.NORTH);
		assertEquals(hunter.getNumArrows(),0);
		hunter.addArrows(2);
		assertEquals(hunter.getNumArrows(),2);
		hunter.removeArrow();
		assertEquals(hunter.getNumArrows(),1);
		hunter.removeArrow();
		assertEquals(hunter.getNumArrows(),0);
		hunter.removeArrow();
		assertEquals(hunter.getNumArrows(),0);
		
	}

}
