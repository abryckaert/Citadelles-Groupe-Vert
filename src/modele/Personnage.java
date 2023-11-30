package modele;

public class Personnage {
    private String nom;
    private int rang;
    private String caracteristiques;
    private int nbPermisDeConstruire;
    private Joueur joueur;
    private boolean estAssassine;
    private boolean estVole;

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

    public void setEstVole() {
        this.estVole = true;
    }

    public void ajouterPieces()
    {
        if (joueur != null && !getEstAssassine())
        {
            joueur.ajouterPieces(2);
        }
    }

    public void ajouterQuartierDansMain(Quartier nouveau)
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
        }
    }

    public void percevoirRessourcesSpecifiques()
    {
        if (joueur != null && !getEstAssassine())
        {
            System.out.println("Aucune ressource spécifique");
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
        joueur = null;
        estAssassine = false;
        estVole = false;
    }

}
