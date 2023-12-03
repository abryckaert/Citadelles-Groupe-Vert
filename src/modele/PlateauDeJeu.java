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

    public PlateauDeJeu getPlateau(){
        return this;
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

    public void ajouterPersonnage(Personnage nouveau) {
        // Vérifier si le personnage passé en paramètre n'est pas null
        if (nouveau != null) {
            // Si l'index actuel est inférieur à la taille du tableau
            if (getNbPersonnages() < listePersonnages.length) {
                // Ajouter le personnage à l'index actuel
                listePersonnages[getNbPersonnages()] = nouveau;
                // Associer le plateau au personnage
                nouveau.setPlateau(PlateauDeJeu.this);
                // Incrémenter le nombre de personnages
                nbPersonnages++;
            } else {
                // Le tableau est plein, vous pouvez gérer cela selon vos besoins (par exemple, redimensionner le tableau)
                System.out.println("Le tableau de personnages est plein.");
            }
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
