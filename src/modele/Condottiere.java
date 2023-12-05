package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class Condottiere extends modele.Personnage {
    public Condottiere() {
        super("Condottiere", 8, Caracteristiques.CONDOTTIERE, 1);
    }

    @Override
    public void utiliserPouvoir() {
        Scanner scanner = new Scanner(System.in);
        char reponse;

        do {
            System.out.println("Voulez-vous utiliser votre pouvoir de destruction ? o/n");
            // Lire la réponse de l'utilisateur
            String userInput = scanner.nextLine().toLowerCase();

            // Vérifier si la réponse est valide (o ou n)
            if (userInput.length() == 1 && (userInput.charAt(0) == 'o' || userInput.charAt(0) == 'n')) {
                reponse = userInput.charAt(0);
                break; // Sortir de la boucle si la réponse est valide
            } else {
                System.out.println("Réponse invalide. Veuillez répondre avec 'o' ou 'n'.");
            }
        } while (true);

        // Utiliser la réponse selon votre logique
        if (reponse == 'o') {

            for (int i=0; i<=getPlateau().getNbJoueurs()-1; i++) {
                ArrayList<Quartier> citeJoueur = getPlateau().getJoueur(i).getCite();
                System.out.print(i +1 + " " + getPlateau().getJoueur(i).getNom() + ": ");
                for (int y = 0; y < getPlateau().getJoueur(i).nbQuartiersDansCite(); y++) {
                    System.out.print(y+1 + " " +citeJoueur.get(y).getNom());
                    System.out.print(" (coût "+citeJoueur.get(y).getCoutConstruction()+ ")" + ", ");
                }
                System.out.println("\n");
            }
            int choixUtilisateurJoueur;

            do {
                System.out.println("Veuillez entrer un chiffre entre 1 et " + getPlateau().getNbJoueurs() + " (ou 0 pour ne rien faire) : ");
                // Lire la réponse de l'utilisateur
                try {
                    choixUtilisateurJoueur = Integer.parseInt(scanner.nextLine());

                    // Vérifier si la réponse est entre 0 et le nombre de joueurs inclus
                    if (choixUtilisateurJoueur >= 0 && choixUtilisateurJoueur <= getPlateau().getNbJoueurs() || choixUtilisateurJoueur ==0) {
                        break; // Sortir de la boucle si la réponse est valide
                    } else {
                        System.out.println("Réponse invalide. Veuillez entrer un chiffre entre 1 et " + getPlateau().getNbJoueurs() + " (ou 0 pour ne rien faire).");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Veuillez entrer un nombre valide.");
                }
            } while (true);

            if (choixUtilisateurJoueur == 0) {
                System.out.println("Vous avez décidé de ne rien faire.");
            } else {
                System.out.println("Quel quartier choisissez vous ?");
                System.out.println("Votre trésor contient " +getJoueur().getTresor() + " pieces" );
                int choixUtilisateurQuartier;

                do {
                    System.out.println("Veuillez entrer un chiffre entre 1 et " + getPlateau().getJoueur(choixUtilisateurJoueur -1).nbQuartiersDansCite());
                    // Lire la réponse de l'utilisateur
                    try {
                        choixUtilisateurQuartier = Integer.parseInt(scanner.nextLine());
                        // Vérifier si la réponse est entre 0 et le nombre de joueurs inclus
                        if (choixUtilisateurQuartier > 0 && choixUtilisateurQuartier <= getPlateau().getJoueur(choixUtilisateurJoueur - 1).nbQuartiersDansCite() && getJoueur().getTresor() >= getPlateau().getJoueur(choixUtilisateurJoueur -1).getCite().get(choixUtilisateurQuartier -1).getCoutConstruction()) {
                            break; // Sortir de la boucle si la réponse est valide
                        } else if (getJoueur().getTresor() < getPlateau().getJoueur(choixUtilisateurJoueur -1).getCite().get(choixUtilisateurQuartier-1).getCoutConstruction()) {
                            System.out.println("Votre trésor n'est pas suffisant");
                        } else {
                            System.out.println("Réponse invalide. Veuillez entrer un chiffre entre 1 et " + getPlateau().getJoueur(choixUtilisateurJoueur- 1).nbQuartiersDansCite());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre valide.");
                    }
                } while (true);
                getPlateau().getJoueur(choixUtilisateurJoueur -1).retirerQuartierDansCite(getPlateau().getJoueur(choixUtilisateurJoueur -1).getCite().get(choixUtilisateurQuartier -1).getNom());
            }
            // Fermer le scanner
            scanner.close();
        }
         else {
            System.out.println("Vous avez choisi de ne pas utiliser votre pouvoir de destruction.");
            // Ajouter votre logique ici
        }
    }

    @Override
    public  void percevoirRessourcesSpecifiques() {
        int nombreQuartierMiliraire = 0;
        for (int i = 0; i < getJoueur().nbQuartiersDansCite(); i++) {
            if (getJoueur().getCite().get(i).getType().equals(Quartier.TYPE_QUARTIERS[2]))
            {
                nombreQuartierMiliraire++;
                getJoueur().ajouterPieces(1);
            }
        }
        System.out.println("Vos batiments militaire vous rapporte " + nombreQuartierMiliraire);
    }
}
