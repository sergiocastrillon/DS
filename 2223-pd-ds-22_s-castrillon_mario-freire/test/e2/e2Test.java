package e2;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class e2Test {
    Tank t1 = new Tank("Piscina de focas","Exterior");
    Tank t2 = new Tank("Piscina de delfines","Interior");

    Sensor oxigeno = new Sensor("Oxigeno");
    Sensor temp1 = new Sensor("Temperatura");
    Sensor temp2 = new Sensor("Temperatura");

    Range r1 = new Range(10,20);
    Range r2 = new Range(5,25);

    Range r3 = new Range(200,300);
    Range r4 = new Range(100,400);

    Range r5 = new Range(0,35);

    Alert atemp = new Alert("Control de temperatura",r1,r2);
    Alert atemp2 = new Alert("Control de temperatura",r2,r5);
    Alert aoxig = new Alert("Control de oxigeno",r3,r4);

    Actuator a = new Actuator();


    Staff p = new Staff();
    Staff p1 = new Staff();

    SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");


    @Test
    void testInforme() throws ParseException {

        Date d1 = f.parse("10-12-2022 18:02:43");

        p.fixDate(d1);
        p1.fixDate(d1);

        aoxig.setSensor(oxigeno);
        atemp.setSensor(temp1);
        atemp2.setSensor(temp1);

        t1.addSensor(oxigeno);
        t1.addSensor(temp1);

        atemp.attach(p);
        aoxig.attach(p);
        atemp2.attach(p1);
        atemp.attach(a); // Actuator
        temp1.setValue(15);
        temp1.setValue(21);

        assertEquals("* Orange alarm in tank Piscina de focas Temperatura sensor" +
                " with value: 21.0",a.getLastUpdate());

        oxigeno.setValue(250); // p
        oxigeno.setValue(500); // p
        oxigeno.setValue(350); // p
        temp1.setValue(27); // Naranja en p1 y roja en p

        assertEquals("* Red alarm in tank Piscina de focas Temperatura sensor" +
                " with value: 27.0",a.getLastUpdate());
        temp1.setValue(40);  // Roja en p1
        oxigeno.setValue(250);
        temp1.setValue(15);

        assertEquals("""
                Alertas de Mantenimiento Focas
                Alertas ROJAS:
                * Alerta ROJA
                Piscina de focas, Exterior
                Control de oxigeno: parámetro Oxigeno, nivel 500.0
                18:02:43 10-12-2022
                * Alerta ROJA
                Piscina de focas, Exterior
                Control de temperatura: parámetro Temperatura, nivel 27.0
                18:02:43 10-12-2022
                * Alerta ROJA
                Piscina de focas, Exterior
                Control de temperatura: parámetro Temperatura, nivel 40.0
                18:02:43 10-12-2022
                
                Alertas NARANJAS:
                * Alerta NARANJA
                Piscina de focas, Exterior
                Control de temperatura: parámetro Temperatura, nivel 21.0
                18:02:43 10-12-2022
                * Alerta NARANJA
                Piscina de focas, Exterior
                Control de oxigeno: parámetro Oxigeno, nivel 350.0
                18:02:43 10-12-2022
                """,p.getReport());


        assertEquals("""
                Alertas de Mantenimiento Focas
                Alertas ROJAS:
                * Alerta ROJA
                Piscina de focas, Exterior
                Control de temperatura: parámetro Temperatura, nivel 40.0
                18:02:43 10-12-2022
                
                Alertas NARANJAS:
                * Alerta NARANJA
                Piscina de focas, Exterior
                Control de temperatura: parámetro Temperatura, nivel 27.0
                18:02:43 10-12-2022
                """,p1.getReport());
    }
    // Probar a cambiar sensores y alertas de sitios

    @Test
    void testOperations(){

        t1.addSensor(oxigeno);
        atemp.setSensor(oxigeno);

        // Prueba borrar sensores de tanque y borrar alertas de sensores
        assertEquals(oxigeno,t1.sensorsList().get(0));

        t1.removeSensor(oxigeno);

        assertNull(oxigeno.getTank());

        assertEquals(oxigeno,atemp.getSensor());

        atemp.removeSensor();

        assertNull(atemp.getSensor());

        t1.addSensor(oxigeno);
        t2.addSensor(oxigeno);

        assertTrue(t1.sensorsList().isEmpty());
        assertEquals(oxigeno,t2.sensorsList().get(0));

        assertEquals(t2,oxigeno.getTank());

        atemp.setSensor(oxigeno);
        atemp.setSensor(temp1);

        assertTrue(oxigeno.getObserverList().isEmpty());
        assertEquals(temp1,atemp.getSensor());
        assertEquals(atemp,temp1.getObserverList().get(0));





    }
}