package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.board.Board;
import es.david.wumpus.board.impl.BoardBuildBottomUp;
import es.david.wumpus.board.impl.BoardMatrix;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.impl.Wumpus;

/**
 * 
 */

/**
 * @author David Doña Corrales
 * 
 */
public class WumpusTest extends TestCase {
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeX() {
		try {
			Wumpus wumpus = new Wumpus(-1,1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testPositionNegativeY() {
		try {
			Wumpus wumpus = new Wumpus(0,-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeX() {
		try {
			Wumpus wumpus = new Wumpus(0,0);
			wumpus.setPosX(-1);
            fail();
        } catch (Exception e) {
        	//
        }
	}
	
	@Test(expected = RuntimeException.class)
	public void testSetPositionNegativeY() {
		try {
			Wumpus wumpus = new Wumpus(0,0);
			wumpus.setPosY(-1);
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
		Wumpus wumpus = new Wumpus(0,0);
		assertEquals(wumpus.getPerception(), KindPerception.WUMPUS);
		
	}

}
