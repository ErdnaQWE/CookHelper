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
public class EtapeAdapter extends ArrayAdapter<String> {

    public EtapeAdapter(Context context, List<String> ingredients) {
        super(context, R.layout.activity_steps_view, ingredients);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.cardview, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.vueEtape);
        TextView numView = (TextView) rowView.findViewById(R.id.nbreEtape);

        textView.setText(getItem(position));
        numView.setText((position+1) + ".");//chuis pas sûr si position est le bon numéro,je crois que ca commence a 0. Ça devrais marcher mais il faut le tester;

        return rowView;
    }
}
