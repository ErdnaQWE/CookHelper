package com.johicmes.cookhelper;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class ListeBruteDeRecette {
    private String fichierALire;

    //associations
    ListeDeRecette listeDeRecette;
    VignetteDeRecherche[] vignetteDeRecherches; //on peut utiliser autre chose qu'un tableau ici

    private DatabaseHelper mainDatabase;

    public ListeBruteDeRecette ()
    {
        mainDatabase = new DatabaseHelper(null);

    }

    public VignetteDeRecherche construireVignette(String id)
    {
        return null;
    }

    public Recette getRecette(String id)
    {
        return null;
    }

    public void ajouterRecette(Recette nouvelleRecette)
    {
        // Gets the data repository in write mode
        SQLiteDatabase workingDatabase = mainDatabase.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(RecetteReaderContract.RecetteEntry.COLUMN_NAME_TITLE, nouvelleRecette.getNom());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = workingDatabase.insert(RecetteReaderContract.RecetteEntry.TABLE_NAME, null, values);
    }
}
