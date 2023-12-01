package modele;

public class PlateauDeJeu {
    private Joueur[] listeJoueurs;
    private Personnage[] listePersonnages;
    private Pioche pioche;
    private int nbPersonnages;
    private int nbJoueurs;

    public PlateauDeJeu()
    {
        this.listeJoueurs = new Joueur[9];
        this.listePersonnages = new Personnage[9];
        this.nbPersonnages = this.nbJoueurs = 0;
        pioche = new Pioche();
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public int getNbPersonnages() {
        return nbPersonnages;
    }

    public Pioche getPioche() {
        return pioche;
    }

    public Joueur getJoueur(int i) {
        Joueur joueurRetourne = null;
        if (i >= 0 && i <= (getNbJoueurs()-1)) {joueurRetourne = listeJoueurs[i];}
        return joueurRetourne;
    }

    public Personnage getPersonnage(int i) {
        Personnage personnageRetourne = null;
        if (i >= 0 && i <= (getNbPersonnages()-1)) {personnageRetourne = listePersonnages[i];}
        return personnageRetourne;
    }

    public void ajouterPersonnage(Personnage nouveau)
    {
        // si le personnage passé en paramètre n'est pas null et que le dernier index n'est pas rempli
        if(getPersonnage(getNbPersonnages()-1) == null && (nouveau != null))
        {
            // on ajoute le personnage au premier membre du tableau pas vide
            listePersonnages[getNbPersonnages()]=nouveau;
            // association du plateau au personnage ?
            nouveau.setPlateau(PlateauDeJeu.this);
            nbPersonnages+=1;
        }
    }

    public void ajouterJoueur(Joueur nouveau)
    {
        // si le joueur passé en paramètre n'est pas null et que le dernier index n'est pas rempli
        if(getJoueur(getNbJoueurs()-1) == null && (nouveau != null))
        {
            // on ajoute le joueur au premier membre du tableau pas vide
            listeJoueurs[getNbJoueurs()]=nouveau;
            nbJoueurs+=1;
        }
    }


}
