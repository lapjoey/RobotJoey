package robot;

import lejos.hardware.Brick;
import lejos.hardware.lcd.GraphicsLCD;

/****************************************************************************
 * 
 * Contr�le de l'�cran LCD
 *
 */

public class Ecran {

	// Attributs
	private GraphicsLCD lcd;

	public Ecran(Brick brick) {
		lcd = brick.getGraphicsLCD();
		efface();
	}

	public void efface() {
		lcd.clear();
	}

	public void ecrire(String texte) {
		lcd.drawString(texte, 0, 0, GraphicsLCD.VCENTER);
	}

}
