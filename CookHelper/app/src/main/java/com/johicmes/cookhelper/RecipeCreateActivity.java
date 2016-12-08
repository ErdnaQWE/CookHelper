package com.johicmes.cookhelper;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class RecipeCreateActivity extends AppCompatActivity {

    private EditText idText, nomText, tempsDeCuissonText, portionsText, descriptionText;
    private EditText[] etapesText;//pourrait être autre chose qu'un array
    private EditText categorieText,typeDePlatText;//devrait être des comboBox/spinner
    private ListView etapeView, ingredientView;
    private EtapeAdapter etapeAdapter;
    private IngredientAdapter ingredientAdapter;
    private LeListnerQuiListenTout l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_create);

        l = new LeListnerQuiListenTout();

        //idText = (EditText) findViewById(R.id.recetteId); // commented out needs to be fixed. easy fix.
        nomText = (EditText) findViewById(R.id.nomderecette);//I guess que c'est pas les bons id ici
        categorieText=(EditText) findViewById(R.id.textCategorie);
        typeDePlatText=(EditText) findViewById(R.id.textType);
        tempsDeCuissonText=(EditText) findViewById(R.id.tempsPrep);
        portionsText=(EditText) findViewById(R.id.portions);
        descriptionText = (EditText) findViewById(R.id.infoAdd);
        etapeView = (ListView) findViewById(R.id.addSteps);
        etapeView.setOnClickListener(l);
        ingredientView = (ListView) findViewById(R.id.addIngredients);
        ingredientView.setOnClickListener(l);
    }

    public void ChargerRecette(Recette recette)
    {
        //mettre les bonnes valeurs dans les EditText
        idText.setText(recette.getId());
        nomText.setText(recette.getNom());
        tempsDeCuissonText.setText(recette.getTempsDeCuisson());
        portionsText.setText(recette.getPortions());
        descriptionText.setText(recette.getDescription());
        categorieText.setText(recette.getCategorie());
        typeDePlatText.setText(recette.getTypeDePlat());

        //ajouter les ingrédients
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        for (Ingredient i : recette.getIngredients())
            ingredients.add(i);
        ingredientAdapter = new IngredientAdapter(this,ingredients);
        ingredientView.setAdapter(ingredientAdapter);

        //ajouter les étapes
        List<String> etapes = new ArrayList<String>();
        for (String s : recette.getEtapes())
            etapes.add(s);
        etapeAdapter = new EtapeAdapter(this,etapes);
        etapeView.setAdapter(etapeAdapter);
    }


    //exemple du lab 6 pour référence
    /*
        ListView listView = (ListView) findViewById(R.id.listRecettes);

        //cette partie est juste créer une liste du type du adapter
        List<Recette> listRecettes = new ArrayList<Recette>();
        listRecettes.add(new Recette("Solitaire 3.14","Canada",R.drawable.ic_launcher));
        listRecettes.add(new Recette("Meilleur jeu du monde","Canada",R.drawable.ic_launcher));


        //cette partie est le Adapter qui est ajouté au listView
        final RecetteAdapter recetteAdapter = new RecetteAdapter(this,listRecettes);
        listView.setAdapter(recetteAdapter);


        //cette partie est juste ajouter à la liste quand le bouton est pesé, c'est juste la partie ajouter qui devrais être intéressante
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick (AdapterView<?> parent, View v, int position, long id)
            {
                recetteAdapter.add(new Recette("nouveau", "inconnu", R.drawable.ic_launcher));
                recetteAdapter.notifyDataSetChanged();
            }
        });
     */

    public Recette compilerRecette()
    {
        // à supprimer si c'est ce qu'il faut que cette classe fait
        /* ce que Anthony a fait
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

        nom = nomText.getText().toString();
        categorie = categorieText.getText().toString();
        typeDePlat = typeDePlatText.getText().toString();


        EditText tempsDeCuissonText=(EditText) findViewById(R.id.tempsPrep);
        tempsDeCuisson = Integer.parseInt(tempsDeCuissonText.getText().toString());

        EditText portionsText=(EditText) findViewById(R.id.portions);
        portions = Integer.parseInt(portionsText.getText().toString());

        EditText descriptionText=(EditText) findViewById(R.id.infoAdd);
        infoAdd = descriptionText.getText().toString();


        // needs to be changed pour prendre les parties de ouias
        //etapes
        //add etapes boutton doit incremeter nombreEtapes
        //EditText[] etapesText = new EditText[nombreEtapes];
        //for (int i=0;i<nombreEtapes;i++){

            //EditText etapesText[i] = (EditText) v.findViewById(R.id.);
            //etapes[i] = etapesText[i].getText().toString();


        //}

        //ingredients
        //add ingredients boutton doit incrementer nombreIngredients
        EditText[] ingredientsText = new EditText[nombreIngredients];
        EditText[] quantiteText = new EditText[nombreIngredients];
        Switch[] switchToggle= new Switch[nombreIngredients];
        for (int i=0;i<nombreIngredients;i++){

            //set ingredients
            ingredientsText[i] = (EditText) findViewById(R.id.nomIngredient);
            ingredients[i].setNom(ingredientsText[i].getText().toString());

            //set quantites
            quantiteText[i] = (EditText) findViewById(R.id.Quantite);
            ingredients[i].setQuantite(Double.parseDouble(quantiteText[i].getText().toString()));


            //set optionnels
            switchToggle[i] = (Switch) findViewById(R.id.switchOptionel);
            ingredients[i].setOptionnel(switchToggle[i].isChecked());

        }

        //image not saved

        Recette nouvelleRecette = new Recette(id,nom,categorie,typeDePlat,tempsDeCuisson,portions,favoris,image,infoAdd);
        nouvelleRecette.ajouterEtapes(etapes);
        nouvelleRecette.ajouterIngredient(ingredients);
        */

        Recette nouvelleRecette = new Recette(
                Integer.parseInt(idText.getText().toString()),
                nomText.getText().toString(),
                categorieText.getText().toString(),
                typeDePlatText.getText().toString(),
                Integer.parseInt(tempsDeCuissonText.getText().toString()),
                Integer.parseInt(portionsText.getText().toString()),
                false,//favoris
                R.drawable.kimchicken,//image
                descriptionText.getText().toString()
        );
        Ingredient[] ingredients = new Ingredient[ingredientAdapter.getCount()];
        for(int i = 0; i<ingredients.length; i++)
            ingredients[i] = ingredientAdapter.getItem(i);
        nouvelleRecette.ajouterIngredient(ingredients);
        String[] etapes = new String[etapeAdapter.getCount()];
        for(int i = 0; i<etapes.length; i++)
            etapes[i] = etapeAdapter.getItem(i);
        nouvelleRecette.ajouterEtapes(etapes);
        return nouvelleRecette;

    }
}
