package modele;

public class Roi extends Personnage {
    public Roi()
    {
        super("Roi",4,Caracteristiques.ROI,1);
    }

    @Override
    public void percevoirRessourcesSpecifiques() {
        int nbQuartierNoble = 0;
        if (getJoueur() != null) {
            for (int i = 0; i < getJoueur().nbQuartiersDansCite(); i++) {
                if (getJoueur().getCite().get(i).getType() == "NOBLE") {
                    nbQuartierNoble += 1;
                }
            }
            getJoueur().ajouterPieces(nbQuartierNoble);
            System.out.println(nbQuartierNoble + " pièces ont été ajoutées à la bourse de " + getJoueur().getNom());
        }
    }
}
