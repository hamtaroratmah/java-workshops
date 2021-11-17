package abonnements;

import java.util.ArrayList;
import java.util.List;


public class Compte {

    private double solde;
    private List<Depense> historique;

    public Compte(double solde) {
        this.solde = solde;
        historique = new ArrayList<>();
    }

    public void depenser(Depense depense) {
        try {
            Thread.sleep((long)(Math.random()*500)); //Ne pas modifier
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        solde -= depense.getMontant();
        historique.add(depense);
    }

    public double getSolde() {
        return solde;
    }

    public void verifier() {
        double total = 0;
        for(Depense d : historique) {
            System.out.println(d.getRaison() + " : -" + d.getMontant());
            total += d.getMontant();
        }
        System.out.println("==================================");
        System.out.println("Total dépensé : " + total);
        System.out.println("Solde restant (doit être positif) : " + solde);
    }
}
