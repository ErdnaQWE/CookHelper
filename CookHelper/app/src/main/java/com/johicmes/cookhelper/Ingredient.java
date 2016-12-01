package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Ingredient {
    private String nom;
    private int quantite;
    private String type;//C'est quoi type?
    private boolean optionnel;

    //associations
    RecipeBuilder recipeBuilder;//je ne suis pas sûr pour cette association
    Recette recette;

    public Ingredient ()
    {

    }

    public float convertirVersLitres()//ca dit String mais je crois que ca devrais retourner un type float
    {
        return 0.0f;
    }

    public float convertinVersOnces()
    {
        return 0.0f;
    }
    public float convertinVersLivres()
    {
        return 0.0f;
    }
    public void afficher()//ca fait quoi afficher?
    {

    }
}
