package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.element.impl.Exit;

/**
 * 
 */

/**
 * @author David Doña Corrales
 * 
 */
public class ExitTest extends TestCase {
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeX() {
		try {
			Exit exit = new Exit(-1,1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeY() {
		try {
			Exit exit = new Exit(0,-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeX() {
		try {
			Exit exit = new Exit(0,0);
			exit.setPosX(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeY() {
		try {
			Exit exit = new Exit(0,0);
			exit.setPosY(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}

}
