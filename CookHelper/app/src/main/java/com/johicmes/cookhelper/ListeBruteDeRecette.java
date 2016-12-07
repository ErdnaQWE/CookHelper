package com.johicmes.cookhelper;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private String FICHIER_NEXTID = "recetteDatabaseNext.txt";

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

        public boolean matches(String input) {

            for (int i = 0; i < values.length; i++) {
                if (values[i].equals(input)) {
                    return true;
                }
            }

            return false;

        }

    }


    public ListeBruteDeRecette (Context context) throws IOException {
        this.context = context;

        File file = context.getFileStreamPath(FICHIER_NEXTID);
        if(file == null || !file.exists()) {
            FileOutputStream fileout = new FileOutputStream(file);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
            outputWriter.write("0");
            outputWriter.close();
        } else {
            FileInputStream fileIn = context.openFileInput(FICHIER_NEXTID);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIn);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            nextId = Integer.parseInt(reader.readLine());

            reader.close();
        }
    }

    public VignetteDeRecherche construireVignette(int id)
    {
        try {
            FileInputStream fileIn = context.openFileInput(FICHIER_A_LIRE);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIn);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String workingLine = reader.readLine();

            while (workingLine != null){

                if (workingLine.charAt(0) == '#') {
                    if (workingLine.substring(2).equals(Integer.toString(id))) { // Trouvé!!
                        String nom = reader.readLine();
                        String categorie = reader.readLine();
                        String typeDePlat = reader.readLine();

                        for (int i = 0; i < 5; i++) {
                            reader.readLine();
                        }

                        inputStreamReader.close();
                        return new VignetteDeRecherche(Integer.parseInt(workingLine.substring(2)), nom, categorie, typeDePlat, -1, Integer.parseInt(reader.readLine().substring(2)));

                    }
                }


            }

            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


     /*
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
        LinkedList<VignetteDeRecherche> resultat = new LinkedList<VignetteDeRecherche>();

        try {
            FileInputStream fileIn = context.openFileInput(FICHIER_A_LIRE);
            InputStreamReader inputStreamReader= new InputStreamReader(fileIn);

            BufferedReader reader = new BufferedReader(inputStreamReader);


            String workingLine = reader.readLine();
            String[] workingIngredient;
            int workingId = -2;
            int currentPertinence = 0;

            while (workingLine != null) {

                if (workingLine.charAt(1) == '#') { //Début d'une nouvelle recette
                    workingId = Integer.parseInt(workingLine.substring(2));

                    for (int i = 0; i < 8; i++) { // Saute aux ingrédients (8 lignes plus bas en concordance avec le modèle)
                        reader.readLine();
                    }

                    currentPertinence = 0;

                    while (true) {
                        workingLine = reader.readLine();

                        if (workingLine.charAt(1) == '%') {
                            break;
                        }
                        workingIngredient = workingLine.split(" | ");

                        for (int ii = 0; ii < entries.length; ii++) {
                            if (entries[ii].matches(workingIngredient[0].toLowerCase())) {
                                currentPertinence += 1;
                            }
                        }
                    }

                    if (currentPertinence > 0) { // On construit la vignette
                        resultat.add(construireVignette(workingId));
                    }

                }

                workingLine = reader.readLine();

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
                        orTerms.add(lastTerm);
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

    public Recette getRecette (int id)
    {

        String nom;
        String categorie;
        String typeDePlat;
        int tempsDeCuisson;
        int portions;
        int image;
        Boolean favoris;
        String description;

        Ingredient[] ingredients;
        String[] etapes;

        try {
            FileInputStream fileIn = context.openFileInput(FICHIER_A_LIRE);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIn);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String workingLine = reader.readLine();
            String[] workingIngredient;

            System.out.println("Entering loop!");
            while (workingLine != null){

                System.out.println(workingLine);

                if (workingLine.length() > 0) {

                    if (workingLine.charAt(0) == '#') {
                        System.out.println("Found?");
                        if (workingLine.substring(2).equals(Integer.toString(id))) { // Trouvé!!
                            nom = reader.readLine();
                            categorie = reader.readLine();
                            typeDePlat = reader.readLine();
                            tempsDeCuisson = Integer.parseInt(reader.readLine());
                            portions = Integer.parseInt(reader.readLine());
                            reader.readLine(); //Puisque image n'est pas implémenté encore
                            image = -1;
                            description = reader.readLine();

                            if (reader.readLine().toLowerCase().equals("true")) {
                                favoris = true;
                            } else {
                                favoris = false;
                            }

                            //Ingrédients
                            int nombreIngredients = Integer.parseInt(reader.readLine().substring(2));

                            ingredients = new Ingredient[nombreIngredients];

                            for (int i = 0; i < nombreIngredients; i++) {
                                workingLine = reader.readLine();
                                workingIngredient = workingLine.split(" | ");

                                boolean tmpOPT = false;

                                if (workingIngredient[3].equals("true")) {
                                    tmpOPT = true;
                                }

                                ingredients[i] = new Ingredient(workingIngredient[0], Double.parseDouble(workingIngredient[1]), workingIngredient[2], tmpOPT);
                            }

                            //Étapes

                            int nombreEtapes = Integer.parseInt(reader.readLine().substring(2));

                            etapes = new String[nombreEtapes];

                            for (int i = 0; i < nombreEtapes; i++) {
                                etapes[i] = reader.readLine();
                            }

                            inputStreamReader.close();
                            reader.close();

                            Recette newRecette = new Recette(id, nom,categorie,typeDePlat,tempsDeCuisson, portions, favoris, image, description);

                            newRecette.ajouterIngredient(ingredients);
                            newRecette.ajouterEtapes(etapes);

                            return newRecette;


                        }
                    }
                }
                workingLine = reader.readLine();

            }

            inputStreamReader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public int getNextId() { // Retourne le prochain id de recette à utiliser et l'incrémente pour utilisation future.
        nextId += 1;




        return nextId - 1;
    }

    public void ajouterRecette(Recette nouvelleRecette)
    {

        try {
            FileOutputStream fileout = context.openFileOutput(FICHIER_A_LIRE, context.MODE_APPEND);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);

            String ls = System.getProperty("line.separator");

            outputWriter.write(nouvelleRecette.getId());
            outputWriter.write(ls);
            outputWriter.write(nouvelleRecette.getNom());
            outputWriter.write(ls);
            outputWriter.write(nouvelleRecette.getCategorie());
            outputWriter.write(ls);
            outputWriter.write(nouvelleRecette.getTypeDePlat());
            outputWriter.write(ls);
            outputWriter.write(Integer.toString(nouvelleRecette.getTempsDeCuisson()));
            outputWriter.write(ls);
            outputWriter.write(Integer.toString(nouvelleRecette.getPortions()));
            outputWriter.write(ls);
            outputWriter.write(" null? "); //IMAGE
            outputWriter.write(ls);
            outputWriter.write(nouvelleRecette.getDescription());
            outputWriter.write(ls);
            if (nouvelleRecette.getFavori()) {
                outputWriter.write("true");
            } else {
                outputWriter.write("false");
            }
            outputWriter.write(ls);

            Ingredient[] workingIngredients = nouvelleRecette.getIngredients();

            outputWriter.write("& " + workingIngredients.length);
            outputWriter.write(ls);

            for (int i = 0; i < workingIngredients.length; i++) {
                outputWriter.write(workingIngredients[i].getNom()
                        + " | " + workingIngredients[i].getQuantite()
                        + " | " + workingIngredients[i].getUniteDeMesure()
                        + " | " + workingIngredients[i].estOptionnel());
                outputWriter.write(ls);
            }

            String[] workingEtapes = nouvelleRecette.getEtapes();

            outputWriter.write("% " + workingEtapes.length);
            outputWriter.write(ls);

            for (int i = 0; i < workingEtapes.length; i++) {
                outputWriter.write(workingEtapes[i]);
                outputWriter.write(ls);
            }

            outputWriter.write("@ " + nouvelleRecette.getId());
            outputWriter.write(ls);

            outputWriter.write(ls);

            outputWriter.close();
            System.out.println("Done!");


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void modifierRecette(Recette nouvelleRecette) {

        suprimmerRecette(nouvelleRecette.getId());
        ajouterRecette(nouvelleRecette);

    }

    public void suprimmerRecette(int id) {

        try {
            FileInputStream fileIn = context.openFileInput(FICHIER_A_LIRE);
            InputStreamReader inputStreamReader = new InputStreamReader(fileIn);

            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            String input = "";

            while ((line = reader.readLine()) != null) input += line + '\n'; // On lit le fichier au complet

            reader.close();

            //System.out.println(input);

            int indexDebut = input.indexOf("# " + id); // On retrouve l'entrée de la recette.
            int indexFin = input.indexOf("@ " + id);

            if (indexDebut != -1 && indexFin != -1) {

                String newInput = input.substring(0,indexDebut) + input.substring(indexFin + ("@ " + id).length()); //On enlève la vieille recette

                // On remplace le vieux fichier avec le nouveau
                FileOutputStream fileOut = context.openFileOutput(FICHIER_A_LIRE, context.MODE_PRIVATE);
                fileOut.write(input.getBytes());
                fileOut.close();
            }

        } catch (Exception e) {
            System.out.println("Problem reading file.");
        }

    }


}
