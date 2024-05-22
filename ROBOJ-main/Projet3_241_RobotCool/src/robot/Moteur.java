package robot;
import lejos.hardware.Brick;
import lejos.hardware.motor.EV3LargeRegulatedMotor;

/****************************************************************************
 * 
 * Contr�le d'un moteur
 *
 */

public class Moteur {
	
	// Attributs
	private EV3LargeRegulatedMotor moteur;
	private int angleMaxPelle;

	public Moteur(Brick brick, char port) {
		moteur = new EV3LargeRegulatedMotor(brick.getPort(Character.toString(port)));
	}
	public Moteur(Brick brick, char port, short angleMaxPelle) {
		this(brick, port);
		this.angleMaxPelle = angleMaxPelle;
	}

	// Activer le moteur un nombre de degr�s
	public void tourne(int degres) {
		moteur.rotate(degres);		// On attend la fin du mouvement
	}
	
	// Monter la pelle
	public void monte() {
		tourne(angleMaxPelle);
	}
	
	// Descendre la pelle
	public void descend() {
		tourne(-angleMaxPelle);
	}

}
