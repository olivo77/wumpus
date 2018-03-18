package es.david.wumpus.element;

import es.david.wumpus.constant.Orientation;
import es.david.wumpus.element.impl.Gold;

public interface MainCharacter extends BoardElement{
	
	/**
	 * obtiene la orientación actual del personaje
	 * @return
	 */
	public Orientation getOrientation();
	
	/**
	 * gira a la izquiera el personaje
	 */
	public void turnLeft();
	
	/**
	 * gira a la derecha el personaje
	 */
	public void turnRight();
	
	/**
	 * Coge el oro
	 */
	public void setGold(Gold gold);
	
	/**
	 * Devuelve true si tiene el oro
	 * @return
	 */
	public boolean hasGold();
	
	/**
	 * Añade flechas al personaje
	 * @param numArrows
	 */
	public void addArrows(int numArrows);
	
	/**
	 * Resta una flecha al personaje
	 * @return
	 */
	public boolean removeArrow();
	
	/**
	 * Devuelve la cantidad de flechas que posee el personaje
	 * @return
	 */
	public int getNumArrows();

}
