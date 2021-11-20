package peer2peer;

import java.util.HashSet;

public class Peer {

    private String adresse;
    private HashSet<Peer> connexions;

    public Peer(String adresse) {
        connexions = new HashSet<>();
        this.adresse = adresse;
    }

    //TODO: modifier cette méthode pour éviter le deadlock.
    public void connecter(Peer peer) {
        if(connexions.contains(peer)) {
            System.out.println(adresse + " est déjà connecté à " + peer.adresse);
            return;
        }
        connexions.add(peer);
        System.out.println(adresse + " est maintenant connecté à " + peer.adresse);
        peer.connecter(this);
    }
}
