package com.company;

import com.company.Personal.Pasajero;
import com.company.Personal.Recepcionista;

import java.util.ArrayList;

public class Hotel {
    private String nombreHotel;
    private String direccion;
    private int categoria;
    private int cantidadEmpleados;
    private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
    private ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private ArrayList<Recepcionista> listaEmpleados = new ArrayList<>();
    private ArrayList<String> reseñasPasajeros = new ArrayList<>();

    public Hotel(String nombreHotel, String direccion, int categoria, int cantidadEmpleados) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public ArrayList<String> getReseñasPasajeros() {
        return reseñasPasajeros;
    }

    public void setReseñasPasajeros(ArrayList<String> reseñasPasajeros) {
        this.reseñasPasajeros = reseñasPasajeros;
    }

    public ArrayList<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    public void setListaPasajeros(ArrayList<Pasajero> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
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

}
