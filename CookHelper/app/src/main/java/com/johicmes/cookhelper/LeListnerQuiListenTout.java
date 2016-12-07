package com.johicmes.cookhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.johicmes.cookhelper.R;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class LeListnerQuiListenTout implements View.OnClickListener
{
    //si tu veux accéder à quelquechose de dynamique ou accéder aux méthodes du view utilise v.

    public void onClick(View v)
    {
        switch (v.findViewById(v.getId()).getId())
        {
            case R.id.buttonDone:
            {
                ListView viewIngredients = (ListView) v.findViewById(R.id.addIngredients);
                int i = viewIngredients.getAdapter().getCount();

                ListView viewSteps = (ListView) v.findViewById(R.id.addSteps);
                int j = viewSteps.getAdapter().getCount();

                RecipeBuilder build = new RecipeBuilder();
                build.compilerRecette(j, i);

            }
            case R.id.buttonEdit:
            {
                RecipeBuilder build = new RecipeBuilder();
            }
            case R.id.search:
            {
                //faire le search
            }
        }
    }
}
