package com.johicmes.cookhelper;

/**
 * Created by Daviiiiid on 2016-12-01.
 */
public class Recette {

    private String nom;
    private String categorie;
    private String typeDePlat;
    private int tempsDeCuisson;
    private int portions;
    private boolean favoris;
    private int image;

    //associations
    Ingredient[] ingredients;
    RecipeBuilder recipeBuilder; //je ne suis pas sûr pour cette association -> Je pouvais pas mettre des one-way dans USE, enlève-le
    ListeBruteDeRecette listeBruteDeRecette;

    //constructeur de ListeBruteDeRecette
    public Recette (VignetteDeRecherche vignette)
    {
        nom = vignette.getNom();
        categorie = vignette.getCategorie();
        typeDePlat = vignette.getTypeDePlat();
        image = vignette.getImage();
        // chuis pas sûr comment j'accède les données en mémoire. -> Passé lors de la construction, working on that, utilise des fausses valeurs pour maintenant
        tempsDeCuisson = 0;
        portions = 0;
        favoris = false;
        ingredients = new Ingredient[vignette.getNumIngredient()];
    }

    public void afficher()//ca fait quoi afficher? -> On avait besoin de mettre sa pour les diagrammes de séquence, c'est pour afficher la recette dans l'activity, sa se peut que tu va pas l'utiliser though
    {
        // Probablement juste se rajouter au activity ou passer à travers le adapter ou quelque chose comme sa.
    }

    public void ajouterIngredient() // me semble que ça c'est pour recipe builder -> Oui, mais faut tu construit les ingrédients et que tu les donne a recette, tu va avoir besoin de cette fonction
    {
// SUP DAVE
    }

    //getters
    public int getImage()
    {
        return image;
    }

    public String getNom()
    {
        return nom;
    }
}
