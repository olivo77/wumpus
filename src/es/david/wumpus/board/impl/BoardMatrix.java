/**
 * 
 */
package es.david.wumpus.board.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import es.david.wumpus.board.Board;
import es.david.wumpus.board.BuildBoardStrategy;
import es.david.wumpus.constant.KeyOption;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.constant.Orientation;
import es.david.wumpus.constant.UserAction;
import es.david.wumpus.element.BoardElement;
import es.david.wumpus.element.Hazard;
import es.david.wumpus.element.impl.Exit;
import es.david.wumpus.element.impl.Gold;
import es.david.wumpus.element.impl.Hole;
import es.david.wumpus.element.impl.Hunter;
import es.david.wumpus.element.impl.Wumpus;
import es.david.wumpus.util.OptionManager;

/**
 * @author David Doña Corrales
 * 
 */
public class BoardMatrix implements Board {

	private List<BoardElement>[][] board = null; // Primera dimension = Y,
													// segunda dimensión = X
	private Option options = null;
	private Hunter hunter = null;
	private int numHoles = 0;
	private int opAux = 0;

	@SuppressWarnings("unchecked")
	public BoardMatrix(BuildBoardStrategy strategy) throws IOException {
		try {
			int nX = Integer.valueOf(OptionManager
					.getOption(KeyOption.NUM_CELLS_X));
			int nY = Integer.valueOf(OptionManager
					.getOption(KeyOption.NUM_CELLS_Y));

			this.options = new Option();
			options.setNumX(nX);
			options.setNumY(nY);
			this.board = new ArrayList[nY][nX];

			this.options.setNumArrow(Integer.valueOf(OptionManager
					.getOption(KeyOption.NUM_ARROWS)));
			numHoles = Integer.valueOf(OptionManager
					.getOption(KeyOption.NUM_HOLES));
			opAux = this.options.getNumX() + this.options.getNumY() + 3;
			opAux = this.options.getNumX() * this.options.getNumY() - opAux;
			if(numHoles >= opAux ){
				throw new RuntimeException("[ERROR] El número de pozos " +
						"debe de cumplir la fórmula: NUM_POZOS < (nX * nY) - (nX + nY + 3)");
			}
			
			this.options.setNumHoles(numHoles);

			strategy.BuildBoard(this);
		} catch (Exception e) {
			throw new IOException(
					"[ERROR]: Ha ocurrido un error en la lectura del "
							+ "fichero de opciones", e);
		}

	}

	@SuppressWarnings("unchecked")
	public BoardMatrix(BuildBoardStrategy strategy, Option options) {
		
		int numHoles = options.getNumHoles();
		int opAux = options.getNumX() + options.getNumY() + 3;
		opAux = options.getNumX() * options.getNumY() - opAux;
		if(numHoles >= opAux ){
			throw new RuntimeException("[ERROR] El número de pozos " +
					"debe de cumplir la fórmula: NUM_POZOS < (nX * nY) - (nX + nY + 3)");
		}
		this.options = options;
		this.board = new ArrayList[options.getNumY()][options.getNumX()];

		strategy.BuildBoard(this);
	}

	@Override
	public List<BoardElement> getElements(int x, int y) {
		List<BoardElement> l = null;
		if (isCorrectPosition(x, y)) {
			l = this.board[y][x];
		} else {
			throw new RuntimeException(
					"[ERROR] Acceso a posición incorrecta del tablero");
		}
		return l;
	}

	@Override
	public boolean setElement(BoardElement element, int x, int y)
			throws Exception {

		if (!isCorrectPosition(x, y)) {
			throw new RuntimeException(
					"[ERROR] Acceso a posición incorrecta del tablero");
		}

		if (this.board[y][x] == null) {
			this.board[y][x] = new ArrayList<BoardElement>();
		}

		if (element instanceof Hazard && isThereHazard(x, y)) {
			throw new Exception(
					"[ERROR] Ya existe una amenaza insertada en la posición indicada");
		}

		// Si no contiene el elemento en la posición
		if (!this.board[y][x].contains(element)) {
			this.board[y][x].add(element);
			// Se delega la configuración de la posición al elemento añadido
			element.configurePosition(this);
		}

		return true;
	}

	@Override
	public boolean isThereHazard(int x, int y) {
		if (!isCorrectPosition(x, y)) {
			throw new RuntimeException(
					"[ERROR] Acceso a posición incorrecta del tablero");
		}
		List<BoardElement> l = this.board[y][x];
		if (l instanceof Hazard) {
			return true;
		}
		return false;
	}

	public boolean isCorrectPosition(int x, int y) {
		boolean isCorrect = false;
		if (x >= 0 && x < this.options.getNumX() && y >= 0
				&& y < this.options.getNumY()) {
			isCorrect = true;
		}
		return isCorrect;
	}

	public Option getOptions() {
		return options;
	}

	public void setHunter(Hunter h) {
		this.hunter = h;
	}

	@Override
	public Hunter getHunter() {
		return this.hunter;
	}

	@Override
	public Gold getGold() {
		Gold g = new Gold();
		return (Gold) findElement(g);
	}

	public BoardElement findElement(BoardElement e) {
		boolean found = false;
		BoardElement eResult = null;
		int x = 0;
		int y = 0;

		while (!found && y < this.board.length) {
			while(!found && x < this.board[0].length){
				if (this.board[y][x] != null && this.board[y][x].contains(e)) {
					eResult = this.board[y][x].get(this.board[y][x].indexOf(e));
					found = true;
				}
				x++;
			}
			x = 0;
			y++;
		}

		return eResult;
	}

	@Override
	public List<KindPerception> userAction(UserAction action) {
		List<KindPerception> lPerception = new ArrayList<KindPerception>();
		int[] positionAux = null;

		if (action == UserAction.TURN_LEFT) {
			this.hunter.turnLeft();
		} else if (action == UserAction.TURN_RIGHT) {
			this.hunter.turnRight();
		} else if (action == UserAction.GO_FORWARD) {

			// Se obtiene la posición resultante de avanzar
			positionAux = getPositionGoForward();
			// Si la posición es nula, es que ha chocado con un muro
			if (positionAux == null) {
				lPerception.add(KindPerception.WALL);
			} else {
				this.hunter.setPosX(positionAux[0]);
				this.hunter.setPosY(positionAux[1]);
			}
			// Si existe algún elemento en la casilla actual
			lPerception.addAll(getActualPerceptions());
		} else if (action == UserAction.THROW_ARROW) {

			if (this.hunter.getNumArrows() == 0) {
				lPerception.add(KindPerception.NO_ARROWS);
			} else if (hunterCanKillWumpus()) {
				// Buscamos y obtenemos el wumpus del tablero
				Wumpus wumpus = new Wumpus();
				wumpus = (Wumpus) this.findElement(wumpus);
				// Desconfiguramos el wumpus
				wumpus.unsetPosition(this);
				// Se elimina el wumpus
				this.board[wumpus.getPosY()][wumpus.getPosX()].remove(wumpus);
				// Se añade las percepciones
				lPerception.add(KindPerception.WUMPUS_SCREAM);

				this.hunter.removeArrow();
			}else{
				lPerception.add(KindPerception.ARROW_WALL);
				this.hunter.removeArrow();
			}

		} else if (action == UserAction.CATCH_GOLD) {

			Gold g = this.getGold();
			if (g != null && this.hunter.getPosX() == g.getPosX()
					&& this.hunter.getPosY() == g.getPosY()) {
				lPerception.add(KindPerception.GOLD_CAUGHT);
				this.hunter.setGold(g);
				// Se elimina el oro del tablero
				this.board[g.getPosY()][g.getPosX()].remove(g);
			}

		} else if (action == UserAction.EXIT) {

			// Buscamos y obtenemos la casilla de salida
			Exit exit = new Exit();
			exit = (Exit) this.findElement(exit);
			if (exit.getPosX() == hunter.getPosX()
					&& exit.getPosY() == hunter.getPosY() && hunter.hasGold()) {
				lPerception.add(KindPerception.WIN);
			}else if(!hunter.hasGold()){
				lPerception.add(KindPerception.NO_GOLD);
			}else{
				lPerception.add(KindPerception.IS_NOT_EXIT);
			}

		}

		return lPerception;
	}

	/**
	 * Devuelve la posición resultante de caminar por el tablero (int[0] = X,
	 * int[1] = Y)
	 * 
	 * @return - Devuelve la nueva posición o nulo si no es posible el
	 *         movimiento
	 */
	private int[] getPositionGoForward() {
		int[] posResult = new int[2];

		if (this.hunter.getOrientation() == Orientation.EAST) {
			if (this.isCorrectPosition(this.hunter.getPosX() + 1, this.hunter
					.getPosY())) {
				posResult[0] = this.hunter.getPosX() + 1;
				posResult[1] = this.hunter.getPosY();
			} else {
				posResult = null;
			}
		} else if (this.hunter.getOrientation() == Orientation.SOUTH) {
			if (this.isCorrectPosition(this.hunter.getPosX(), this.hunter
					.getPosY() - 1)) {
				posResult[0] = this.hunter.getPosX();
				posResult[1] = this.hunter.getPosY() - 1;
			} else {
				posResult = null;
			}
		} else if (this.hunter.getOrientation() == Orientation.WEST) {
			if (this.isCorrectPosition(this.hunter.getPosX() - 1, this.hunter
					.getPosY())) {
				posResult[0] = this.hunter.getPosX() - 1;
				posResult[1] = this.hunter.getPosY();
			} else {
				posResult = null;
			}
		} else if (this.hunter.getOrientation() == Orientation.NORTH) {
			if (this.isCorrectPosition(this.hunter.getPosX(), this.hunter
					.getPosY() + 1)) {
				posResult[0] = this.hunter.getPosX();
				posResult[1] = this.hunter.getPosY() + 1;
			} else {
				posResult = null;
			}
		}

		return posResult;
	}

	private boolean hunterCanKillWumpus() {
		boolean canKill = false;
		Wumpus wumpus = new Wumpus();
		wumpus = (Wumpus) this.findElement(wumpus);
		//Si el wumpus está muerto...
		if(wumpus == null){
			return false;
		}
		boolean horizontal = this.hunter.getPosX() == wumpus.getPosX();
		boolean vertical = this.hunter.getPosY() == wumpus.getPosY();

		if (horizontal) {
			if (wumpus.getPosY() - this.hunter.getPosY() > 0
					&& this.hunter.getOrientation() == Orientation.NORTH) {
				canKill = true;
			} else if (wumpus.getPosY() - this.hunter.getPosY() < 0
					&& this.hunter.getOrientation() == Orientation.SOUTH) {
				canKill = true;
			}
		} else if (vertical) {
			if (wumpus.getPosX() - this.hunter.getPosX() > 0
					&& this.hunter.getOrientation() == Orientation.EAST) {
				canKill = true;
			} else if (wumpus.getPosX() - this.hunter.getPosX() < 0
					&& this.hunter.getOrientation() == Orientation.WEST) {
				canKill = true;
			}
		}

		return canKill && this.hunter.getNumArrows() > 0;

	}
	
	public List<KindPerception> getActualPerceptions(){
		BoardElement elementAux;
		Iterator<BoardElement> it;
		List<KindPerception> lPerception = new ArrayList<KindPerception>();
		if (this.board[this.hunter.getPosY()][this.hunter.getPosX()] != null) {

			it = this.board[this.hunter.getPosY()][this.hunter.getPosX()]
					.iterator();

			while (it.hasNext()) {
				elementAux = it.next();
				if (elementAux.getPerception() != null) {
					lPerception.add(elementAux.getPerception());
				}
			}
		}
		
		return lPerception;
	}
	
	public String toString(){
		String result = "";
		String subStrAux = "";
		Wumpus wumpus = new Wumpus();
		Hole hole = new Hole();
		Gold gold = new Gold();
		Exit exit = new Exit();
		
		for(int y = 0; y < this.board.length; y++){
			for(int x = 0; x < this.board[0].length; x++){
				if( this.hunter.getPosX() == x && this.hunter.getPosY() == y ){
					subStrAux += "{*}";
				}else if(this.board[y][x] != null && this.board[y][x].contains(wumpus) && 
						OptionManager.getOption(KeyOption.DEBUG_MODE).equals("true")){
					subStrAux += "{@}";
				}else if(this.board[y][x] != null && this.board[y][x].contains(hole) && 
						OptionManager.getOption(KeyOption.DEBUG_MODE).equals("true")){
					subStrAux += "{#}";
				}else if(this.board[y][x] != null && this.board[y][x].contains(gold) && 
						OptionManager.getOption(KeyOption.DEBUG_MODE).equals("true")){
					subStrAux += "{+}";
				}else if(this.board[y][x] != null && this.board[y][x].contains(exit) && 
						OptionManager.getOption(KeyOption.DEBUG_MODE).equals("true")){
					subStrAux += "{S}";
				}else{				
					subStrAux += "{ }";
				}
			}
			result = subStrAux + "\n" + result;
			subStrAux = "";
		}
		
		return result;
	}

}
