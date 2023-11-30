package modele;

import java.util.ArrayList;
import java.util.Random;

public class Joueur {

    private String nom;
    private int tresor;
    private ArrayList<Quartier> cite;
    private ArrayList<Quartier> mainJoueur;
    private boolean possedeCouronne;

    public Joueur(String nom)
    {
        this.nom = nom;
        tresor = 0;
        possedeCouronne = false;
        this.cite = new ArrayList<Quartier>();
        this.mainJoueur = new ArrayList<Quartier>();
    }

    public String getNom() {
        return nom;
    }

    public int getTresor() {
        return tresor;
    }

    public ArrayList<Quartier> getCite() {
        return cite;
    }

    public ArrayList<Quartier> getMainJoueur() {
        return mainJoueur;
    }

    public boolean getPossedeCouronne() {
        return possedeCouronne;
    }

    public void setPossedeCouronne(boolean possedeCouronne) {
        this.possedeCouronne = possedeCouronne;
    }

    public void ajouterPieces(int pieces)
    {
        if (pieces > 0)
        {
            this.tresor = tresor + pieces;
        }
        else
        {
            System.out.println("Erreur : Le nombre de pièces à ajouter est négatif");
        }
    }

    public void retirerPieces(int pieces)
    {
        if (pieces > 0 && getTresor() > pieces)
        {
            this.tresor = tresor - pieces;
        }
        else
        {
            System.out.println("Erreur : Le nombre de pièces à retirer est négatif, ou vous voulez retirer trop de pièces");
        }
    }

    public int nbQuartiersDansCite()
    {
        return cite.size();
    }

    public int nbQuartiersDansMain()
    {
        return mainJoueur.size();
    }

    public void ajouterQuartierDansCite(Quartier q)
    {
        cite.add(nbQuartiersDansCite(),q);
    }

    public boolean quartierPresentDansCite(String nomQuartier)
    {
        boolean quartierPresent = false;
        // on itère sur l'Arraylist
        for (int i = 0; i < nbQuartiersDansCite(); i++) {
            // si le nom du quartier est le même que celui du quartier sur lequel on itère
            if (nomQuartier.equals(cite.get(i).getNom()))
            {
                // on dit que le quartier existe et on sort de la condition
                quartierPresent = true;
            }
        }
        return quartierPresent;
    }

    public void retirerQuartierDansCite(String nomQuartier)
    {
        // si le quartier est présent dans la cité, alors
        if (quartierPresentDansCite(nomQuartier))
        {
            // on itère sur les quartiers présents sur la cité
            for (int i = 0; i < nbQuartiersDansCite(); i++) {
                // si le nom du quartier i de la cité est le même que celui indiqué
                if (cite.get(i).getNom().equals(nomQuartier))
                {
                    // on enlève le quartier de la main
                    cite.remove(i);
                }
            }

        }
        else
        {
            // on envoie un message d'erreur
            System.out.println("Le quartier n'est pas présent dans la cité");
        }
    }

    public void ajouterQuartierDansMain(Quartier quartier)
    {
        mainJoueur.add(quartier);
    }

    public Quartier retirerQuartierDansMain()
    {
        Random generateur = new Random();
        // génère un nombre au hasard entre 0 et le nombre de quartiers dans la main
        int numeroHasard = generateur.nextInt(this.nbQuartiersDansMain());
        // quartier retiré
        Quartier quartierRetire = null;
        // si on a 1 ou plus de quartiers dans notre main :
        if (nbQuartiersDansMain() > 0) {
            quartierRetire = mainJoueur.get(numeroHasard);
            // c'est le quartier à cet index qu'on retire
            mainJoueur.remove(numeroHasard);
        } else if (nbQuartiersDansMain() == 0) {
            // traiter le cas où la méthode renvoie null
            quartierRetire = null;
        }
        return quartierRetire;
    }

    public void reinitialiser()
    {
        // retire autant de pièces que ce que le joueur en a
        retirerPieces(getTresor());
        // vide l'arraylist mainJoueur
        mainJoueur.clear();
        // vide l'arraylist cité
        cite.clear();
    }


}
