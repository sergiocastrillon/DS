package e2;

public class Actuator implements Observer{
    String last = null;
    @Override
    public void update(Alert alert) {
        StringBuilder b = new StringBuilder();
        if(alert.isRed()) b.append("* Red alarm in tank ");
        if(alert.isOrange()) b.append("* Orange alarm in tank ");
        b.append(alert.getSensor().getTank().getName());
        b.append(" ");
        b.append(alert.getSensor().getSensorType());
        b.append(" sensor with value: ");
        b.append(alert.getSensor().getValue());
        last = b.toString();
    }

    public String getLastUpdate(){
        return last;
    }
}
