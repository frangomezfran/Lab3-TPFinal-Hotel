package com.company.Personal;

import com.company.Habitacion;
import com.company.Hotel;
import com.company.TipoHabitacion;

import java.util.ArrayList;

public class Administrador extends Recepcionista{

    public Administrador(String nombre, String apellido,double sueldo, long dni,String contrasenia) {
        super(nombre, apellido, dni,sueldo,contrasenia);
    }

    public void crearUnNuevoRecepcionista(Hotel hotel,String nombre,String apellido,long dni,double sueldo,String contrasenia) {
        hotel.getListaEmpleados().add(new Recepcionista(nombre,apellido,dni,sueldo,contrasenia));
    }

    public void crearUnNuevoAdministrador(Hotel hotel,String nombre,String apellido,long dni,double sueldo,String contrasenia) {
        hotel.getListaEmpleados().add(new Administrador(nombre,apellido,sueldo,dni,contrasenia));
    }

    public void establecePrecioHabitacion(Habitacion habitacion,double precio){

        habitacion.setPrecio(precio);
    }

    public void establecePrecioHabitacionPorTipo(Hotel hotel,TipoHabitacion tipoHabitacion,double precio){

        for(Habitacion aux : hotel.getListaHabitaciones()){
            if(aux.getTipoHabitacion().equals(tipoHabitacion)){
                aux.setPrecio(precio);
            }
        }

    }

    public void eliminarPasajero(Hotel hotel,long dni){

        for(Pasajero aux : hotel.getListaPasajeros()){
            if(aux.getDni()==dni){
                hotel.getListaReservas().remove(aux);
            }
        }
    }

    public void modificaEmpleado(Recepcionista empleado, String nombre, String apellido,long dni,double sueldo,String contrasenia){

        if(!empleado.getNombre().equals(nombre))
            empleado.setNombre(nombre);

        if(!empleado.getApellido().equals(apellido))
            empleado.setApellido(apellido);

        if(empleado.getDni()!=dni)
            empleado.setDni(dni);

        if(empleado.getSueldo()!=sueldo)
            empleado.setSueldo(sueldo);

        if(!empleado.getContrasenia().equals(contrasenia))
            empleado.setContrasenia(contrasenia);

    }

    public void verEmpleados(ArrayList<Recepcionista> empleados){

        for (Recepcionista aux : empleados){
            System.out.println(aux.toString());
        }
    }

    @Override
    public String toString() {
        return  "\nAdministrador :\n" +
                "Nombre: "+getNombre()+" | Apellido: "+getApellido()+"\n" +
                "DNI: "+getDni()+" | Sueldo: "+getSueldo()+"\n" +
                "Contrasenia: "+getContrasenia();

    }
}