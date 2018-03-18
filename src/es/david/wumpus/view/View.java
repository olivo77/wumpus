/**
 * 
 */
package es.david.wumpus.view;

import es.david.wumpus.constant.UserAction;

/**
 * @author David Doña Corrales
 *
 */
public interface View {
	
	/**
	 * Obtiene una orden del usuario
	 * @return Instrucción introducida por el usuario
	 */
	public UserAction getUserInstruction();
	
	/**
	 * Obtiene una orden del usuario indicando el mensaje a mostrar al usuario
	 * @param sms - Mensaje a mostrar al usuario
	 * @return
	 */
	public UserAction getUserInstruction(String sms);
	
	/**
	 * Muestra un texto al usuario
	 * @param text - texto a mostrar al usuario
	 */
	public void showText(String text);
	
	/**
	 * Muestra por pantalla el elemento indicado
	 * @param o
	 */
	public void show(Object o);
	
	

}
