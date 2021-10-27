package domaine;

import domaine.AJ_seance07.workshop07.src.domaine.Moniteur;
import domaine.AJ_seance07.workshop07.src.domaine.SportImpl;

import java.util.Set;

public class SportStub implements SportImpl {
	
	private boolean contientMoniteur;
	
	public SportStub(boolean contientMoniteur) {
		this.contientMoniteur=contientMoniteur;
	}

	@Override
	public String getIntitule() {
		return null;
	}

	@Override
	public boolean ajouterMoniteur(Moniteur moniteur) {
		return true;
	}

	@Override
	public boolean supprimerMoniteur(Moniteur moniteur) {
		return true;
	}

	@Override
	public boolean contientMoniteur(Moniteur moniteur) {
		return this.contientMoniteur;
	}

	@Override
	public Set<Moniteur> moniteurs() {
		return null;
	}

}
