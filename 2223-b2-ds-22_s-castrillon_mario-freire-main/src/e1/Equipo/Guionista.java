package e1.Equipo;

import e1.EquipoTecnico;

public class Guionista extends EquipoTecnico {
    private final boolean guionOriginal;

    public Guionista(String nombre, String apellido, String DNI, int telefono,
                 String nacionalidad, int horasTrabajadas, boolean guionOriginal){
        super(nombre,apellido,DNI,nacionalidad,telefono,horasTrabajadas,70,0.05);
        this.guionOriginal = guionOriginal;
    }


    public float getSueldo() {
        if(guionOriginal) return 4000 + super.getSueldo();
        else return super.getSueldo();
    }

    @Override
    public String getCategoria() {
        return "Screenwriter";
    }

    @Override
    public String getCategoriaExtra(){
        if(guionOriginal) return "Screenwriter, original screenplay";
        else return getCategoria();
    }
}
