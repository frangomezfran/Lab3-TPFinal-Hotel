package com.company;

import com.company.Personal.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Hotel {
    private String nombreHotel;
    private String direccion;
    private int categoria;//1 2 3 4 5 estrellas
    private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
    private ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    public ArrayList<Recepcionista> listaEmpleados = new ArrayList<>();


    //-------------- Constructor --------------
    public Hotel(String nombreHotel, String direccion, int categoria) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;
    }

    //-------------- Nombre Hotel --------------
    public String getNombreHotel() {
        return nombreHotel;
    }
    public void setNombreHotel(String nombreHotel) {
        this.nombreHotel = nombreHotel;
    }

    //-------------- Direccion --------------
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //-------------- Categoria --------------
    public int getCategoria() {
        return categoria;
    }
    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    //-------------- Lista de Pasajeros --------------
    public ArrayList<Pasajero> getListaPasajeros() {
        return listaPasajeros;
    }
    public void setListaPasajeros(ArrayList<Pasajero> listaPasajeros) {
        this.listaPasajeros = listaPasajeros;
    }

    //-------------- Lista de Habitaciones --------------
    public ArrayList<Habitacion> getListaHabitaciones() {
        return listaHabitaciones;
    }
    public void setListaHabitaciones(ArrayList<Habitacion> listaHabitaciones) {
        this.listaHabitaciones = listaHabitaciones;
    }

    //-------------- Lista de Reservas --------------
    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }
    public void setListaReservas(ArrayList<Reserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    //-------------- Lista de Empleados --------------
    public ArrayList<Recepcionista> getListaEmpleados() {
        return listaEmpleados;
    }
    public void setListaEmpleados(ArrayList<Recepcionista> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }


    //-------------- Metodos --------------
    public void agregaHabitacion(Habitacion habitacion) {
        this.listaHabitaciones.add(habitacion);
    }

    public void agregaReserva(Reserva reserva){
        //Las nuevas reservas se agregaran al principio
        this.listaReservas.add(0,reserva);
    }

    public void agregaEmpleado(Recepcionista empleado) {
        //Los nuevos empleados se agregaran al princio
        this.listaEmpleados.add(0,empleado);
    }

    public String historialDeUnPasajero (long dni){

        String reservasDeUnPasajero = "Reservas del Pasajero con DNI : "+dni+"\n";

        for(Reserva aux : listaReservas){
            if(aux.getPasajero().getDni() == dni)
                reservasDeUnPasajero += aux.toString() + "\n";
        }

        return reservasDeUnPasajero;
    }

    public Reserva retornaReservadelPasajero(long dni){

        for(Reserva aux : listaReservas){
            if(aux.getPasajero().getDni()==dni)
                return aux;//Las reservas estan ingresadas por la mas nueva a la mas vieja
        }
        return null; //Por x razon si no existe...
    }

    public boolean agregaNuevoPasajero (Pasajero pasajero){

        for(Pasajero aux : listaPasajeros){
            if(aux.getDni() == pasajero.getDni()){
                return false;
            }
        }
        listaPasajeros.add(pasajero);
        return true;
    }

    public void actualizaHabitacionesPorCheckOut(){ //Administrador?

        for(Reserva aux : listaReservas){
            if(aux.getHabitacion().getEstadoHabitacion() == EstadoHabitacion.RESERVADA
                    && aux.getCheckOut().isAfter(LocalDateTime.now())) {
                aux.getHabitacion().setEstadoHabitacion(EstadoHabitacion.DISPONIBLE);
                //Hay que llenar la lista de productos de la habitacion tmb
            }
        }
    }

    public Habitacion retornaHabitacion(char piso,int letra){
        for(Habitacion aux: listaHabitaciones){
            if(aux.getPiso() == piso && aux.getLetra()==letra){
                return aux;
            }
        }
        return null;
    }

    public Pasajero retornaPasajero(long dni){

        for(Pasajero aux: listaPasajeros){
            if(aux.getDni() == dni){
                return aux;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nombreHotel='" + nombreHotel + '\'' +
                ", direccion='" + direccion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", cantidad de empleados=" + listaEmpleados.size() +
                ", listaHabitaciones=" + listaHabitaciones +
                ", listaPasajeros=" + listaPasajeros +
                ", listaReservas=" + listaReservas +
                ", listaEmpleados=" + listaEmpleados +
                '}';
    }
}