package cuisine;

import static util.Util.*;

public class IngredientQuantifie {
    private Ingredient ingredient;
    private int quantite;
    private Unite unite;

    public IngredientQuantifie(Ingredient ingredient, int quantite, Unite unite){

        checkObject(ingredient);
        checkPositive(quantite);
        checkObject(unite);
        this.ingredient=ingredient;
        this.quantite=quantite;
        this.unite=unite;
    }

    public Unite getUnite() {
        return unite;
    }

    public int getQuantite() {
        return quantite;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setUnite(Unite unite) {
        this.unite = unite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Quantité de "+ingredient+" nécessaire : "+ quantite + " " + unite;
    }
}
