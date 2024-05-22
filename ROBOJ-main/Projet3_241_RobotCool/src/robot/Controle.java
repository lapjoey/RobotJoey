package robot;
import java.util.Random;
import java.util.concurrent.*;

/****************************************************************************
 * 
 * @author Nicola Grenon
 * @version 0.1
 * @date 20160304
 * 
 * Objectif: LE robot parcoura la surface disponible en changeant quelque
 * fois de direction al�atoirement ou suite � la rencontre d'un obstacle.
 * 
 * - S'il frappe un obstacle:
 * 		. il fait un son de collision
 * 		. il recule un peu et tourne de 90 degr�s.
 * - S'il arrive au bord de la surface:
 * 		. il fait un son de chute
 * 		. il tourne de 180 degr�s
 * - Il tente de reproduire avec ses LED de couleur la couleur sur laquelle
 * 	 il roule (vert, rouge ou orange).
 * - S'il d�tecte un obstacle s'approchant, il, selon la distance:
 * 		. ralentit
 * 		. clignore plus ou moins rapidement
 * 		. bip, plus ou moins rapidement
 * - Lorsqu'il tourne, il le fait toujours par angles de multiples de
 * 	 45/90 degr�s, qu'il corrige au moyen du gyroscope.
 * - Si on appuie sur le bouton ESCAPE, le programme s'arr�te.
 */

public class Controle {
	
	private static Random hasard;
	private static long actuel = 0l;
	private static int diffAngle;						// La correction d'angle selon le gyro
	private static byte delaisCorriger = 0;				// Pour ne pas faire de correction pendant autre chose
	private static final int DIFF_ANGLE_MAX = 8;		// La diff�rence minimale d'angle pour une correction
	private static final int FREQ_CORR_ANGLE = 2;		// � chaque combien de secondes on corrige
	private static final byte TEMPS_ATTENTE_NORMAL = 5;	// D�lais avant de corriger
	private static final double DIST_CONTACT = 4.0;		// En cm, distance pour consid�rer un contact
	private static final double DIST_PROCHE = 10.0;		// En cm, pour d�tection distance avec ultrasons
	private static final double DIST_MOYEN = 20.0;		// En cm, pour d�tection distance avec ultrasons
	private static final double DIST_LOIN = 30.0;		// En cm, pour d�tection distance avec ultrasons
	private static final double VITESSE_NORMALE = 10.0;	// La vitesse roul�e normalement.
	private static final double MIN_SEC = 10.0;			// D�lais minimum avant de changer de direction
	private static final double MAX_SEC = 25.0;			// D�lais maximum avant de changer de direction
	
	public static void main(String[] args) {

		// On cr�e/r�cup�re le robot.
		Robot robot = Robot.connect();
		hasard = new Random();
		
		// On initialise quelques variables
		int couleurVue = 0;								// La couleur d�j� vue.
		int detectCouleur = 0;							// La couleur vue en ce moment.
		double vitesse = VITESSE_NORMALE;				// La vitesse du robot en ce moment.
		double distance = Double.MAX_VALUE;				// La distance � un objet face au robot.
		double prochain = (MAX_SEC-MIN_SEC)/2.+MIN_SEC;	// Quand aura lieu le prochain changement de direction
		
		// On attend le signal de d�part!
	    robot.ecran().ecrire("Peser OK!");
		robot.son().bip(Son.MONTE);
		robot.boutons().attend();
		robot.ecran().efface();
	    
		// On ex�cute un Thread qui compte les secondes d'ex�cution (pour simplifier la logique)
	    final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	    executorService.scheduleAtFixedRate(new Runnable() {
	        @Override
	        public void run() {
	            plusXSeconde();
	        }
	    }, TEMPS_ATTENTE_NORMAL, FREQ_CORR_ANGLE, TimeUnit.SECONDS);
		
	    // On boucle tant que le bouton ESCAPE n'est pas pes�.
		while (!robot.boutons().clicEscape()) {
			
			// Est-on au bord du vide??
			detectCouleur = robot.optique().couleur();
			if (detectCouleur == Optique.AUCUN) {
				robot.pilote().arrete();
				delaisCorriger = TEMPS_ATTENTE_NORMAL;
				robot.son().jouer(Son.PIANO, new String[] {"sol","sol","sol","re#"}, new int[] {250,250,250,850});
				robot.pilote().avance(VITESSE_NORMALE/2.0, -10.0);		// On recule un peu, lentement
				robot.pilote().tourne(180*posNegAlea());
			} else

			// Quelle couleur on voit?
			if (couleurVue != detectCouleur && distance > 30.0) {		// Pour �viter interf�rence avec distance � un objet
				couleurVue = detectCouleur;
				robot.diodes().couleurOptic2LED(couleurVue);
				robot.son().bip(Son.BIPBIP);
			}
			
			// A-t-on frapp� un objet?
			if (robot.contact().oui()) {
				robot.pilote().arrete();
				delaisCorriger = TEMPS_ATTENTE_NORMAL;
				robot.son().bip(Son.BUZ);
				robot.pilote().avance(VITESSE_NORMALE/2.0, -7.0);		// On recule un peu, lentement
				robot.pelle().descend();
				robot.pelle().monte();
				robot.pilote().tourne(90*posNegAlea());
			}
			
			// Est-ce qu'on s'approche de quelque chose?
			distance = robot.ultra().distance();
			if (distance < DIST_CONTACT) {
				robot.pilote().arrete();
				delaisCorriger = TEMPS_ATTENTE_NORMAL;
				robot.son().bip(Son.BUZ);
				robot.pilote().avance(VITESSE_NORMALE/2.0, -7.0);		// On recule un peu, lentement
				robot.pelle().descend();
				robot.pelle().monte();
				robot.pilote().tourne(90*posNegAlea());
			} else if (distance < DIST_PROCHE) {
				vitesse = VITESSE_NORMALE/4.0;
				robot.diodes().couleur(Diodes.ROUGE__);
				robot.son().jouer(Son.XYLOPHONE, new String[] {"mi"}, new int[] {200});
			} else if (distance < DIST_MOYEN) {
				vitesse = VITESSE_NORMALE/3.0;
				robot.diodes().couleur(Diodes.ROUGE_);
				robot.son().jouer(Son.XYLOPHONE, new String[] {"re"}, new int[] {400});
			} else if (distance < DIST_LOIN) {
				vitesse = VITESSE_NORMALE/2.0;
				robot.diodes().couleur(Diodes.ROUGE);
				robot.son().jouer(Son.XYLOPHONE, new String[] {"do"}, new int[] {800});
			} else {
				vitesse = VITESSE_NORMALE;
			}
			
			// On progresse normalement donc... et on tourne parfois...
			if (prochain < actuel) {
				delaisCorriger = TEMPS_ATTENTE_NORMAL;
				robot.pilote().tourne(angleAlea());
				prochain = actuel + quelquesSecondes(MIN_SEC, MAX_SEC);
			}
			
			// On ajuste l'angle du trajet si on a d�vi� de trop de degr�s
			int angleActuel = robot.gyro().angle();
			int angleNormal = robot.pilote().angleRef();
			diffAngle = angleActuel - angleNormal;
			if (angleNormal == 0 && angleActuel > 180)	// 358-0=358 ==> -2
				diffAngle -= 360;
			// On ne peut corriger ici, on risquerait de corriger trop souvent...
			// On utilise donc la r�p�tition une fois par *seconde.
			
			// On avance!
			robot.pilote().avance(vitesse);
		}
		
		// On fait le m�nage et on quitte
		robot.pilote().arrete();
		robot.diodes().eteint();
		robot.ecran().efface();
		robot.ecran().ecrire("Au revoir!");
		robot.son().bip(Son.DESCEND);
		System.exit(0);
	}
	
	// M�thodes pour tirer au hasard
	static private byte posNegAlea() {
		return (byte) (hasard.nextFloat()<0.5f?1:-1);
	}
	static private byte angleAlea() {
		double x = hasard.nextFloat();
		/*if (x < 0.25f) return -45;
		else*/ if (x < 0.5f) return -90;
		/*else if (x < 0.75f) return 45;*/
		else return 90;
	}
	static private double quelquesSecondes(double min, double max) {
		return hasard.nextDouble() * (max-min) + min;
	}
	
	// Compte le temps qui passe
	static private void plusXSeconde() {
		actuel+=FREQ_CORR_ANGLE;
		correction();	// On lance une corrrection de trajectoire au besoin
	}
	
	// Correction de trajectoire
	static private void correction() {
		delaisCorriger -= FREQ_CORR_ANGLE;
		if (delaisCorriger < 0) delaisCorriger = 0;
		if (Math.abs(diffAngle) >= DIFF_ANGLE_MAX && delaisCorriger == 0) {
			// On corrige
			delaisCorriger = TEMPS_ATTENTE_NORMAL;
			Robot.connect().pilote().tourne(diffAngle);
		}
	}
}