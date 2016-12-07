package com.johicmes.cookhelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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
        /* Notes
            -Il vas peut être falloir que tu joue avec des paramètres de tes views comme isVisible, clickable, setDefaultText, setWeight, setGravity...
            -J'utilise generateViewId pour qu'il n'y ai pas de conflit lorsqu'on appelle un view par son id, c'est pour ca qu'il y a le int array
            -L'idéal est d'insérer tes views dynamiques dans un layout qui contient juste ça
            -La méthode addView insère à la fin, il-y-a du polymorphisme pour cette méthode alors tu peux jouer avec ça si tu veux changer du stuff de plaçe
            -Les tooltips sont pas mal utiles pour ce genre de chose, la plus part des problèmes que tu peux avoir ont déjà été répondus sur des forums comme stack overflow
         */

    }
}
