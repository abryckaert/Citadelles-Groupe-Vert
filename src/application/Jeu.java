package application;

import controleur.Interaction;
import modele.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Jeu {
    private PlateauDeJeu plateauDeJeu;
    private int numeroConfiguration;
    private Random generateur;

    public Jeu() {
        // Initialisation des attributs
        this.plateauDeJeu = new PlateauDeJeu();
        this.numeroConfiguration = 0;
        this.generateur = new Random();
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);
        boolean quitter = false;

        afficherLesRegles();

        while (!quitter) {
            afficherLesRegles();
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
        for(int i=0 ; i< plateauDeJeu.getNbPersonnages() ; i++){
            plateauDeJeu.getPersonnage(i).reintitialiser();
        }
    }

    private void initialisation() {
        Configuration config = new Configuration();
        Pioche pioche = Configuration.nouvellePioche();
        PlateauDeJeu plateauDeJeu = config.configurationDeBase(pioche, 4, 4);

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
        // génère les rangs des cartes mises à l'écart, entre 1 et 8 inclus
        int carteFaceVisible1 = random1.nextInt(8)+1;
        int carteFaceVisible2 = random2.nextInt(8)+1;
        int carteFaceCachee = random3.nextInt(8)+1;
        int nombreAllouerCartes = plateauDeJeu.getNbJoueurs();

        // Si les 3 nombres sont différents et que le roi n'est pas retourné face visible, alors on affiche le texte
        if (carteFaceCachee != carteFaceVisible2 && carteFaceCachee != carteFaceVisible1 && carteFaceVisible1 != 4 && carteFaceVisible2 != 4) {
            System.out.println("Le personnage \" " + plateauDeJeu.getPersonnage(carteFaceVisible1) + " a été retourné face visible !");
            System.out.println("Le personnage \" " + plateauDeJeu.getPersonnage(carteFaceVisible2) + " a été retourné face visible !");
            System.out.println("Un personnage a été écarté face cachée ! ");
        }
        // sinon on relance la méthode
        else
        {
            choixPersonnages();
        }

        // affiche qui possède la couronne
        for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
            if (plateauDeJeu.getJoueur(i).isPossedeCouronne())
            {
                System.out.println(plateauDeJeu.getJoueur(i).getNom() + " possède la couronne !");
            }
        }

        while(nombreAllouerCartes > 0)
        {
            if (nombreAllouerCartes == plateauDeJeu.getNbJoueurs())
            {
                for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
                    if (plateauDeJeu.getJoueur(i).isPossedeCouronne())
                    {
                        // on lui montre les cartes disponibles
                        for (int j = 0; j < plateauDeJeu.getNbPersonnages(); j++) {
                            if (j != carteFaceCachee && j!=carteFaceVisible1 && j!=carteFaceVisible2)
                            {
                                System.out.println(j + ". " + plateauDeJeu.getPersonnage(j));
                            }
                        }
                        System.out.println("Quel personnage voulez-vous choisir ?");
                        int personnageAssigne = Interaction.lireUnEntier();
                        plateauDeJeu.getPersonnage(personnageAssigne).setJoueur(plateauDeJeu.getJoueur(i));
                    }
                }
            }
            else {
                for (int i = 0; i < plateauDeJeu.getNbJoueurs(); i++) {
                    if (!plateauDeJeu.getJoueur(i).isPossedeCouronne())
                    {
                        // on montre les cartes dispo au joueur
                        for (int j = 0; j < plateauDeJeu.getNbPersonnages(); j++)
                        {
                            // on vérifie que la carte n'a pas été mise au tapis ni distribuée
                            if (j != carteFaceCachee && j!=carteFaceVisible1 && j!=carteFaceVisible2 && plateauDeJeu.getPersonnage(j).getJoueur() == null)
                            {
                                System.out.println(j + ". " + plateauDeJeu.getPersonnage(j));
                            }
                        }
                        System.out.println("Quel personnage voulez-vous choisir ?");
                        int personnageAssigne = Interaction.lireUnEntier();
                        plateauDeJeu.getPersonnage(personnageAssigne).setJoueur(plateauDeJeu.getJoueur(i));
                    }
                }

            }
            // une carte de moins à allouer !
            nombreAllouerCartes--;
        }
    }

    private void tourDeJeu() {

    }

    private void percevoirRessources(Personnage p) {
        // Corps vide pour l'instant
    }

    private void percevoirRessourcesAvatar(Personnage p) {
        // Corps vide pour l'instant
    }

    private void construire(Personnage p) {
        // Corps vide pour l'instant
    }

    private void construireAvatar(Personnage p) {
        // Corps vide pour l'instant
    }

    private void gestionCouronne() {
        // Corps vide pour l'instant
    }

    private boolean partieFinie() {
            {
                //Check du nombre de quartier de chaque personnage
                for(int i = 0 ; i< plateauDeJeu.getNbJoueurs() ; i++){
                    Joueur j = plateauDeJeu.getJoueur(i);
                    if(j.nbQuartiersDansCite() >= 8){
                        int nombre_de_tour=0;
                        return false;
                    }
                }

                //Check du nombre de quartier dans la pioche et de la possibilité d'un personnage de finir
                if(plateauDeJeu.getPioche().nombreQuartiersDansPioche()==0){

                    for(int i=0 ; i <plateauDeJeu.getNbJoueurs() ; i++){

                    }

                }


                return true;
            }
    }
}
