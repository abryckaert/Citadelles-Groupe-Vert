package modele;

public class Architecte extends modele.Personnage{
    public Architecte() {
        super("Architecte", 7, Caracteristiques.ARCHITECTE, 3);
    }
    @Override
    public void utiliserPouvoir() {


    }
    @Override
    public  void percevoirRessourcesSpecifiques() {
        Quartier cartePioche1;
        Quartier cartePioche2;
        cartePioche1 = getPlateau().getPioche().piocher();
        cartePioche2 = getPlateau().getPioche().piocher();
        getJoueur().ajouterQuartierDansMain(cartePioche1);
        getJoueur().ajouterQuartierDansMain(cartePioche2);

    }
}
