/**
 * 
 */
package es.david.wumpus.element;

import es.david.wumpus.board.Board;


/**
 * @author David Do�a Corrales
 *
 */
public interface Hazard extends BoardElement {
	
	/**
	 * Desconfigura la posici�n del elemento en el tablero
	 * @param board
	 */
	public void unsetPosition(Board board);

}
