/**
 * 
 */
package es.david.wumpus.board;



/**
 * @author David Doña Corrales
 *
 */
public interface BuildBoardStrategy {
	
	/**
	 * Estrategia de creación de elementos en el tablero
	 * @param board
	 * @return
	 */
	public Board BuildBoard(Board board);

}
