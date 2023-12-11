package test;

import modele.Caracteristiques;
import modele.Architecte;
import modele.Joueur;
import modele.Pioche;
import modele.PlateauDeJeu;
import modele.Quartier;

public class TestArchitecte {
    public static void main(String[] args) {
        TestArchitecte test = new TestArchitecte();
        test.test1();
        test.test2();
    }

    public void test1() {
        System.out.println("TEST DU CONSTRUCTEUR");
        Architecte architecte = new Architecte();
        Test.test(architecte.getNom().equals("Architecte"), "test du nom du personnage");
        Test.test(architecte.getRang() == 7, "test du rang du personnage");
        Test.test(architecte.getCaracteristiques().equals(Caracteristiques.ARCHITECTE), "test des caractéristiques du personnage");
        Test.test(architecte.getNbPermisDeConstruire() == 3, "test du nombre de permis de construire");
        Test.test(architecte.getJoueur() == null, "test de l'initialisation de la variable \"joueur\"");
        Test.test(architecte.getEstAssassine() == false, "test de l'initialisation de la variable \"assassine\"");
        Test.test(architecte.getEstVole() == false, "test de l'initialisation de la variable \"vole\"");
    }

    public void test2() {
        System.out.println("TEST DE LA PERCEPTION DES RESSOURCES SPECIFIQUES");
        // on crée un plateau et on ajoute des cartes Quartier à la pioche :
        PlateauDeJeu plateau = new PlateauDeJeu();
        Quartier quartier1 = new Quartier("temple", Quartier.TYPE_QUARTIERS[0], 1);
        Quartier quartier2 = new Quartier("prison", Quartier.TYPE_QUARTIERS[1], 2);
        Quartier quartier3 = new Quartier("palais", Quartier.TYPE_QUARTIERS[2], 5);
        Pioche pioche = plateau.getPioche();
        pioche.ajouter(quartier1);
        pioche.ajouter(quartier2);
        pioche.ajouter(quartier3);

        // on ajoute le personnage au plateau :
        Architecte architecte = new Architecte();
        plateau.ajouterPersonnage(architecte);

        // on ajoute le joueur au plateau :
        Joueur  joueur = new Joueur("Billy");
        plateau.ajouterJoueur(joueur);
        architecte.setJoueur(joueur);

        Test.test(architecte.getJoueur().nbQuartiersDansMain() == 0, "test du nombre de cartes dans la main avant la perception des ressources spécifiques");
        architecte.percevoirRessourcesSpecifiques();
        Test.test(architecte.getJoueur().nbQuartiersDansMain() == 2, "test du nombre de cartes dans la main après la perception des ressources spécifiques");
    }
}
