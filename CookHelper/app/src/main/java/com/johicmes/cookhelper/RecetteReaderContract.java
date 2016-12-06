package com.johicmes.cookhelper;

import android.provider.BaseColumns;

/**
 * Created by alexandredion on 16-12-06.
 */

public final class RecetteReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private RecetteReaderContract() {}

    /* Inner class that defines the table contents */
    public static class RecetteEntry implements BaseColumns {
        public static final String TABLE_NAME = "Recette";
        public static final String COLUMN_NAME_TITLE = "Name";
    }
}