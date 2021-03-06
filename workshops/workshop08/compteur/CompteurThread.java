package compteur;

public class CompteurThread extends Thread {

    private final String nom;
    private final int max;

    //Cette variable de classe permet de retenir quel CompteurThread
    //a fini de compter le premier.
    private static CompteurThread gagnant;

    public CompteurThread(String nom, int max) {
        this.nom = nom;
        this.max = max;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public void run() {
        //TODO: 1. Compter jusqu'à max en attendant 10 ms entre chaque incrémentation
        //         A chaque incrémentation, afficher le nom du compteur et son compte actuel.
        //      2. Quand le compte est terminé, afficher que le compteur a finit de compter.
        //         Si le gagnant actuel est null, attendre 10 ms et mettre le gagnant à this,
        //         puis afficher que ce thread est le gagnant.
        for (int i=0; i<max; i++){
            synchronized (CompteurThread.class){

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(i==max-1){
                    System.out.println(nom + " est arrivé(e) !");
                }
            }
            this.gagnant=this;
        }//TODO affichage incorrect
    }

    public static CompteurThread getGagnant() {
        return gagnant;
    }
}
