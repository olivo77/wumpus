/**
 * 
 */
package es.david.wumpus.util;


/**
 * @author David Doña Corrales
 *
 */
public class Position {
	
	private Integer posX = null, posY = null;
	
	public Position(int x, int y){
		this.posX = x;
		this.posY = y;
	}
	
	/**
	 * @return the posX
	 */
	public Integer getPosX() {
		return posX;
	}

	/**
	 * @param posX the posX to set
	 */
	public void setPosX(Integer posX) {
		this.posX = posX;
	}

	/**
	 * @return the posY
	 */
	public Integer getPosY() {
		return posY;
	}

	/**
	 * @param posY the posY to set
	 */
	public void setPosY(Integer posY) {
		this.posY = posY;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (o instanceof Position && ((Position)o).posX == this.posX
				&& ((Position)o).posY == this.posY) {
			return true;
		}
		return false;
	}
	
	public String toString(){
		return "{" + this.posX + "," + this.posY + "}";
	}
	
}
