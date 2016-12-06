package com.johicmes.cookhelper;

import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.LinkedList;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class RecipeBuilder {
    private int id;
    private String nom;
    private String categorie;
    private String typeDePlat;
    private int tempsDeCuisson;
    private String[] etapes;
    private int portions;
    private boolean favoris;
    private int image;
    private String infoAdd;

    //// TODO: 2016-12-06 mettre toutes les findViewById dans le listner
    private View v;//le view du activity qui construit les recettes

    //associations
    Ingredient[] ingredients;
    private Recette recette;

    public RecipeBuilder()
    {
        this.id=-1;
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
        this.id=recette.getId();
        this.nom=recette.getNom();
        this.categorie=recette.getCategorie();
        this.typeDePlat=recette.getTypeDePlat();
        this.ingredients=recette.getIngredients();
        this.etapes=recette.getEtapes();
        this.image=recette.getImage();
        this.tempsDeCuisson=recette.getTempsDeCuisson();
        this.portions=recette.getPortions();
        this.favoris=recette.getFavori();
        this.infoAdd = recette.getDescription();



    }

    public void ChargerRecette(Recette recette)
    {
        RecipeBuilder editRecette = new RecipeBuilder(recette);

        EditText idText = (EditText) v.findViewById(R.id.recetteId);//Ca c'est ce qui faut que tu fasse pour que ca marche mais il faudrait que tu le fasse dans LeListnerQuiListenTout
        idText.setText(editRecette.id);

        EditText nomText = (EditText) v.findViewById(R.id.nomDeRecette);
        nomText.setText(editRecette.nom);

        EditText categorieText=(EditText) v.findViewById(R.id.categorie);
        categorieText.setText(editRecette.categorie);

        EditText typeDePlatText=(EditText) v.findViewById(R.id.typeDePlats);
        typeDePlatText.setText(editRecette.typeDePlat);

        EditText tempsDeCuissonText=(EditText) v.findViewById(R.id.tempsPrep);
        tempsDeCuissonText.setText(editRecette.tempsDeCuisson);

        EditText portionsText=(EditText) v.findViewById(R.id.portions);
        portionsText.setText(editRecette.portions);

        EditText descriptionText = (EditText) v.findViewById(R.id.description);
        descriptionText.setText(editRecette.infoAdd);

        //affiche les etapes
        int sizeEtapes = etapes.length;
        EditText[] etapesText = new EditText[sizeEtapes];
        for (int i=0;i<sizeEtapes;i++) {

            etapesText[i] = (EditText) v.findViewById(R.id.step[i]);
            etapesText[i].setText(editRecette.etapes[i]);
        }

        //affiche les ingredients
        int sizeIngredients = ingredients.length;
        EditText[] ingredientsText = new EditText[sizeIngredients];
        EditText[] quantiteText = new EditText[sizeIngredients];
        Switch[] switchToggle = new Switch[sizeIngredients];

        for (int i=0;i<sizeIngredients;i++){
            //set ingredients
            ingredientsText[i] = (EditText) v.findViewById(R.id.ingredient[i]);
            ingredientsText[i].setText(editRecette.ingredients[i].getNom());

            //set quantites
            quantiteText[i] = (EditText) v.findViewById(R.id.quantite[i]);
            quantiteText[i].setText(new Double(editRecette.ingredients[i].getQuantite()).toString());

            //set optionnels
            switchToggle[i] = (Switch) v.findViewById(R.id.switch[i]);
            switchToggle[i].checked=true;
        }



    }

    public Recette compilerRecette(int nombreEtapes, int nombreIngredients)
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
        String infoAdd=null;

        EditText idText=(EditText) v.findViewById(R.id.recetteId);
        id= Integer.parseInt(idText.getText().toString());

        EditText nomText = (EditText) v.findViewById(R.id.nomDeRecette);
        nom = nomText.getText().toString();

        EditText categorieText=(EditText) v.findViewById(R.id.categorie);
        categorie = categorieText.getText().toString();

        EditText typeDePlatText=(EditText) v.findViewById(R.id.typeDePlats);
        typeDePlat = typeDePlatText.getText().toString();

        EditText tempsDeCuissonText=(EditText) v.findViewById(R.id.tempsPrep);
        tempsDeCuisson = Integer.parseInt(tempsDeCuissonText.getText().toString());

        EditText portionsText=(EditText) v.findViewById(R.id.portions);
        portions = Integer.parseInt(portionsText.getText().toString());

        EditText descriptionText=(EditText) v.findViewById(R.id.description);
        infoAdd = descriptionText.getText().toString();

        //etapes
        //add etapes boutton doit incremeter nombreEtapes
        EditText[] etapesText = new EditText[nombreEtapes];
        for (int i=0;i<nombreEtapes;i++){

            EditText etapesText[i] = (EditText) v.findViewById(R.id.step[i]);
            etapes[i] = etapesText[i].getText().toString();


        }

        //ingredients
        //add ingredients boutton doit incrementer nombreIngredients
        EditText[] ingredientsText = new EditText[nombreIngredients];
        EditText[] quantiteText = new EditText[nombreIngredients];
        Switch[] switchToggle= new Switch[nombreIngredients];
        for (int i=0;i<nombreIngredients;i++){

            //set ingredients
            ingredientsText[i] = (EditText) v.findViewById(R.id.ingredient[i]);
            ingredients[i].setNom(ingredientsText[i].getText().toString());

            //set quantites
            quantiteText[i] = (EditText) v.findViewById(R.id.quantite[i]);
            ingredients[i].setQuantite(Double.parseDouble(quantiteText[i].getText().toString()));


            //set optionnels
            switchToggle[i] = (Switch) v.findViewById(R.id.switch[i]);
            ingredients[i].setOptionnel(switchToggle[i].isChecked());

        }

        //image not saved

        Recette nouvelleRecette = new Recette(id,nom,categorie,typeDePlat,tempsDeCuisson,portions,favoris,image,infoAdd);
        nouvelleRecette.ajouterEtapes(etapes);
        nouvelleRecette.ajouterIngredient(ingredients);

        return nouvelleRecette;

    }
}
