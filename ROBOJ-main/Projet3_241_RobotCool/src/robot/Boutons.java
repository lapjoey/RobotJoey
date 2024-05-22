package robot;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.Keys;

/****************************************************************************
 * 
 * Contr�le des boutons
 *
 */

public class Boutons {

	// Attributs
	private Keys touche;
	
	public Boutons(Brick brick) {
		touche = brick.getKeys();
	}

	// Bloquer en attendant la touche, retourne la touche (Keys.CONSTANTE)
	public int attend() {
		return touche.waitForAnyPress();
	}

	// Teste si une touche est pes�e
	public boolean clicCentre() {
		return Button.ENTER.isDown();
	}
	public boolean clicHaut() {
		return Button.UP.isDown();
	}
	public boolean clicBas() {
		return Button.DOWN.isDown();
	}
	public boolean clicGauche() {
		return Button.LEFT.isDown();
	}
	public boolean clicDroite() {
		return Button.RIGHT.isDown();
	}
	public boolean clicEscape() {
		return Button.ESCAPE.isDown();
	}
}