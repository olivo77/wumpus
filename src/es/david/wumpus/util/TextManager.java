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
public class TextManager {

	private TextManager() {
	};

	public static String getText(String key) {
		String result = "";
		Properties p = new Properties();
		try {
			p.load(new FileReader("resources/text_es_ES.properties"));
			result = p.getProperty(key);
		} catch (Exception e) {
			result = key;
			e.printStackTrace();
		}

		return result;
	}

}
