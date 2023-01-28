package e2;

import java.util.ArrayList;
import java.util.List;

public class Tank {
    private final String name;
    private final String location;

    private final List<Sensor> sensors = new ArrayList<>();

    public Tank(String name, String location){
        this.name = name;
        this.location = location;
    }

    public void addSensor(Sensor sensor){
        sensor.changeTank(this);
        sensors.add(sensor);
    }

    public void removeSensor(Sensor sensor){
        sensor.removeTank();
        sensors.remove(sensor);
    }

    public ArrayList<Sensor> sensorsList(){
        // No queremos que manipulen la lista desde fuera
        return new ArrayList<>(sensors);
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }


}
