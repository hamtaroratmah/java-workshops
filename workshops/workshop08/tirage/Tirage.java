package tirage;

import java.util.*;

public class Tirage {

	private String nom;
	private Set<Participant> participants;
	private Set<Groupe> groupes;
	private final Stack<Participant> participantSansCacahuete = new Stack<>();
	private final Set<Participant> participantsTires = new HashSet<>();

	private Map<Participant, Participant> resultats;

	public Tirage(String nom) {
		this.nom = nom;
		participants = new HashSet<>();
		groupes = new HashSet<>();
	}

	public String getNom() {
		return this.nom;
	}

	public Set<Participant> getParticipants() {
		return Collections.unmodifiableSet(this.participants);
	}

	public Set<Groupe> getGroupes() {
		return Collections.unmodifiableSet(this.groupes);
	}

	public Map<Participant, Participant> getResultats() {
		return Collections.unmodifiableMap(this.resultats);
	}

	/**
	 * Ajoute les membres en paramètres dans un nouveau groupe. Les membres en
	 * paramètres doivent se trouver dans le tirage (si pas renvoyer false). Il ne
	 * peut y avoir de groupe de 1 membre. Un membre ne peut pas être deux fois dans
	 * le même groupe.
	 */
	public boolean ajouterGroupe(Participant... participants) {
		//TODO
		return false;
	}

	/**
	 * ajoute un membre au tirage
	 */
	public boolean ajouter(Participant participant) {
		//TODO
		return false;
	}

	/**
	 * Initialise correctement les structures de données pour préparer au tirage
	 * Efface les données du tirage précédent.
	 */
	public void preparerTirage() {
		for(Participant participant : participants) {
			participantSansCacahuete.push(participant);
		}
		participantsTires.clear();
		resultats = new HashMap<>();
	}

	/**
	 * Crée un thread dont le nom est fourni en paramètre
	 */
	public Thread creerThread() {
		//TODO
		return null;
	}

	//TODO: partie 2 implémentez la classe TirageThread qui étend Thread


}
