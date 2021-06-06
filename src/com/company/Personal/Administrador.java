package com.company.Personal;

import com.company.Habitacion;



public class Administrador extends Recepcionista{

    public Administrador(String nombre, String apellido,double sueldo,String turno, long dni) {
        super(nombre, apellido, dni,sueldo,turno);

    }

    //con esta funcion se actualizara en el archivo json que habitaciones estan disponibles
    public void actualizarHabitacionesDisponibles(Habitacion habitacionNoDisponible)
    {

    }


    @Override
    public String toString() {
        return super.toString();
    }
}
