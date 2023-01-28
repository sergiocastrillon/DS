package e1;

import java.util.ArrayList;
import java.util.List;

public class Pelicula {
    private final String titulo;
    private final float recaudacion;
    private final List<EquipoHumano> list = new ArrayList<>();

    Pelicula(String titulo, float recaudacion){
        this.titulo = titulo;
        this.recaudacion = recaudacion;
    }

    float getRecaudacion(){
        return recaudacion;
    }

    String getTitulo(){
        return titulo;
    }

    void addEquipo (EquipoHumano persona){
        String DNI = persona.getDNI();
        for(EquipoHumano i : list){
            if(DNI.equals(i.getDNI())) throw new IllegalArgumentException("No pueden haber dos personas" +
                    "con el mismo DNI / no se puede añadir dos veces a la misma persona");
        }
        list.add(persona);
    }


    String printSalaries(){
        float gasto = 0;
        StringBuilder str = new StringBuilder();
        for(EquipoHumano i : list){
            gasto += i.getSueldo();
            str.append(i.getNombre());
            str.append(" ");
            str.append(i.getApellido());
            str.append(" (");
            str.append(i.getCategoriaExtra());
            str.append("): ");
            str.append(i.getSueldo());
            str.append(" euro");
            str.append("\n");
        }
        str.append("The total payroll for ");
        str.append(this.titulo);
        str.append(" is ");
        str.append(gasto);
        str.append(" euro");
        return str.toString();
    }


    String printRoyalties(){
        StringBuilder str = new StringBuilder();
        EquipoTecnico tecnico;
        for(EquipoHumano i : list) {

            /*try { // Si se puede hacer el cast entonces estamos con un objeto de EquipoHumanoRoyal que tiene porcentaje
                EquipoHumanoRoyal royal = (EquipoHumanoRoyal) i;
            } catch (Exception e) {
                continue;
            }
            EquipoHumanoRoyal royal = (EquipoHumanoRoyal) i;*/
            if(i instanceof EquipoTecnico){
                 tecnico = (EquipoTecnico) i;
            }else continue;

            str.append(i.getNombre());
            str.append(" ");
            str.append(i.getApellido());
            str.append(" (");
            str.append(i.getCategoria());
            str.append("): ");
            str.append(tecnico.getPorcentaje() * recaudacion);
            str.append(" euro");
            str.append("\n");
        }
        str.replace(str.length()-1,str.length(),""); // Eliminar el \n que aparece en el último elemento
        return str.toString();
    }
}
