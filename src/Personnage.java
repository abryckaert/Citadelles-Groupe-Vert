import modele.Joueur;
import modele.Quartier;

public class Personnage {
    private String nom;
    private int rang;
    private String caracteristiques;
    private int nbPermisDeConstruire;
    private Joueur joueur;
    private boolean estAssassine;
    private boolean estVole;
    private PlateauDeJeu PlateauDeJeu;

    public Personnage(nom: String, rang: entier, caracteristiques: String){}
    public void ajouterPieces(){}
    public void ajouterQuartierDansMain(Quartier quartier){}
    public void construire(Quartier quartier){}
    public void percevoirRessourcesSpecifiques(){}
    public void utiliserPouvoir(){}
    public void setPlateau(PlateauDeJeu plateauDeJeu){}
    public PlateauDeJeu getPlateau(){}
    public void setJoueur(8){
        this.joueur.monPersonnage = this;
    }

    public void reinitialiser(){
        this.joueur.monPersonnage = null;
    }
}
