package modele;

import java.util.Random;

public class Eveque extends Personnage{
    public Eveque(){
        super("Eveque", 5, Caracteristiques.EVEQUE, 1);
    }
    //Cette protection devra être gérée au niveau des méthodes utiliserPouvoir() des personnages de rang 8.
    @Override
    public void utiliserPouvoir() {
        // pas de pouvoir spécifique
    }

    @Override
    public void utiliserPouvoirAvatar() {
        // idem
    }


    @Override
    public void percevoirRessourcesSpecifiques() {
        int nombreQuartierReligieux = 0;
        for (int i = 0; i < getJoueur().nbQuartiersDansCite(); i++) {
            if (getJoueur().getCite().get(i).getType().equals(Quartier.TYPE_QUARTIERS[0]))
            {
                nombreQuartierReligieux++;
                getJoueur().ajouterPieces(1);
            }
        }
        System.out.println("Vos batiments religieux vous rapporte " + nombreQuartierReligieux);
    }
}
