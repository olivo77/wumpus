/**
 * 
 */
package es.david.wumpus.board.impl;

/**
 * @author David Do�a Corrales
 *
 */
public class Option {
	
	private int numX, numY, numArrow, numHoles;

	/**
	 * @return the numX
	 */
	public int getNumX() {
		return numX;
	}

	/**
	 * @param numX the numX to set
	 */
	public void setNumX(int numX) {
		if(numX < 2){
			throw new RuntimeException("[Error] n�mero de casillas inferior al m�nimo");
		}
		this.numX = numX;
	}

	/**
	 * @return the numY
	 */
	public int getNumY() {
		return numY;
	}

	/**
	 * @param numY the numY to set
	 */
	public void setNumY(int numY) {
		if(numY < 2){
			throw new RuntimeException("[Error] n�mero de casillas inferior al m�nimo");
		}
		this.numY = numY;
	}

	/**
	 * @return the numArrow
	 */
	public int getNumArrow() {
		return numArrow;
	}

	/**
	 * @param numArrow the numArrow to set
	 */
	public void setNumArrow(int numArrow) {
		if(numArrow < 0){
			throw new RuntimeException("[Error] n�mero de flechas no v�lido");
		}
		this.numArrow = numArrow;
	}

	/**
	 * @return the numHoles
	 */
	public int getNumHoles() {
		return numHoles;
	}

	/**
	 * @param numHoles the numHoles to set
	 */
	public void setNumHoles(int numHoles) {
		if(numHoles < 0){
			throw new RuntimeException("[Error] n�mero de pozos no v�lido");
		}
		this.numHoles = numHoles;
	}
	
	

}
