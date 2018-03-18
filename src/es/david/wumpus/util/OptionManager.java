/**
 * 
 */
package es.david.wumpus.util;

import java.io.FileReader;
import java.util.Properties;

/**
 * @author David Doña Corrales
 *
 */
public class OptionManager {
	
	private OptionManager(){ };
	
	public static String getOption(String key){
		String result = "";
		Properties p = new Properties();
		try {
			p.load(new FileReader("resources/config.properties"));
			result = p.getProperty(key);
		} catch (Exception e) {
			result = key;
			e.printStackTrace();
		}

		return result;
	}

}
