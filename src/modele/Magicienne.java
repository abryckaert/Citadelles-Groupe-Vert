package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class Magicienne extends modele.Personnage {
    public Magicienne() {
        super("Magicienne", 3, Caracteristiques.MAGICIENNE, 1);
    }

    @Override
    public void utiliserPouvoir() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Vous avez " + getJoueur().nbQuartiersDansMain() + " cartes");

        if (!getJoueur().getMainJoueur().isEmpty()) {
            String choixUtilisateurEchanger;
            do {
                System.out.println("Voulez-vous échanger TOUTES vos cartes avec celles d'un autre joueur ? (o/n)");
                choixUtilisateurEchanger = scanner.nextLine();
            } while (!choixUtilisateurEchanger.equals("o") && !choixUtilisateurEchanger.equals("n"));

            if (choixUtilisateurEchanger.equals("o")) {
                // Afficher tous les joueurs présents avec le nombre de cartes dans leur main
                for (int i = 0; i < getPlateau().getNbJoueurs(); i++) {
                    Joueur joueur = getPlateau().getJoueur(i);
                    if (!joueur.getNom().equals(getJoueur().getNom())) {
                        System.out.println((i + 1) + " " + joueur.getNom() + ": " + joueur.getMainJoueur().size() + " cartes");
                    }
                }

                // Demander à l'utilisateur de choisir un joueur (vérifier que la magicienne ne peut pas se choisir)
                int choixUtilisateur;
                do {
                    System.out.println("Veuillez choisir un joueur (entre 1 et " + (getPlateau().getNbJoueurs() - 1) + ") : ");
                    choixUtilisateur = scanner.nextInt();
                } while (choixUtilisateur < 1 || choixUtilisateur > (getPlateau().getNbJoueurs() - 1) || getPlateau().getJoueur(choixUtilisateur - 1).getNom().equals(getJoueur().getNom()));

                // Verification du nom
                System.out.println("Voulez-vous échanger toutes vos cartes avec celles de " + getPlateau().getJoueur(choixUtilisateur - 1).getNom() + " ? o/n");
                if(!getPlateau().getJoueur(choixUtilisateur - 1).getPersonnage().getNom().equals("Eveque")) {

                    char reponse = scanner.next().charAt(0);

                    if (reponse == 'o') {
                        // Faire une copie de la main de la magicienne et du joueur choisi
                        ArrayList<Quartier> copieMainMagicienne = new ArrayList<>(getJoueur().getMainJoueur());
                        ArrayList<Quartier> copieMainJoueurChoisi = new ArrayList<>(getPlateau().getJoueur(choixUtilisateur - 1).getMainJoueur());

                        // Vider la main (originale) de la magicienne et celle du joueur choisi
                        getJoueur().getMainJoueur().clear();
                        getPlateau().getJoueur(choixUtilisateur - 1).getMainJoueur().clear();

                        // Ajouter le contenu des copies dans les mains (originales)
                        getJoueur().getMainJoueur().addAll(copieMainJoueurChoisi);
                        getPlateau().getJoueur(choixUtilisateur - 1).getMainJoueur().addAll(copieMainMagicienne);
                    } else {
                        //Ne rien faire
                    }
                }
                else {
                    System.out.println("Vous ne pouvez pas attaquer l'Eveque");
                }
            } else {
                String choixUtilisateurEchangerTout;
                do {
                    System.out.println("Voulez-vous échanger toute vos cartes avec la pioche ? (o/n)");
                    choixUtilisateurEchangerTout = scanner.nextLine().toLowerCase(); // Convertir la réponse en minuscules
                } while (!choixUtilisateurEchangerTout.equals("o") && !choixUtilisateurEchangerTout.equals("n"));

                if (choixUtilisateurEchangerTout.equals("o")) {
                    ArrayList<Quartier> copieMainMagicienne = new ArrayList<>(getJoueur().getMainJoueur());
                    System.out.println("Vous ajoutez a la pioche toute vos cartes");

                    // Retirer un à un les quartiers de la main (originale) et les ajouter dans la pioche
                    for (Quartier quartier : getJoueur().getMainJoueur()) {
                        getPlateau().getPioche().ajouter(quartier);
                    }

                    // Vider la main (originale) de la magicienne
                    getJoueur().getMainJoueur().clear();
                    System.out.println("Vous piochez " + copieMainMagicienne.size() +" nouvelle cartes**");
                    // Ajouter autant de cartes de la pioche dans la main
                    for (int i = 0; i < copieMainMagicienne.size(); i++) {
                        Quartier nouvelleCarte = getPlateau().getPioche().piocher();
                        getJoueur().ajouterQuartierDansMain(nouvelleCarte);
                    }

                } else {
                    String choixUtilisateurEchangerCertaine;
                    do {
                        System.out.println("Voulez-vous échanger certaine de vos carte avec la pioche ? (o/n)");
                        choixUtilisateurEchangerCertaine = scanner.nextLine().toLowerCase(); // Convertir la réponse en minuscules
                    } while (!choixUtilisateurEchangerCertaine.equals("o") && !choixUtilisateurEchangerCertaine.equals("n"));
                    if (choixUtilisateurEchangerCertaine.equals("o")) {
                        ArrayList<Quartier> copieMainMagicienne = new ArrayList<>(getJoueur().getMainJoueur());

                        // Afficher toutes les cartes de la copie
                        System.out.println("Voici les cartes de votre main : ");
                        for (int i = 0; i < copieMainMagicienne.size(); i++) {
                            System.out.println((i + 1) + " " + copieMainMagicienne.get(i).getNom() + " type: " + copieMainMagicienne.get(i).getType() + " - " + " pièces: " + copieMainMagicienne.get(i).getCoutConstruction());
                        }

                        int nbFois;
                        do {
                            System.out.println("Combien de fois voulez-vous échanger des cartes avec la pioche ?");
                            // Lire la réponse de l'utilisateur
                            try {
                                nbFois = Integer.parseInt(scanner.nextLine());
                                // Vérifier si la réponse est valide
                                if (nbFois >= 0) {
                                    break; // Sortir de la boucle si la réponse est valide
                                } else {
                                    System.out.println("Veuillez entrer un nombre entier positif.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Veuillez entrer un nombre entier valide.");
                            }
                        } while (true);

// Faire une copie de la main de la magicienne

// Afficher toutes les cartes de la copie
                        System.out.println("Voici les cartes de la copie :");
                        for (Quartier quartier : copieMainMagicienne) {
                            System.out.println(quartier.getNom() + " - " + quartier.getType() + " - " + quartier.getCoutConstruction());
                        }

// Faire nbFois :
                        for (int i = 0; i < nbFois; i++) {
                            // Demander à l'utilisateur de choisir une carte
                            int choixCarte;
                            do {
                                System.out.println("Veuillez choisir une carte (entre 1 et " + copieMainMagicienne.size() + ") : ");

                                // Lire la réponse de l'utilisateur
                                try {
                                    choixCarte = Integer.parseInt(scanner.nextLine());

                                    // Vérifier si la réponse est valide
                                    if (choixCarte >= 1 && choixCarte <= copieMainMagicienne.size()) {
                                        // Sortir de la boucle si la réponse est valide
                                        break;
                                    } else {
                                        System.out.println("Veuillez entrer un nombre entre 1 et " + copieMainMagicienne.size() + ".");
                                    }
                                } catch (NumberFormatException e) {
                                    System.out.println("Veuillez entrer un nombre valide.");
                                }

                                // Consommer la ligne suivante pour éviter une boucle infinie
                                scanner.nextLine();
                            } while (true);
                            // Supprimer la carte de la copie et l'ajouter dans la pioche
                            Quartier carteEchangee = copieMainMagicienne.remove(choixCarte - 1);
                            getPlateau().getPioche().ajouter(carteEchangee);

                            // Ajouter une nouvelle carte de la pioche dans la copie
                            Quartier nouvelleCarte = getPlateau().getPioche().piocher();
                            copieMainMagicienne.add(nouvelleCarte);
                        }

// Vider la main (originale) de la magicienne
                        getJoueur().getMainJoueur().clear();

// Ajouter toutes les cartes de la copie dans la main (originale)
                        getJoueur().getMainJoueur().addAll(copieMainMagicienne);


                    } else {
                        //ne rien faire
                    }
                }
            }
        } else {
            //Ne rien faire
        }
    }
}
