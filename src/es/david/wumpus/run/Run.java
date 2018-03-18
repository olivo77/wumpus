/**
 * 
 */
package es.david.wumpus.run;

import es.david.wumpus.board.Board;
import es.david.wumpus.board.impl.BoardBuildBottomUp;
import es.david.wumpus.board.impl.BoardMatrix;
import es.david.wumpus.controller.MainController;
import es.david.wumpus.view.View;
import es.david.wumpus.view.impl.CommandLineView;

/**
 * @author David Doña Corrales
 *
 */
public class Run {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Board board = new BoardMatrix(new BoardBuildBottomUp());
			View view = new CommandLineView();
			MainController controller = new MainController(view, board);
			controller.run();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
