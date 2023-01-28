package e4;

import java.util.ArrayList;
import java.util.List;

public class Proyecto {
    private final String nombre;
    private final String organizacion;

    private List<Algoritmo> algos = new ArrayList<>();

    Proyecto(String nombre, String organizacion){
        if(nombre == null || organizacion == null) throw new IllegalArgumentException();
        this.nombre = nombre;
        this.organizacion = organizacion;
    }

    public void addAlgorithm(Algoritmo algo){
        algos.add(algo);
    }

    public String printAlgorithms(){
        StringBuilder str = new StringBuilder();
        for(Algoritmo algo : algos){
            str.append(algo.getName());
            str.append("\n");
        }
        return str.toString();
    }

    public float calculateCO2Emissions(){
        int sum = 0;
        for(Algoritmo algo : algos){
            sum += algo.emisionesAlgoritmo();
        }
        return sum;
    }
}
