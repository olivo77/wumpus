/**
 * 
 */
package es.david.wumpus.controller;

import java.util.ArrayList;
import java.util.List;

import es.david.wumpus.board.Board;
import es.david.wumpus.constant.KeyOption;
import es.david.wumpus.constant.KeyText;
import es.david.wumpus.constant.KindPerception;
import es.david.wumpus.constant.UserAction;
import es.david.wumpus.util.OptionManager;
import es.david.wumpus.util.TextManager;
import es.david.wumpus.view.View;


/**
 * @author David Doña Corrales
 *
 */
public class MainController {

	private Board board;
	private View view;
	
	public MainController(View view, Board board){
		this.board = board;
		this.view = view;
	}
	
	public void run() throws Exception{
		
		List<KindPerception> lPerceptions = new ArrayList<KindPerception>();
		UserAction action = null;
		boolean showMap = OptionManager.getOption(KeyOption.SHOW_MAP).equals("true");
		boolean showArrows = OptionManager.getOption(KeyOption.SHOW_NUM_ARROWS).equals("true");
		lPerceptions = board.getActualPerceptions();
		
		while(!isGameOver(lPerceptions)){
			
			//Si no ha muerto por el wumpus o caido a un pozo
			if( !lPerceptions.contains(KindPerception.WUMPUS) && 
					!lPerceptions.contains(KindPerception.HOLE)){
				
				if( lPerceptions.contains(KindPerception.SMELL_WUMPUS) ){
					view.showText(TextManager.getText(KeyText.SMELL_WUMPUS));
				}
				if( lPerceptions.contains(KindPerception.FEEL_BREEZE) ){
					view.showText(TextManager.getText(KeyText.FEEL_BREEZE));
				}
				if( lPerceptions.contains(KindPerception.GOLD) ){
					view.showText(TextManager.getText(KeyText.FEEL_GOLD));
					//Cogemos inmediantamente el oro
					board.userAction(UserAction.CATCH_GOLD);
					view.showText(TextManager.getText(KeyText.CATCH_GOLD));
				}
				if( lPerceptions.contains(KindPerception.WALL) ){
					view.showText(TextManager.getText(KeyText.WALL));
				}
				if( lPerceptions.contains(KindPerception.WUMPUS_SCREAM) ){
					view.showText(TextManager.getText(KeyText.WUMPUS_SCREAM));
				}
				if( lPerceptions.contains(KindPerception.NO_ARROWS) ){
					view.showText(TextManager.getText(KeyText.NO_ARROW));
				}
				if( lPerceptions.contains(KindPerception.ARROW_WALL) ){
					view.showText(TextManager.getText(KeyText.ARROW_WALL));
				}
				if( lPerceptions.contains(KindPerception.IS_NOT_EXIT) ){
					view.showText(TextManager.getText(KeyText.IS_NOT_EXIT));
				}
				if( lPerceptions.contains(KindPerception.NO_GOLD) ){
					view.showText(TextManager.getText(KeyText.NO_GOLD));
				}
			}
			
			if(showMap){
				view.show(board);
			}
			if(showArrows){
				view.showText(TextManager.getText(KeyText.NUM_ARROWS_LEFT) + ":" + board.getHunter().getNumArrows());
			}
			action = view.getUserInstruction();
			lPerceptions = board.userAction(action);
			
		}
		
		if( lPerceptions.contains(KindPerception.WUMPUS) ){
			view.showText(TextManager.getText(KeyText.WUMPUS_IS_HERE));
			view.showText(TextManager.getText(KeyText.WUMPUS_KILLED_YOU));
		}else if( lPerceptions.contains(KindPerception.HOLE) ){
			view.showText(TextManager.getText(KeyText.HOLE_KILLED_YOU));
		}else if( lPerceptions.contains(KindPerception.WIN) ){
			view.showText(TextManager.getText(KeyText.WIN));
		}
		
	}
	
	private boolean isGameOver(List<KindPerception> lPerceptions){
		
		if(lPerceptions.contains(KindPerception.HOLE) || 
				lPerceptions.contains(KindPerception.WIN) || lPerceptions.contains(KindPerception.WUMPUS)){
			return true;
		}else{
			return false;
		}
	}

}
