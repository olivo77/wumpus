/**
 * 
 */
package es.david.wumpus.element.test;

import junit.framework.TestCase;

import org.junit.Test;

import es.david.wumpus.board.Board;
import es.david.wumpus.board.impl.BoardBuildBottomUp;
import es.david.wumpus.board.impl.BoardMatrix;
import es.david.wumpus.board.impl.Option;
import es.david.wumpus.element.impl.Exit;
import es.david.wumpus.element.impl.Gold;
import es.david.wumpus.element.impl.Hole;
import es.david.wumpus.element.impl.Wumpus;


/**
 * @author David Doña Corrales
 *
 */
public class BoardTest extends TestCase{
	
	@Test
	public void testExistAllElements(){
		Wumpus wumpus = new Wumpus();
		Gold gold = new Gold();
		Hole hole = new Hole();
		Exit exit = new Exit();
		
		try{
			Board board = new BoardMatrix(new BoardBuildBottomUp());
			assertNotNull(board.findElement(wumpus));
			assertNotNull(board.findElement(gold));
			assertNotNull(board.findElement(hole));
			assertNotNull(board.findElement(exit));
			assertNotNull(board.getHunter());
		}catch(Exception e){
			fail();
		}
	}
	
	@Test(expected = Exception.class)
	public void testOptionsBoard(){

		try{
			Option options = new Option();
			options.setNumArrow(5);
			options.setNumHoles(20);
			options.setNumX(5);
			options.setNumY(5);
			
			Board board = new BoardMatrix(new BoardBuildBottomUp(),options);
			fail();
		}catch(Exception e){
			//
		}
	}
	
	@Test
	public void testCorrectPosition(){

		try{
			Option options = new Option();
			options.setNumArrow(5);
			options.setNumHoles(3);
			options.setNumX(5);
			options.setNumY(5);
			
			Board board = new BoardMatrix(new BoardBuildBottomUp(),options);
			assertTrue(board.isCorrectPosition(0, 0));
			assertTrue(board.isCorrectPosition(4, 4));
			assertFalse(board.isCorrectPosition(5, 5));
		}catch(Exception e){
			//
		}
	}

}
