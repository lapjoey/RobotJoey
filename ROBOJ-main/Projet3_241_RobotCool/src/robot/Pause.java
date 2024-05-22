/**
 * Fichier:	Pause.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 

public class Pause extends Action { // Déclare une classe publique nommée Pause qui hérite de la classe Action
    private int duree; // Durée en millisecondes

    public Pause(Robot robot, int dureeEnSecondes) { // Constructeur de la classe Pause
        super(robot); // Appelle le constructeur de la classe parente Action
        this.duree = dureeEnSecondes * 1000; // Convertir la durée en secondes en millisecondes
        this.description = "Pause de " + dureeEnSecondes + " secondes"; // Initialise la description avec la durée
    }

    @Override
    public void go() { // Implémente la méthode abstraite go de la classe Action
        try {
            Thread.sleep(duree); // Met en pause le thread actuel pendant la durée spécifiée
        } catch (InterruptedException e) { // Gère les interruptions éventuelles pendant le sleep
            e.printStackTrace(); // Affiche la trace de la pile d'erreurs
        }
    }
}
