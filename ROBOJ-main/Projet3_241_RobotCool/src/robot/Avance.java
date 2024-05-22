/**
 * Fichier:	Avance.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 

public class Avance extends Action { // Déclare une classe publique nommée Avance qui hérite de la classe Action
    private int cm; // Déclare un champ privé pour la distance en centimètres

    public Avance(Robot bot, int cm) { // Constructeur de la classe Avance
        super(bot); // Appelle le constructeur de la classe parente Action
        this.cm = cm; // Initialise le champ cm avec la valeur passée en paramètre
        this.description = "Avance de " + cm + " cm"; // Initialise la description avec la distance
    }

    @Override
    public void go() { // Implémente la méthode abstraite go de la classe Action
        robot.pilote().avance(10, cm); // Utilise la méthode avance du pilote pour avancer de la distance spécifiée
    }
}
