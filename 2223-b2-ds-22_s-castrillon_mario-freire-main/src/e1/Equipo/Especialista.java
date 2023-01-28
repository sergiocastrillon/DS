package e1.Equipo;

import e1.EquipoArtistico;
import e1.EquipoHumano;

public class Especialista extends EquipoArtistico {
    private final boolean escenasPeligrosas;

    public Especialista(String nombre, String apellido, String DNI, int telefono,
                 String nacionalidad, int horasTrabajadas, boolean pel) {
        super(nombre, apellido, DNI, nacionalidad, telefono, horasTrabajadas, 40);
        this.escenasPeligrosas = pel;
    }

    @Override
    public float getSueldo() {
        if (escenasPeligrosas) return 1000 + super.getSueldo();
        else return super.getSueldo();
    }

    @Override
    public String getCategoria() {
        return "Stunt performer";
    }

    @Override
    public String getCategoriaExtra() {
        if (escenasPeligrosas) return "Stunt performer with extra for danger";
        else return getCategoria();
    }
}

