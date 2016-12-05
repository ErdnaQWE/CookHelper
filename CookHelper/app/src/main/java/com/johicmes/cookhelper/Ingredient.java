package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Ingredient {
    private String nom;
    private int quantite;
    private String type; //C'est quoi type? -> Type de plat
    private boolean optionnel;

    //associations
    RecipeBuilder recipeBuilder; //je ne suis pas sûr pour cette association -> Je pouvais pas mettre des one-way dans USE, enlève-le
    Recette recette;

    public Ingredient ()
    {

    }

    public float convertirVersLitres()
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
    public void afficher()//ca fait quoi afficher? -> On avait besoin de mettre sa pour les diagrammes de séquence, c'est pour afficher l'ingrédient dans l'activity, sa se peut que tu va pas l'utiliser though
    {

    }
}
