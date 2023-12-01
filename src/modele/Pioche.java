package modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Pioche {
    private ArrayList<Quartier> listeQuartiers;

    Random generateur = new Random();

    public Pioche()
    {
        // initialisation de l'arrayList listeQuartiers.
        listeQuartiers = new ArrayList<>();
    }

    public Quartier piocher()
    {
        Quartier cartePiochee = null;
        if (nombreQuartiersDansPioche() > 0) {
            cartePiochee = listeQuartiers.get(0);
            listeQuartiers.remove(0);
            return cartePiochee;
        }
        else if (nombreQuartiersDansPioche() == 0)
        {
            cartePiochee = null;
        }
        return cartePiochee;
    }

    public void ajouter(Quartier nouveau)
    {
        listeQuartiers.addLast(nouveau);
    }

    public int nombreQuartiersDansPioche()
    {
        return listeQuartiers.size();
    }

    public void melanger()
    {
        for (int i = 0; i < nombreQuartiersDansPioche(); i++) {
            int k = generateur.nextInt(nombreQuartiersDansPioche());
            int j = generateur.nextInt(nombreQuartiersDansPioche());
            Collections.swap(listeQuartiers,j,k);
        }
    }

}
