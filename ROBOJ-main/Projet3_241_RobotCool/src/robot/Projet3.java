/**
 * Fichier:	Projet3.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot;

import java.util.ArrayList;

public class Projet3 {
    
    // Constantes
    private static final double DISTANCE_CONTACT = 15.0; // Distance en cm pour un contact.
    private static final double VITESSE_DEFAUT = 5.0; // Vitesse par défaut du robot.
    
    public static void main(String[] args) {
        
        Robot robot = Robot.connect(); // Initialisation du robot
        
        // Variables
        boolean contientFin = false; // Variable pour déterminer si on arrête le programme en cas de création d'objet Fin (Couleur noire)
        
        // Instanciation de la liste d'action.
        ArrayList<Action> listeActions = new ArrayList<Action>();
        
        // Premières instructions effectuées avant la lecture des couleurs (Couleur verte, bip, message de confirmation)
        robot.diodes().couleur(Diodes.VERT);
        robot.son().bip();
        robot.ecran().ecrire("Appuyez sur OK");
        robot.boutons().attend(); // On attend que l'utilisateur appuie sur OK.
        robot.ecran().efface();
        
        // Lecture par le robot, on avance jusqu'à ce qu'on détecte une couleur, et on ajoute l'action associée à la liste. On fait une boucle jusqu'à ce que l'on appuie sur escape ou quand l'objet fin est créé.
        while (!robot.boutons().clicEscape() && !contientFin) {
            robot.pilote().avance(VITESSE_DEFAUT); // Avancer doucement
            
            int couleurLue = robot.optique().couleur(); // Lire la couleur actuelle
            
            if (couleurLue != Optique.BLANC) { // Si une couleur autre que blanc est détectée
                ajouterAction(robot, listeActions, couleurLue); // Ajouter l'action correspondante à la liste
                
                if (couleurLue == Optique.NOIR) { // Si la couleur noire est détectée
                    contientFin = true; // Arrêter la boucle
                    robot.son().bip();
                    robot.diodes().couleur(Diodes.ROUGE);
                    robot.ecran().ecrire("Appuyez sur OK");
                    robot.boutons().attend();
                    robot.ecran().efface();
                }
                
                // Avancer jusqu'à retrouver le blanc
                while (robot.optique().couleur() != Optique.BLANC) {
                    robot.pilote().avance(1);
                }
            }
        }
        
        // Après avoir stocké toutes les actions, une boucle for(each) qui traversera toutes les actions pour y faire un .go().
        for (Action action : listeActions) {
            robot.ecran().ecrire("Action : " + action.toString());
            action.go();
            robot.ecran().efface();
        }
        
        // Exécution de mouvements supplémentaires
        robot.pilote().avance(10.0, 10.0);
        robot.pilote().tourne(180.0);
        robot.pilote().avance(5.0, 15.0);
        robot.pelle().descend();
        robot.pelle().monte();
        robot.ecran().ecrire("Voila!");
        robot.boutons().attend();
    }
    
    public static void ajouterAction(Robot robot, ArrayList<Action> listeActions, int couleur) {
    switch (couleur) {
        case Optique.BLANC:
            break;
        case Optique.BRUN:
            Pause pause = new Pause(robot, 3); // Pause de 3 secondes
            listeActions.add(pause);
            robot.ecran().ecrire("Lu : " + pause.toString());
            break;
        case Optique.JAUNE:
            Avance avance = new Avance(robot, 10);
            listeActions.add(avance);
            robot.ecran().ecrire("Lu : " + avance.toString());
            break;
        case Optique.VERT:
            Tourne tourneDroite = new Tourne(robot, 90, true);
            listeActions.add(tourneDroite);
            robot.ecran().ecrire("Lu : " + tourneDroite.toString());
            break;
        case Optique.BLEU:
            Tourne tourneGauche = new Tourne(robot, 90, false);
            listeActions.add(tourneGauche);
            robot.ecran().ecrire("Lu : " + tourneGauche.toString());
            break;
        case Optique.ROUGE:
            Bip bip = new Bip(robot, 1);
            listeActions.add(bip);
            robot.ecran().ecrire("Lu : " + bip.toString());
            break;
        case Optique.ROSE:
            Pelle pelleMontee = new Pelle(robot, true);
            listeActions.add(pelleMontee);
            robot.ecran().ecrire("Lu : " + pelleMontee.toString());
            break;
        case Optique.ORANGE:
            Pelle pelleDescendue = new Pelle(robot, false);
            listeActions.add(pelleDescendue);
            robot.ecran().ecrire("Lu : " + pelleDescendue.toString());
            break;
        case Optique.NOIR:
            Fin fin = new Fin(robot);
            listeActions.add(fin);
            robot.ecran().ecrire("Lu : " + fin.toString() + " [Couleur imprévue!]");
            break;
            
        default: // Toutes les couleurs imprévues ajoutent une série de 2 bips seulement.
            Bip bips = new Bip(robot, 2);
            listeActions.add(bips);
            robot.ecran().ecrire("Lu : " + bips.toString() + " [Couleur imprévue!]");
            break;
    	}
	}
	
}
