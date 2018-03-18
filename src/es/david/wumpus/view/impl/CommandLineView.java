/**
 * 
 */
package es.david.wumpus.view.impl;

import java.util.Scanner;

import es.david.wumpus.constant.KeyText;
import es.david.wumpus.constant.UserAction;
import es.david.wumpus.util.TextManager;
import es.david.wumpus.view.View;

/**
 * @author David Doña Corrales
 * 
 */
public class CommandLineView implements View {

	@Override
	public UserAction getUserInstruction() {
		try {
			showOptionList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Scanner scan = new Scanner(System.in);
		String s = scan.next();
		int action;
		UserAction actionResult = null;
		
		try{
			action = Integer.valueOf(s);
		}catch(Exception e){
			action = -1;
		}
		switch (action) {
		case 1:
			actionResult = UserAction.TURN_LEFT;
			break;
		case 2:
			actionResult = UserAction.TURN_RIGHT;
			break;
		case 3:
			actionResult = UserAction.GO_FORWARD;
			break;
		case 4:
			actionResult = UserAction.THROW_ARROW;
			break;
		case 5:
			actionResult = UserAction.EXIT;
			break;
		default: this.showText(TextManager.getText(KeyText.COMMAND_UNKNOWN));
				 actionResult = getUserInstruction();
		}

		return actionResult;
	}

	@Override
	public UserAction getUserInstruction(String sms) {
		this.showText(sms);
		return getUserInstruction();
	}

	@Override
	public void showText(String text) {
		System.out.println(text);
	}
	
	public void showOptionList() throws Exception{
		showText("******************************");
		showText(TextManager.getText(KeyText.OPTION_1));
		showText(TextManager.getText(KeyText.OPTION_2));
		showText(TextManager.getText(KeyText.OPTION_3));
		showText(TextManager.getText(KeyText.OPTION_4));
		showText(TextManager.getText(KeyText.OPTION_5));
		showText("******************************");
	}

	@Override
	public void show(Object o) {
		showText(o.toString());
	}

}
