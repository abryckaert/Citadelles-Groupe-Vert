package modele;

public class Quartier {

    private String nom;
    private String type;
    private int coutConstruction;
    private String caracteristiques;

    public static final String[] TYPE_QUARTIERS = {"RELIGIEUX", "MILITAIRE", "NOBLE", "COMMERCANT", "MERVEILLE"};

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        for (String type_quartier:
             TYPE_QUARTIERS) {
            if (type.equals(type_quartier)) {
                this.type = type;
                break;
            }
            else {
                this.type = "";
            }
        }
    }

    public int getCoutConstruction() {
        return coutConstruction;
    }

    public void setCoutConstruction(int coutConstruction) {
        if (coutConstruction < 7 && coutConstruction > 0)
        {
            this.coutConstruction = coutConstruction;
        }
        else
        {
            this.coutConstruction = 0;
        }
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public Quartier() {
        setNom("");
        setType("");
        setCoutConstruction(0);
        setCaracteristiques("");
    }

    public Quartier(String nom, String type, int cout)
    {
        setNom(nom);
        setType(type);
        setCoutConstruction(cout);
        setCaracteristiques("");
    }

    public Quartier(String nom, String type, int cout, String caracteristiques)
    {
        setNom(nom);
        setType(type);
        setCoutConstruction(cout);
        setCaracteristiques(caracteristiques);
    }
}
