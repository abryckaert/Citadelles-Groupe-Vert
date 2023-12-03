package modele;

public class Roi extends Personnage {
    public Roi()
    {
        super("Roi",4,Caracteristiques.ROI,1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        int nbQuartierNoble = 0;
        // on enlève la couronne à tous les joueurs du plateau
        for (int i = 0; i < getPlateau().getNbPersonnages(); i++) {
            getPlateau().getJoueur(i).setPossedeCouronne(false);
        }
        // on perçoit les pièces
        if (getJoueur() != null) {
            // on itère sur tous les quartiers
            for (int i = 0; i < getJoueur().nbQuartiersDansCite(); i++) {
                // si le quartier du joueur est de type noble
                if (getJoueur().getCite().get(i).getType().equals("NOBLE")) {
                    nbQuartierNoble += 1;
                }
            }
            getJoueur().ajouterPieces(nbQuartierNoble);
            System.out.println(nbQuartierNoble + " pièces ont été ajoutées à la bourse de " + getJoueur().getNom());
        }
    }
}
