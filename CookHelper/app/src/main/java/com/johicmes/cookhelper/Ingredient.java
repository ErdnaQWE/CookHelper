package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Ingredient {
    private String nom;
    private int quantite;
    private String uniteDeMesure; //C'est quoi type? -> Type de plat
    private boolean optionnel;

    //associations
    Recette recette;

    public Ingredient (String nom, int quantite,String uniteDeMesure, boolean optionnel)
    {
        this.nom = nom;
        this.quantite=quantite;
        this.optionnel=optionnel;
        this.uniteDeMesure=uniteDeMesure;
    }

    public String[] convert(int quantite, String uniteDeMesure){

        String[] newProperties = new String[2];

        return newProperties;

    }
    public void afficher()//ca fait quoi afficher? -> On avait besoin de mettre sa pour les diagrammes de séquence, c'est pour afficher l'ingrédient dans l'activity, sa se peut que tu va pas l'utiliser though
    {

    }
}
