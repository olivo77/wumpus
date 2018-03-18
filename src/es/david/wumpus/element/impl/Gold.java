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
public class Gold implements BoardElement {
	private Integer posX = null, posY = null;
	
	public Gold(){ }
	
	public Gold(int posX, int posY){
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
			throw new Exception("[ERROR] No se ha establecido la posición del elemento");
		}
	}
	
	@Override
	public KindPerception getPerception() {
		return KindPerception.GOLD;
	}
	
	public boolean equals(Object o){
		if (o == null){
			return false;
		}
	    if (o == this){
	    	return true;
	    }
	    if (o instanceof Gold){
	    	return true;
	    }
		return false;
	}	

}
