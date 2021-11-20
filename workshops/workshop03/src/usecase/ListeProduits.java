package usecase;

import domaine.Prix;
import domaine.Produit;
import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import exceptions.ProduitNonPresentException;

import java.time.LocalDate;
import java.util.Iterator;

public class ListeProduits {


	//TODO ajouter un attribut pour garder les produits


	/**
	 * Cette méthode renvoie vraie si le produit passé en paramètre est présent dans la liste
	 *
	 * @param p le produit recherché
	 * @return true si le produit est dans la liste, false sinon
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public boolean contient(Produit p) {
		//TODO complétez la méthode
		return false;
	}

	/**
	 * Cette méthode ajoute le produit si celui-ci ne s'y trouve pas encore
	 *
	 * @param p le produit à ajouter
	 * @return true si le produit a pu être ajouté, false sinon
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public boolean ajouterProduit(Produit p) {
		//TODO complétez la méthode
		return false;
	}

	/**
	 * Cette méthode supprime le produit si celui-ci est présent dans la liste
	 *
	 * @param p le produit à supprimer
	 * @return true si le produit a pu être supprimé, false sinon
	 * @throws IllegalArgumentException en cas de paramètre invalide
	 */
	public boolean supprimerProduit(Produit p) {
		//TODO complétez la méthode
		return false;
	}


	/**
	 * Cette méthode trouve le produit correspondant aux paramètres s'il existe et le renvoie
	 *
	 * @param nom    le nom du produit recherché
	 * @param marque la marque du produit recherché
	 * @param rayon  le rayon du produit recherché
	 * @return le produit s'il existe, null sinon
	 */
	public Produit trouverProduit(String nom, String marque, String rayon) {
		//TODO complétez la méthode
		return null;
	}

	/**
	 * Cette méthode renvoie un itérateur permettant de parcourir les produits
	 * Si l'on tente d'appeler la méthode remove() sur l'itérateur renvoyé, veillez à ce que cela lance une
	 * UnsupportedOperationException.
	 * TIPS ? voir p.18 de la théorie "collections" (wrappers méthodes)
	 * @return un itérateur sur les produits
	 */

	public Iterator<Produit> produits() {
		//TODO complétez la méthode
		return null;
	}


	/**
	 * Cette méthode ajoute un prix à appliquer à partir d'une certaine date au produit
	 *
	 * @param prod le produit auquel s'applique le prix
	 * @param date la date à partir de laquelle s'applique le prix
	 * @param prix le prix à appliquer
	 * @throws ProduitNonPresentException si le produit n'est pas dans la liste
	 * @throws DateDejaPresenteException  s'il y a déjà un prix pour cette date pour ce produit
	 * @throws IllegalArgumentException   en cas de paramètre invalide ou si le produit n'est pas présent
	 */
	public void ajouterPrix(Produit prod, LocalDate date, Prix prix) throws DateDejaPresenteException, ProduitNonPresentException {
		//TODO complétez la méthode
	}

	/**
	 * Cette méthode retrouve et renvoie le prix appliqué à un certain produit à une date donnée.
	 *
	 * @param prod le produit dont on cherche le prix
	 * @param date la date pour laquelle on veut connaître le prix
	 * @throws ProduitNonPresentException si le produit n'est pas dans la liste
	 * @throws PrixNonDisponibleException s'il n'y a de prix pour cette date pour ce produit
	 * @throws IllegalArgumentException   en cas de paramètre invalide ou si le produit n'est pas présent
	 */
	public Prix trouverPrix(Produit prod, LocalDate date) throws ProduitNonPresentException, PrixNonDisponibleException {
		//TODO complétez la méthode
		return null;
	}


	public String toString() {
		String detail = "";
		//TODO remplacer ??? pour parcourir les produits
		for (Produit p : ???){
			detail += p + "\n----------------------------------------------------------------------\n";
		}
		return detail;
	}

}
