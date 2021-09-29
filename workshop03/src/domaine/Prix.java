package domaine;


import static util.Util.*;


import exceptions.QuantiteNonAutoriseeException;

public class Prix {

    private final Promo typePromo;
    private final double valeurPromo;

    //TODO Ajouter l'attribut pour garder les différents prix correspondant à une quantité minimale et l'initialiser


    public Prix() {
        typePromo = null;
        valeurPromo = 0;
    }

    public Prix(Promo promo, double valeurPromo) {
        checkObject(promo);
        checkPositiveOrZero(valeurPromo);
        this.typePromo = promo;
        this.valeurPromo = valeurPromo;
    }


    public Promo getTypePromo() {
        return typePromo;
    }

    public double getValeurPromo() {
        return valeurPromo;
    }

    /**
     * Cette méthode permet de définir le prix unitaire correspondant à une quantité minimale.
     * S'il existe déjà un prix correspondant à la quantité, il sera remplacé.
     *
     * @param quantiteMin
     * @param valeur      le prix unitaire à partir de cette quantité
     * @throws IllegalArgumentException en cas de quantité négative ou nulle ou en cas de valeur négative ou nulle
     */
    public void definirPrix(int quantiteMin, double valeur) {
        //TODO complétez la méthode
    }


    /**
     * Cette méthode renvoie le prix à appliquer selon la quantité achetée
     *
     * @param la quantité achetée
     * @return le prix unitaire pour cette quantité
     * @throws QuantiteNonAutoriseeException si la quantité est inférieure à la quantité minimale autorisée
     * @throws IllegalArgumentException      en cas de quantité négative ou nulle
     */
    public double getPrix(int quantite) throws QuantiteNonAutoriseeException {
        //TODO complétez la méthode
        return 0;
    }


    @Override
    public String toString() {
        String detail = "";
        if (typePromo != null) detail += "Promo : " + typePromo + " - " + valeurPromo + "\n";
        //TODO Ajoutez les différents prix en passant à la ligne entre chaque prix. Format attendu :
        /*
         * 99.9 euros à partir de 1 unités
         * 89.9 euros à partir de 3 unités
         * 59.9 euros à partir de 5 unités
         */

        return detail;
    }


}
