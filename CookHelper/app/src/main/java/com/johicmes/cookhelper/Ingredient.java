package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Ingredient {
    private String nom;
    private double quantite;
    private String uniteDeMesure;
    private boolean optionnel;

    //associations
    Recette recette;

    public Ingredient(String nom, double quantite, String uniteDeMesure, boolean optionnel) {
        this.nom = nom;
        this.quantite = quantite;
        this.optionnel = optionnel;
        this.uniteDeMesure = uniteDeMesure;
    }

    public void convert(Ingredient ingredient) {

        double quantite = ingredient.quantite;
        String uniteDeMesure = ingredient.uniteDeMesure;
        switch (uniteDeMesure) {
            case ("g"):
                ingredient.uniteDeMesure = "lb";
                ingredient.quantite = Math.round((quantite * 0.00220462) * 100.00) / 100.00;
                break;
            case ("lb"):
                ingredient.uniteDeMesure = "g";
                ingredient.quantite = Math.round((quantite * 453.592) * 100.00) / 100.00;

                break;
            case ("ml"):
                ingredient.uniteDeMesure = "oz";
                ingredient.quantite = Math.round((quantite * 0.033814) * 100.00) / 100.00;

                break;
            case ("oz"):
                ingredient.uniteDeMesure = "tasse";
                ingredient.quantite = Math.round((quantite * 0.125) * 100.00) / 100.00;

                break;
            case ("tasse"):
                ingredient.uniteDeMesure = "ml";
                ingredient.quantite = Math.round((quantite * 236.588) * 100.00) / 100.00;
                break;
            default:
                break;

        }


    }
}