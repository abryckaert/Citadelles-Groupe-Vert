import modele.Assassin;
import modele.Personnage;
import modele.PlateauDeJeu;
import test.*;

public class Main {
    public static void main(String[] args) {
        /*
        PlateauDeJeu plateauDeJeu = new PlateauDeJeu();
        Personnage personnage = new Personnage("Anthony", 1, "a", 2);
        Personnage personnage1 = new Personnage("Lucile", 1, "a", 2);
        Assassin assassin = new Assassin();
        plateauDeJeu.ajouterPersonnage(personnage);
        plateauDeJeu.ajouterPersonnage(personnage1);
        plateauDeJeu.ajouterPersonnage(assassin);
        assassin.utiliserPouvoir();

         */
        TestArchitecte testAssassin = new TestArchitecte();
        testAssassin.test1();
        testAssassin.test2();
    }
}

