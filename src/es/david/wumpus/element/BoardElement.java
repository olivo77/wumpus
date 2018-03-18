package es.david.wumpus.element;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KindPerception;

public interface BoardElement {
	
	/**
	 * Establece la posición del elemento en el tablero
	 * @param x
	 * @param y
	 */
	public void configurePosition(Board board) throws Exception;
	
	/**
	 * Devuelve la posición X actual del elemento en el tablero
	 */
	public int getPosX();
	
	/**
	 * Devuelve la posición Y actual del elemento en el tablero
	 */
	public int getPosY();
	
	public void setPosX(int x);
	
	public void setPosY(int y);
	
	public KindPerception getPerception();
	
}
