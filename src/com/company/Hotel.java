package com.company;

import com.company.Personal.Pasajero;
import com.company.Personal.Recepcionista;

import java.util.ArrayList;

public class Hotel {
    private String nombreHotel;
    private String direccion;
    private int categoria;//1 2 3 4 5 estrellas
    public static int cantidadEmpleados;
    private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
    private ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    public static ArrayList<Recepcionista> listaEmpleados = new ArrayList<>();
    private ArrayList<String> reseniasPasajeros = new ArrayList<>();

    public Hotel(String nombreHotel, String direccion, int categoria) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;

    }

    public ArrayList<String> getReseniasPasajeros() {
        return reseniasPasajeros;
    }

    public void aniadirElementoResenias(String resenia) {
        this.reseniasPasajeros.add(resenia);
    }

    public ArrayList<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    public void aniadirElementoPasajero(Pasajero pasajero) {
        this.listaPasajeros.add(pasajero);
    }

    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }

    public void aniadirElementoHabitacion(Habitacion habitacion) {
        this.listaHabitaciones.add(habitacion);
    }


    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void aniadirListaReservas(Reserva reserva) {
        this.listaReservas.add(reserva);
    }

    public ArrayList<Recepcionista> getListaEmpleados() {
        return listaEmpleados;
    }
    public void aniadirListaEmpleados(Recepcionista empleado) {
        this.listaEmpleados.add(empleado);
    }

/*
*
*   public boolean buscarReservaPasajero(Pasajero pasajero){
        for(Pasajero auxPasajero: this.listaPasajeros){ //La idea es comparar el pasajero de la lista de pasajeros  con los pasajeros de lista de reservas
            if(auxPasajero.getNombre().equals(this.listaReservas.get(auxPasajero).));// getNombrePasajero es temporal hasta saber el metodo de obtener pasajero en reserva.
            return true;
        }
        return false;
    }
*
*
* */

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
