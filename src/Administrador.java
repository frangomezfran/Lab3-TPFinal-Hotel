public class Administrador extends Recepcionista {

    public Administrador(String nombre, String apellido,double sueldo,String turno, long dni) {
        super(nombre, apellido, dni,sueldo,turno);

    }

    public void actualizarHabitacionesDisponibles(Habitacion habitacionNoDisponible)
    {

    }
    public Recepcionista crearUnNuevoResepcionista(String nombre,String apellido,long dni,int sueldo,String turno)
    {
        Recepcionista nuevoRecepcionista;
        return nuevoRecepcionista =new Recepcionista(nombre,apellido,dni,sueldo,turno);
    }
    public Administrador crearUnNuevoAdministrador(String nombre,String apellido,long dni,int sueldo,String turno)
    {
        Administrador nuevoAdministrador;
        return nuevoAdministrador =new Administrador(nombre,apellido,sueldo,turno,dni);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
