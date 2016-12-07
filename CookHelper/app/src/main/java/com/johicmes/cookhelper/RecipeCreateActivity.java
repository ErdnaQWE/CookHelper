package com.johicmes.cookhelper;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
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
    private EditText[] etapesText;//pourrait être autre chose qu'un array
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
        //todo mettre les bon id pour les spinners
        categorieText = (Spinner) findViewById(0); //tu avais oublié d'ajouter
        typeDePlatText = (Spinner) findViewById(0);
    }

    public void ChargerRecette(Recette recette)
    {
        idText.setText(recette.getId());
        nomText.setText(recette.getNom());
        tempsDeCuissonText.setText(recette.getTempsDeCuisson());
        portionsText.setText(recette.getPortions());
        descriptionText.setText(recette.getDescription());
        //todo trouver comment setter le spinner a une valeur par défaut setText n'existe pas pour des spinners, on peut faire ça demain
        //categorieText.setText(recette.getCategorie());
        //typeDePlatText.setText(recette.getTypeDePlat());


        //affiche les etapes
        int sizeEtapes = recette.getEtapes().length;
        etapesText = new EditText[sizeEtapes];// on doit créer les étapes dynamiquement
        //todo créer ces EditText dynamiquement, demande a Johic a propos des adapters qu'il a fait pour étapes et ingrédients ca pourrait te faciliter la tâche
        for (int i=0;i<sizeEtapes;i++) {

            etapesText[i] = (EditText) findViewById(R.id.step[i]);//ce id est invalide, tu dois créer les EditText dynamiquement comme dans l'exemple en haut
            etapesText[i].setText(recette.getEtapes()[i]);//cette ligne marche
        }

        //affiche les ingredients
        int sizeIngredients = recette.getIngredients().length;
        //demande a Johic c'est quoi qu'il a fait pour ingrédient, il a fait un xml pour les ingrédients et je crois que tu peux les ajouter comme dans lab 6
        //tu peux checker les powerpoints de ce lab quand tu comprends c'est assez simple, le adapter est déjà fait
        EditText[] ingredientsText = new EditText[sizeIngredients];
        EditText[] quantiteText = new EditText[sizeIngredients];
        Switch[] switchToggle = new Switch[sizeIngredients];

        for (int i=0;i<sizeIngredients;i++){
            //set ingredients
            ingredientsText[i] = (EditText) findViewById(R.id.ingredient[i]);//ce id est invalide
            ingredientsText[i].setText(recette.getIngredients()[i].getNom());

            //set quantites
            quantiteText[i] = (EditText) findViewById(R.id.quantite[i]);
            quantiteText[i].setText(new Double(recette.getIngredients()[i].getQuantite()).toString());

            //set optionnels
            switchToggle[i] = (Switch) findViewById(R.id.switch[i]);
            switchToggle[i].toggle();
        }
    }


    //exemple du lab 6, tu peux peut être faire ça pour étapes et ingrédients
    /*
        ListView listView = (ListView) findViewById(R.id.listRecettes);

        //cette partie est juste créer une liste du type du adapter
        List<Recette> listRecettes = new ArrayList<Recette>();
        listRecettes.add(new Recette("Solitaire 3.14","Canada",R.drawable.ic_launcher));
        listRecettes.add(new Recette("Meilleur jeu du monde","Canada",R.drawable.ic_launcher));


        //cette partie est le Adapter qui est ajouté au listView
        final RecetteAdapter recetteAdapter = new RecetteAdapter(this,listRecettes);
        listView.setAdapter(recetteAdapter);


        //cette partie est juste ajouter à la liste quand le bouton est pesé, c'est juste la partie ajouter qui devrais t'intéresser
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick (AdapterView<?> parent, View v, int position, long id)
            {
                recetteAdapter.add(new Recette("nouveau", "inconnu", R.drawable.ic_launcher));
                recetteAdapter.notifyDataSetChanged();
            }
        });
     */

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


        EditText tempsDeCuissonText=(EditText) findViewById(R.id.tempsPrep);
        tempsDeCuisson = Integer.parseInt(tempsDeCuissonText.getText().toString());

        EditText portionsText=(EditText) findViewById(R.id.portions);
        portions = Integer.parseInt(portionsText.getText().toString());

        EditText descriptionText=(EditText) findViewById(R.id.infoAdd);
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

        return nouvelleRecette;

    }
}
