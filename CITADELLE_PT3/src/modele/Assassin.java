package modele;

import java.util.Random;
import java.util.Scanner;

public class Assassin extends modele.Personnage{
    public Assassin(){
        super("Assassin", 1, Caracteristiques.ASSASSIN, 1);
    }

    @Override
    public void utiliserPouvoir() {
        PlateauDeJeu plateau = super.getPlateau();

        if (plateau != null) {
            for (int i = 0; i <= plateau.getNbPersonnages()-1; i++) {
                System.out.println(i+1 + ". " + plateau.getPersonnage(i).getNom());
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
        System.out.print("Entrez un nombre entre 1 et " + maxPersonnage + ": ");

        // Lire la réponse de l'utilisateur
        int choixUtilisateur;

        // S'assurer que l'utilisateur entre un entier valide
        while (true) {
            try {
                choixUtilisateur = Integer.parseInt(scanner.nextLine());

                // Vérifier si le nombre est compris entre 1 et maxPersonnage
                if (choixUtilisateur >= 1 && choixUtilisateur <= maxPersonnage && plateau.getPersonnage(choixUtilisateur -1) != this) {
                    break; // Sortir de la boucle si le nombre est valide
                }else {
                    System.out.println("Veuillez entrer un nombre entre 1 et " + maxPersonnage + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide entre 1 et " + maxPersonnage + ": ");
            }
        }
        System.out.println("Vous avez choisi d'assasiner le personnage " + plateau.getPersonnage(choixUtilisateur -1 ).getNom());
        plateau.getPersonnage(choixUtilisateur - 1).setEstAssassine();
    }
    public void UtiliserPouvoirAvatar(){
        PlateauDeJeu plateau = super.getPlateau();
        Random random = new Random();

        if (plateau != null) {
            int maxPersonnage = plateau.getNbPersonnages();
            int choixAuto;
            while (true) {
                choixAuto = random.nextInt(maxPersonnage) + 1;
                if (plateau.getPersonnage(choixAuto - 1) != this) {
                    break;
                }
            }
            System.out.println("L'avatar a choisi de tuer le personnage " + plateau.getPersonnage(choixAuto - 1).getNom());
            plateau.getPersonnage(choixAuto - 1).setEstAssassine();
        } else {
            System.out.println("Le plateau n'est pas défini pour le personnage.");
        }
    }
}
