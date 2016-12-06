package com.johicmes.cookhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daviiiiid on 2016-12-06.
 */
public class IngredientAdapter extends ArrayAdapter<Ingredient> {

public IngredientAdapter (Context context, List<Ingredient> ingredients)
        {
        super(context,R.layout.cardview,ingredients);
        }

public View getView(int position, View convertView, ViewGroup parent)
        {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cardview, parent, false);


        TextView nomView = (TextView) rowView.findViewById(R.id.textView4);
        TextView quantitéView = (TextView) rowView.findViewById(R.id.bleh);
        //TextView optionnel = (TextView) rowView.findViewById(R.id.textView3);

        //thumbnail.setImageResource(getItem(position).getImage());
        nomView.setText(getItem(position).getNom());
        quantitéView.setText(getItem(position).getQuantite() + " " + getItem(position).getUniteDeMesure());//cast implicite en string

        return rowView;
        }

