package modele;

import java.util.Scanner;

public class Assassin extends modele.Personnage{
    public Assassin(){
        super("modele.Assassin", 1, "a", 4);
    }

    @Override
    public void utiliserPouvoir() {
        PlateauDeJeu plateau = super.getPlateau();

        if (plateau != null) {
            for (int i = 0; i <= plateau.getNbPersonnages()-1; i++) {
                System.out.println(i+1 + ". " + plateau.getPersonnage(i));
            }
            /*
            modifiez l’état du personnage choisi (appel à setAssassine()).
             */
        } else {
            System.out.println("Le plateau n'est pas défini pour l'assassin.");
        }
        System.out.println("Quel personnage voulez vous assasiner ?");
        Scanner scanner = new Scanner(System.in);

        int maxPersonnage = plateau.getNbPersonnages();
        System.out.println("Entrez un nombre entre 1 et " + maxPersonnage + ": ");

        // Lire la réponse de l'utilisateur
        int choixUtilisateur;

        // S'assurer que l'utilisateur entre un entier valide
        while (true) {
            try {
                choixUtilisateur = Integer.parseInt(scanner.nextLine());
                choixUtilisateur =- 1;
                // Vérifier si le nombre est compris entre 1 et maxPersonnages
                if (choixUtilisateur >= 0 && choixUtilisateur <= maxPersonnage) {
                    break; // Sortir de la boucle si le nombre est valide
                } else {
                    System.out.println("Veuillez entrer un nombre entre 1 et " + maxPersonnage + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide entre 1 et " + maxPersonnage + ": ");
            }
        }

        System.out.println("Vous avez choisi le nombre : " + choixUtilisateur);
    }

}
