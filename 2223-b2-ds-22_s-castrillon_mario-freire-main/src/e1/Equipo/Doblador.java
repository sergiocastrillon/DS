package e1.Equipo;

import e1.EquipoArtistico;
import e1.EquipoHumano;

public class Doblador extends EquipoArtistico {

    public Doblador(String nombre, String apellido, String DNI, int telefono,
                    String nacionalidad, int horasTrabajadas){
        super(nombre,apellido,DNI,nacionalidad,telefono,horasTrabajadas,20);
    }



    @Override
    public String getCategoria() {
        return "Dubber";
    }
}
