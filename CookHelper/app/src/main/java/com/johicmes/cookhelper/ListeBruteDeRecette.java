package com.johicmes.cookhelper;

import android.content.Context;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class ListeBruteDeRecette {
    private String FICHIER_A_LIRE = "recetteDatabase.txt";

    //associations
    ListeDeRecette listeDeRecette;
    VignetteDeRecherche[] vignetteDeRecherches; //on peut utiliser autre chose qu'un tableau ici

    private Context context;
    private int nextId = 0;

    private enum searchModifier {ET,OU,NON};

    private class SearchEntry {

        public final String[] values;
        public final searchModifier modifier;

        SearchEntry(String value, searchModifier modifier) {

            this.values = new String[1];
            this.values[0] = value;
            this.modifier = modifier;

        }

        SearchEntry(String[] values, searchModifier modifier) {

            this.values = values.clone(); // Copies the values to avoid errors later on
            this.modifier = modifier;
        }

    }


    public ListeBruteDeRecette (Context context)
    {
        this.context = context;
    }

    public VignetteDeRecherche construireVignette(String id)
    {
        return null;
    }


     /* La fonction de recherche. Si le tout s'exécute correctement, devrait afficher les résultats dans la liste

    Exemple de input : Patate & !Carotte & Chou | Sirop d'érable
    sera interprété comme suit:
        Je veux: (Patate) ET (Chou OU Sirop d'érable)
        Je ne veux pas: (Carotte)
    Une recette de pertinence parfaite aurait: Des patates, des chous et aucune carottes
    Une recette qui aurait tout ces ingrédients mais du Sirop d'érable en plus ne serait pas plus haut en pertinence

    Autre exemple: Patate et Carotte et pas Chou ou Sirop d'érable
    sera interprété comme suit:
        Je veux: (Patate) ET (Carotte) ET (Sirop d'érable OU pas de Chous)
    Donc, une recette ne contenant pas de chou aurait un point de pertinence additionelle, une recette avec du sirop d'érable aurait aussi un point.
    MAIS, une recette contenant pas de chou ET du sirop d'érable aurait seulement un point. Aussi, une recette avec du chou et du sirop d'érable aurait un point.

     */

    public VignetteDeRecherche[] rechercheBooleenne(String input) {

        SearchEntry[] entries = separerTermes(input);

        try {
            FileInputStream fileIn = context.openFileInput(FICHIER_A_LIRE);
            InputStreamReader inputStreamReader= new InputStreamReader(fileIn);

            BufferedReader reader = new BufferedReader(inputStreamReader);


            String workingLine = reader.readLine();
            String workingIngredient = reader.get
            int workingId = -2;
            int currentPertinence = 0;

            while (workingLine != null) {


                if (workingLine.charAt(1) == '#') { //Début d'une nouvelle recette
                    workingId = Integer.parseInt(workingLine.substring(2));

                    for (int i = 0; i < 8; i++) { // Saute aux ingrédients (8 lignes plus bas en concordance avec le modèle)
                        reader.readLine();
                    }


                }








            }

            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }





        return null;
    }



    public SearchEntry[] separerTermes(String input)
    {

        LinkedList<SearchEntry> entries = new LinkedList<SearchEntry>();

        String[] rawTerms = input.split(" ");
        String lastTerm = "";
        Boolean lastOperatorWasOr = false;
        LinkedList<String> orTerms = new LinkedList<String>();

        for (int i = 0; i < rawTerms.length; i++) {

            rawTerms[i] = rawTerms[i].toLowerCase(); //Unify the case of all characters to make the searches non case sensitive

            if (rawTerms[i].equals("and") || rawTerms[i].charAt(0) == '&') { // AND

                if (!lastTerm.equals("")) { // Rien avant le AND, on l'ignore

                    if (lastOperatorWasOr) { // Il faut construire le OR
                        orTerms.add(lastTerm);
                        entries.add(new SearchEntry(orTerms.toArray(new String[orTerms.size()]), searchModifier.OU));
                        lastOperatorWasOr = false;
                    } else {
                        entries.add(new SearchEntry(lastTerm, searchModifier.ET));
                    }

                    lastTerm = ""; // AND n'enchaîne rien, on peut libérer le dernier terme
                }

            } else if (rawTerms[i].equals("or") || rawTerms[i].charAt(0) == '|') { // OR

                if (!lastTerm.equals("")) { // Rien avant le OR, on l'ignore

                    if (lastOperatorWasOr) { //Si le OR fait pas partie d'une chaîne
                        orTerms.add(lastTerm);
                        lastTerm = "";
                    } else { // Début d'une nouvelle chaîne
                        orTerms = new LinkedList<String>();
                        lastOperatorWasOr = true;
                        lastTerm = "";
                    }

                }

            } else { // NORMAL TERM

                if (lastTerm.equals("")) {
                    lastTerm = rawTerms[i];
                } else {
                    lastTerm += " " + rawTerms[i]; // Si l'ingrédient contient des espaces
                }

            }

        }

        //Il faut gérer le dernier terme
        if (lastOperatorWasOr) {
            orTerms.add(lastTerm);
            entries.add(new SearchEntry(orTerms.toArray(new String[orTerms.size()]), searchModifier.OU));
        } else {
            entries.add(new SearchEntry(lastTerm, searchModifier.ET));
        }



        return (SearchEntry[]) entries.toArray(new SearchEntry[entries.size()]);

    }

    public Recette getRecette(String id)
    {

        //TEMP VARIABLE
        int recipeId = -1;
        String name = "KRAFT DINEER";
        String categorie = "CANADIANA";
        String typeDePlat = "UN BOL DE QUELQUE CHOSE";
        Integer tempsDeCuisson = 5; // 5 minutes et pis that's it!
        Integer portions = 1; // Une portion tabarnouche, je partage pas
        Boolean favoris = true; // Ben c'est ben certain
        String description = "C'est bon en caleer";

        // READING HERE!





        return new Recette(recipeId, name,categorie,typeDePlat,tempsDeCuisson, portions, favoris, 0, description);
                //Catégorie et type de plat devraient avoir des enum quelques parts...
    }

    public void ajouterRecette(Recette nouvelleRecette)
    {

        try {
            FileOutputStream fileout = context.openFileOutput(FICHIER_A_LIRE, context.MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);


            outputWriter.write("# " + nextId);
            outputWriter.write(nouvelleRecette.getNom());
            outputWriter.write(nouvelleRecette.getCategorie());
            outputWriter.write(nouvelleRecette.getTypeDePlat());
            outputWriter.write(nouvelleRecette.getTempsDeCuisson());
            outputWriter.write(nouvelleRecette.getPortions());
            outputWriter.write(" null? "); //IMAGE
            outputWriter.write(nouvelleRecette.getDescription());
            if (nouvelleRecette.getFavori()) {
                outputWriter.write("true");
            } else {
                outputWriter.write("false");
            }

            Ingredient[] workingIngredients = nouvelleRecette.getIngredients();

            outputWriter.write("& " + workingIngredients.length);

            for (int i = 0; i < workingIngredients.length; i++) {
                outputWriter.write(workingIngredients[i].getNom()
                        + " | " + workingIngredients[i].getQuantite()
                        + " | " + workingIngredients[i].getUniteDeMesure()
                        + " | " + workingIngredients[i].estOptionnel());
            }

            String[] workingEtapes = nouvelleRecette.getEtapes();

            outputWriter.write("% " + workingEtapes.length);

            for (int i = 0; i < workingEtapes.length; i++) {
                outputWriter.write(workingEtapes[i]);
            }

            outputWriter.write(" "); // Espace additionel pour une meilleur lecture du fichier

            outputWriter.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
