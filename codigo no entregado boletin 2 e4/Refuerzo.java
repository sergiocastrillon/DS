package e4;

public class Refuerzo extends Algoritmo{
    private int interaccion; // Que es?
    public Refuerzo(String name, ubicaciones ubicacion, String libreria, float potencia,int interaccion) {
        super(name, ubicacion, libreria, potencia);
        this.interaccion = interaccion;
    }
    @Override
    public float emisionesAlgoritmo(){
        return super.emisionesAlgoritmo() * interaccion;
    }
}
