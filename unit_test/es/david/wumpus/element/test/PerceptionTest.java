package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.impl.Perception;

/**
 * 
 */

/**
 * @author David Doña Corrales
 * 
 */
public class PerceptionTest extends TestCase {
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeX() {
		try {
			Perception perception = new Perception(KindPerception.FEEL_BREEZE,-1,1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeY() {
		try {
			Perception perception = new Perception(KindPerception.FEEL_BREEZE,0,-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeX() {
		try {
			Perception perception = new Perception(KindPerception.FEEL_BREEZE,0,0);
			perception.setPosX(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeY() {
		try {
			Perception perception = new Perception(KindPerception.FEEL_BREEZE,0,0);
			perception.setPosY(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test
	public void testKindPerception() throws Exception{
		Perception perception = new Perception(KindPerception.SMELL_WUMPUS,0,0);
		assertEquals(perception.getPerception(), KindPerception.SMELL_WUMPUS);
		
	}

}
