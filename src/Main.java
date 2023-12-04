import modele.Assassin;
import modele.Personnage;
import modele.PlateauDeJeu;
import test.TestAssassin;
import test.TestEveque;
import test.TestMarchande;
import test.TestVoleur;

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
        TestEveque testAssassin = new TestEveque();
        testAssassin.test1();
        testAssassin.test2();
    }
}

