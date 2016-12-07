package com.johicmes.cookhelper;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class VignetteAdapter extends RecyclerView.Adapter<VignetteAdapter.MyViewHolder> {
    ArrayAdapter<VignetteDeRecherche> vignettes;
    Context context;

    public VignetteAdapter (Context context, ArrayAdapter<VignetteDeRecherche> vignettes){
        this.context = context;
        this.vignettes = vignettes;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        MyViewHolder rowView = new MyViewHolder(view);
        return rowView;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        ImageView thumbnail = (ImageView) holder.itemView.findViewById(R.id.thumbnail);
        TextView nomView = (TextView) holder.itemView.findViewById(R.id.textNom);
        TextView categorietTypeDePlatView = (TextView) holder.itemView.findViewById(R.id.textCategorieEtType);
        TextView fractionView = (TextView) holder.itemView.findViewById(R.id.texteElementContenue);

        thumbnail.setImageResource(getItem(position).getImage());
        nomView.setText(getItem(position).getNom());
        categorietTypeDePlatView.setText(getItem(position).getCategorieEtTypeDePlat());
        fractionView.setText(getItem(position).getFraction());


    }

    @Override
    public int getItemCount() {
        return vignettes.getCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }





    /*
    Temporary Comment out ArrayAdapter<VignetteDeRecherche>

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

    */



}
