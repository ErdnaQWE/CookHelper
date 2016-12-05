package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Recette {

    private String nom;
    private String categorie;
    private String typeDePlat;
    private int tempsDeCuisson;
    private int portions;
    private boolean favoris;
    private int image;
    private String[] etapes;

    //associations
    Ingredient[] ingredients;
    ListeBruteDeRecette listeBruteDeRecette;

    private void constructeur (String nom, String categorie, String typeDePlat, int tempsDeCuisson, int portions, boolean favoris, int image)
    {
        this.nom = nom;
        this.categorie = categorie;
        this.typeDePlat = typeDePlat;
        this.image = image;
        this.portions = portions;
        this.favoris = favoris;
    }

    //constructeur avec tout les arguments
    public Recette (String nom, String categorie, String typeDePlat, int tempsDeCuisson, int portions, boolean favoris, int image)
    {
        constructeur(nom, categorie, typeDePlat, tempsDeCuisson, portions, favoris, image);
    }

    //constructeur avec les éléments de vignette
    public Recette(VignetteDeRecherche vignette, int tempsDeCuisson, int portions, boolean favoris)
    {
        constructeur(vignette.getNom(), vignette.getCategorie(), vignette.getTypeDePlat(), tempsDeCuisson, portions, favoris, vignette.getImage());
    }

    //constructeur avec les éléments par défaut
    public Recette ()
    {
        constructeur("nom", "categorie", "type de plat", 0, 0, false, 0);
    }

    public void afficher()//ca fait quoi afficher? -> On avait besoin de mettre sa pour les diagrammes de séquence, c'est pour afficher la recette dans l'activity, sa se peut que tu va pas l'utiliser though
    {
        // Probablement juste se rajouter au activity ou passer à travers le adapter ou quelque chose comme sa.
    }

    //méthodes pour les array de Dion, si ca marche pas je peux toujours les mettre dans le constructeur.

    public void ajouterIngredient(Ingredient[] ingredients) //Cette méthode remplace le array d'Ingrédients
    {
        this.ingredients = ingredients;
    }

    public void ajouterEtapes(String[] etapes) //Cette méthode remplace les étapes
    {
        this.etapes = etapes;
    }

    //getters
    public int getImage()
    {
        return image;
    }

    public String getNom()
    {
        return nom;
    }
}
