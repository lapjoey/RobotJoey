/**
 * Fichier:	Bip.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 

public class Bip extends Action { // Déclare une classe publique nommée Bip qui hérite de la classe Action
    private int nombre; // Déclare un champ privé pour le nombre de bips

    public Bip(Robot robot, int nombre) { // Constructeur de la classe Bip
        super(robot); // Appelle le constructeur de la classe parente Action
        this.nombre = nombre; // Initialise le champ nombre avec la valeur passée en paramètre
        this.description = "Bip " + nombre + " fois"; // Initialise la description avec le nombre de bips
    }

    @Override
    public void go() { // Implémente la méthode abstraite go de la classe Action
        for (int i = 0; i < nombre; i++) { // Boucle pour faire le nombre de bips spécifié
            robot.son().bip(); // Appelle la méthode bip du composant sonore du robot
            try {
                Thread.sleep(500); // Pause de 500ms entre chaque bip
            } catch (InterruptedException e) { // Gère les interruptions éventuelles pendant le sleep
                e.printStackTrace(); // Affiche la trace de la pile d'erreurs
            }
        }
    }
}
