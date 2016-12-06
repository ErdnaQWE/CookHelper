package com.johicmes.cookhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class RecipeCreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_create);
        //s'il manque du stuff ici ajoute le

        //exemple d'ajouter un Button a un array de button, peut se faire pour tout type de view
        LeListnerQuiListenTout l = new LeListnerQuiListenTout();//pour les views qui sont Clickable
        Button[] buttons = new Button[10];//juste pour l'exemple
        int[] buttonIds = new int[10];//pour garder les id, devrait être privé avec une méthode publique du genre getElement(int)
        for (int i = 0; i<10; i++)
        {
            buttons[i] = new Button(this);//fonctionne aussi avec les layouts
            buttons[i].generateViewId();//Cette méthode génère un id unique todo augmenter le niveau du API à au moins 17
            buttonIds[i] = buttons[i].getId();//sauvegarde ce int pour un appel futur
            buttons[i].setOnClickListener(l);//pour handler onclick
        }

    }
}
