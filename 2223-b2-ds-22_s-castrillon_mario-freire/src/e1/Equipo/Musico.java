package e1.Equipo;

import e1.EquipoTecnico;

public class Musico extends EquipoTecnico {
    public Musico(String nombre, String apellido, String DNI, int telefono,
                  String nacionalidad, int horasTrabajadas){
        super(nombre,apellido,DNI,nacionalidad,telefono,horasTrabajadas,60,0.04);
    }
    @Override
    public String getCategoria() {
        return "Musician";
    }
}
