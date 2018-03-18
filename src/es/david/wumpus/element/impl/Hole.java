/**
 * 
 */
package es.david.wumpus.element.impl;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.Hazard;

/**
 * @author David Doña Corrales
 * 
 */
public class Hole implements Hazard {
	private Integer posX = null, posY = null;

	public Hole() {
	}

	public Hole(int posX, int posY) {
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
		
		try {
			if (board.isCorrectPosition(this.getPosX() - 1, this.getPosY())
					&& !board.isThereHazard(this.getPosX() - 1, this.getPosY())) {
				board.setElement(new Perception(KindPerception.FEEL_BREEZE), this.getPosX() - 1, this.getPosY());
			}
			if (board.isCorrectPosition(this.getPosX() + 1, this.getPosY())
					&& !board.isThereHazard(this.getPosX() + 1, this.getPosY())) {
				board.setElement(new Perception(KindPerception.FEEL_BREEZE), this.getPosX() + 1, this.getPosY());
			}
			if (board.isCorrectPosition(this.getPosX(), this.getPosY() - 1)
					&& !board.isThereHazard(this.getPosX(), this.getPosY() - 1)) {
				board.setElement(new Perception(KindPerception.FEEL_BREEZE), this.getPosX(), this.getPosY() - 1);
			}
			if (board.isCorrectPosition(this.getPosX(), this.getPosY() + 1)
					&& !board.isThereHazard(this.getPosX(), this.getPosY() + 1)) {
				board.setElement(new Perception(KindPerception.FEEL_BREEZE), this.getPosX(), this.getPosY() + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public KindPerception getPerception() {
		return KindPerception.HOLE;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (o instanceof Hole) {
			return true;
		}
		return false;
	}

	@Override
	public void unsetPosition(Board board) { 
		//Nothing
	}

}
