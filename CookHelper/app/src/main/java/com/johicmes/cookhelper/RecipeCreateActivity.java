package com.johicmes.cookhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class RecipeCreateActivity extends AppCompatActivity {

    private EditText idText, nomText, tempsDeCuissonText, portionsText, descriptionText;
    private Spinner categorieText,typeDePlatText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_create);
        //s'il manque du stuff ici ajoute le

        //un exemple pour Anthony
        /*
        //exemple d'ajouter un Button a un array de button, peut se faire pour tout type de view
        //typiquement tu veux ces variables comme des membres privés de cette classe.
        LeListnerQuiListenTout l = new LeListnerQuiListenTout();//pour les views qui sont Clickable
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.relative_layout);//r.id.relative_layout indique un relativeLayout hardcoded
        Button[] buttons = new Button[10];//juste pour l'exemple
        int[] buttonIds = new int[10];//pour garder les id, devrait être privé avec une méthode publique du genre getElement(int)
        for (int i = 0; i<10; i++)
        {
            buttons[i] = new Button(this);//fonctionne aussi avec les layouts
            buttons[i].generateViewId();//Cette méthode génère un id unique
            buttonIds[i] = buttons[i].getId();//sauvegarde ce int pour un appel futur
            buttons[i].setOnClickListener(l);//pour handler onclick
            relativeLayout.addView(buttons[i]);//pout ajouter ton view dynamique dans un layout particulier
        }
           Notes
            -Il vas peut être falloir que tu joue avec des paramètres de tes views comme isVisible, clickable, setDefaultText, setWeight, setGravity...
            -J'utilise generateViewId pour qu'il n'y ai pas de conflit lorsqu'on appelle un view par son id, c'est pour ca qu'il y a le int array
            -L'idéal est d'insérer tes views dynamiques dans un layout qui contient juste ça
            -La méthode addView insère à la fin, il-y-a du polymorphisme pour cette méthode alors tu peux jouer avec ça si tu veux changer du stuff de plaçe
            -Les tooltips sont pas mal utiles pour ce genre de chose, la plus part des problèmes que tu peux avoir ont déjà été répondus sur des forums comme stack overflow
         */

        idText = (EditText) findViewById(R.id.recetteId);

        nomText = (EditText) findViewById(R.id.nomderecette);//I guess que c'est pas les bons id ici
        categorieText=(Spinner) findViewById(R.id.categorieSpinner);
        typeDePlatText=(Spinner) findViewById(R.id.TypeDePlatSpinner);
        tempsDeCuissonText=(EditText) findViewById(R.id.tempsPrep);
        portionsText=(EditText) findViewById(R.id.portions);
        descriptionText = (EditText) findViewById(R.id.infoAdd);
    }

    public void ChargerRecette(Recette recette)
    {
        idText.setText(recette.getId());
        nomText.setText(recette.getNom());
        tempsDeCuissonText.setText(recette.getTempsDeCuisson());
        portionsText.setText(recette.getPortions());
        descriptionText.setText(recette.getDescription());
        categorieText.setText(recette.getCategorie());
        typeDePlatText.setText(recette.getTypeDePlat());


        //affiche les etapes
        int sizeEtapes = recette.getEtapes().length;
        EditText[] etapesText = new EditText[sizeEtapes];
        for (int i=0;i<sizeEtapes;i++) {

            etapesText[i] = (EditText) findViewById(R.id.step[i]);
            etapesText[i].setText(recette.getEtapes()[i]);
        }

        //affiche les ingredients
        int sizeIngredients = recette.getIngredients().length;
        EditText[] ingredientsText = new EditText[sizeIngredients];
        EditText[] quantiteText = new EditText[sizeIngredients];
        Switch[] switchToggle = new Switch[sizeIngredients];

        for (int i=0;i<sizeIngredients;i++){
            //set ingredients
            ingredientsText[i] = (EditText) findViewById(R.id.ingredient[i]);
            ingredientsText[i].setText(recette.getIngredients()[i].getNom());

            //set quantites
            quantiteText[i] = (EditText) findViewById(R.id.quantite[i]);
            quantiteText[i].setText(new Double(recette.getIngredients()[i].getQuantite()).toString());

            //set optionnels
            switchToggle[i] = (Switch) findViewById(R.id.switch[i]);
            switchToggle[i].toggle();
        }
    }
}
