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
		if(participants.length==1 || participants.length==0) return false;
		Groupe groupe = new Groupe();
		for (int i = 0; i < participants.length; i++) {
			if(groupe.contient(participants[i].getMail())) return false;
			groupe.ajouter(participants[i]);
		}
		groupes.add(groupe);
		return true;
	}

	/**
	 * ajoute un membre au tirage
	 */
	public boolean ajouter(Participant participant) {
		//TODO
		//if(participant==null) return false;
		this.participants.add(participant);
		return true;
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
		return new TirageThread();
	}

	//TODO: partie 2 implémentez la classe TirageThread qui étend Thread

	public class TirageThread extends Thread{


		@Override
		public void run() {

		}

		public void tirage(Participant participant) throws TirageImpossibleException{

			Stack<Groupe> groupesDuParticipant = new Stack<>();
			ArrayList<Groupe> groupesTirage = new ArrayList<>();
			//Séparer les groupes où se trouve le participant des autres groupes pour le tirage.
			for (Groupe groupe : groupes) {
				if(groupe.contient(participant.getMail())){
					groupesDuParticipant.push(groupe);
				}else{
					groupesTirage.add(groupe);
				}
			}
			Random random = new Random();
			Groupe groupeTire = (Groupe) groupesTirage.get(random.nextInt(0, groupesTirage.size()-1));
			groupeTire.supprimer(null);
		}
	}
}
