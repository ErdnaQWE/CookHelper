package com.johicmes.cookhelper;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import java.util.LinkedList;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class RecipeBuilder{
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

        //set le view avec les valeurs de la recette. reste à ajouter les ingrédients/steps et les spinners

        EditText nomText = (EditText) v.findViewById(R.id.nomderecette);
        nomText.setText(nom);

        EditText tempsDeCuissonText=(EditText) v.findViewById(R.id.tempsPrep);
        tempsDeCuissonText.setText(tempsDeCuisson);

        EditText portionsText=(EditText) v.findViewById(R.id.portions);
        portionsText.setText(portions);

        EditText descriptionText=(EditText) v.findViewById(R.id.infoAdd);
        descriptionText.setText(infoAdd);

        EditText idToKeep =(EditText) v.findViewById(R.id.IdPlaceHolder);
        idToKeep.setText(id);






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

        EditText nomText = (EditText) v.findViewById(R.id.nomderecette);
        nom = nomText.getText().toString();

        /* Are now spinners... to be updated
        EditText categorieText=(EditText) v.findViewById(R.id.categorie);
        categorie = categorieText.getText().toString();

        EditText typeDePlatText=(EditText) v.findViewById(R.id.typeDePlats);
        typeDePlat = typeDePlatText.getText().toString();
        */


        EditText tempsDeCuissonText=(EditText) v.findViewById(R.id.tempsPrep);
        tempsDeCuisson = Integer.parseInt(tempsDeCuissonText.getText().toString());

        EditText portionsText=(EditText) v.findViewById(R.id.portions);
        portions = Integer.parseInt(portionsText.getText().toString());

        EditText descriptionText=(EditText) v.findViewById(R.id.infoAdd);
        infoAdd = descriptionText.getText().toString();


        /* needs to be changed pour prendre les parties de ouias
        //etapes
        //add etapes boutton doit incremeter nombreEtapes
        EditText[] etapesText = new EditText[nombreEtapes];
        for (int i=0;i<nombreEtapes;i++){

            EditText etapesText[i] = (EditText) v.findViewById(R.id.);
            etapes[i] = etapesText[i].getText().toString();


        }
       */
        //ingredients
        //add ingredients boutton doit incrementer nombreIngredients
        EditText[] ingredientsText = new EditText[nombreIngredients];
        EditText[] quantiteText = new EditText[nombreIngredients];
        Switch[] switchToggle= new Switch[nombreIngredients];
        for (int i=0;i<nombreIngredients;i++){

            //set ingredients
            ingredientsText[i] = (EditText) v.findViewById(R.id.nomIngredient);
            ingredients[i].setNom(ingredientsText[i].getText().toString());

            //set quantites
            quantiteText[i] = (EditText) v.findViewById(R.id.Quantite);
            ingredients[i].setQuantite(Double.parseDouble(quantiteText[i].getText().toString()));


            //set optionnels
            switchToggle[i] = (Switch) v.findViewById(R.id.switchOptionel);
            ingredients[i].setOptionnel(switchToggle[i].isChecked());

        }

        //image not saved

        Recette nouvelleRecette = new Recette(id,nom,categorie,typeDePlat,tempsDeCuisson,portions,favoris,image,infoAdd);
        nouvelleRecette.ajouterEtapes(etapes);
        nouvelleRecette.ajouterIngredient(ingredients);

        return nouvelleRecette;

    }
}
