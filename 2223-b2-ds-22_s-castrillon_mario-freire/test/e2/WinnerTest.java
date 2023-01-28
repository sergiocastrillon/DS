package e2;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WinnerTest {

    private static final ArrayList<String> oglist = new ArrayList<>();

    @BeforeAll
    static void createTVRealityList(){

        oglist.add("1");
        oglist.add("2");
        oglist.add("3");
        oglist.add("4");
        oglist.add("5");
        //oglist.add("6");
    }


    @Test
    void exampleTest(){
        ArrayList<String> listreb = new ArrayList<>(oglist);
        TVRealityList tvlist = new TVRealityList(listreb);
        ArrayList<String> listcir = new ArrayList<>(oglist);
        tvlist.setIterator("Rebote");

        assertEquals("1",Winner.selectCandidate(tvlist,3));

        tvlist.setIterator("Circular");
        tvlist.changeList(listcir);

        assertEquals("1",Winner.selectCandidate(tvlist,4));
    }

    @Test
    void numbersTest(){
        // Añadimos más numeros para probar
        oglist.add("6");
        oglist.add("7");
        oglist.add("8");
        oglist.add("9");


        TVRealityList tvlist = new TVRealityList(oglist);
        tvlist.setIterator("Rebote");

        assertEquals("9",Winner.selectCandidate(tvlist,2));
        tvlist.changeList(oglist);
        assertEquals("8",Winner.selectCandidate(tvlist,3));
        tvlist.changeList(oglist);
        assertEquals("3",Winner.selectCandidate(tvlist,4));
        tvlist.changeList(oglist);
        assertEquals("9",Winner.selectCandidate(tvlist,5));
        tvlist.changeList(oglist);
        assertEquals("8",Winner.selectCandidate(tvlist,9));
        tvlist.changeList(oglist);
        assertEquals("9",Winner.selectCandidate(tvlist,1));

        tvlist.setIterator("Circular");

        tvlist.changeList(oglist);
        assertEquals("3",Winner.selectCandidate(tvlist,2));
        tvlist.changeList(oglist);
        assertEquals("1",Winner.selectCandidate(tvlist,3));
        tvlist.changeList(oglist);
        assertEquals("1",Winner.selectCandidate(tvlist,4));
        tvlist.changeList(oglist);
        assertEquals("9",Winner.selectCandidate(tvlist,1));
    }

    @Test
    void strangeCases(){
        ArrayList<String> list = new ArrayList<>();
        //list.add("Hola");
        TVRealityList tvlist = new TVRealityList(list);
        // k va a ser mayor que 0 con lista vacía asi que lanza excepción
        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist,0));
        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist));

        tvlist.addElement("Hola");

        assertEquals("Hola",Winner.selectCandidate(tvlist,1));
        assertEquals("Hola",Winner.selectCandidate(tvlist));

        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist,2));
        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist,-1));

        // Prueba con rebote
        tvlist.setIterator("rebote");

        assertEquals("Hola",Winner.selectCandidate(tvlist,1));
        assertEquals("Hola",Winner.selectCandidate(tvlist));

        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist,2));
        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist,-1));

        tvlist.deleteElement(0);

        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist,0));
        assertThrows(IllegalArgumentException.class, () -> Winner.selectCandidate(tvlist));
    }

}