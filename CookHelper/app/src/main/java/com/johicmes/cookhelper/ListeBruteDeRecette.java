package com.johicmes.cookhelper;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.Cursor;

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

        //TEMP VARIABLE
        String name = "KRAFT DINEER";
        String categorie = "CANADIANA";
        String typeDePlat = "UN BOL DE QUELQUE CHOSE";
        Integer tempsDeCuisson = 5; // 5 minutes et pis that's it!
        Integer portions = 1; // Une portion tabarnouche, je partage pas
        Boolean favoris = true; // Ben c'est ben certain

        // DATABASE HANDLING
        SQLiteDatabase workingDatabase = mainDatabase.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                RecetteReaderContract.RecetteEntry._ID,
                RecetteReaderContract.RecetteEntry.COLUMN_NAME_TITLE
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = RecetteReaderContract.RecetteEntry.COLUMN_NAME_TITLE + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                RecetteReaderContract.RecetteEntry.COLUMN_NAME_SUBTITLE + " DESC";

        Cursor c = workingDatabase.query(
                RecetteReaderContract.RecetteEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        c.moveToFirst();
        long itemId = c.getLong(
                c.getColumnIndexOrThrow(RecetteReaderContract.RecetteEntry._ID)
        );

        //And queries as such



        return new Recette(name,categorie,typeDePlat,tempsDeCuisson, portions, favoris, 0);
                //Catégorie et type de plat devraient avoir des enum quelques parts...
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
