/**
 * 
 */
package es.david.wumpus.board.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import es.david.wumpus.board.Board;
import es.david.wumpus.board.BuildBoardStrategy;
import es.david.wumpus.constant.Orientation;
import es.david.wumpus.element.impl.Exit;
import es.david.wumpus.element.impl.Gold;
import es.david.wumpus.element.impl.Hole;
import es.david.wumpus.element.impl.Hunter;
import es.david.wumpus.element.impl.Wumpus;
import es.david.wumpus.util.Position;

/**
 * @author David Doña Corrales
 *
 */
public class BoardBuildBottomUp implements BuildBoardStrategy {

	public BoardBuildBottomUp(){ };

	@Override
	public Board BuildBoard(Board board) {
		
		List<Position> pathMapAux = null; //Representación de una ruta al oro
		Position positionAux; //Posición en el tablero
		int posXAux = getRandomIntegerBetween(0, board.getOptions().getNumX() - 1, null);
		int posYAux = 0;
		List<Position> lExcludePositions = new ArrayList<Position>();
		
		try {
			//Creación casilla salida
			board.setElement(new Exit(posXAux, posYAux), posXAux, posYAux);
			lExcludePositions.add(new Position(posXAux, posYAux));
			
			//El cazador empieza en la misma casilla de salida
			Hunter hunter = new Hunter(posXAux, posYAux, Orientation.EAST);
			hunter.addArrows(board.getOptions().getNumArrow());
			board.setHunter(hunter);
			
			//Creación del objeto del oro
			positionAux = getRandomPositionBetween(board, lExcludePositions);
			board.setElement(new Gold(positionAux.getPosX(), positionAux.getPosY()), positionAux.getPosX(), positionAux.getPosY());
			lExcludePositions.add(positionAux);
			
			//Creamos una posible ruta entre el cazador y el oro
			pathMapAux = getPathToGold(board);
			//Se conserva las posiciones de la ruta como exclusiones para añadir peligros
			lExcludePositions.addAll(pathMapAux);
			
			//Situamos el wumpus
			positionAux = getRandomPositionBetween(board, lExcludePositions);
			board.setElement(new Wumpus(positionAux.getPosX(), positionAux.getPosY()), positionAux.getPosX(), positionAux.getPosY());
			lExcludePositions.add(positionAux);
			
			//Situamos los pozos
			for(int i = 0; i < board.getOptions().getNumHoles(); i++){
				positionAux = getRandomPositionBetween(board, lExcludePositions);
				board.setElement(new Hole(positionAux.getPosX(), positionAux.getPosY()), positionAux.getPosX(), positionAux.getPosY());
				lExcludePositions.add(positionAux);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}
	
	/**
	 * Devuelve un valor comprendido entre x (inclusive) e y (inclusive)
	 * @param min
	 * @param max
	 */
	private int getRandomIntegerBetween(int min, int max, List<Integer> excludeValues){
		
		Random rand = new Random();
		boolean ok = false;
		int result, diff;
		
		if(min > max){
			throw new RuntimeException("[ERROR] El rango entre mínimo y máximo no es congruente");
		}
		
		diff = max - min + 1;
		if(excludeValues != null && excludeValues.size() >= diff){
			throw new RuntimeException("[ERROR] La cantidad de num. excluidos supera el rango");
		}
		
		result = rand.nextInt((max - min) + 1) + min;
		
		if(excludeValues != null && excludeValues.size() > 0){
			while(!ok){
				if(!excludeValues.contains(result)){
					ok = true;
				}else{
					result = rand.nextInt((max - min) + 1) + min;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Devuelve una posición aleatoria en un tablero, pasándole opcionalmente una lista de posiciones a excluir
	 * @param board
	 * @param excludeValues
	 * @return - Devuelve la posición encontrada
	 */
	private Position getRandomPositionBetween(Board board, List<Position> excludeValues){
		
		int numCellsX = board.getOptions().getNumX();
		int numCellsY = board.getOptions().getNumY();
		boolean ok = false;
		int posXAux, posYAux;
		Position posResult = null;
		
		//Si no existe la lista de excluidos, se crea una vacía
		if(excludeValues == null){
			excludeValues = new ArrayList<Position>();
		}
		
		while(!ok){
			posXAux = getRandomIntegerBetween(0, numCellsX - 1, null);
			posYAux = getRandomIntegerBetween(0, numCellsY - 1, null);
			posResult = new Position(posXAux, posYAux);
			
			if( !excludeValues.contains(posResult) ){
				ok = true;
			}
		}
		
		return posResult;
	}
	
	private List<Position> getPathToGold(Board board){
		List<Position> pathMapAux = new ArrayList<Position>();
		Hunter hunterAux = new Hunter(board.getHunter().getPosX(), 
				board.getHunter().getPosY(), board.getHunter().getOrientation());
		int posXAux;
		int posYAux;

		Gold gold = board.getGold();
		
		if(hunterAux.getPosX() != gold.getPosX()){
			
			posXAux = hunterAux.getPosX();
			if(posXAux < gold.getPosX()){
				while(posXAux <= gold.getPosX()){
					pathMapAux.add(new Position(posXAux, hunterAux.getPosY()));
					hunterAux.setPosX(posXAux);
					hunterAux.setPosY(hunterAux.getPosY());
					posXAux++;
				}
			}else if(posXAux > gold.getPosX()){
				while(posXAux >= gold.getPosX()){
					pathMapAux.add(new Position(posXAux, hunterAux.getPosY()));
					hunterAux.setPosX(posXAux);
					hunterAux.setPosY(hunterAux.getPosY());
					posXAux--;
				}
			}
			
		}
		
		if(hunterAux.getPosY() != gold.getPosY()){
			
			posYAux = hunterAux.getPosY();
			if(posYAux < gold.getPosY()){
				while(posYAux <= gold.getPosY()){
					pathMapAux.add(new Position(hunterAux.getPosX(), posYAux));
					hunterAux.setPosX(hunterAux.getPosX());
					hunterAux.setPosY(posYAux);
					posYAux++;
				}
			}else if(posYAux > gold.getPosY()){
				while(posYAux >= gold.getPosY()){
					pathMapAux.add(new Position(hunterAux.getPosX(), posYAux));
					hunterAux.setPosX(hunterAux.getPosX());
					hunterAux.setPosY(posYAux);
					posYAux--;
				}
			}
			
		}
		
		/*for (Position s : pathMapAux)
		{
			System.out.println(s + "\t");
		}*/
		
		return pathMapAux;
		
	}

}
