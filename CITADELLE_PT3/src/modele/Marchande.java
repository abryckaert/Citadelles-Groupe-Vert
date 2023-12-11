package modele;

public class Marchande extends Personnage {

    public Marchande(){
        super("Marchande", 6, Caracteristiques.MARCHANDE, 1);
    }
    //Cette protection devra être gérée au niveau des méthodes utiliserPouvoir() des personnages de rang 8.
    @Override
    public void utiliserPouvoir() {
        getJoueur().ajouterPieces(1);
    }
    @Override
    public  void percevoirRessourcesSpecifiques() {
        int nombreQuartierCommercant = 0;
        for (int i = 0; i < getJoueur().nbQuartiersDansCite(); i++) {
            if (getJoueur().getCite().get(i).getType().equals(Quartier.TYPE_QUARTIERS[3]))
            {
                nombreQuartierCommercant++;
                getJoueur().ajouterPieces(1);
            }
        }
        System.out.println("Vos batiments religieux vous rapporte " + nombreQuartierCommercant);
    }
    public abstract UtiliserPouvoirAvatar();

}
}
