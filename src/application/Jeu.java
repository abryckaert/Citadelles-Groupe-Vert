package application;

import controleur.Interaction;
import modele.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
    private int numeroConfiguration;
    private Random generateur;
    Configuration config = new Configuration();
    Pioche pioche = Configuration.nouvellePioche();
    PlateauDeJeu plateauDeJeu = config.configurationDeBase(pioche, 4, 8);

    public Jeu() {
        // Initialisation des attributs
        this.numeroConfiguration = 0;
        this.generateur = new Random();
        jouer();
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;

        while (!quitter) {
            System.out.println("Veuillez choisir une option :");
            System.out.println("1. Jouer une partie");
            System.out.println("2. Afficher les règles");
            System.out.println("3. Quitter");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    jouerPartie();
                    break;
                case "2":
                    afficherLesRegles();
                    break;
                case "3":
                    quitter = true;
                    break;
                default:
                    System.out.println("Choix non reconnu, veuillez réessayer.");
                    break;
            }
        }

        System.out.println("Merci d'avoir joué à Citadelles !");
        scanner.close();
    }


    // Méthodes privées
    private void afficherLesRegles(){
        System.out.println("Citadelles est un jeu de plateau créé en 2000 par Bruno Faidutti"
                + "aux éditions Edge Entertainment. La présentation du jeu d´ecrite ici correspond à"
                + "la quatrième édition. Vous trouverez sur Moodle le livret complet du jeu en version pdf."
                + "Deux à huit joueurs s’affrontent pour construire le plus rapidement possible la plus prestigieuse"
                + "cité. Pour cela, chaque joueur devra construire des quartiers, ayant chacun des co^uts differents."
                + "Comme dans un jeu de rôle, chaque joueur doit se mettre dans la peau d’un personnage, à ceci"
                + "près que les joueurs changent de personnage à chaque tour de jeu. Ces personnages ont chacun des"
                + "pouvoirs particuliers : la meilleure strategie est de choisir un personnage au bon moment du jeu.");
    }

    private void jouerPartie() {
        initialisation();

        //Boucle d'organisation de chaque tour
        do{
            choixPersonnages();
            tourDeJeu();
            gestionCouronne();
            reinitialisationPersonnages();
        }
        while(partieFinie());

        System.out.println("---------PARTIE TERMINEE---------");

        calculDesPoints();

    }

    private void calculDesPoints() {
    }

    private void reinitialisationPersonnages() {
       for (int i = 0; i<plateauDeJeu.getNbPersonnages(); i++){
           plateauDeJeu.getPersonnage(i).setJoueur(null);
       }
    }

    private void initialisation() {

        for (int i = 0; i<plateauDeJeu.getNbJoueurs(); i++){
            plateauDeJeu.getJoueur(i).ajouterPieces(2);
            plateauDeJeu.getJoueur(i).ajouterQuartierDansCite(pioche.piocher());
            plateauDeJeu.getJoueur(i).ajouterQuartierDansCite(pioche.piocher());
        }

        Random random = new Random();
        int nombreAleatoire = random.nextInt(plateauDeJeu.getNbJoueurs());
        plateauDeJeu.getJoueur(nombreAleatoire).setPossedeCouronne(true);
    }

    private void choixPersonnages() {
        Random random1 = new Random();
        Random random2 = new Random();
        Random random3 = new Random();
        // génère les rangs des cartes mises à l'écart, entre 0 inclus et 8 exclus
        int carteFaceVisible1 = random1.nextInt(8);
        int carteFaceVisible2 = random2.nextInt(8);
        int carteFaceCachee = random3.nextInt(8);

        // Si les 3 nombres sont différents et que le roi n'est pas retourné face visible, alors on affiche le texte
        if (carteFaceVisible2 != carteFaceVisible1 && carteFaceCachee != carteFaceVisible2 && carteFaceCachee != carteFaceVisible1 && carteFaceVisible1 != 6 && carteFaceVisible2 != 6) {
            System.out.println("Le personnage \" " + plateauDeJeu.getPersonnage(carteFaceVisible1).getNom() + " \" a été retourné face visible !");
            System.out.println("Le personnage \" " + plateauDeJeu.getPersonnage(carteFaceVisible2).getNom() + " \" a été retourné face visible !");
            System.out.println("Un personnage a été écarté face cachée ! ");
        }
        // sinon on relance la méthode
        else
        {
            return;
        }


        // affiche qui possède la couronne
        for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
            if (plateauDeJeu.getJoueur(i).isPossedeCouronne())
            {
                System.out.println(plateauDeJeu.getJoueur(i).getNom() + " possède la couronne !");
            }
        }

        // Le joueur qui a la couronne joue en premier
        for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
            if (plateauDeJeu.getJoueur(i).isPossedeCouronne())
            {
                Joueur joueurActuel = plateauDeJeu.getJoueur(i);

                // si c'est un joueur
                if (!joueurActuel.isAvatar())
                {
                    // on lui montre les cartes disponibles
                    for (int j = 0; j < plateauDeJeu.getNbPersonnages(); j++) {
                        if (j != carteFaceCachee && j!=carteFaceVisible1 && j!=carteFaceVisible2 && plateauDeJeu.getPersonnage(j).getJoueur()==null)
                        {
                            System.out.println(j + ". " + plateauDeJeu.getPersonnage(j).getNom());
                        }
                    }
                    System.out.println("Quel personnage voulez-vous choisir ?");
                    int personnageAssigne = Interaction.lireUnEntier();
                    plateauDeJeu.getPersonnage(personnageAssigne).setJoueur(plateauDeJeu.getJoueur(i));
                }
                // si c'est un avatar
                else {
                    Random random4 = new Random();
                    // on met dans un tableau les cartes disponibles
                    ArrayList<Personnage> personnagesDisponibles = new ArrayList<>();
                    for (int j = 0; j < plateauDeJeu.getNbPersonnages(); j++) {
                        if (j != carteFaceCachee && j!=carteFaceVisible1 && j!=carteFaceVisible2 && plateauDeJeu.getPersonnage(j).getJoueur()==null)
                        {
                            personnagesDisponibles.add(plateauDeJeu.getPersonnage(j));
                        }
                    }
                    // on choisit un indice random à assigner à l'avatar
                    int personnageAssigne = random4.nextInt(personnagesDisponibles.size()-1);
                    // on assigne le personnage à l'avatar
                    plateauDeJeu.getPersonnage(personnageAssigne).setJoueur(plateauDeJeu.getJoueur(i));
                }
            }
        }

        // ensuite, les autres joueurs choisissent leurs cartes
        for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
            // si le joueur possède pas la couronne (pour ne pas que le roi ne joue 2x)
            if (!plateauDeJeu.getJoueur(i).isPossedeCouronne())
            {
                Joueur joueurActuel = plateauDeJeu.getJoueur(i);


                // si c'est un joueur
                if (!joueurActuel.isAvatar())
                {
                    // on lui montre les cartes disponibles
                    for (int j = 0; j < plateauDeJeu.getNbPersonnages(); j++) {
                        if (j != carteFaceCachee && j!=carteFaceVisible1 && j!=carteFaceVisible2 && plateauDeJeu.getPersonnage(j).getJoueur()==null)
                        {
                            System.out.println(j + ". " + plateauDeJeu.getPersonnage(j).getNom());
                        }
                    }
                    System.out.println("Quel personnage voulez-vous choisir ?");
                    int personnageAssigne = Interaction.lireUnEntier();
                    plateauDeJeu.getPersonnage(personnageAssigne).setJoueur(plateauDeJeu.getJoueur(i));
                }
                // si c'est un avatar
                else {
                    Random random4 = new Random();
                    // on met dans un tableau les cartes disponibles
                    ArrayList<Personnage> personnagesDisponibles = new ArrayList<>();
                    for (int j = 0; j < plateauDeJeu.getNbPersonnages(); j++) {
                        if (j != carteFaceCachee && j!=carteFaceVisible1 && j!=carteFaceVisible2 && plateauDeJeu.getPersonnage(j).getJoueur()==null)
                        {
                            personnagesDisponibles.add(plateauDeJeu.getPersonnage(j));
                        }
                    }
                    // on choisit un indice random à assigner à l'avatar
                    int personnageAssigne = random4.nextInt(personnagesDisponibles.size()-1);
                    // on assigne le personnage à l'avatar
                    plateauDeJeu.getPersonnage(personnageAssigne).setJoueur(plateauDeJeu.getJoueur(i));
                }

            }
        }

    }

    private void tourDeJeu () {
        for (int i = 0; i < plateauDeJeu.getNbPersonnages(); i++) {
            if (plateauDeJeu.getPersonnage(i).getJoueur() != null)
            {
                // si le personnage n'est pas assassiné
                if (!plateauDeJeu.getPersonnage(i).getEstAssassine()) {
                    // si le personnage est volé
                    if (plateauDeJeu.getPersonnage(i).getEstVole()) {
                        int piecesVole = plateauDeJeu.getPersonnage(i).getJoueur().getTresor();
                        // on ajoute les pièces au Voleur et on retire les pièces à la personne volée
                        plateauDeJeu.getPersonnage(1).getJoueur().ajouterPieces(piecesVole);
                        plateauDeJeu.getPersonnage(i).getJoueur().retirerPieces(piecesVole);
                    }

                    // Si le joueur n'est pas un bot
                    if (!plateauDeJeu.getPersonnage(i).getJoueur().isAvatar()) {
                        // on perçoit les ressources
                        percevoirRessources(plateauDeJeu.getPersonnage(i));

                        // puis les ressources spécifiques
                        plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();

                        // on utilise le pouvoir ?
                        System.out.println("Voulez-vous utiliser votre pouvoir ? o | n");
                        boolean utiliserPouvoirON = Interaction.lireOuiOuNon();
                        if (utiliserPouvoirON) {
                            plateauDeJeu.getPersonnage(i).utiliserPouvoir();
                        }

                        // on construit le quartier
                        construire(plateauDeJeu.getPersonnage(i));
                    } else {
                        //alors c'est un bot

                        // on perçoit nos ressources
                        percevoirRessourcesAvatar(plateauDeJeu.getPersonnage(i));
                        // on perçoit les ressources spécifiques
                        plateauDeJeu.getPersonnage(i).percevoirRessourcesSpecifiques();
                        // on utilise notre pouvoir
                        plateauDeJeu.getPersonnage(i).utiliserPouvoirAvatar();
                        // et on construit si possible
                        construireAvatar(plateauDeJeu.getPersonnage(i));
                    }

                }
                System.out.println("Le personnage" + plateauDeJeu.getPersonnage(i).getNom() + " du joueur " +  plateauDeJeu.getPersonnage(i).getJoueur().getNom() +  " est assassiné, il ne fait donc rien");
            }
        }
    }

    private void percevoirRessources (Personnage p){
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("Veuillez choisir une option :");
            System.out.println("1. Prendre 2 pièces");
            System.out.println("2. Prendre 2 cartes");
            while (!scanner.hasNextInt()) {
                System.out.println("Ce n'est pas un nombre valide. Essayez encore:");
                scanner.next(); // nettoyer le buffer
            }
            choix = scanner.nextInt();
        } while (choix != 1 && choix != 2);

        if (choix == 1) {
            // Code pour ajouter 2 pièces au trésor du joueur
            p.getJoueur().ajouterPieces(2);
        } else if (choix == 2) {
            // Code pour permettre au joueur de piocher 2 cartes
            Quartier q1 = plateauDeJeu.getPioche().piocher();
            Quartier q2 = plateauDeJeu.getPioche().piocher();

            int choixCarteADefausser = Interaction.lireUnEntier(1, 3);
            do {
                System.out.println("Vous avez pioché 2 cartes : " + " 1. " + q1.getNom() + " et " + " 2. " + q2.getNom());
                System.out.println("Quelle carte voulez-vous défausser ? [1 ou 2 ?]");

                while (!scanner.hasNextInt()) {
                    System.out.println("Ce n'est pas un nombre valide. Essayez encore:");
                    scanner.next(); // nettoyer le buffer
                }

                choixCarteADefausser = scanner.nextInt();
            } while (choixCarteADefausser != 1 && choixCarteADefausser != 2);

            if (choixCarteADefausser == 1) {
                // Défausser la carte 1
                plateauDeJeu.getPioche().ajouter(q1);
                p.getJoueur().ajouterQuartierDansMain(q2);
            } else {
                // Défausser la carte 2
                plateauDeJeu.getPioche().ajouter(q2);
                p.getJoueur().ajouterQuartierDansMain(q1);
            }
        }
    }

    private void percevoirRessourcesAvatar (Personnage p){
        Random random = new Random();
        int choix = random.nextInt(2); // Génère 1 ou 2

        if (choix == 1) {
            // Ajouter 2 pièces au trésor de l'Avatar
            p.getJoueur().ajouterPieces(2);
        } else if (choix == 2) {
            // L'Avatar pioche 2 cartes
            Quartier q1 = plateauDeJeu.getPioche().piocher();
            Quartier q2 = plateauDeJeu.getPioche().piocher();

            int choixCarteADefausser = random.nextInt(2) + 1; // Génère 1 ou 2 pour choisir quelle carte défausser
            if (choixCarteADefausser == 1) {
                // Défausser la carte 1 et ajouter la carte 2 à la main de l'Avatar
                plateauDeJeu.getPioche().ajouter(q1);
                p.getJoueur().ajouterQuartierDansMain(q2);
            } else {
                // Défausser la carte 2 et ajouter la carte 1 à la main de l'Avatar
                plateauDeJeu.getPioche().ajouter(q2);
                p.getJoueur().ajouterQuartierDansMain(q1);
            }
        }
    }


    private void construire(Personnage p) {

        // Demander si on construit un quartier
        System.out.println("Voulez-vous construire un quartier ?");
        boolean construireQuartierON = Interaction.lireOuiOuNon();

        if (construireQuartierON) {
            ArrayList<Quartier> quartiersJoueur = p.getJoueur().getMainJoueur();

            // Montrer les quartiers que l'on a dans notre main
            System.out.println("Votre main :");
            for (Quartier quartier : quartiersJoueur) {
                System.out.println(quartier.getNom() + " - " + quartier.getCoutConstruction() + " pièces");
            }

            //if (!quartiersConstruisibles.isEmpty()) {
                // Choisir un quartier aléatoirement parmi les construisibles
                //int indexQuartierChoisi = random.nextInt(quartiersConstruisibles.size());
                //Quartier quartierChoisi = quartiersConstruisibles.get(indexQuartierChoisi);

                // Construire le quartier choisi
                //p.construire(quartierChoisi);
            //}
        }
    }

    private void construireAvatar (Personnage p){
        Random random = new Random();
        // Décider aléatoirement si l'Avatar construit un quartier
        boolean construireQuartierON = random.nextBoolean();

        if (construireQuartierON) {
            ArrayList<Quartier> quartiersJoueur = p.getJoueur().getMainJoueur();
            ArrayList<Quartier> quartiersConstruisibles = new ArrayList<>();

            // Filtrer les quartiers que l'Avatar peut se permettre de construire
            for (Quartier quartier : quartiersJoueur) {
                if (quartier != null && quartier.getCoutConstruction() <= p.getJoueur().getTresor()) {
                    quartiersConstruisibles.add(quartier);
                }
            }

            if (!quartiersConstruisibles.isEmpty()) {
                // Choisir un quartier aléatoirement parmi les construisibles
                int indexQuartierChoisi = random.nextInt(quartiersConstruisibles.size());
                Quartier quartierChoisi = quartiersConstruisibles.get(indexQuartierChoisi);

                // Construire le quartier choisi
                p.construire(quartierChoisi);
            }
        }
    }


    private void gestionCouronne() {
        // Corps vide pour l'instant
    }

    private boolean partieFinie() {
        {
            // Check du nombre de quartier de chaque joueur

            for (int i = 0 ; i< plateauDeJeu.getNbJoueurs() ; i++){
                Joueur j = plateauDeJeu.getJoueur(i);
                if (j.nbQuartiersDansCite() >= 8){
                    return false;
                }
            }

            /*// Check du nombre de quartier dans la pioche et de la possibilité d'un personnage de finir
            if(plateauDeJeu.getPioche().nombreQuartiersDansPioche()==0){

                for(int i=0 ; i <plateauDeJeu.getNbJoueurs() ; i++){

                }

            }*/


            return true;
        }
    }
}