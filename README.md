# <code style="color : darkorange">Fonctionnement du code</code>
## Lecture de la séquence de couleurs :
1. L'application initialise le robot et affiche un message demandant à l'utilisateur d'appuyer sur OK pour commencer la lecture.
2. Le robot avance lentement sur la surface de travail jusqu'à ce qu'il détecte une couleur autre que le blanc.
3. Lorsqu'une couleur est détectée, l'application crée une action appropriée et l'ajoute à une liste d'actions.
4. Le robot continue d'avancer jusqu'à ce qu'il détecte à nouveau du blanc, indiquant la fin de la lecture de l'action.

### Exécution de la séquence d'actions :
5. Lorsque l'application a ajouté une action "Fin" à la liste d'actions, elle exécute chaque action stockée dans la liste, une à une.
6. Chaque action est exécutée en appelant sa méthode "go()".
7. Si, pendant l'exécution d'une action, le contact est enclenché sur "Contact" ou que la distance est de moins de 15 cm avec un obstacle (Ultra), l'application arrête le programme en émettant 3 bips.

//

# <code style="color : magenta">Structure du code</code>

- La classe "Main" contient la méthode principale qui lance l'application.
- La classe "Robot" représente le robot EV3 et contient des méthodes pour lire les capteurs et contrôler les moteurs.
- La classe "Action" est une classe abstraite qui représente une action que le robot peut effectuer. Elle contient une méthode abstraite "go()" qui doit être implémentée par chaque classe d'action concrète.
- Les classes "Pause", "Avance", "Tourne", "Bip", "Pelle" et "Fin" héritent de la classe "Action" et implémentent la méthode "go()" pour effectuer l'action correspondante.

//
# <code style="color : red">Les actions effectuées par le robot pour chaque couleur</code>
- Blanc : entre chaque couleur il y aura une zone de blanc.
- Brun : pause de duree secondes.
- Jaune : avance du nombre de centimètres.
- Vert : tourne de angle degrés vers la droite si sens est vrai, sinon vers la gauche.
- Bleu : tourne de angle degrés vers la droite si sens est vrai, sinon vers la gauche.
- Rouge : émet une série de bips.
- Rose : lève ou baisse la pelle selon la direction true ou false.
- Orange : lève ou baisse la pelle selon la direction true ou false.
- Noir : fin du programme, le robot fait un tour sur lui-même et bip.
