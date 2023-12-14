package modele;

import java.util.AbstractList;
import java.util.ArrayList;

public abstract class Personnage {
    private String nom;
    private int rang;
    private String caracteristiques;
    private int nbPermisDeConstruire;
    private Joueur joueur;
    private boolean estAssassine;
    private boolean estVole;
    private PlateauDeJeu plateau;

    public Personnage(String nom, int rang, String caracteristiques, int nbPermisDeConstruire)
    {
        this.nom = nom;
        this.rang = rang;
        this.caracteristiques = caracteristiques;
        this.nbPermisDeConstruire = nbPermisDeConstruire;
        joueur = null;
        estAssassine = estVole = false;
    }

    public String getNom() {
        return nom;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public int getNbPermisDeConstruire() {
        return nbPermisDeConstruire;
    }

    public int getRang() {
        return rang;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public PlateauDeJeu getPlateau() {
        return plateau;
    }

    public void setPlateau(PlateauDeJeu plateau) {
        this.plateau = plateau;
    }

    public boolean getEstAssassine() {
        return estAssassine;
    }

    public boolean getEstVole() {
        return estVole;
    }

    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }

    public void setEstAssassine() {
        this.estAssassine = true;
    }

    public void setNonAssassine(){this.estAssassine = false;}

    public void setEstVole() {
        this.estVole = true;
    }

    public void ajouterPieces()
    {
        if (!getEstAssassine())
        {
            joueur.ajouterPieces(2);
        }
    }

    public void ajouterQuartier(Quartier nouveau)
    {
        if (joueur != null && !getEstAssassine())
        {
            joueur.ajouterQuartierDansMain(nouveau);
        }
    }

    public void construire(Quartier nouveau)
    {
        if (joueur != null && !getEstAssassine())
        {
            joueur.ajouterQuartierDansCite(nouveau);
            joueur.retirerPieces(nouveau.getCoutConstruction());
            for (int i = 0; i < joueur.nbQuartiersDansMain(); i++) {
                if (joueur.getMainJoueur().get(i) == nouveau)
                {
                    joueur.getMainJoueur().remove(i);
                }
            }
            // Envoyer un message de confirmation
            System.out.println("Le joueur " + joueur.getNom() + " a bien construit le quartier " + nouveau.getNom());
        }
    }

    public void percevoirRessourcesSpecifiques()
    {
        if (joueur != null && !getEstAssassine()) {
            {
                System.out.println("Aucune ressource spécifique");
            }
        }
    }

    public void utiliserPouvoir()
    {
        if (joueur != null && !getEstAssassine())
        {
            System.out.println("Aucun pouvoir");
        }
    }

    public void reinitialiser()
    {
       //System.out.println("Le personnage " + this.getNom() + " est reinitialisé !");
        this.estAssassine = false;
        this.estVole = false;
    }

    public abstract void utiliserPouvoirAvatar();
    public ArrayList<Personnage> getAllPersonnage() {
        ArrayList<Personnage> tousLesPersonnnages = new ArrayList<Personnage>();
        
        for (int i = 0; i < plateau.getNbPersonnages(); i++) {
            tousLesPersonnnages.add(plateau.getPersonnage(i));
        }
        return tousLesPersonnnages;
    }

    public void setNonVole() {
        estVole = false;
    }
}


