package compteur;

import java.util.concurrent.atomic.AtomicInteger;

public class CompteurRunnable implements Runnable {

    private String nom;
    private int max;

    // ajouter un attribut de classe qui retient l'ordre d'arrivée.
    private static AtomicInteger position= new AtomicInteger(0);

    @Override
    public void run() {
        // 1. Compter jusqu'à max en attendant 10 ms entre chaque incrémentation
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        // 2. Quand le compte est terminé, afficher que le compteur a finit de compter
        //         et indiquer son ordre d'arrivée.
        for (int i = 0; i < max; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(i==max-1){
                System.out.println(nom + " tour  " +(i+1));
            }
        }
        System.out.println(this.getNom() + " : " + this.setAndGetPosition());
    }

    public int setAndGetPosition(){
        return position.incrementAndGet();
    }

    public CompteurRunnable(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

}