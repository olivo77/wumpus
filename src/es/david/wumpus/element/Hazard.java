/**
 * 
 */
package es.david.wumpus.element;

import es.david.wumpus.board.Board;


/**
 * @author David Doña Corrales
 *
 */
public interface Hazard extends BoardElement {
	
	/**
	 * Desconfigura la posición del elemento en el tablero
	 * @param board
	 */
	public void unsetPosition(Board board);

}
