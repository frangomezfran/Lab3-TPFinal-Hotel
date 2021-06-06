package com.company;

import com.company.Personal.Pasajero;
import com.company.Personal.Recepcionista;

import java.util.ArrayList;

//solo crear un solo archivo .json el cual contenga todas las listas
public class Hotel {
    private String nombreHotel;
    private String direccion;
    private int categoria;//1 2 3 4 5 estrellas
    public  int cantidadEmpleados;
    private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
    private ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private ArrayList<Recepcionista> listaEmpleados = new ArrayList<>();
    private ArrayList<String> reseniasPasajeros = new ArrayList<>();

    public Hotel(String nombreHotel, String direccion, int categoria) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;

    }

    /***resenias pasajeros**/
    public ArrayList<String> getReseniasPasajeros() {
        return reseniasPasajeros;
    }

    public void aniadirElementoResenias(String resenia) {
        this.reseniasPasajeros.add(resenia);
    }

    /***lista pasajeros**/

    public  ArrayList<Pasajero> getListaPasajeros() {
        return this.listaPasajeros;
    }

    public  void aniadirListaPasajeros(Pasajero pasajero) {
        listaPasajeros.add(pasajero);
    }
    /***habitaciones**/
    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void aniadirElementoHabitacion(Habitacion habitacion) {
        this.listaHabitaciones.add(habitacion);
    }

    /***reservas**/
    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void aniadirListaReservas(Reserva reserva) {
        this.listaReservas.add(reserva);
    }
    /***empleados**/
    public ArrayList<Recepcionista> getListaEmpleados() {
        return listaEmpleados;
    }
    public void aniadirListaEmpleados(Recepcionista empleado) {
        this.listaEmpleados.add(empleado);
    }

    /***funciones para crear nuevos recepcionistas y administradores**/
    public void crearUnNuevoResepcionista(String nombre, String apellido, long dni,double sueldo,String turno)
    {
        this.cantidadEmpleados++;
        aniadirListaEmpleados(new Recepcionista(nombre,apellido,dni,sueldo,turno));
    }

    public void crearUnNuevoAdministrador(String nombre, String apellido,double sueldo,String turno, long dni)
    {
        this.cantidadEmpleados++;
        aniadirListaEmpleados(new Recepcionista(nombre,apellido,dni,sueldo,turno));

    }


    /***esta funcion nos permite retornar un String con toda la informacion del hotel**/
    @Override
    public String toString() {
        return "Hotel{" +
                "nombreHotel='" + nombreHotel + '\'' +
                ", direccion='" + direccion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", cantidad de empleados=" + cantidadEmpleados +
                ", listaHabitaciones=" + listaHabitaciones +
                ", listaPasajeros=" + listaPasajeros +
                ", listaReservas=" + listaReservas +
                ", listaEmpleados=" + listaEmpleados +
                ", reseniasPasajeros=" + reseniasPasajeros +
                '}';
    }
}
