package e1.Equipo;

import e1.EquipoTecnico;

public class Director extends EquipoTecnico {


    private final int aTrabajados;

    public Director(String nombre, String apellido, String DNI, int telefono,
                    String nacionalidad, int horasTrabajadas, int aTrabajados){
        super(nombre,apellido,DNI,nacionalidad,telefono,horasTrabajadas,100,0.05);
        this.aTrabajados = aTrabajados;
    }

    @Override
    public float getSueldo() {
        return super.getSueldo() + aTrabajados * 1000;
    }

    @Override
    public String getCategoria() {
        return "Director";
    }

    public String getCategoriaExtra(){
        return "Director, " + aTrabajados +" years of experience";
    }

}
