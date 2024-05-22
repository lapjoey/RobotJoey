package robot;
import lejos.hardware.Brick;
import lejos.hardware.Button;
import lejos.hardware.sensor.EV3ColorSensor;

/****************************************************************************
 * 
 * Senseur optique
 *
 */

public class Optique {

	// Constantes
	public static final int ROUGE = 0;
	public static final int VERT = 1;
	public static final int BLEU = 2;
	public static final int JAUNE = 3;
	public static final int MAGENTA = 4;
	public static final int ORANGE = 5;
	public static final int BLANC = 6;
	public static final int NOIR = 7;
	public static final int ROSE = 8;
	public static final int GRIS = 9;
	public static final int GRIS_PALE = 10;
	public static final int GRIS_FONCE = 11;
	public static final int CYAN = 12;
	public static final int BRUN = 13;
	public static final int AUCUN = -1;
	
	private static final String[] COULEURS = { "ROUGE", "VERT", "BLEU", "JAUNE", "MAGENTA", "ORANGE", "BLANC", "NOIR", "ROSE", "GRIS", "GRIS_PALE", "GRIS_FONCE", "CYAN", "BRUN" };
	
	// Attributs
	private EV3ColorSensor senseur;
	private int tailleEchantillon;
	private float[] echantillon;
	
	// Constructeur
	public Optique(Brick brick, int port) {
		senseur =  new EV3ColorSensor(brick.getPort("S"+port));
		tailleEchantillon = senseur.sampleSize();
		echantillon = new float[tailleEchantillon];
	}

	// Lecture
	public int couleur() {
		return senseur.getColorID();
	}
	
	// Quelle couleur?
	static public String nomCouleur(int couleur) {
		if (couleur < 0 || couleur > 13)
			return "INCONNU";
		else
			return COULEURS[couleur];
	}
}
