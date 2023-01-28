package e1.Equipo;


import e1.EquipoArtistico;
import e1.EquipoHumano;

public class Interprete extends EquipoArtistico {
    public enum papeles {protagonist,secondary,extra}

    private final papeles papel;
    public Interprete(String nombre, String apellido, String DNI, int telefono,
                      String nacionalidad, int horasTrabajadas, papeles papel){

        super(nombre,apellido,DNI,nacionalidad,telefono,horasTrabajadas,200);
        this.papel = papel;
    }

    @Override
    public float getSueldo() {
        if(papel == papeles.protagonist) return 3 * super.getSueldo();
        else return super.getSueldo();
    }

    @Override
    public String getCategoria() {
        return "Actor";
    }

    @Override
    public String getCategoriaExtra(){
        return "Actor " + papel.toString();
    }

}
