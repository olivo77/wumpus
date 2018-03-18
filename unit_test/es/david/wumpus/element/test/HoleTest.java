package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.board.Board;
import es.david.wumpus.board.impl.BoardBuildBottomUp;
import es.david.wumpus.board.impl.BoardMatrix;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.impl.Hole;

/**
 * 
 */

/**
 * @author David Doña Corrales
 * 
 */
public class HoleTest extends TestCase {
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeX() {
		try {
			Hole hole = new Hole(-1,1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeY() {
		try {
			Hole hole = new Hole(0,-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeX() {
		try {
			Hole hole = new Hole(0,0);
			hole.setPosX(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeY() {
		try {
			Hole hole = new Hole(0,0);
			hole.setPosY(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test
	public void testConfigurePosition() throws Exception{
		try{
			Board board = new BoardMatrix(new BoardBuildBottomUp());
		}catch(Exception e){
			fail();
		}
		
	}
	
	@Test
	public void testKindPerception() throws Exception{
		Hole hole = new Hole(0,0);
		assertEquals(hole.getPerception(), KindPerception.HOLE);
		
	}

}
