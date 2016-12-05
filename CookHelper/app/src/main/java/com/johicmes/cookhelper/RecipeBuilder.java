package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class RecipeBuilder {
    private String nom;
    private String categorie;
    private String typeDePlat;

    //associations
    private Ingredient[] ingredients;// préférablement utiliser autre chose qu'un tableau -> Ouin
    private Recette recette;//ca fait quoi cette association? -> Pour contenir le produit final pendant sa construction, si tu l'as pas besoin, then enleve la
    private ListeDeRecette listeDeRecette;//ca fait quoi cette association? -> Je pouvais pas mettre des one-way dans USE, enlève-le

    public RecipeBuilder()
    {

    }

    public void ChargerRecette(Recette recette)
    {

    }

    public Recette compilerRecette()
    {
        return null;
    }
}
