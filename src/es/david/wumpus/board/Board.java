/**
 * 
 */
package es.david.wumpus.board;

import java.util.List;

import es.david.wumpus.board.impl.Option;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.constant.UserAction;
import es.david.wumpus.element.BoardElement;
import es.david.wumpus.element.impl.Gold;
import es.david.wumpus.element.impl.Hunter;

/**
 * @author David Doña Corrales
 *
 */
public interface Board {
	
	/**
	 * Obtiene los elementos existentes en una posicion
	 * @param x
	 * @param y
	 * @return
	 */
	public List<BoardElement> getElements(int x, int y);
	
	/**
	 * Inserta un elemento en la posicion indicada
	 * @param element
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean setElement(BoardElement element, int x, int y) throws Exception;
	
	/**
	 * Indica si existe algún elemento de peligro en la posición indicada
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isThereHazard(int x, int y);
	
	/**
	 * Obtiene la configuración actual del tablero
	 * @return
	 */
	public Option getOptions();
	
	/**
	 * Coloca el cazador en el tablero
	 * @param h
	 */
	public void setHunter(Hunter h);
	
	public Hunter getHunter();
	
	/**
	 * Devuelve el elemento Oro existente en el tablero
	 * @return
	 */
	public Gold getGold();
	
	/**
	 * Devuelve true en caso de que las posiciones indicadas existan en el tablero
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isCorrectPosition(int x, int y);
	
	/**
	 * Ejecuta una acción del usuario y devuelve las percepciones que
	 * siente después de ejecutar el movimiento
	 * @param action - Acción a llevar a cabo
	 * @return
	 */
	public List<KindPerception> userAction(UserAction action);
	
	/**
	 * Obtiene las percepciones actuales que tiene el cazador en la casilla actual
	 * @return
	 */
	public List<KindPerception> getActualPerceptions();
	
	/**
	 * Encuentra la primera coincidencia del elemento indicado en el tablero.
	 * Devuelve null en caso de no encontrarlo
	 * 
	 * @param e
	 *            - Elemento a buscar
	 * @return
	 */
	public BoardElement findElement(BoardElement e);

}
