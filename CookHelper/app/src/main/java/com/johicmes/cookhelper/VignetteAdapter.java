package com.johicmes.cookhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class VignetteAdapter extends ArrayAdapter<VignetteDeRecherche> {

    public VignetteAdapter (Context context, List<VignetteDeRecherche> vignette)
    {
        super(context,R.layout.cardview,vignette);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cardview, parent, false);

        ImageView thumbnail = (ImageView) rowView.findViewById(R.id.thumbnail);
        TextView nomView = (TextView) rowView.findViewById(R.id.textNom);
        TextView categorietTypeDePlatView = (TextView) rowView.findViewById(R.id.textCategorieEtType);
        TextView fractionView = (TextView) rowView.findViewById(R.id.texteElementContenue);

        thumbnail.setImageResource(getItem(position).getImage());
        nomView.setText(getItem(position).getNom());
        categorietTypeDePlatView.setText(getItem(position).getCategorieEtTypeDePlat());
        fractionView.setText(getItem(position).getFraction());

        return rowView;
    }
}
