package domaine;

import exceptions.DateDejaPresenteException;
import exceptions.PrixNonDisponibleException;
import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Prix prixPub;
    private Prix prixAucune;
    private Prix prixSolde;

    private Produit souris;
    private Produit ecran;

    LocalDate naissance;

    @BeforeEach
    void setUp() throws DateDejaPresenteException {
        //Prix
        prixAucune = new Prix();
        prixPub = new Prix(Promo.PUB, 20);
        prixSolde = new Prix(Promo.SOLDE, 20);

        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);
        prixPub.definirPrix(3,15);

        //Produit
        souris = new Produit("souris", "Razer", "Gaming");
        ecran = new Produit("�cran","BenQ", "TV");

        naissance = LocalDate.of(2001,4, 2);

        souris.ajouterPrix(LocalDate.now(), prixAucune);
        souris.ajouterPrix(naissance, prixPub);
        souris.ajouterPrix(LocalDate.MAX, prixSolde);

    }

    /*
        Test constructeur
     */

    @Test
    @DisplayName("Test si le constructeur lance bien une exception quand un param�tre est null ou est une chaine vide")
    void constructeurException(){
        assertAll(
                ()-> assertThrows(IllegalArgumentException.class, ()-> new Produit("   ", "razer","gaming")),
                ()-> assertThrows(IllegalArgumentException.class, ()-> new Produit("souris", "  ","gaming")),
                ()-> assertThrows(IllegalArgumentException.class, ()-> new Produit("souris", "razer","         ")),
                ()-> assertThrows(IllegalArgumentException.class, ()-> new Produit(null, "razer","gaming")),
                ()-> assertThrows(IllegalArgumentException.class, ()-> new Produit("souris", null,"gaming")),
                ()-> assertThrows(IllegalArgumentException.class, ()-> new Produit("souris", "razer",null))
        );
    }

    /*
        Test ajouterPrix() et getPrix()
     */

    @Test
    @DisplayName("Test si ajouterPrix() lance une exception avec un param�tre null")
    void ajouterPrix(){
        assertAll(
                ()-> assertThrows(IllegalArgumentException.class, ()->souris.ajouterPrix(null, prixAucune)),
                ()-> assertThrows(IllegalArgumentException.class, ()-> souris.ajouterPrix(LocalDate.now(), null))
        );
    }

    @Test
    @DisplayName("Test si une exception est lanc�e quand on veut ajouter une date d�j� pr�sente")
    void testDatePresente(){
        assertThrows(DateDejaPresenteException.class, ()-> souris.ajouterPrix(naissance, prixAucune));
    }

    @Test
    @DisplayName("Test si le prix est ajout� � la bonne date")
    void testAjoutDate() throws PrixNonDisponibleException, QuantiteNonAutoriseeException {
        assertEquals(prixPub.getPrix(5), souris.getPrix(naissance).getPrix(5));
    }

    @Test
    @DisplayName("Test si les valeurs renvoy� par getPrix() sont bien des clones")
    void getPrixClone() throws PrixNonDisponibleException {
        assertNotSame(prixPub, souris.getPrix(naissance));
    }

    @Test
    @DisplayName("Test si les prix sont clont� durant l'ajout")
    void prixCloneAjout() throws QuantiteNonAutoriseeException, PrixNonDisponibleException {
        assertNotSame(prixPub.getPrix(5), souris.getPrix(naissance).getPrix(5));
    }

    @Test
    @DisplayName("Test si une exeption est lanc�e quand le prix n'est pas disponible")
    void prixNonDispo() throws PrixNonDisponibleException {
        assertThrows(PrixNonDisponibleException.class, ()->souris.getPrix(LocalDate.MIN));
    }

    @Test
    @DisplayName("Test si une exception est lanc�e quand le prix  n'existe pas")
    void prixInexistant(){
        assertThrows(PrixNonDisponibleException.class, ()->ecran.getPrix(naissance));
    }

    @Test
    @DisplayName("Test si lorsqu'on demande le prix � une certaine date que �a soit la date ant�rieure qui soit renvoy�e")
    void dateAnterieur() throws PrixNonDisponibleException, QuantiteNonAutoriseeException {
        assertEquals(prixPub.getPrix(5), souris.getPrix(LocalDate.of(2003,7,4)).getPrix(5));
    }

    /*
        Test equals() et hashcode()
    */

    @Test
    @DisplayName("Test si equals fonctionne pour deux objets diff�rents ayant le m�me �tat")
    void equalsSame(){
        assertEquals(true, souris.equals(new Produit("souris", "Razer", "Gaming")));
    }

    @Test
    @DisplayName("Test si equals return false si un des attributs n'est pas identique")
    void equalsNotSame(){
        assertAll(
                ()-> assertEquals(false, souris.equals(new Produit("clavier", "Razer", "Gaming"))), //produit diff�rent
                ()-> assertEquals(false, souris.equals(new Produit("souris", "samsung", "Gaming"))), //marque diff�rente
                ()-> assertEquals(false, souris.equals(new Produit("souris", "Razer", "sport"))) //rayon diff�rent
        );
    }

    @Test
    @DisplayName("Test si hashcode return true pour deux objets diff�rents ayant le m�me �tat")
    void hashcodeSame(){
        assertAll(
                ()-> assertTrue(souris.hashCode()==(new Produit("souris", "Razer", "Gaming").hashCode())),
                ()-> assertFalse( souris.hashCode()==(new Produit("souris", "Razer", "gaming").hashCode()))
        );

    }

    /*
        Test clone()
     */

    @Test
    @DisplayName("Test si le clone est �gale � l'objet initial")
    void cloneIdentique(){
        assertEquals(souris, souris.clone());
    }

    @Test
    @DisplayName("Test si le clone n'a pas la m�me adresse m�moire que l'objet initial")
    void cloneNouvelObjet(){
        assertNotSame(souris, souris.clone());
    }

    @Test
    @DisplayName("Test si le prix du clone est �gale au prix de l'objet initial")
    void clonePrix() throws PrixNonDisponibleException, QuantiteNonAutoriseeException {
        assertEquals(souris.getPrix(LocalDate.now()).getPrix(5), souris.clone().getPrix(LocalDate.now()).getPrix(5));
    }

    @Test
    @DisplayName("Test si un objet ajout� dans le clone ne se trouve pas dans l'objet initial")
    void cloneAjout() throws DateDejaPresenteException, PrixNonDisponibleException, QuantiteNonAutoriseeException {
        Produit clone = souris.clone();
        LocalDate temp = LocalDate.of(2021,7,27);
        clone.ajouterPrix(temp, prixAucune);
        assertNotEquals(souris.getPrix(temp).getPrix(20), clone.getPrix(temp).getPrix(20));
    }

}