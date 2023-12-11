import application.Configuration;
import modele.Assassin;
import modele.Personnage;
import modele.PlateauDeJeu;
import test.*;

import javax.xml.xpath.XPathFactoryConfigurationException;

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

        TestMagicienne testAssassin = new TestMagicienne();
        testAssassin.test1();
        testAssassin.test2();
         */

        String[][] names = {

                {"Temple", "REGILIGEUX", ""},

                {"Église", "REGILIGEUX", ""},

                {"Monastère", "REGILIGEUX", ""},

                {"Cathédrale", "REGILIGEUX", ""},

                {"Tour de guet", "MILITAIRE", ""},

                {"Prison", "MILITAIRE", ""},

                {"Caserne", "MILITAIRE", ""},

                {"Forteresse", "MILITAIRE", ""},

                {"Manoir", "NOBLE", ""},

                {"Château", "NOBLE", ""},

                {"Palais", "NOBLE", ""},

                {"Taverne", "COMMERCANT", ""},

                {"Échoppe", "COMMERCANT", ""},

                {"Marché", "COMMERCANT", ""},

                {"Comptoir", "COMMERCANT", ""},

                {"Port", "COMMERCANT", ""},

                {"Hôtel de ville", "COMMERCANT", ""},

        };



        int[][] counts = {

                {1, 3},

                {2, 3},

                {3, 3},

                {5, 2},

                {1, 3},

                {2, 3},

                {3, 3},

                {5, 2},

                {2, 3},

                {3, 5},

                {4, 4},

                {5, 3},

                {1, 5},

                {2, 3},

                {2, 4},

                {3, 3},

                {4, 3},

                {5, 2},

        };

        Configuration.nouvellePioche();
    }
}

