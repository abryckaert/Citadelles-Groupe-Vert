package application;

import modele.*;

import java.util.Random;
import java.util.Scanner;


public class Configuration {

    public static String nom="";
    public static String[] noms = {"Marc" , "Henri" , "Kenza" , "Fara" , "Tintin" , "Milou", "George" , "Jules" , "Pierre"};
    private Random generateur = new Random();
    private Assassin Assassin = new Assassin();
    private Voleur Voleur = new Voleur();
    private Magicienne Magicienne = new Magicienne();
    private Eveque Eveque = new Eveque();
    private Marchande Marchande = new Marchande();
    private Architecte Architecte = new Architecte();
    private Roi Roi = new Roi();
    private Condottiere Condottiere = new Condottiere();
    public static Pioche nouvellePioche() {
        Pioche P1 = new Pioche();

        //Ajout des quartiers religieux
        //Ajout des temples
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Temple", "RELIGIEUX", 1));
        }

        //Ajout des �glises
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Eglise", "RELIGIEUX", 2));
        }

        //Ajout des monast�res
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Monastere", "RELIGIEUX", 3));
        }

        //Ajout des cath�drales
        for (int i = 0; i < 2; i++) {
            P1.ajouter(new Quartier("Cathedrale", "RELIGIEUX", 5));
        }

        //Ajout des quartiers Militaires
        //Ajout des tours de guet
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Tour de guet", "MILITAIRE", 1));
        }

        //Ajout des prisons
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Prison", "MILITAIRE", 2));
        }

        //Ajout des casernes
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Caserne", "MILITAIRE", 3));
        }

        //Ajout des forteresses
        for (int i = 0; i < 2; i++) {
            P1.ajouter(new Quartier("Forteresse", "MILITAIRE", 5));
        }

        //Ajout des quartiers Nobles
        //Ajout des manoirs
        for (int i = 0; i < 5; i++) {
            P1.ajouter(new Quartier("Manoir", "NOBLE", 3));
        }

        //Ajout des chateaux
        for (int i = 0; i < 4; i++) {
            P1.ajouter(new Quartier("Chateau", "NOBLE", 4));
        }

        //Ajout des palais
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Palais", "NOBLE", 5));
        }

        //Ajout des quartiers commerçants
        //Ajout des tavernes
        for (int i = 0; i < 5; i++) {
            P1.ajouter(new Quartier("Taverne", "COMMERCANT", 1));
        }

        //Ajout des échoppes
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Echoppe", "COMMERCANT", 2));
        }

        //Ajout des march�s
        for (int i = 0; i < 4; i++) {
            P1.ajouter(new Quartier("Marché", "COMMERCANT", 2));
        }

        //Ajout des comptoirs
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Comptoir", "COMMERCANT", 3));
        }

        //Ajout des ports
        for (int i = 0; i < 3; i++) {
            P1.ajouter(new Quartier("Port", "COMMERCANT", 4));
        }

        //Ajout des h�tels de ville
        for (int i = 0; i < 2; i++) {
            P1.ajouter(new Quartier("Hotel de ville", "COMMERCANT", 5));
        }

        return P1;
    }

    public PlateauDeJeu configurationDeBase(Pioche p, int nombreDeJoueur, int nombrePersonnage){

        // ajout des merveilles dans la pioche
        p.ajouter(new Quartier("Bibliothèque", "MERVEILLE", 6));
        p.ajouter(new Quartier("Carrière", "MERVEILLE", 5));
        p.ajouter(new Quartier("Cour des Miracles", "MERVEILLE", 2));
        p.ajouter(new Quartier("Donjon", "MERVEILLE", 3));
        p.ajouter(new Quartier("Dracoport", "MERVEILLE", 6));
        p.ajouter(new Quartier("Ecole de Magie", "MERVEILLE", 6));
        p.ajouter(new Quartier("Fontaine aux Souhaits", "MERVEILLE", 5));
        p.ajouter(new Quartier("Forge", "MERVEILLE", 5));
        p.ajouter(new Quartier("Laboratoire", "MERVEILLE", 5));
        p.ajouter(new Quartier("Manufacture", "MERVEILLE", 5));
        p.ajouter(new Quartier("Salle des Cartes", "MERVEILLE", 5));
        p.ajouter(new Quartier("Statue Equestre", "MERVEILLE", 3));
        p.ajouter(new Quartier("Trésor Impérial", "MERVEILLE", 5));
        p.ajouter(new Quartier("Tripot", "MERVEILLE", 6));

        // mélanger la pioche
        p.melanger();

        // on crée le plateau
        PlateauDeJeu Pl = new PlateauDeJeu(p);


        // ajout des personnages
        Pl.ajouterPersonnage(Assassin);
        Pl.ajouterPersonnage(Voleur);
        Pl.ajouterPersonnage(Magicienne);
        Pl.ajouterPersonnage(Roi);
        Pl.ajouterPersonnage(Eveque);
        Pl.ajouterPersonnage(Marchande);
        Pl.ajouterPersonnage(Architecte);
        Pl.ajouterPersonnage(Condottiere);

        return Pl;
    }
}