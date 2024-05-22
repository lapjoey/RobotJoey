/**
 * Fichier:	Tourne.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 

public class Tourne extends Action { // Déclare une classe publique nommée Tourne qui hérite de la classe Action
    private int angle; // Déclare un champ privé pour l'angle de rotation
    private boolean sens; // Déclare un champ privé pour le sens de rotation

    public Tourne(Robot bot, int angle, boolean sens) { // Constructeur de la classe Tourne
        super(bot); // Appelle le constructeur de la classe parente Action
        this.angle = angle; // Initialise le champ angle avec la valeur passée en paramètre
        this.sens = sens; // Initialise le champ sens avec la valeur passée en paramètre
        this.description = "Tourne de " + angle + " degrés " + (sens ? "dans le sens horaire" : "dans le sens anti-horaire"); // Initialise la description avec l'angle et le sens de rotation
    }

    @Override
    public void go() { // Implémente la méthode abstraite go de la classe Action
        double angleRotation = sens ? angle : -angle; // Détermine l'angle de rotation en fonction du sens
        bot.pilote().tourne(angleRotation); // Utilise la méthode tourne du pilote pour effectuer la rotation avec l'angle spécifié
    }
}
