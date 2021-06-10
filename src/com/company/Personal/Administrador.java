package com.company.Personal;

import com.company.Habitacion;
import com.company.Hotel;
import com.company.TipoHabitacion;

public class Administrador extends Recepcionista{

    public Administrador(String nombre, String apellido,double sueldo,String turno, long dni) {
        super(nombre, apellido, dni,sueldo,turno);

    }

    public void actualizarHabitacionesDisponibles(Habitacion habitacionNoDisponible)
    {

    }
    public void crearUnNuevoResepcionista(Hotel hotel,String nombre,String apellido,long dni,int sueldo,String turno)
    {
        hotel.getListaEmpleados().add(new Recepcionista(nombre,apellido,dni,sueldo,turno));
    }

    public void crearUnNuevoAdministrador(Hotel hotel,String nombre,String apellido,long dni,int sueldo,String turno)
    {
        hotel.getListaEmpleados().add(new Administrador(nombre,apellido,sueldo,turno,dni));
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