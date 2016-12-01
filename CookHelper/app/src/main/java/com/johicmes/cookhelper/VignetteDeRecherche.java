package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class VignetteDeRecherche {

    private String nom;
    private String categorie;
    private String typeDePlat;
    private int image;
    //Ajoutés pour vignetteAdapter
    private int numIngredient;
    private int resultat;//nombre d'ingrédients concordant à la recherche

    //associations
    ListeDeRecette listeDeRecette;
    ListeBruteDeRecette listeBruteDeRecette;

    //constructeur pour ListeBruteDeRecette
    public VignetteDeRecherche(String id)
    {
        //on construit de la base de donné
    }

    public void afficher()//ca fait quoi afficher?
    {

    }

    ///////////
    //getters//
    ///////////
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

    //getters pour VignetteAdapter
    public String getCategorieEtTypeDePlat()
    {
        return categorie + " | " + typeDePlat;
    }

    public String getFraction()
    {
        return resultat +"/" + numIngredient;
    }
}
