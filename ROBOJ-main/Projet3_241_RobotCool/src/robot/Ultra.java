package robot;
import lejos.hardware.Brick;
import lejos.hardware.sensor.EV3UltrasonicSensor;

/****************************************************************************
 * 
 * Senseur ultrasonic
 *
 */

public class Ultra {

	// Attributs
	private EV3UltrasonicSensor senseur;
	private int tailleEchantillon;
	private float[] echantillon;
	
	// Constructeur
	public Ultra(Brick brick, int port) {
		senseur =  new EV3UltrasonicSensor(brick.getPort("S"+port));
		tailleEchantillon = senseur.sampleSize();
		echantillon = new float[tailleEchantillon];
	}

	// Lecture
	public double distance() {
		senseur.fetchSample(echantillon, 0);
		return echantillon[0] * 100.0;			// En cm
	}
}
