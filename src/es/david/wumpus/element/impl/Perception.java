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
public class Perception implements BoardElement {
	private Integer posX = null, posY = null;
	private KindPerception perception = null;
	
	public Perception(KindPerception perception){
		this.perception = perception;
	}
	
	public Perception(KindPerception perception, int x, int y){
		if(x < 0 || y < 0){
			throw new RuntimeException("[ERROR] Posiciones incorrectas");
		}
		this.perception = perception;
		this.posX = x;
		this.posY = y;
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
	}
	
	/**
	 * @return the perception
	 */
	public KindPerception getPerception() {
		return perception;
	}

	/**
	 * @param perception the perception to set
	 */
	public void setPerception(KindPerception perception) {
		this.perception = perception;
	}

	public boolean equals(Object o){
		if (o == null){
			return false;
		}
	    if (o == this){
	    	return true;
	    }
	    if ( o instanceof Perception && ((Perception)o).getPerception() == this.getPerception() ){
	    	return true;
	    }
		return false;
	}


}
