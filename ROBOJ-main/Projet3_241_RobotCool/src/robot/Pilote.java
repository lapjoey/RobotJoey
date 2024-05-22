package robot;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

/****************************************************************************
 * 
 * Contr�le des roues par le protocole pilot
 *
 */

public class Pilote {

	// Attributs
	private MovePilot vehicule;
	private short angleNormal;		// R�f�rence d'orientation absolue (0 � 259)

	public Pilote(Brick brick, char gauche, char droite, double diametre, double distanceAxe) {

		// Les roues (par pilot)
		Wheel roueGauche = WheeledChassis.modelWheel(new EV3LargeRegulatedMotor(brick.getPort(Character.toString(gauche))), diametre).offset(-distanceAxe);
		Wheel roueDroite = WheeledChassis.modelWheel(new EV3LargeRegulatedMotor(brick.getPort(Character.toString(droite))), diametre).offset(distanceAxe);
		Chassis chassis = new WheeledChassis(new Wheel[] { roueGauche, roueDroite }, WheeledChassis.TYPE_DIFFERENTIAL);
		vehicule = new MovePilot(chassis);
		angleNormal = 0;
	}

	// Pour faire avancer/reculer le robot
	public void avance(double vitesse) {
		vehicule.setLinearSpeed(vitesse);
		if (!vehicule.isMoving())
			vehicule.forward();
	}

	public void avance(double vitesse, double distance) {
		vehicule.setLinearSpeed(vitesse);
		vehicule.travel(distance);			// Ici pas de true, on veut faire la distance et ensuite reprendre!
	}

	// M�thodes de gestion du d�placement
	public void arrete() {
		vehicule.stop();
	}

	public boolean bouge() {
		return vehicule.isMoving();
	}

	// Pour faire tourner le robot
	public void tourne(double angle) {
		if (!vehicule.isMoving())
			vehicule.rotate(angle);				// Ici pas de true, on veut tourner et ensuite reprendre!
		else {
			vehicule.stop();
			vehicule.rotate(angle);
			vehicule.forward();			
		}
		angleNormal = (short) ((angleNormal + angle + 360) % 360);
	}
	
	// Pour r�cup�rer l'angle de r�f�rence
	public short angleRef() {
		return angleNormal;
	}

}
