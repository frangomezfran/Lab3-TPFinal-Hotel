package com.company.Personal;

import com.company.Habitacion;
import com.company.Hotel;
import com.company.TipoHabitacion;

public class Administrador extends Recepcionista{

    public Administrador(String nombre, String apellido,double sueldo,String turno, long dni,String contrasenia) {
        super(nombre, apellido, dni,sueldo,turno,contrasenia);

    }

    public void actualizarHabitacionesDisponibles(Habitacion habitacionNoDisponible)
    {

    }
    public void crearUnNuevoResepcionista(Hotel hotel,String nombre,String apellido,long dni,int sueldo,String turno,String contrasenia)
    {
        hotel.getListaEmpleados().add(new Recepcionista(nombre,apellido,dni,sueldo,turno,contrasenia));
    }

    public void crearUnNuevoAdministrador(Hotel hotel,String nombre,String apellido,long dni,int sueldo,String turno,String contrasenia)
    {
        hotel.getListaEmpleados().add(new Administrador(nombre,apellido,sueldo,turno,dni,contrasenia));
    }

    public void establecePreciosHabitaciones(Hotel hotel,TipoHabitacion tipo,double precio){

        for(Habitacion aux : hotel.getListaHabitaciones()){

            if(aux.getTipoHabitacion() == tipo){
                aux.setPrecio(precio);
            }
        }

    }

    @Override
    public String toString() {
        return super.toString();
    }
}