package com.johicmes.cookhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.johicmes.cookhelper.R;

import java.io.IOException;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class LeListnerQuiListenTout implements View.OnClickListener
{

    public void onClick(View v)
    {
        switch (v.findViewById(v.getId()).getId())
        {
            case R.id.buttonEdit:
            {
                RecipeBuilder build = new RecipeBuilder();
            }
            case R.id.search://je ne suis pas trop sûr de ce qu'il faut faire ici pour passer les paramètres
            {
                try
                {
                    ListeBruteDeRecette listeBruteDeRecette = new ListeBruteDeRecette(v.getContext());
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
            case R.id.addIngredients:
            {//ajouter un nouvel ingrédient (RecipeBuilder)
                ((IngredientAdapter)((ListView)v).getAdapter()).add(new Ingredient("",0f,"",false));
                ((IngredientAdapter)((ListView)v).getAdapter()).notifyDataSetChanged();
            }
            case R.id.addSteps:
            {//ajouter une étape (RecipeBuilder)
                ((EtapeAdapter)((ListView)v).getAdapter()).add("");
                ((EtapeAdapter)((ListView)v).getAdapter()).notifyDataSetChanged();
            }
            case R.id.buttonDone:
            {
                Recette recette = ((RecipeCreateActivity)v.getParent()).compilerRecette();// il vas falloir vérifier si ce cast fonctionne, je ne suis pas sur si getParent retourne le activity
                //code pour finir le activity
                //on la met où la recette
            }
        }
    }
}
