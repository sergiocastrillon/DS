package e4;

public abstract class Algoritmo {
    private String name;

    public enum ubicaciones {Espana,Francia,Portugal,Alemania}
    private ubicaciones ubicacion; // Solo España,Francia,Portugal y Alemania setter
    private String libreria; // Que es libreria??
    private float potencia;

    public Algoritmo(String name, ubicaciones ubicacion,String libreria,float potencia){
        if(name == null || libreria == null || potencia <= 0) throw new IllegalArgumentException();
        this.name = name;
        this.ubicacion = ubicacion;
        this.libreria = libreria;
        this.potencia = potencia;
    }

    public float emisionesAlgoritmo(){
        int e;
        if(ubicacion == ubicaciones.Espana) e = 190;
        else if(ubicacion == ubicaciones.Portugal) e = 201;
        else if(ubicacion == ubicaciones.Francia) e = 55;
        else e = 301;

        return e * potencia;
    }

    public String getName(){
        return name;
    }
    public ubicaciones getUbicacion(){
        return ubicacion;
    }
}
