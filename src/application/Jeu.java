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
                + "aux éditions Edge Entertainment."
                + "Deux à huit joueurs s’affrontent pour construire le plus rapidement possible la plus prestigieuse"
                + "cité. Pour cela, chaque joueur devra construire des quartiers, ayant chacun des coûts differents."
                + "Comme dans un jeu de rôle, chaque joueur doit se mettre dans la peau d’un personnage, à ceci"
                + "près que les joueurs changent de personnage à chaque tour de jeu. Ces personnages ont chacun des"
                + "pouvoirs particuliers : la meilleure stratégie est de choisir un personnage au bon moment du jeu.");
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
        // COMMENT CALCULER LES POINTS ?
        // - Coût total des bâtiments construits dans la cité.
        // - Si 5 types de quartiers présents : +3 points
        // - Si premier joueur à avoir complété sa cité : +4 pts
        // - Si cité complétée après le premier joueur : +2 pts
        // - Bonus liés à certaines merveilles

        // tableau permettant de stocker les scores des joueurs
        ArrayList<Integer> scores = new ArrayList<>();
        boolean premierJoueurComplet = true;
        // pour chaque joueur
        for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
            scores.add(i,0);

            // on rajoute le coût de construction de chaque bâtiment dans la cité
            for (int j = 0; j < plateauDeJeu.getJoueur(i).getCite().size(); j++) {
                scores.set(i, scores.get(i) + plateauDeJeu.getJoueur(i).getCite().get(j).getCoutConstruction());
            }

            // on regarde si les 5 types de bâtiments sont présents
            boolean quartierNoblePresent = false;
            boolean quartierCommercantPresent = false;
            boolean quartierReligieuxPresent = false;
            boolean quartierMilitairePresent = false;
            boolean quartierMerveillePresent = false;

            for (int j = 0; j < plateauDeJeu.getJoueur(i).getCite().size(); j++) {
                switch (plateauDeJeu.getJoueur(i).getCite().get(j).getType().toLowerCase()){
                    case "noble":
                        quartierNoblePresent = true;
                        break;
                    case "commercant":
                        quartierCommercantPresent = true;
                        break;
                    case "religieux":
                        quartierReligieuxPresent = true;
                        break;
                    case "militaire":
                        quartierMilitairePresent = true;
                        break;
                    case "merveille":
                        quartierMerveillePresent = true;
                        break;
                    default:
                        break;
                }
            }

            // dans le cas où on a tous les types de quartiers, on rajoute 3 pts
            if (quartierNoblePresent && quartierCommercantPresent && quartierReligieuxPresent && quartierMilitairePresent && quartierMerveillePresent)
            {
                scores.set(i,scores.get(i) + 3);
            }

            // premier joueur à avoir complété sa cité > +4
            if (plateauDeJeu.getJoueur(i).getCite().size() >= 8 && premierJoueurComplet)
            {
                scores.set(i, scores.get(i) + 4);
                premierJoueurComplet = false;
            }
            // tous les joueurs qui ont complété leur cité > +2
            else if (plateauDeJeu.getJoueur(i).getCite().size() >= 8 && !premierJoueurComplet)
            {
                scores.set(i, scores.get(i) + 2);
            }

            /* ----- EFFETS DES MERVEILLES ----- */

            for (int j = 0; j < plateauDeJeu.getJoueur(i).getCite().size(); j++) {
                switch (plateauDeJeu.getJoueur(i).getCite().get(j).getNom().toLowerCase())
                {
                    // On cherche si le Dracoport, la Fontaine aux Souhaits, la Salle des Cartes, la Statue Équestre, le Trésor Impérial sont présents
                    case "dracoport":
                        // +2 points
                        scores.set(i, scores.get(i) + 2);
                        break;
                    case "fontaine aux souhaits":
                        // +1 point par merveille
                        for (int k = 0; k < plateauDeJeu.getJoueur(i).getCite().size(); k++) {
                            if (plateauDeJeu.getJoueur(i).getCite().get(k).getType().equalsIgnoreCase("merveille"))
                            {
                                scores.set(i, scores.get(i) + 1);
                            }
                        }
                        break;
                    case "salle des cartes":
                        // +1 point par carte dans la main
                        scores.set(i, scores.get(i)+plateauDeJeu.getJoueur(i).getMainJoueur().size());
                        break;
                    case "statue équestre":
                        // si le joueur a la couronne en fin de partie, +5 pts
                        if (plateauDeJeu.getJoueur(i).isPossedeCouronne())
                        {
                            scores.set(i, scores.get(i) + 5);
                        }
                        break;
                    case "trésor impérial":
                        // +1 pt par pièce d'or dans le trésor du joueur
                        scores.set(i, scores.get(i)+plateauDeJeu.getJoueur(i).getTresor());
                        break;
                    default:
                        // on ne fait rien car ce n'est pas la merveille recherchée
                        break;

                }
            }

            // On affiche les points qu'ont obtenu chaque joueur
            System.out.println("Le joueur " + plateauDeJeu.getJoueur(i).getNom() + " a obtenu " + scores.get(i) + " points !");
        }
    }

    private void reinitialisationPersonnages() {
       for (int i = 0; i<plateauDeJeu.getNbPersonnages(); i++){
           plateauDeJeu.getPersonnage(i).setJoueur(null);
           plateauDeJeu.getPersonnage(i).setNonAssassine();
           plateauDeJeu.getPersonnage(i).setNonVole();
       }
    }

    private void initialisation() {

        // ajouter les joueurs bot
        for(int k=1 ;k < 4 ;k++){
            Joueur j = new Joueur(Configuration.noms[k]);
            j.setAvatar();
            plateauDeJeu.ajouterJoueur(j);
        }

        // ajouter le joueur Joueur
        System.out.println("Quel est votre nom ?");
        String nom = Interaction.lireUneChaine();
        boolean isBot = false;
        Joueur J1 = new Joueur(nom, isBot);

        plateauDeJeu.ajouterJoueur(J1);

        for (int i = 0; i<plateauDeJeu.getNbJoueurs(); i++){
            plateauDeJeu.getJoueur(i).ajouterPieces(2);
            plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
            plateauDeJeu.getJoueur(i).ajouterQuartierDansMain(pioche.piocher());
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
        if (carteFaceVisible2 != carteFaceVisible1 && carteFaceCachee != carteFaceVisible2 && carteFaceCachee != carteFaceVisible1 && carteFaceVisible1 != 3 && carteFaceVisible2 != 3) {
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
                System.out.println(plateauDeJeu.getPersonnage(i).getJoueur().getNom() + " possède le personnnage " + plateauDeJeu.getPersonnage(i).getNom());
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
                else {
                    System.out.println("Le personnage " + plateauDeJeu.getPersonnage(i).getNom() + " du joueur " +  plateauDeJeu.getPersonnage(i).getJoueur().getNom() +  " est assassiné, il ne fait donc rien");
                }
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

            int choixCarteADefausser = Interaction.lireUnEntier(1, 2);
            // Regarder si le joueur possède la Merveille Bibliothèque
            boolean possedeBibliotheque = false;

            for (int i = 0; i < p.getJoueur().getCite().size(); i++) {
                if (p.getJoueur().getCite().get(i).getNom().equalsIgnoreCase("bibliothèque")) {
                    possedeBibliotheque = true;
                    break;
                }
            }

            if (!possedeBibliotheque)
            {
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
            else {
                System.out.println("Vous avez pioché 2 cartes : " + " 1. " + q1.getNom() + " et " + " 2. " + q2.getNom());
                System.out.println("Vous possédez la Bibliothèque, les deux cartes sont donc ajoutées à votre inventaire");
                p.getJoueur().ajouterQuartierDansMain(q1);
                p.getJoueur().ajouterQuartierDansMain(q2);
            }

        }

        // ---- GESTION FORGE -----

        // Regarder si le joueur possède la Merveille Forge
        boolean possedeForge = false;

        for (int i = 0; i < p.getJoueur().getCite().size(); i++) {
            if (p.getJoueur().getCite().get(i).getNom().equalsIgnoreCase("forge")) {
                possedeForge = true;
                break;
            }
        }

        // si le joueur possède la Forge, on lui propose l'effet de la merveille
        if (possedeForge)
        {
            System.out.println("Souhaitez-vous payer 2 pièces d'or en échange de 3 cartes quartier ?");
            boolean choixForge = Interaction.lireOuiOuNon();

            if (choixForge)
            {
                p.getJoueur().retirerPieces(2);
                Quartier q1 = plateauDeJeu.getPioche().piocher();
                Quartier q2 = plateauDeJeu.getPioche().piocher();
                Quartier q3 = plateauDeJeu.getPioche().piocher();
                System.out.println("Vous avez échangé deux pièces d'or et pioché les quartiers " + q1.getNom() + " ," + q2.getNom() + " et " + q3.getNom() + ".");
                p.getJoueur().ajouterQuartierDansMain(q1);
                p.getJoueur().ajouterQuartierDansMain(q2);
                p.getJoueur().ajouterQuartierDansMain(q3);
            }
            else {
                System.out.println("Vous n'avez pas récupéré de nouvelles cartes quartier.");
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

            boolean possedeBibliotheque = false;

            for (int i = 0; i < p.getJoueur().getCite().size(); i++) {
                if (p.getJoueur().getCite().get(i).getNom().equalsIgnoreCase("bibliothèque")) {
                    possedeBibliotheque = true;
                    break;
                }
            }

            if (!possedeBibliotheque)
            {
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
            else {
                plateauDeJeu.getPioche().ajouter(q1);
                plateauDeJeu.getPioche().ajouter(q2);
            }

        }

        // ---- GESTION FORGE -----

        // Regarder si l'avatar possède la Merveille Forge
        boolean possedeForge = false;

        for (int i = 0; i < p.getJoueur().getCite().size(); i++) {
            if (p.getJoueur().getCite().get(i).getNom().equalsIgnoreCase("forge")) {
                possedeForge = true;
                break;
            }
        }

        // si l'avatar possède la Forge, on lui propose l'effet de la merveille
        if (possedeForge)
        {
            boolean choixForge = generateur.nextBoolean();
            if (choixForge)
            {
                p.getJoueur().retirerPieces(2);
                Quartier q1 = plateauDeJeu.getPioche().piocher();
                Quartier q2 = plateauDeJeu.getPioche().piocher();
                Quartier q3 = plateauDeJeu.getPioche().piocher();
                System.out.println("L'avatar a pioché deux pièces d'or et pioché les quartiers " + q1.getNom() + " ," + q2.getNom() + " et " + q3.getNom() + ".");
                p.getJoueur().ajouterQuartierDansMain(q1);
                p.getJoueur().ajouterQuartierDansMain(q2);
                p.getJoueur().ajouterQuartierDansMain(q3);
            }
            else {
                System.out.println("L'avatar n'a pas récupéré de nouvelles cartes quartier.");
            }
        }
    }


    private void construire(Personnage p) {

        // Demander si on construit un quartier
        // avec la gestion des 3 possibilités de construire de l'Architecte
        for (int i = 0; i < p.getNbPermisDeConstruire(); i++) {
            System.out.println("Voulez-vous construire un quartier ?");
            boolean construireQuartierON = Interaction.lireOuiOuNon();

            if (construireQuartierON) {
                ArrayList<Quartier> quartiersJoueur = p.getJoueur().getMainJoueur();

                // Montrer les quartiers que l'on a dans notre main
                System.out.println("Votre main :");
                int indiceQuartier = 1;
                for (Quartier quartier : quartiersJoueur) {
                    System.out.println(indiceQuartier + " - " + quartier.getNom() + " - " + quartier.getCoutConstruction() + " pièces");
                    indiceQuartier++;
                }

                // Montrer cb on a de pièces
                System.out.println("Vous avez " + p.getJoueur().getTresor() + " pieces.");

                // Construire le quartier choisi
                System.out.println("Quel quartier voulez-vous construire ?");
                int quartierAConstruire = Interaction.lireUnEntier(1,p.getJoueur().nbQuartiersDansMain());
                Quartier quartierChoisi = p.getJoueur().getMainJoueur().get(quartierAConstruire-1);
                if (p.getJoueur().getTresor() >= quartierChoisi.getCoutConstruction())
                {p.construire(quartierChoisi);}
            } else {
                System.out.println("Le joueur "+ p.getJoueur().getNom() + " n'a pas construit de quartier");
                break;
            }
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
                if (p.getJoueur().getTresor() >= quartierChoisi.getCoutConstruction())
                {p.construire(quartierChoisi);}
            }
        }
    }


    private void gestionCouronne() {
        // on regarde si un joueur a le rôle de roi
        Joueur nouveauRoi = null;
        for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
            if (plateauDeJeu.getJoueur(i).getPersonnage() != null && plateauDeJeu.getJoueur(i).getPersonnage().getNom().equalsIgnoreCase("roi"))
            {
                nouveauRoi = plateauDeJeu.getJoueur(i);
            }
        }

        // s'il y a un joueur avec le rôle de roi, on lui attribue la couronne et on la retire au préalable de tous les joueurs. Sinon rien ne change.
        if (nouveauRoi != null)
        {
            for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
                plateauDeJeu.getJoueur(i).setPossedeCouronne(false);
            }
            nouveauRoi.setPossedeCouronne(true);
        }
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