/**
 * 
 */
package es.david.wumpus.element.impl;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.BoardElement;

/**
 * @author David Doña Corrales
 *
 */
public class Arrow implements BoardElement {
	private Integer posX = null, posY = null;
	
	@Override
	public int getPosX() {
		return posX;
	}

	@Override
	public int getPosY() {
		return posY;
	}
	
	@Override
	public void setPosX(int x) {
		this.posX = x;
	}

	@Override
	public void setPosY(int y) {
		this.posY = y;
	}
	
	@Override
	public void configurePosition(Board board) throws Exception {
		if(this.posX == null || this.posY == null){
			throw new Exception("[ERROR] No se ha establecido la posición del elemento");
		}
	}
	
	@Override
	public KindPerception getPerception() {
		return null;
	}
	
	public boolean equals(Object o){
		if (o == null){
			return false;
		}
	    if (o == this){
	    	return true;
	    }
	    if (o instanceof Arrow){
	    	return true;
	    }
		return false;
	}

}
