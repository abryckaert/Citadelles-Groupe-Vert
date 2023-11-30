package modele;

import java.util.ArrayList;

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
        cite.add(nbQuartiersDansCite()+1,q);
    }

    public boolean quartierPresentDansCite(String nomQuartier)
    {
        boolean quartierPresent = false;
        // on itère sur l'Arraylist
        for (int i = 0; i < nbQuartiersDansCite(); i++) {
            // si le nom du quartier est le même que celui du quartier sur lequel on itère
            if (nomQuartier.equals(cite.get(i).toString()))
            {
                // on dit que le quartier existe et on sort de la condition
                quartierPresent = true;
                break;
            }
            else
            {
                quartierPresent = false;
            }
        }
        return quartierPresent;
    }

    public void retirerQuartierDansCite(String q)
    {
        if (quartierPresentDansCite(q))
        {
            // on cherche à quel id ça correspond

            // puis on retire le quartier
        }
        else
        {
            // on envoie un message d'erreur
        }
    }


}
