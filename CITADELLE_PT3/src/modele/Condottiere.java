package modele;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public abstract class Condottiere extends modele.Personnage {
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

            for (int i = 0; i < getPlateau().getNbJoueurs(); i++) {
                Joueur joueur = getPlateau().getJoueur(i);
                ArrayList<Quartier> citeJoueur = joueur.getCite();

                System.out.print((i + 1) + " " + joueur.getNom() + ": ");

                for (int y = 0; y < joueur.nbQuartiersDansCite(); y++) {
                    Quartier quartier = citeJoueur.get(y);

                    System.out.print((y +1) + " " + quartier.getNom());
                    System.out.print(" (coût " + quartier.getCoutConstruction() + "), ");
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
                //...
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

                Quartier quartierRetire = getPlateau().getJoueur(choixUtilisateurJoueur - 1).getCite().get(choixUtilisateurQuartier - 1);
                getPlateau().getJoueur(choixUtilisateurJoueur - 1).retirerQuartierDansCite(quartierRetire.getNom());

                System.out.println("On retire " + quartierRetire.getNom() + " à " + getPlateau().getJoueur(choixUtilisateurJoueur-1).getNom());
            }
            scanner.close();
        }
         else {
            System.out.println("Vous avez choisi de ne pas utiliser votre pouvoir de destruction.");
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
    public void UtiliserPouvoirAvatar(){

        Random random = new Random();
        boolean utiliserPouvoir = random.nextBoolean();

        if (utiliserPouvoir) {
            int nombreDeJoueurs = getPlateau().getNbJoueurs();

            // Choisir un joueur au hasard (autre que soi-même)
            int choixJoueur;
            do {
                choixJoueur = random.nextInt(nombreDeJoueurs);
            } while (choixJoueur == this.getIndiceJoueur()); // Supposons que getIndiceJoueur() renvoie l'indice du joueur actuel

            Joueur joueurCible = getPlateau().getJoueur(choixJoueur);
            ArrayList<Quartier> citeJoueur = joueurCible.getCite();

            if (!citeJoueur.isEmpty()) {
                // Choisir un quartier au hasard dans la cité du joueur ciblé
                int choixQuartier = random.nextInt(joueurCible.nbQuartiersDansCite());

                Quartier quartierCible = citeJoueur.get(choixQuartier);
                joueurCible.retirerQuartierDansCite(quartierCible.getNom());

                System.out.println("L'avatar a détruit le quartier " + quartierCible.getNom() + " appartenant à " + joueurCible.getNom());
            } else {
                System.out.println("Le joueur ciblé n'a pas de quartiers à détruire.");
            }
        } else {
            System.out.println("L'avatar a choisi de ne pas utiliser son pouvoir de destruction.");
        }
    }

}

