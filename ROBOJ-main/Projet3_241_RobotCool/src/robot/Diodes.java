package robot;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.LED;

/****************************************************************************
 * 
 * Contr�le de l'�cran LCD
 *
 */

public class Diodes {

	// Couleurs | Les _ repr�sentent du clignotement
	public static final int OFF = 0;
	public static final int VERT = 1;
	public static final int ROUGE = 2;
	public static final int ORANGE = 3;
	public static final int VERT_ = 4;
	public static final int ROUGE_ = 5;
	public static final int ORANGE_ = 6;
	public static final int VERT__ = 7;
	public static final int ROUGE__ = 8;
	public static final int ORANGE__ = 9;

	// Attributs
	private LED diodes;

	public Diodes(Brick brick) {
		diodes = brick.getLED();
		eteint();
	}

	public void eteint() {
		couleur(OFF);
	}

	public void couleur(int couleur) {
		diodes.setPattern(couleur);
	}

	// Pour utiliser les diodes pour afficher la couleur vue par le d�tecteur optique
	public void couleurOptic2LED(int couleur) {
		switch (couleur) {
			case Optique.VERT:
				couleur(VERT);
				break;
			case Optique.ROUGE:
				couleur(ROUGE);
				break;
			case Optique.ORANGE:
				couleur(ORANGE);
				break;
			default:
				couleur(OFF);
		}
	}

}
