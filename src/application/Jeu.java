package application;

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
            reinitialisationPersonnages();
            tourDeJeu();
            gestionCouronne();


        }while(partieFinie());

        System.out.println("---------PARTIE TERMINEE---------");

        calculDesPoints();

    }

    private void reinitialisationPersonnages() {
        for(int i=0 ; i< plateauDeJeu.getNbPersonnages() ; i++){
            plateauDeJeu.getPersonnage(i).reintitialiser();
        }
    }

    private void gestionCouronne() {

    }

    private void initialisation() {

    }

    private void choixPersonnages() {


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
                        nombre_de_tour=0;
                        return false;
                    }
                }

                //Check du nombre de quartier dans la pioche et de la possibilité d'un personnage de finir
                if(plateauDeJeu.getPioche().nombreQuartiersDansPioche()==0){

                    for(int i=0 ; i <plateauDeJeu.getNbJoueurs() ; i++){

                    }

                }


                return true;    }

    private void calculDesPoints() {
        // Corps vide pour l'instant
    }
}
