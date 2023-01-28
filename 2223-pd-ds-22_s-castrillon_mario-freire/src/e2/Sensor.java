package e2;

import java.util.ArrayList;
import java.util.List;

public class Sensor {


    private final String sensorType;
    private double value;
    private final List<SensorObserver> observers = new ArrayList<>();

    private Tank tank = null;

    public Sensor(String sensorType){
        this.sensorType = sensorType;
    }

    public void setValue(double value){
        if(tank == null) throw new IllegalStateException("No se puede cambiar el valor de un sensor sin tanque");
        this.value = value;
        notifyObservers();
    }

    public void notifyObservers(){
        for(SensorObserver o : observers){
            o.update(this);
        }
    }
    public void changeTank(Tank tank){
        if(this.tank != null) this.tank.removeSensor(this);
        this.tank = tank;
    }

    public void removeTank(){
        tank = null;
    }
    public double getValue(){
        return value;
    }

    public Tank getTank(){
        return tank;
    }


    public String getSensorType(){
        return sensorType;
    }
    public void attach(SensorObserver observer){
        observers.add(observer);
    }

    public void detach(SensorObserver observer){
        observers.remove(observer);
    }

    public List<SensorObserver> getObserverList(){
        return observers;
    }

}
