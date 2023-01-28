package e1.Equipo;

import e1.EquipoTecnico;

public class Productor extends EquipoTecnico {
    public Productor(String nombre, String apellido, String DNI, int telefono,
                     String nacionalidad, int horasTrabajadas){
        super(nombre,apellido,DNI,nacionalidad,telefono,horasTrabajadas,90,0.02);
    }



    @Override
    public String getCategoria() {
        return "Producer";
    }


}
