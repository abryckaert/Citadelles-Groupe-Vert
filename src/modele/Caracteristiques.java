package modele;

public class Caracteristiques {
    public static String ASSASSIN=
            "L'assassin peut tuer un personnage de son choix. " +
                    "Celui-ci ne jouera pas ce tour-ci.";
    public static String ECHEVIN=
            "L'échevin place des mandats de réquisition, face cachée, " +
                    "sous 3 jetons Personnage différents. Il peut révéler le " +
                    "mandat signé pour confisquer le premier quartier bâti par " +
                    "le joueur ciblé, qui récupère le coût de construction.";
    public static String SORCIERE=
            "Après avoir pris or ou carte, la Sorcière ensorcelle un " +
                    "personnage et interrompt son tour. Elle le terminera en " +
                    "jouant à la place du personnage ensorcelé";
    public static String MAITRE_CHANTEUSE=
            "La Maître-chanteuse place des menaces, face cachée, sous " +
                    "2 jetons Personnage différents. Un personnage menacé peut " +
                    "lui payer la moitié de son or pour retirer la menace. Si " +
                    "la Maître-chanteuse révèle le vrai jeton Menace, elle " +
                    "prend tout l'or du joueur ciblé";
    public static String ESPION=
            "L'Espion choisit un type (couleur) de quartier et regarde " +
                    "la main d'un autre joueur. Pour chaque quartier du type " +
                    "indiqué, il reçoit 1 pièce d'or de ce joueur et pioche 1 " +
                    "carte Quartier.";
    public static String VOLEUR=
            "Le Voleur peut piller le trésor du personnage de son choix. " +
                    "Quand le personnage détroussé sera révélé, il donnera tout " +
                    "son or au Voleur.";
    public static String MAGICIENNE=
            "La Magicienne peut, au choix : soit échanger la totalité " +
                    "des cartes de sa main avec celle d'un autre joueur, soit " +
                    "échanger des cartes de sa main contre le même nombre de " +
                    "cartes de la pioche.";
    public static String SORCIER=
            "Le Sorcier choisit 1 carte dans la main d'un autre joueur " +
                    "et peut soit payer son coût pour la bâtir, soit l'ajouter " +
                    "à sa main. Il peut bâtir des quartiers identiques à "+
                    "d'autres quartiers de sa cité.";
    public static String VOYANTE=
            "La Voyante prend 1 carte au hasard dans la main de chaque " +
                    "joueur, puis donne à chacun 1 carte de son choix. Son " +
                    "permis de construire est de 2 quartiers.";
    public static String EMPEREUR=
            "L'Empereur donne la Couronne à un autre joueur, qui lui " +
                    "paie 1 pièce d'or ou 1 carte. Chaque quartier Noble dans " +
                    "sa cité lui rapporte 1 pièce d'or.";
    public static String PATRICIEN=
            "Le Patricien prend la Couronne et choisira en premier " +
                    "en premier son Personnage lors du prochain tour. " +
                    "Chaque quartier Noble dans sa cité lui rapporte 1 " +
                    "pièce d'or.";
    public static String ROI =
            "Le Roi prend la Couronne et choisira " +
                    "en premier son personnage lors du prochain tour." +
                    "Chaque quartier Noble dans sa cité lui rapporte 1 " +
                    "pièce d'or.";
    public static String ABBE=
            "Le joueur le plus riche doit donner 1 pièce d'or à l'Abbé." +
                    "Chaque quartier Religieux dans sa cité lui rapport 1 pièce " +
                    "d'or ou 1 carte.";
    public static String CARDINAL=
            "Si le Cardinal n'a pas assez d'or pour bâtir un quartier, " +
                    "il peut le prendre à un joueur en échange de carte (1 " +
                    "carte = 1 pièce d'or). Chaque quartier Religieux dans sa " +
                    "cité lui rapport 1 carte quartier.";
    public static String EVEQUE=
            "La cité de l'Evêque est protégée contre les personnages " +
                    "de rang 8 (gemme rouge). Chaque quartier Religieux dans sa "+
                    "cité lui rapport 1 pièce d'or";
    public static String ALCHIMISTE=
            "A la fin de son tour, l'Alchimiste récupère l'or qu'il a " +
                    "dépensé pour bâtir des quartiers durant son tour.";
    public static String MARCHANDE=
            "La Marchande reçoit 1 pièce d'or supplémentaire. Chaque " +
                    "quartier Commerçant dans sa cité lui rapporte 1 pièce d'or.";
    public static String NEGOCIANT=
            "Le Négociant peut bâtir des quartiers Commerçants sans " +
                    "restrictions. Chaque quartier Commerçant dans sa cité " +
                    "lui rapporte 1 pièce d'or.";
    public static String ARCHITECTE=
            "L'Architecte pioche 2 cartes supplémentaires. Son permis " +
                    "de construire est de 3 quartiers.";
    public static String ARCHIVISTE=
            "L'Archiviste pioche 7 cartes et en conserve 1 de son choix. " +
                    "Son permis de construire est de 2 quartiers.";
    public static String NAVIGATRICE=
            "La Navigatrice gagne 4 pièce d'or ou 4 cartes. Elle ne " +
                    "ne peut bâtir de quartiers.";
    public static String CAPITAINE=
            "Le Capitaine peut confisquer un quartier de coût inférieur " +
                    "ou égal à 3, en payant son coût à son propriétaire." +
                    "Chaque quartier Militaire dans sa cité lui rapporte 1 " +
                    "pièce d'or.";
    public static String DIPLOMATE=
            "Le Diplomate peut échanger l'un de ses quartiers bâtis " +
                    "avec un quartier adverse. Chaque quartier Militaire " +
                    "dans sa cité lui rapporte 1 pièce d'or.";
    public static String CONDOTTIERE=
            "Le Condottiere peut détruire un quartier en payant " +
                    "son coût de construction moins 1. " +
                    "Chaque quartier Militaire dans sa cité lui rapporte 1 " +
                    "pièce d'or.";
    public static String ARTISTE=
            "L'Artiste peut embellir un ou deux de ses quartiers en " +
                    "leur assignant 1 pièce d'or. Un même quartier ne peut être " +
                    "embelli qu'une seule fois.";
    public static String BAILLI=
            "Chaque fois qu'un joueur bâtit un quartier, il doit placer " +
                    "1 pièce d'or sur le jeton Bailli. Prenez toutes les pièces" +
                    "d'or sur le jeton Bailli.";
    public static String REINE=
            "Si la Reine est à côté du joueur qui a choisi la carte " +
                    "du personnage de rang 4 (gemme jaune), elle reçoit 3 " +
                    "pièces d'or.";
}
