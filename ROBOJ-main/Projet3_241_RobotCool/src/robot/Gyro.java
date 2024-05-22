package robot;
import lejos.hardware.Brick;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

/****************************************************************************
 * 
 * Senseur gyroscopique
 *
 */

public class Gyro {

	// Attributs
	private EV3GyroSensor senseur;
	private int tailleEchantillon;
	private float[] echantillon;
	private SampleProvider angle;
	
	// Constructeur
	public Gyro(Brick brick, int port) {
		senseur =  new EV3GyroSensor(brick.getPort("S"+port));
		angle = senseur.getMode("Angle");
		tailleEchantillon = angle.sampleSize();
		echantillon = new float[tailleEchantillon];
	}

	// Lecture
	public int angle() {
		angle.fetchSample(echantillon, 0);
		return (int) (echantillon[0] +.5);			// Arrondi au degr� pr�s.
	}
}
