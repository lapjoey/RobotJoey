/**
 * Fichier:	Pelle.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 
public class Pelle extends Action { // Déclare une classe publique nommée Pelle qui hérite de la classe Action
    private boolean direction; // Déclare un champ privé pour la direction de la pelle (levée ou baissée)

    public Pelle(Robot robot, boolean direction) { // Constructeur de la classe Pelle
        super(robot); // Appelle le constructeur de la classe parente Action
        this.direction = direction; // Initialise le champ direction avec la valeur passée en paramètre
        this.description = "Pelle " + (direction ? "levée" : "baissée"); // Initialise la description avec la direction de la pelle
    }

    @Override
    public void go() { // Implémente la méthode abstraite go de la classe Action
        if (direction) { // Si la direction est true (levée)
            robot.pelle().monte(); // Appelle la méthode monte du composant de la pelle du robot
        } else { // Sinon (direction est false, baissée)
            robot.pelle().descent(); // Appelle la méthode descent du composant de la pelle du robot
        }
    }
}
