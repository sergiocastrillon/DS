package e1;

// Las clases de Equipo Artístico heredan de aquí porque no tienen ningún parámetro extra
public abstract class EquipoHumano {
    private final String nombre;
    private final String apellido;
    private final String DNI;
    private final int telefono;
    private final String nacionalidad;

    private final int impPorHora;
    private final int horasTrabajadas;

    // Error si el DNI no contiene la letra o los carácteres necesarios
    // Como la variable impPorHora necesita ser cambiada por las subclases y que la función getSueldo
    // definida en esta clase siga funcionando, no podemos declarar impPorHora como static y
    // nos la deben pasar por el constructor
    public EquipoHumano(String nombre, String apellido, String DNI, String nacionalidad, int telefono, int horasTrabajadas
                        , int impPorHora) {
        if (nombre == null || apellido == null || DNI == null)
            throw new IllegalArgumentException("null no permitido en el campo o campos");
        if (horasTrabajadas < 0) throw new IllegalArgumentException("Las horas no pueden ser menores que 0");
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.horasTrabajadas = horasTrabajadas;
        this.impPorHora = impPorHora;

    }

    // void setEquipo(){}

    public String getNombre() {
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public String getDNI() {
        return DNI;
    }

    public int getTelefono() {
        return telefono;
    }

    public float getSueldo() {
        return horasTrabajadas * impPorHora;
    }

    public abstract String getCategoria();

    public String getCategoriaExtra(){
        return getCategoria();
    }

    public String getNacionalidad(){
        return nacionalidad;
    }

}