package test;

import modele.Assassin;
import modele.Roi;
import modele.Voleur;
import modele.Joueur;
import modele.PlateauDeJeu;

public class TestVoleur {
    public static void main(String[] args) {
        TestVoleur test = new TestVoleur();
        test.test1();
        test.test2();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        PlateauDeJeu plateau = new PlateauDeJeu();
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Voleur voleur = new Voleur();
        plateau.ajouterPersonnage(voleur);
        Test.test(plateau.getNbPersonnages() == 3, "nombre de joueurs");
        Test.test(plateau.getPersonnage(2) == voleur, "récupération du personnage du voleur");
        Test.test(plateau.getPersonnage(2).getRang() == 2, "rang du voleur");
        Test.test(plateau.getPersonnage(2).getNbPermisDeConstruire() == 1, "test du nombre de permis de construire du voleur");
    }

    public void test2() {
        System.out.println("TEST DU VOL DU ROI");
        PlateauDeJeu plateau = new PlateauDeJeu();

        // création de trois personnages
        Roi roi = new Roi();
        plateau.ajouterPersonnage(roi);
        Assassin assassin = new Assassin();
        plateau.ajouterPersonnage(assassin);
        Voleur voleur = new Voleur();
        plateau.ajouterPersonnage(voleur);

        // création de trois joueurs
        Joueur joueur1 = new Joueur("Milou");
        plateau.ajouterJoueur(joueur1);
        Joueur joueur2 = new Joueur("Billy");
        plateau.ajouterJoueur(joueur2);
        Joueur joueur3 = new Joueur("Rantanplan");
        plateau.ajouterJoueur(joueur3);

        // on associe les personnages aux joueurs
        roi.setJoueur(joueur1);
        assassin.setJoueur(joueur2);
        voleur.setJoueur(joueur3);

        // on utilise le pouvoir du voleur
        // NB : seul le roi peut être volé dans cette situation
        roi.ajouterPieces();
        voleur.utiliserPouvoir();

        // tests:
        Test.test(roi.getEstVole(), "le roi est bien volé");
    }
}
