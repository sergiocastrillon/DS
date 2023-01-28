package e2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Staff implements Observer{

    private final List<StringBuilder> redAlerts = new ArrayList<>();
    private final List<StringBuilder> orangeAlerts = new ArrayList<>();


    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    private String dateTest = null;
    @Override
    public void update(Alert alert) {
        StringBuilder aux = new StringBuilder();
        Sensor sensor = alert.getSensor();
        Tank tank = sensor.getTank();

        aux.append(tank.getName());
        aux.append(", ");
        aux.append(tank.getLocation());
        aux.append("\n");
        aux.append(alert.getName());
        aux.append(": parámetro ");
        aux.append(sensor.getSensorType());
        aux.append(", nivel ");
        aux.append(sensor.getValue());
        aux.append("\n");

        if(dateTest == null){ // Si no estamos en test obtenemos la fecha
            aux.append(dateFormat.format(new Date(System.currentTimeMillis())));
        }else{
            aux.append(dateTest);
        }


        if(alert.isRed()) redAlerts.add(aux);
        if(alert.isOrange()) orangeAlerts.add(aux);

    }

    // SOLO usar para test, permite fijar la fecha y hora de las alertas para los assertEqual, no usar en otro caso
    public void fixDate(Date date){
        dateTest = dateFormat.format(date);
    }

    public String getReport(){
        StringBuilder aux = new StringBuilder();
        aux.append("Alertas de Mantenimiento Focas\n");
        aux.append("Alertas ROJAS:\n");
        while(!redAlerts.isEmpty()){
            aux.append("* Alerta ROJA\n");
            aux.append(redAlerts.get(0));
            aux.append("\n");
            redAlerts.remove(0);
        }

        aux.append("\nAlertas NARANJAS:\n");
        while(!orangeAlerts.isEmpty()){
            aux.append("* Alerta NARANJA\n");
            aux.append(orangeAlerts.get(0));
            aux.append("\n");
            orangeAlerts.remove(0);
        }
        return aux.toString();
    }
}
