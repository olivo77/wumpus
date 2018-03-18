/**
 * 
 */
package es.david.wumpus.element.impl;

import java.util.ArrayList;
import java.util.List;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.constant.Orientation;
import es.david.wumpus.constant.UserAction;
import es.david.wumpus.element.MainCharacter;

/**
 * @author David Doña Corrales
 *
 */
public class Hunter implements MainCharacter {

	private Integer posX = null, posY = null;
	private Orientation orientation = null;
	private Gold gold = null;
	private List<Arrow> lArrows = new ArrayList<Arrow>();
	
	public Hunter(int x, int y, Orientation orientation){
		if(x < 0 || y < 0){
			throw new RuntimeException("[ERROR] Posiciones incorrectas");
		}
		this.posX = x;
		this.posY = y;
		this.orientation = orientation;
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
	public Orientation getOrientation() {
		return this.orientation;
	}

	@Override
	public void turnLeft() {
		this.orientation = getResultTurn(UserAction.TURN_LEFT);
	}

	@Override
	public void turnRight() {
		this.orientation = getResultTurn(UserAction.TURN_RIGHT);
	}
	
	private Orientation getResultTurn(UserAction uAction){
		int incAux = 0;
		int posActual = this.orientation.ordinal();
		int posResult = 0;
		
		Orientation result = this.orientation;
		
		if(uAction == UserAction.TURN_LEFT){
			incAux = -1;
		}else if(uAction == UserAction.TURN_RIGHT){
			incAux = 1;
		}
		
		posResult = posActual + incAux;
		if(posResult < 0){
			posResult = 3;
		}

		result = Orientation.values()[posResult % 4];
		return result;
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
	    if (o instanceof Hunter){
	    	return true;
	    }
		return false;
	}

	@Override
	public boolean hasGold() {
		return gold != null;
	}

	@Override
	public void setGold(Gold gold) {
		this.gold = gold;
	}

	@Override
	public void addArrows(int numArrows) {
		if(numArrows < 1){
			throw new RuntimeException("[ERROR] El número de flechas es menor que 1");
		}
		for(int i = 0; i < numArrows; i++){
			this.lArrows.add(new Arrow());
		}
	}

	@Override
	public int getNumArrows() {
		return this.lArrows.size();
	}

	@Override
	public boolean removeArrow() {
		if(this.lArrows.size() == 0){
			return false;
		}else{
			this.lArrows.remove(new Arrow());
			return true;
		}
	}

}
