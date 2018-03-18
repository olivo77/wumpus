package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.impl.Gold;

/**
 * 
 */

/**
 * @author David Doña Corrales
 * 
 */
public class GoldTest extends TestCase {
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeX() {
		try {
			Gold gold = new Gold(-1,1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeY() {
		try {
			Gold gold = new Gold(0,-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeX() {
		try {
			Gold gold = new Gold(0,0);
			gold.setPosX(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeY() {
		try {
			Gold gold = new Gold(0,0);
			gold.setPosY(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test
	public void testKindPerception() throws Exception{
		Gold gold = new Gold(0,0);
		assertEquals(gold.getPerception(), KindPerception.GOLD);
		
	}

}
