/**
 * Fichier:	Fin.java
 * Cours:   420-238/241-AH
 * Par:     Elliot Boisjoly, Joey Lapierre, Joshua Ramirez Kawoly, Mohammed Rayane Ghofiri, Jacob Somphanthabansouk
 * Travail: Projet3_241
 * Date:    20 mai 2024
 */

package robot; 

public class Fin extends Action { // Déclare une classe publique nommée Fin qui hérite de la classe Action

    public Fin(Robot bot) { // Constructeur de la classe Fin
        super(bot, "Fin"); // Appelle le constructeur de la classe parente Action avec le robot et la description "Fin"
    }

    @Override
    public void go() { // Implémente la méthode abstraite go de la classe Action
        bot.pilote().arrete(); // Arrête le robot en appelant la méthode arrete du pilote
        bot.son().bip(); // Émet un bip en appelant la méthode bip du composant sonore
    }
}
