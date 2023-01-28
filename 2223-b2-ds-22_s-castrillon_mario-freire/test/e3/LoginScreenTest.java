package e3;

import e3.LoginStrategies.EmailStrategy;
import e3.LoginStrategies.PhoneStrategy;
import e3.LoginStrategies.UsernameStrategy;
import e3.MFAStrategies.AuthApp;
import e3.MFAStrategies.OTPStrategy;
import e3.MFAStrategies.SMSStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginScreenTest {

    private static final User e1 = new User("sergio@gmail.com","sergio","674234454","1234",new AuthApp());
    private static final User e2 = new User("patricia@outlook.com","patricia","674244454","12345",new OTPStrategy());
    private static final User e3 = new User("sergio@outlook.com","sergio1","999999999","1234",new SMSStrategy());

    private static final DataBase data = new DataBase();
    @BeforeAll
    static void addUsers(){
        data.addUser(e1);
        data.addUser(e2);
        data.addUser(e3);
    }

    @Test
    void checkLogin(){
        // email
        LoginScreen screen = new LoginScreen(new EmailStrategy(data));
        assertNull(screen.authetication("sergio","1234"));
        assertNotNull(screen.authetication("sergio@gmail.com","1234"));
        assertNull(screen.authetication("674234454","1234"));

        assertNull(screen.authetication("patricia","12345"));
        assertNotNull(screen.authetication("patricia@outlook.com","12345"));
        assertNull(screen.authetication("674244454","12345"));

        assertNull(screen.authetication("patricia@outlook.com","1234"));
        assertNull(screen.authetication("sergio@gmail.com","12345"));

        screen.setLoginStrategy(new PhoneStrategy(data));

        assertNull(screen.authetication("sergio","1234"));
        assertNull(screen.authetication("sergio@gmail.com","1234"));
        assertNotNull(screen.authetication("674234454","1234"));

        assertNull(screen.authetication("patricia","12345"));
        assertNull(screen.authetication("patricia@outlook.com","12345"));
        assertNotNull(screen.authetication("674244454","12345"));

        assertNull(screen.authetication("674234454","12345"));
        assertNull(screen.authetication("674244454","1234"));

        screen.setLoginStrategy(new UsernameStrategy(data));


        assertNotNull(screen.authetication("sergio","1234"));
        assertNull(screen.authetication("sergio@gmail.com","1234"));
        assertNull(screen.authetication("674234454","1234"));

        assertNotNull(screen.authetication("patricia","12345"));
        assertNull(screen.authetication("patricia@outlook.com","12345"));
        assertNull(screen.authetication("674234454","12345"));

        // Contraseñas incorrectas
        assertNull(screen.authetication("sergio","12345"));
        assertNull(screen.authetication("patricia","1234"));


        // Cambio de método

        String a = screen.authetication("sergio","1234");
        int b;
        assertEquals(3,a.length());
        for(int x = 0; x < a.length();x++){ // Auth App
            b = a.codePointAt(x);
            assertTrue(b >= 48 && b <= 59);
        }

        assertFalse(screen.setMfaStrategy("sergio@gmail.com",new SMSStrategy()));
        assertFalse(screen.setMfaStrategy("maria",new AuthApp()));
        assertTrue(screen.setMfaStrategy("sergio",new SMSStrategy()));

        a = screen.authetication("sergio","1234");

        assertEquals(7,a.length());

        for(int x = 0; x<a.length(); x++){ // SMS
            b = a.codePointAt(x);
            if(x == 0 || x == a.length() - 1){
                assertTrue((b >= 65 && b <= 90) || (b >= 97 && b <= 122));
            }else{
                assertTrue(b >= 48 && b <= 59);
            }

        }

        screen.setLoginStrategy(new EmailStrategy(data));
        assertTrue(screen.setMfaStrategy("sergio@gmail.com",new AuthApp()));
        a = screen.authetication("sergio@gmail.com","1234");

        assertEquals(3,a.length());
        for(int x = 0; x < a.length();x++){ // Auth App
            b = a.codePointAt(x);
            assertTrue(b >= 48 && b <= 59);
        }

        screen.setLoginStrategy(new PhoneStrategy(data));
        assertTrue(screen.setMfaStrategy("674244454",new AuthApp()));

        assertEquals(3,a.length());
        for(int x = 0; x < a.length();x++){ // Auth App
            b = a.codePointAt(x);
            assertTrue(b >= 48 && b <= 59);
        }

        // Añadir usuario ya existente

        assertFalse(data.addUser(e1));

        // Borrado de usuarios
        assertFalse(data.deleteUser("sergio@gmail.com","123"));
        assertTrue(data.deleteUser("patricia","12345"));
        assertTrue(data.deleteUser("sergio@gmail.com","1234"));

        assertNull(screen.authetication("patricia","12345"));





    }

    @Test
    void notValidIds(){
        LoginStrategy l = new EmailStrategy(data);

        assertFalse(l.validateId("sergiogmail.com"));
        assertFalse(l.validateId("sergio@gmailcom"));
        assertFalse(l.validateId("@sergiogmail.com"));
        assertFalse(l.validateId("@sergio@gmail.com"));

        LoginStrategy l1 = new PhoneStrategy(data);
        assertFalse(l1.validateId("6742344544"));
        assertFalse(l1.validateId("6742a4454"));
        assertFalse(l1.validateId("67424454"));

        LoginStrategy l2 = new UsernameStrategy(data);

        assertFalse(l2.validateId("@sergio"));
        assertFalse(l2.validateId("/sergio"));
        assertFalse(l2.validateId("-sergio"));
        assertFalse(l2.validateId("s<ergio"));
        assertFalse(l2.validateId("castrillón"));

        assertTrue(l2.validateId("sergio1"));
        assertTrue(l2.validateId("s_ergio"));
        assertTrue(l2.validateId("s.castrillon"));
    }




    @Test
    void AuthApp(){
        MfaStrategy m = new AuthApp();
        String c = "a";
        int b;
        for(int i = 0; i<10; i++){
            String a = m.generateCode();

            assertNotEquals(c, a);
            assertEquals(3,a.length());
            for(int x = 0; x < a.length();x++){ // Todos son digitos
                b = a.codePointAt(x);
                assertTrue(b >= 48 && b <= 59);
            }
            c = a;
        }
    }


    @Test
    void SMSStrategy(){
        MfaStrategy m = new SMSStrategy();
        String c = "a";
        int b;
        for(int i = 0; i<500; i++){
            String a = m.generateCode();
            assertNotEquals(a,c);
            c = a;
            assertEquals(7,a.length());

            for(int x = 0; x<a.length(); x++){
                b = a.codePointAt(x);
                if(x == 0 || x == a.length() - 1){
                    assertTrue((b >= 65 && b <= 90) || (b >= 97 && b <= 122)); // Primer caracter
                }else{
                    assertTrue(b >= 48 && b <= 59);
                }

            }
        }
    }

    @Test
    void OTPStrategy(){
        MfaStrategy m = new OTPStrategy();
        String c = "a";
        int b,cont = 0;
        for(int i = 0; i<1000; i++){
            String a = m.generateCode();
            assertNotEquals(a,c);
            c = a;
            assertEquals(8,a.length());

            for(int x = 0; x<a.length(); x++){
                b = a.codePointAt(x);
                if(b>=65 && b<=90){
                    cont++;
                }else{
                    assertTrue(b >= 48 && b <= 57);
                }
            }
            assertEquals(2,cont);
            cont = 0;
        }
    }
}