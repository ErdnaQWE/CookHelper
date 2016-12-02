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

    //associations
    Ingredient[] ingredients;
    RecipeBuilder recipeBuilder;//je ne suis pas sûr pour cette association
    ListeBruteDeRecette listeBruteDeRecette;

    //constructeur de ListeBruteDeRecette
    public Recette (VignetteDeRecherche vignette)
    {
        nom = vignette.getNom();
        categorie = vignette.getCategorie();
        typeDePlat = vignette.getTypeDePlat();
        image = vignette.getImage();
        // chuis pas sûr comment j'accède les données en mémoire.
        tempsDeCuisson = 0;
        portions = 0;
        favoris = false;
        ingredients = new Ingredient[vignette.getNumIngredient()];
    }

    public void afficher()//ca fait quoi afficher?
    {

    }

    public void ajouterIngredient()//me semble que ça c'est pour recipe builder
    {

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
