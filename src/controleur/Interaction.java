package controleur;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Interaction {
    private static Scanner sc = new Scanner(System.in);

    public static int lireUnEntier() {
        int i = 0;
        boolean continu = true;
        do {
            try {
                i = sc.nextInt();
                continu = false;
                sc.nextLine();  // vide le scanner jusqu'à la fin de ligne
            } catch (InputMismatchException e) {
                System.out.print("Veuillez rentrer un entier : ");
                sc.nextLine();  // vide le scanner jusqu'à la fin de ligne
            }
        } while (continu);
        return i;
    }

    // renvoie un entier lu au clavier compris dans l'intervalle
    //     [borneMin, borneMax[
    public static int lireUnEntier(int borneMin, int borneMax) {
        Scanner scanner = new Scanner(System.in);
        int i;

        do {
            System.out.print("Veuillez entrer un entier entre " + borneMin + " et " + (borneMax ) + " : ");

            while (!scanner.hasNextInt()) {
                System.out.println("Erreur : Veuillez entrer un entier valide.");
                scanner.next(); // consomme l'entrée invalide
            }

            i = scanner.nextInt();

            if (i < borneMin || i >= borneMax) {
                System.out.println("Erreur : L'entier doit être dans l'intervalle [" + borneMin + ", " + (borneMax) + "[");
            }

        } while (i < borneMin || i >= borneMax);

        return i;
    }

    // lit les réponses "oui", "non", "o" ou "n" et renvoie un booléen
    public static boolean lireOuiOuNon() {
        Scanner scanner = new Scanner(System.in);
        String reponse;

        do {
            System.out.print("Veuillez répondre par 'oui'/'non' ou 'o'/'n' : ");
            reponse = scanner.nextLine().toLowerCase();

            if (reponse.equals("oui") || reponse.equals("o")) {
                return true;
            } else if (reponse.equals("non") || reponse.equals("n")) {
                return false;
            } else {
                System.out.println("Erreur : Réponse invalide. Veuillez répondre par 'oui'/'non' ou 'o'/'n'.");
            }

        } while (true);
    }

    // renvoie une chaîne de caractère lue au clavier :
    public static String lireUneChaine() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Veuillez entrer une chaîne de caractères : ");
        String chaineLue = scanner.nextLine();

        return chaineLue;
    }
}
