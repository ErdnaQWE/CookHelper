package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class VignetteDeRecherche {

    private int id;
    private String nom;
    private String categorie;
    private String typeDePlat;
    private int image;
    //Ajoutés pour vignetteAdapter
    private int numIngredient;//nombre d'ingredients total
    private int pertinence;//nombre d'ingrédients concordant à la recherche
    private Recette objetRecette;

    //associations
    ListeDeRecette listeDeRecette;
    ListeBruteDeRecette listeBruteDeRecette;

    //constructeur pour ListeBruteDeRecette
    private void construction (int id, String nom, String categorie, String typeDePlat, int image, int numIngredient, int pertinence)
    {
        this.id = id;
        this.nom = nom;
        this.categorie = categorie;
        this.typeDePlat = typeDePlat;
        this.image = image;
        this.numIngredient = numIngredient;
        this.pertinence = pertinence;
        this.objetRecette = new Recette(); // TODO insert constructors or it's just gonna have no attributes like 0 ecerywhere
    }
    public VignetteDeRecherche(int id, String nom, String categorie, String typeDePlat, int image, int numIngredient, int pertinence)
    {
        construction(id,nom,categorie,typeDePlat,image,numIngredient,pertinence);
    }

    public VignetteDeRecherche(int id, String nom, String categorie, String typeDePlat, int image, int numIngredient)
    {
        construction(id,nom,categorie,typeDePlat,image,numIngredient,0);//j'ai mis 0 par défaut mais tu peux le changer
    }

    public void afficher()//ca fait quoi afficher?
    {

    }

    //getters

    public Integer getId() {
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

    public String getTypeDePlat()
    {
        return typeDePlat;
    }

    public int getImage ()
    {
        return image;
    }

    public int getNumIngredient()
    {
        return numIngredient;
    }

    public void setPertinence(int pertinence) {
        this.pertinence = pertinence;
    }

    //getters pour VignetteAdapter
    public String getCategorieEtTypeDePlat()
    {
        return categorie + " | " + typeDePlat;
    }

    public String getFraction()
    {
        return pertinence +"/" + numIngredient;
    }
}
