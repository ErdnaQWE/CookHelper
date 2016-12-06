package com.johicmes.cookhelper;

import android.widget.EditText;

import java.util.LinkedList;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class RecipeBuilder {
    private String nom;
    private String categorie;
    private String typeDePlat;
    private int tempsDeCuisson;
    private String[] etapes;
    private int portions;
    private boolean favoris;
    private int image;
    private String infoAdd;

    //associations
    Ingredient[] ingredients;
    private Recette recette;

    public RecipeBuilder()
    {
        this.nom=null;
        this.categorie=null;
        this.typeDePlat=null;
        this.ingredients=null;
        this.etapes=null;
        this.image=0;
        this.tempsDeCuisson=0;
        this.portions=0;
        this.favoris=false;
        this.infoAdd=null;


    }

    //constructor d'une recette modifier.
    public RecipeBuilder(Recette recette)
    {
        this.nom=recette.getNom();
        this.categorie=recette.getCategorie();
        this.typeDePlat=recette.getTypeDePlat();
        this.ingredients=recette.getIngredients();
        this.etapes=recette.getEtapes();
        this.image=recette.getImage();
        this.tempsDeCuisson=recette.getTempsDeCuisson();
        this.portions=recette.getPortions();
        this.favoris=recette.getFavori();



    }

    public void ChargerRecette(Recette recette)
    {
        RecipeBuilder editRecette = new RecipeBuilder(recette);

        EditText nomText = (EditText) findViewById(R.id.nomDeRecette);
        nomText.setText(editRecette.nom);

        EditText categorieText=(EditText) findViewById(R.id.categorie);
        categorieText.setText(editRecette.categorie);

        EditText typeDePlatText=(EditText) findViewById(R.id.typeDePlats);
        typeDePlatText.setText(editRecette.typeDePlat);

        EditText tempsDeCuissonText=(EditText) findViewById(R.id.tempsPrep);
        tempsDeCuissonText.setText(editRecette.tempsDeCuisson);

        EditText portionsText=(EditText) findViewById(R.id.portions);
        portionsText.setText(editRecette.portions);

        //quantite pas inclu nul part
        //etapes still vide
        //ingredients still vide

    }

    public Recette compilerRecette()
    {

        String nom=null;
        String categorie=null;
        String typeDePlat=null;
        int tempsDeCuisson=0;
        String[] etapes=null;
        Ingredient[] ingredients=null;
        int portions=0;
        boolean favoris=false;
        int image=0;

        EditText nomText = (EditText) findViewById(R.id.nomDeRecette);
        nom = nomText.getText().toString();

        EditText categorieText=(EditText) findViewById(R.id.categorie);
        categorie = categorieText.getText().toString();

        EditText typeDePlatText=(EditText) findViewById(R.id.typeDePlats);
        typeDePlat = typeDePlatText.getText().toString();

        EditText tempsDeCuissonText=(EditText) findViewById(R.id.tempsPrep);
        tempsDeCuisson = Integer.parseInt(tempsDeCuissonText.getText().toString());

        EditText portionsText=(EditText) findViewById(R.id.portions);
        portions = Integer.parseInt(portionsText.getText().toString());

        //quantite pas inclu nul part
        //etapes still vide
        //ingredients still vide

        Recette nouvelleRecette = new Recette(nom,categorie,typeDePlat,tempsDeCuisson,portions,favoris,image);
        nouvelleRecette.ajouterEtapes(etapes);
        nouvelleRecette.ajouterIngredient(ingredients);

        return nouvelleRecette;

    }
}
