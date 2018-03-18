/**
 * 
 */
package es.david.wumpus.board;



/**
 * @author David Do�a Corrales
 *
 */
public interface BuildBoardStrategy {
	
	/**
	 * Estrategia de creaci�n de elementos en el tablero
	 * @param board
	 * @return
	 */
	public Board BuildBoard(Board board);

}
