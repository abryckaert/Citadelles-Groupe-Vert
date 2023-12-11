package modele;

public abstract class Architecte extends modele.Personnage{
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
    public void utiliserPouvoirAvatar(){
        if(!getEstAssassine()){
            int m=0;
            while(getPlateau().getPioche().nombreQuartiersDansPioche()>0 && m<2){
                m++;
                getJoueur().getMainJoueur().add(getPlateau().getPioche().piocher());
            }
        }else if(getJoueur().equals(null)){
            System.out.println("Ce personnage n'a pas été attribué");
        }else{
            System.out.println("Vous avez été assassiné, vous ne pouvez pas utiliser votre pouvoir");
        }
    }
}

