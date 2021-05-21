package com.company.Personal;

public class Administrador extends Persona{

    public Administrador(String nombre, String apellido, long dni) {
        super(nombre, apellido, dni);

    }

    public void actualizarHabitacionesDisponibles(Habitacion habitacionNoDisponible)
    {

    }
    public Recepcionista crearUnNuevoResepcionista(String nombre,String apellido,long dni,int sueldo,String turno)
    {
        Recepcionista nuevoRecepcionista;
        return nuevoRecepcionista =new Recepcionista(nombre,apellido,dni,sueldo,turno);
    }




}
