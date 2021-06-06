package com.company;

import com.company.Personal.Pasajero;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva {

    private String id; //Podria llegar a generar problemas, pero tengo un registro de el orden de las reservas
    private Habitacion habitacion;
    private Pasajero pasajero;
    private ArrayList<Producto> productosConsumidos =  new ArrayList<>();
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;

    //--------------- Constructor ---------------
    public Reserva(Pasajero pasajero, Habitacion habitacion, int cantDiasReserva) {
        this.pasajero = pasajero;
        this.habitacion = habitacion;
        this.checkIn = LocalDateTime.now(); // Reserva se instanciara solo entre las 14:00 y las 00:00, los horarios del checkin
        this.checkOut= checkIn.plusDays(cantDiasReserva);
        this.id = this.creaID();

    }


    //--------------- Habitacion ---------------
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    //--------------- Productos Consumidos ---------------
    public ArrayList<Producto> getProductosConsumidos() {
        return productosConsumidos;
    }
    public void aniadirProductosConsumidos(ArrayList<Producto> productosConsumidos) {
        this.productosConsumidos = productosConsumidos;
    }

    //--------------- Pasajero ---------------
    public Pasajero getPasajero() {
        return pasajero;
    }
    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    //--------------- CheckIn ---------------
    public LocalDateTime getCheckIn() {
        return checkIn;
    }
    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    //--------------- Metodos ---------------

    public int getCantDiasReserva(){

        int cantDiasReserva = 0;
        LocalDateTime aux = this.checkOut;

        while(aux.getDayOfYear() != checkIn.getDayOfYear()){
            cantDiasReserva++;
            aux.minusDays(1);
        }

        return cantDiasReserva;
    }

    public String muestraListaProductos(){

        String listaProductosConsumidos = " Productos Consumidos por "+pasajero.getNombre()+" en la habitacion Piso: "+habitacion.getPiso()+" Letra: "+habitacion.getLetra()+": \n";

        for (Producto aux : this.productosConsumidos){

            listaProductosConsumidos += ( aux.toString() + "\n" ) ;

        }

        return listaProductosConsumidos;
    }

    public double getMontoTotalProductos(){

        double auxMontoTotal = 0 ;

        for (Producto aux : this.productosConsumidos){
            auxMontoTotal+=aux.getPrecio();
            //Ojo con la cantidad de stock, necesito q los productos sean
            // almacenados de manera que cada elemento sea por unidad de Producto
            // sino multiplicar la cantidad de veces que consumio el producto por el precio
        }

        return auxMontoTotal;
    }

    public double getMontoTotalHabitacion(){
        return ( this.habitacion.getPrecio() * this.getCantDiasReserva() );
    }

    public void pasajeroSeQuedaMasDias(int dias){//Pulir
        this.checkOut.plusDays(dias);
    }

    public void pasajeroSeVaAntesDelCheckOut(int dias){//Pulir
        this.checkOut.minusDays(dias);
    }

    public double gastoTotal(){
        //checkear que el pasajero se esta yendo en el horario del check out
        //correcto, si no es asi creo q podria sumarle un dia mas de alquiler
        //al pasajero
        return this.getMontoTotalProductos() + this.getMontoTotalHabitacion();
    }

    private String creaID(){

        return pasajero.getDni() + "-" + this.getHabitacion().getLetra()+this.getHabitacion().getPiso() +"-" +this.getCheckIn();

    }

}