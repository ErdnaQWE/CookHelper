package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Recette {

    private int id;
    private String nom;
    private String categorie;
    private String typeDePlat;
    private int tempsDeCuisson;
    private int portions;
    private boolean favoris;
    private int image;
    private String[] etapes;
    private String description;

    //associations
    Ingredient[] ingredients;
    ListeBruteDeRecette listeBruteDeRecette;

    private void constructeur (int id, String nom, String categorie, String typeDePlat, int tempsDeCuisson, int portions, boolean favoris, int image, String description)
    {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.typeDePlat = typeDePlat;
        this.tempsDeCuisson = tempsDeCuisson;
        this.image = image;
        this.portions = portions;
        this.favoris = favoris;
        this.description = description;
    }

    //constructeur avec tout les arguments
    public Recette (int id, String nom, String categorie, String typeDePlat, int tempsDeCuisson, int portions, boolean favoris, int image, String description)
    {
        constructeur(id, nom, categorie, typeDePlat, tempsDeCuisson, portions, favoris, image, description);
    }

    //constructeur avec les éléments de vignette
    public Recette(VignetteDeRecherche vignette, int tempsDeCuisson, int portions, boolean favoris)
    {
        constructeur(vignette.getId(), vignette.getNom(), vignette.getCategorie(), vignette.getTypeDePlat(), tempsDeCuisson, portions, favoris, vignette.getImage(), description); //Broken!
    }

    //constructeur avec les éléments par défaut
    public Recette ()
    {
        constructeur(-1,"nom", "categorie", "type de plat", 0, 0, false, 0, "Lorem Ipsum");
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

    public int getId() {
        return id;
    }

    public String getNom()
    {
        return nom;
    }

    public String getCategorie()
    {
        return categorie;
    }

    public String getTypeDePlat ()
    {
        return typeDePlat;
    }

    public String getDescription () {
        return description;
    }

    public Ingredient[] getIngredients()
    {
     return ingredients;
    }

    public int getTempsDeCuisson()
    {
        return tempsDeCuisson;
    }

    public int getPortions()
    {
        return portions;
    }

    public boolean getFavori()
    {
        return favoris;
    }

    public String[] getEtapes()
    {
        return etapes;
    }

    //getters pour le adapter
    public String getCategorieEtTypeDePlat()
    {
        return categorie + " | " + typeDePlat;
    }
}
