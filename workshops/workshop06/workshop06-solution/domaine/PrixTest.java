package domaine;

import exceptions.QuantiteNonAutoriseeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PrixTest {

    private Prix prixPub;
    private Prix prixAucune;
    private Prix prixSolde;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        prixAucune = new Prix();
        prixPub = new Prix(Promo.PUB, 20);
        prixSolde = new Prix(Promo.SOLDE, 20);

        prixAucune.definirPrix(1, 20);
        prixAucune.definirPrix(10, 10);
        prixPub.definirPrix(3,15);
    }

    @Test
    @DisplayName("Test si le contructeur renvoi une exception si la promo est nulle")
    void promoNull(){
        assertThrows(IllegalArgumentException.class, ()-> new Prix(null,20));
    }

    @Test
    @DisplayName("Test si le contructeur renvoi une exception si la valeur de la promo est n�gative")
    void valeurPromoNegative(){
        assertThrows(IllegalArgumentException.class, ()-> new Prix(Promo.SOLDE,-2));
    }

    @Test
    @DisplayName("Test le type de promo renvoyer par la m�thode getTYpePromo()")
    void getTypePromo() {
        assertEquals(Promo.PUB, prixPub.getTypePromo());
        assertEquals(Promo.SOLDE, prixSolde.getTypePromo());
        assertEquals(null, prixAucune.getTypePromo());
    }

    @Test
    @DisplayName("Test si la valeur de retour est celle attendue")
    void getValeurPromo() {
        assertEquals(0, prixAucune.getValeurPromo());
        assertEquals(20, prixSolde.getValeurPromo());
    }

    @Test
    @DisplayName("Test si une exception est bien lanc�e si la quantit� minimum sont n�gatifs ou null")
    void quantiteMinNullNegative() {
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(0, 5.5));
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(-1, 5.5));
    }

    @Test
    @DisplayName("Test si une exception est bien lanc�e si la valeur est n�gative ou null")
    void valeurNullNegative(){
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(5, 0));
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(5, -5));
    }

    @Test
    @DisplayName("Test si la valeur renvoy�e par la m�thode est �gale � la valeur attendue")
    void definirPrix() throws QuantiteNonAutoriseeException {
        prixAucune.definirPrix(6, 10);
        assertEquals(10, prixAucune.getPrix(6));
        //change le prix pour v�rifier que �a reste correct
        prixAucune.definirPrix(6, 15);
        assertEquals(15, prixAucune.getPrix(6));
    }

    @Test
    @DisplayName("Test si une exception est lanc�e quand la quantit� en param�tre est nulle")
    void exceptionGetPrix() {
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.getPrix(-5));
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.getPrix(0));
    }

    @ParameterizedTest
    @DisplayName("Test si les prix renvoy�s correspondent pour les valeurs inf�rieux � 10")
    @ValueSource(ints = {1,5,9})
    void valeurGetPrixMoinsDix(int quantite) throws QuantiteNonAutoriseeException {
        assertEquals(20, prixAucune.getPrix(quantite));
    }

    @ParameterizedTest
    @DisplayName("Test si les prix renvoy�s correspondent pour les valeur sup�rieux ou �gale � 10")
    @ValueSource(ints = {10,15,20,25})
    void valeurGetPrixPlusDix(int quantite) throws QuantiteNonAutoriseeException {
        assertEquals(10, prixAucune.getPrix(quantite));
    }

    @Test
    @DisplayName("Test si une excpetion est lanc�e")
    void valeurGetPrixPub(){
        assertThrows(QuantiteNonAutoriseeException.class, ()-> prixPub.getPrix(2));
        assertThrows(QuantiteNonAutoriseeException.class, ()-> prixSolde.getPrix(1));
    }

    @Test
    @DisplayName("Test si la m�thode renvoie un clone et non le m�me objet")
    void testClone() {
        assertAll(()->assertNotSame(prixAucune, prixAucune.clone()),
                ()->assertNotSame(prixPub, prixPub.clone()),
                ()->assertNotSame(prixSolde, prixSolde.clone()));
    }

    @ParameterizedTest
    @DisplayName("Test si l'�tat des clones est �gal � l'�tat de l'objet initial")
    @ValueSource(ints = {1,5,9,10,15,20,25})
    void testEtatClone(int quantite) throws QuantiteNonAutoriseeException{
        assertEquals(prixAucune.getPrix(quantite), prixAucune.clone().getPrix(quantite));
    }

    @Test
    @DisplayName("Test si le setter s'applique bien sur le clone et non l'original")
    void testSetterClone() throws QuantiteNonAutoriseeException {
        Prix clone = prixAucune.clone();
        clone.definirPrix(1,30);

        assertAll(()-> assertEquals(30, clone.getPrix(1)),
                ()->assertEquals(20, prixAucune.getPrix(1)));
    }
}