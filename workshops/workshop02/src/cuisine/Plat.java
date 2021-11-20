package cuisine;

import static util.Util.*;
import java.time.Duration;
import java.util.*;

public class Plat{

    public enum Difficulte implements Cloneable{
        X,XX,XXX,XXXX,XXXXX;

        @Override
        public String toString() {
            return super.toString().replace("X","*");
        }
    }

    public enum Cout{
        $,$$,$$$,$$$$,$$$$$;

        @Override
        public String toString() {
            return super.toString().replace("$","€");
        }
    }

    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> recette;
    private Set<IngredientQuantifie> ingredients;

    public Plat(String nom, int nbPersonnes, Difficulte niveauDeDifficulte, Cout cout) {
        checkString(nom);
        checkPositive(nbPersonnes);
        checkObject(niveauDeDifficulte);
        checkObject(cout);
        this.nom = nom;
        this.nbPersonnes = nbPersonnes;
        this.niveauDeDifficulte = niveauDeDifficulte;
        this.cout = cout;
        this.dureeEnMinutes=Duration.ofMinutes(0);
        this.recette = new ArrayList<>();
        this.ingredients = new HashSet<>();
    }

    public String getNom() {
        return nom;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    //Gestion des instructions (recette)

    public void insererInstruction(int position, Instruction instruction){
        checkObject(instruction);
        checkPositive(position);
        recette.add(position-1, instruction);
    }

    public void ajouterInstruction(Instruction instruction){
        checkObject(instruction);
        recette.add(instruction);
    }

    public Instruction remplacerInstruction(int position, Instruction instruction){
        checkObject(instruction);
        checkPositive(position);
        Instruction temp = recette.get(position-1);
        recette.remove(position-1);
        recette.add(position-1, instruction);
        return temp;
    }

    public Instruction supprimerInstruction(int position){
        checkPositive(position);
        return recette.remove(position-1);
    }

    public Iterator<Instruction> instructions(){
        return new Iterator<Instruction>() {

            private int count=0;
            @Override
            public boolean hasNext() {
                return count> recette.size();
            }

            @Override
            public Instruction next() {
                count++;
                return recette.get(count-1);
            }
        };
    }

    //Gestion des ingredients

    public boolean ajouterIngredient(Ingredient ingredient, int quantite, Unite unite){
        checkObject(ingredient);
        checkObject(unite);
        checkPositive(quantite);
        IngredientQuantifie temp = new IngredientQuantifie(ingredient, quantite, unite);
        return ingredients.add(temp);
    }

    public boolean ajouterIngredient(Ingredient ingredient, int quantite){
        checkObject(ingredient);
        checkPositive(quantite);
        IngredientQuantifie temp = new IngredientQuantifie(ingredient, quantite, Unite.NEANT);
        return ingredients.add(temp);
    }

    public boolean modifierIngredient(Ingredient ingredient, int quantite, Unite unite){
        checkObject(ingredient);
        checkObject(unite);
        checkPositive(quantite);
        if(ingredients.contains(ingredient)){
            IngredientQuantifie temp = new IngredientQuantifie(ingredient, quantite, unite);
            ingredients.remove(ingredient);
            ingredients.add(temp);
            return true;
        }
        return false;
    }

    public boolean supprimerIngredient(Ingredient ingredient){
        checkObject(ingredient);
        return ingredients.remove(ingredient);
    }

    public IngredientQuantifie trouverIngredientQuantifie(Ingredient ingredient){
        checkObject(ingredient);
        for(IngredientQuantifie ingredientQuantifie : ingredients){
            if(ingredientQuantifie.getIngredient().equals(ingredient)){
                return ingredientQuantifie;
            }
        }
        return null;
    }

    public String toString(){ //TODO régler l'affichage de la durée totale de la recette
        String aRenvoyer = nom + "\n\nPour " + nbPersonnes + " personnes\nDifficulté : " + niveauDeDifficulte+"\nCoût : "+
                cout+"\nDurée : "+dureeEnMinutes.toHours()+" h "+dureeEnMinutes.toMinutes()+" m \n\nIngrédients : ";
        for (IngredientQuantifie ingredientQuantifie : ingredients) {
            aRenvoyer += "\n"+ingredientQuantifie.getQuantite()+" "+ingredientQuantifie.getUnite()+" "+ingredientQuantifie.getIngredient().getNom();
        }
        aRenvoyer +="\n";
        for (int i=0; i<recette.size(); i++) {
            aRenvoyer += "\n"+(i+1)+". "+recette.get(i).toString();
        }
        return aRenvoyer;
    }

}