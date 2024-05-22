/**
 * Fichier:	Action.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 

public abstract class Action { 
    protected Robot robot; // Déclare un champ protégé de type Robot
    protected String description; // Déclare un champ protégé de type String pour la description

    public Action(Robot robot) { // Constructeur de la classe Action qui prend un objet Robot en paramètre
        this.robot = robot; // Initialise le champ robot avec l'objet passé en paramètre
    }

    public abstract void go(); // Méthode abstraite que les sous-classes doivent implémenter

    @Override
    public String toString() { // Redéfinition de la méthode toString pour retourner la description
        return description; // Retourne la valeur du champ description
    }
}
