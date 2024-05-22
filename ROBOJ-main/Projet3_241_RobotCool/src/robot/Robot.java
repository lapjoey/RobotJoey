package robot;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.lcd.GraphicsLCD;
import lejos.hardware.motor.*;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.chassis.*;
import lejos.robotics.navigation.MovePilot;

/****************************************************************************
 * 
 * La classe Robot, qui manipulera le robot EV3.
 *
 */

public class Robot {
	
	// D�tails du robot construit
	static private final char ROUE_GAUCHE = 'B';
	static private final char ROUE_DROITE = 'C';
	static private final char PELLE = 'A';
	static private final short ANGLE_MAX_PELLE = 155;	// degr�s
	static private final double DIAMETRE_ROUE = 5.65;
	static private final double DISTANCE_AXE = 5.9;
	static private final byte PORT_CONTACT = 1;
	static private final byte PORT_GYRO = 2;
	static private final byte PORT_OPTIQUE = 3;
	static private final byte PORT_ULTRA = 4;
	
	// Attributs de classe
	static private Robot bot;
	
	// Les morceaux du robot
	private Brick brick;		// Le module
	private Pilote pilote;		// Gestion du d�placement
	private Moteur pelle;		// Gestion de la pelle
	private Ecran ecran;		// Acc�s � l'�cran LCD
	private Diodes diodes;		// Acc�s aux diodes autour des boutons
	private Son son;			// Acc�s au haut-parleur
	private Boutons boutons;	// Acc�s aux boutons du robot
	private Contact contact;	// Senseur de contact
	private Gyro gyro;			// Senseur gyroscopique
	private Ultra ultra;		// Senseur � ultrasons
	private Optique couleur;	// Senseur optique

	
	// Cr�ation de l'instance si elle n'existe pas.
	static public Robot connect() {
		if (bot == null) {
			bot = new Robot();
		}
		return bot;
	}
	
	// Constructeur (priv�)
	private Robot() {
		// Le module
		brick = BrickFinder.getDefault();
	}
	
	// Gestion des roues, du d�placement
	public Pilote pilote() {
		if (pilote == null)
			pilote = new Pilote(brick, ROUE_GAUCHE, ROUE_DROITE, DIAMETRE_ROUE, DISTANCE_AXE);
		return pilote;
	}

	public Moteur pelle() {
		if (pelle == null)
			pelle = new Moteur(brick, PELLE, ANGLE_MAX_PELLE);
		return pelle;
	}

	// Pour acc�der � l'�cran
	public Ecran ecran() {
		if (ecran == null)
			ecran = new Ecran(brick);
		return ecran;
	}
	
	// Pour acc�der aux diodes
	public Diodes diodes() {
		if (diodes == null)
			diodes = new Diodes(brick);
		return diodes;
	}
	
	// Pour acc�der au son
	public Son son() {
		if (son == null)
			son = new Son(brick);
		return son;
	}
	
	// Pour acc�der aux boutons
	public Boutons boutons() {
		if (boutons == null)
			boutons = new Boutons(brick);
		return boutons;
	}
	
	// Pour acc�der au senseur de contact
	public Contact contact() {
		if (contact == null)
			contact = new Contact(brick, PORT_CONTACT);
		return contact;
	}
	
	// Pour acc�der au senseur gyroscopique
	public Gyro gyro() {
		if (gyro == null)
			gyro = new Gyro(brick, PORT_GYRO);
		return gyro;
	}
	
	// Pour acc�der au senseur optique
	public Optique optique() {
		if (couleur == null)
			couleur = new Optique(brick, PORT_OPTIQUE);
		return couleur;
	}

	// Pour acc�der au senseur � ultrasons
	public Ultra ultra() {
		if (ultra == null)
			ultra = new Ultra(brick, PORT_ULTRA);
		return ultra;
	}
}
