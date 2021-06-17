package com.company;

import com.company.Personal.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

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

    public ArrayList<Reserva> historialDeUnPasajero (long dni){

        ArrayList<Reserva> reservasDeUnPasajero = new ArrayList<>();

        for(Reserva aux : listaReservas){
            if(aux.getPasajero().getDni() == dni)
                reservasDeUnPasajero.add(aux);
        }

        if(reservasDeUnPasajero.isEmpty()){
            return null;
        }else {
            return reservasDeUnPasajero;
        }

    }

    public Reserva retornaReservadelPasajero(long dni){

        for(Reserva aux : listaReservas){
            if(aux.getPasajero().getDni()==dni)
                return aux;//Las reservas estan ingresadas por la mas nueva a la mas vieja
        }
        return null; //Por x razon si no existe...
    }

    public Reserva retornaReservaPorCheckIn(LocalDate fecha){

        for(Reserva aux : listaReservas){
            if(aux.getCheckIn().toLocalDate().equals(fecha))
                return aux;
        }
        return null;
    }

    public Reserva retornaReservaVigentedelPasajero(long dni){

        for(Reserva aux : this.listaReservas){
            if(aux.getPasajero().getDni()==dni &&
                aux.getCheckOut().isAfter(LocalDateTime.now()))
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

    public Habitacion retornaHabitacion(int piso,char letra){
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

    public Recepcionista retornaEmpleado(long dni){

        for(Recepcionista aux: listaEmpleados){
            if(aux.getDni() == dni){
                return aux;
            }
        }
        return null;
    }

    public ArrayList<Reserva> retornaListaReservasVigentes(){

        ArrayList<Reserva> reservasVigentes = new ArrayList<>();

        for(Reserva aux : listaReservas){

            if(LocalDateTime.now().isBefore(aux.getCheckOut())){
                reservasVigentes.add(aux);
            }
        }

        return reservasVigentes;
    }

    public ArrayList<Reserva> retornaListaReservasNoVigentes(){

        ArrayList<Reserva> reservasNoVigentes = new ArrayList<>();

        for(Reserva aux : listaReservas){

            if( (LocalDateTime.now().isAfter(aux.getCheckOut())) ){
                reservasNoVigentes.add(aux);
            }
        }

        return reservasNoVigentes;
    }

    public ArrayList<TipoHabitacion> retornaTiposHabitacionesDeUnHotel(){

        ArrayList<TipoHabitacion> tiposHabitacion = new ArrayList<>();

        for (Habitacion aux : listaHabitaciones){

            tiposHabitacion.add(aux.getTipoHabitacion());

        }

        Set<TipoHabitacion> set = new LinkedHashSet<>(tiposHabitacion); //Elimina duplicados y no pierde el orden
        tiposHabitacion.clear();
        tiposHabitacion.addAll(set);

        return tiposHabitacion;

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