package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class RecipeBuilder {
    private String nom;
    private String categorie;
    private String typeDePlat;

    //associations
    private Ingredient[] ingredients;//préférablement utiliser autre chose qu'un tableau
    private Recette recette;//ca fait quoi cette association?
    private ListeDeRecette listeDeRecette;//ca fait quoi cette association?

    public RecipeBuilder()
    {

    }

    public void ChargerRecette(Recette recette)
    {

    }

    public Recette compilerRecette()
    {
        return void;
    }
}
