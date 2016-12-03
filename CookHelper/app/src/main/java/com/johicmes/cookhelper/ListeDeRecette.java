package com.johicmes.cookhelper;

import java.util.LinkedList;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class ListeDeRecette {

    //associations
    private RecipeBuilder recipeBuilder;
    private ListeBruteDeRecette listeBruteDeRecette;
    private VignetteDeRecherche[] vignetteDeRecherches;
    private ListeDeRecherche[] listeDeRecherches;

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

    public ListeDeRecette ()
    {

    }

    public void debuterCreation()
    {

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
    public void rechercher(String input)
    {




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

    public void compilerResultatsRecherche()
    {

    }

    public void compilerListeDeRecherche()
    {

    }

    public void ajouterVignette(VignetteDeRecherche vignette)
    {

    }

    public void afficherAide()
    {

    }

    public void choisirVignette()
    {

    }
}
