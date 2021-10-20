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
    @DisplayName("Test si le contructeur renvoi une exception si la valeur de la promo est négative")
    void valeurPromoNegative(){
        assertThrows(IllegalArgumentException.class, ()-> new Prix(Promo.SOLDE,-2));
    }

    @Test
    @DisplayName("Test le type de promo renvoyer par la méthode getTYpePromo()")
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
    @DisplayName("Test si une exception est bien lancée si la quantité minimum sont négatifs ou null")
    void quantiteMinNullNegative() {
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(0, 5.5));
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(-1, 5.5));
    }

    @Test
    @DisplayName("Test si une exception est bien lancée si la valeur est négative ou null")
    void valeurNullNegative(){
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(5, 0));
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.definirPrix(5, -5));
    }

    @Test
    @DisplayName("Test si la valeur renvoyée par la méthode est égale à la valeur attendue")
    void definirPrix() throws QuantiteNonAutoriseeException {
        prixAucune.definirPrix(6, 10);
        assertEquals(10, prixAucune.getPrix(6));
        //change le prix pour vérifier que ça reste correct
        prixAucune.definirPrix(6, 15);
        assertEquals(15, prixAucune.getPrix(6));
    }

    @Test
    @DisplayName("Test si une exception est lancée quand la quantité en paramètre est nulle")
    void exceptionGetPrix() {
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.getPrix(-5));
        assertThrows(IllegalArgumentException.class, ()-> prixAucune.getPrix(0));
    }

    @ParameterizedTest
    @DisplayName("Test si les prix renvoyés correspondent pour les valeurs inférieux à 10")
    @ValueSource(ints = {1,5,9})
    void valeurGetPrixMoinsDix(int quantite) throws QuantiteNonAutoriseeException {
        assertEquals(20, prixAucune.getPrix(quantite));
    }

    @ParameterizedTest
    @DisplayName("Test si les prix renvoyés correspondent pour les valeur supérieux ou égale à 10")
    @ValueSource(ints = {10,15,20,25})
    void valeurGetPrixPlusDix(int quantite) throws QuantiteNonAutoriseeException {
        assertEquals(10, prixAucune.getPrix(quantite));
    }

    @Test
    @DisplayName("Test si une excpetion est lancée")
    void valeurGetPrixPub(){
        assertThrows(QuantiteNonAutoriseeException.class, ()-> prixPub.getPrix(2));
        assertThrows(QuantiteNonAutoriseeException.class, ()-> prixSolde.getPrix(1));
    }

    @Test
    @DisplayName("Test si la méthode renvoie un clone et non le même objet")
    void testClone() {
        assertAll(()->assertNotSame(prixAucune, prixAucune.clone()),
                ()->assertNotSame(prixPub, prixPub.clone()),
                ()->assertNotSame(prixSolde, prixSolde.clone()));
    }

    @ParameterizedTest
    @DisplayName("Test si l'état des clones est égal à l'état de l'objet initial")
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