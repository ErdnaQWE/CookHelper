package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Recette {

    private String nom;
    private String categorie;
    private String typeDePlat;
    private int temps;
    private int portions;
    private boolean favoris;
    //Image

    //associations
    Ingredient[] ingredients;
    RecipeBuilder recipeBuilder;//je ne suis pas sûr pour cette association
    ListeBruteDeRecette listeBruteDeRecette;

    public Recette ()
    {

    }

    public void afficher()//ca fait quoi afficher?
    {

    }

    public void ajouterIngredient()//me semble que ça c'est pour recipe builder
    {

    }
}
