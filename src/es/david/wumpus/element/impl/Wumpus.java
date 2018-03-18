/**
 * 
 */
package es.david.wumpus.element.impl;

import java.util.List;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.element.BoardElement;
import es.david.wumpus.element.Hazard;

/**
 * @author David Doña Corrales
 * 
 */
public class Wumpus implements Hazard {
	private Integer posX = null, posY = null;

	public Wumpus() {
	}

	public Wumpus(int posX, int posY) {
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
		int x = this.posX;
		int y = this.posY;
		try {	
			if (board.isCorrectPosition(x - 1, y)
					&& !board.isThereHazard(x - 1, y)) {
				board.setElement(new Perception(KindPerception.SMELL_WUMPUS),
						x - 1, y);
			}
			if (board.isCorrectPosition(x + 1, y)
					&& !board.isThereHazard(x + 1, y)) {
				board.setElement(new Perception(KindPerception.SMELL_WUMPUS),
						x + 1, y);
			}
			if (board.isCorrectPosition(x, y - 1)
					&& !board.isThereHazard(x, y - 1)) {
				board.setElement(new Perception(KindPerception.SMELL_WUMPUS),
						x, y - 1);
			}
			if (board.isCorrectPosition(x, y + 1)
					&& !board.isThereHazard(x, y + 1)) {
				board.setElement(new Perception(KindPerception.SMELL_WUMPUS),
						x, y + 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public KindPerception getPerception() {
		return KindPerception.WUMPUS;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o == this) {
			return true;
		}
		if (o instanceof Wumpus) {
			return true;
		}
		return false;
	}

	@Override
	public void unsetPosition(Board board) {

		List<BoardElement> lElements = null;
		Perception perception = new Perception(KindPerception.SMELL_WUMPUS);

		if (board.isCorrectPosition(this.posX - 1, this.posY)) {
			lElements = board.getElements(this.posX - 1, this.posY);
			lElements.remove(perception);
		}
		if (board.isCorrectPosition(this.posX + 1, this.posY)) {
			lElements = board.getElements(this.posX + 1, this.posY);
			lElements.remove(perception);
		}
		if (board.isCorrectPosition(this.posX, this.posY - 1)) {
			lElements = board.getElements(this.posX, this.posY - 1);
			lElements.remove(perception);
		}
		if (board.isCorrectPosition(this.posX, this.posY + 1)) {
			lElements = board.getElements(this.posX, this.posY + 1);
			lElements.remove(perception);
		}

	}

}
