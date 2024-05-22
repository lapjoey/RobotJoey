package robot;
import lejos.hardware.Brick;
import lejos.hardware.sensor.EV3TouchSensor;

/****************************************************************************
 * 
 * Senseur de contact
 *
 */

public class Contact {

	// Attributs
	private EV3TouchSensor senseur;
	private int tailleEchantillon;
	private float[] echantillon;
	
	// Constructeur
	public Contact(Brick brick, int port) {
		senseur =  new EV3TouchSensor(brick.getPort("S"+port));
		tailleEchantillon = senseur.sampleSize();
		echantillon = new float[tailleEchantillon];
	}

	// Lecture
	public boolean oui() {
		senseur.fetchSample(echantillon, 0);
		return (echantillon[0] == 1.0f);
	}
}
