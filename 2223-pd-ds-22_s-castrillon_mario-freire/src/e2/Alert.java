package e2;

import java.util.ArrayList;
import java.util.List;

public class Alert implements SensorObserver {
    private final String name;
    private final Range orange;
    private final Range red;

    private Sensor sensor;

    private AlertState state;

    private final List<Observer> observers = new ArrayList<>();

    public enum AlertState {None,Orange,Red}

    public Alert(String name,Range orange, Range red){
        if(orange.MIN() < red.MIN() || orange.MAX() > red.MAX())
            throw new IllegalArgumentException("Orange alert more restrictive than red");
        this.name = name;
        this.orange = orange;
        this.red = red;
    }
    public void update(Sensor sensor){
        double value = sensor.getValue();
        if(!red.isInRange(value)){
            state = AlertState.Red;
            notifyObservers();
            return;
        }
        if(!orange.isInRange(value)){
            state = AlertState.Orange;
            notifyObservers();
            return;
        }
        state = AlertState.None;
    }

    public void setSensor(Sensor sensor){
        if(this.sensor != null){
            this.sensor.detach(this);
        }
        sensor.attach(this);
        this.sensor = sensor;
    }

    public void removeSensor(){
        this.sensor.detach(this);
        this.sensor = null;
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }

    public void notifyObservers(){
        for(Observer o : observers){
           o.update(this);
        }
    }

    public boolean isRed(){
        return state == AlertState.Red;
    }
    public boolean isOrange(){
        return state == AlertState.Orange;
    }

    public Sensor getSensor(){
        return sensor;
    }

    public String getName(){
        return name;
    }
}
