package e1;

public abstract class EquipoArtistico extends EquipoHumano {
    public EquipoArtistico(String nombre, String apellido, String DNI, String nacionalidad, int telefono, int horasTrabajadas, int impPorHora) {
        super(nombre, apellido, DNI, nacionalidad, telefono, horasTrabajadas, impPorHora);
    }
}
