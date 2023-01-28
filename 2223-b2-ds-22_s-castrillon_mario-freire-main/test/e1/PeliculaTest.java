package e1;

import e1.Equipo.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaTest {

    private static final Pelicula p1 = new Pelicula("Piratas del Caribe", 1654000);
    private static final Pelicula p2 = new Pelicula("Shrek", 232456);

    @BeforeAll
    static void addPeople() {
        p1.addEquipo(new Interprete("Johnny","Deep","78654321A",678985487,
                "Estadounidense",200, Interprete.papeles.protagonist));
        p1.addEquipo(new Interprete("Sophia","Jones","56724321A",665385487,
                "Estadounidense",200, Interprete.papeles.secondary));
        p1.addEquipo(new Director("Maria","Lopez","67891234A",695992222,"Española",
                200,7));

        p1.addEquipo(new Doblador("Lucas", "Fernandez", "87612345S", 234534533,
                "Español", 90));

        p1.addEquipo(new Especialista("Celia", "Romero", "55512345S", 234535653,
                "Española",30, true));
        p1.addEquipo(new Especialista("Francisco", "Doval", "57805345X", 236779653,
                "Español",40, false));
        p1.addEquipo(new Musico("Sergio","Castrillon","54314587J",642566432,
                "Noruego",400));
        p1.addEquipo(new Guionista("Lorena","Martinez","24242453U",634213760,
                "Española",260,true));
        p1.addEquipo(new Productor("Jerry", "Bruckheimer", "36355544S", 875746554,
                "Estadounidense", 230));

        p2.addEquipo(new Productor("Jerry", "Bruckheimer", "36355544S", 875746554,
                "Estadounidense", 90));
    }


    @Test
    void getTituloTest() {
        assertEquals("Piratas del Caribe", p1.getTitulo());
        // assertNotEquals("Shrek", p1.getTitulo());
        assertEquals("Shrek", p2.getTitulo());
        // assertNotEquals("Shrek", p2.getTitulo());

    }
    @Test
    void getRecaudacionTest() {
        assertEquals(1654000, p1.getRecaudacion());
        // assertNotEquals(232456, p1.getRecaudacion());
        assertEquals(232456, p2.getRecaudacion());
        // assertNotEquals(232456, p2.getRecaudacion());

    }

    @Test
    void printSalariesTest() {

        assertEquals("""
                Johnny Deep (Actor protagonist): 120000.0 euro
                Sophia Jones (Actor secondary): 40000.0 euro
                Maria Lopez (Director, 7 years of experience): 27000.0 euro
                Lucas Fernandez (Dubber): 1800.0 euro
                Celia Romero (Stunt performer with extra for danger): 2200.0 euro
                Francisco Doval (Stunt performer): 1600.0 euro
                Sergio Castrillon (Musician): 24000.0 euro
                Lorena Martinez (Screenwriter, original screenplay): 22200.0 euro
                Jerry Bruckheimer (Producer): 20700.0 euro
                The total payroll for Piratas del Caribe is 259500.0 euro""",p1.printSalaries());

        assertEquals("""
                Jerry Bruckheimer (Producer): 8100.0 euro
                The total payroll for Shrek is 8100.0 euro""", p2.printSalaries());
    }
    @Test
   void printRoyaltiesTest() {

       assertEquals("""
               Maria Lopez (Director): 82700.0 euro
               Sergio Castrillon (Musician): 66160.0 euro
               Lorena Martinez (Screenwriter): 82700.0 euro
               Jerry Bruckheimer (Producer): 33080.0 euro""",p1.printRoyalties());
       assertEquals("Jerry Bruckheimer (Producer): 4649.12 euro", p2.printRoyalties());

   }

}