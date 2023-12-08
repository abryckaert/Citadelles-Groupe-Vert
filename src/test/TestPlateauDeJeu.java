package test;

import modele.Joueur;
import modele.PlateauDeJeu;
import modele.Roi;

public class TestPlateauDeJeu {

    public static void main(String[] args) {
        TestPlateauDeJeu testPlateau = new TestPlateauDeJeu();
        testPlateau.test1();
        testPlateau.test2();
        testPlateau.test3();
        testPlateau.test4();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Test.test(plateau.getNbJoueurs() == 0, "test du nombre de joueurs");
        Test.test(plateau.getNbPersonnages() == 0, "test du nombre de personnages");
        Test.test(plateau.getPioche() != null && plateau.getPioche().nombreQuartiersDansPioche() == 0, "test de l'existence de la pioche");
    }

    public void test2() {
        System.out.println("TEST DE L'AJOUT D'UN JOUEUR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Joueur joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        Test.test(plateau.getNbJoueurs() == 1, "nombre de joueurs");
        Test.test(plateau.getJoueur(0) == joueur, "récupération de ce joueur depuis le tableau");
    }

    public void test3() {
        System.out.println("TEST DE L'AJOUT D'UN PERSONNAGE");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Test.test(plateau.getNbPersonnages() == 1, "nombre de joueurs");
        Test.test(plateau.getPersonnage(0) == roi, "récupération du personnage depuis le tableau");
    }

    public void test4() {
        System.out.println("TEST DE L'ASSOCIATION DU PLATEAU AU PERSONNAGE");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Test.test(roi.getPlateau() == plateau, "association du plateau au personnage");
    }
}
