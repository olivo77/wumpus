/**
 * 
 */
package es.david.wumpus.element.impl;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.BoardElement;

/**
 * @author David Do�a Corrales
 *
 */
public class Exit implements BoardElement {
	private Integer posX = null, posY = null;
	
	public Exit(){ }
	
	public Exit(int posX, int posY){
		if(posX < 0 || posY < 0){
			throw new RuntimeException("[ERROR] Posiciones incorrectas");
		}
		this.posX = posX;
		this.posY = posY;
	}
	
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
		if(x < 0){
			throw new RuntimeException("[ERROR] Posiciones incorrectas");
		}
		this.posX = x;
	}

	@Override
	public void setPosY(int y) {
		if(y < 0){
			throw new RuntimeException("[ERROR] Posiciones incorrectas");
		}
		this.posY = y;
	}
	@Override
	public void configurePosition(Board board) throws Exception {
		if(this.posX == null || this.posY == null){
			throw new Exception("[ERROR] No se ha establecido la posici�n del elemento");
		} 
	}
	
	@Override
	public KindPerception getPerception() {
		return KindPerception.EXIT;
	}
	
	public boolean equals(Object o){
		if (o == null){
			return false;
		}
	    if (o == this){
	    	return true;
	    }
	    if (o instanceof Exit){
	    	return true;
	    }
		return false;
	}

}
