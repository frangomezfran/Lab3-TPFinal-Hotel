package com.company;

import com.company.Personal.Pasajero;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva {

    private String id; //Podria llegar a generar problemas, pero tengo un registro de el orden de las reservas
    private Habitacion habitacion;
    private Pasajero pasajero;
    private ArrayList<Producto> productosConsumidos =  new ArrayList<>();
    private LocalDateTime checkIn;
    private LocalDateTime checkOut; //Es un atributo para ver el historial
    private String opinion;

    //--------------- Constructor ---------------
    public Reserva(LocalDateTime checkIn,Pasajero pasajero, Habitacion habitacion, int cantDiasReserva) {
        this.pasajero = pasajero;
        this.habitacion = habitacion;
        this.checkIn = checkIn;
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
    //--------------- CheckOut ---------------
    public LocalDateTime getCheckOut() {
        return checkOut;
    }
    public void setCheckOut(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    //--------------- Opinion ---------------
    public String getOpinion() {
        return opinion;
    }
    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }


    //--------------- Metodos ---------------

    public int getCantDiasReserva() {

        return (int) Duration.between(checkIn.toLocalDate(), checkOut.toLocalDate()).toDays();
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

    public boolean eliminaProductoConsumido(String nombre){

        for(Producto aux : productosConsumidos){
            if(aux.getNombre().equals(nombre)){
                productosConsumidos.remove(aux);
                return true;
            }
        }
        return false;
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

    @Override
    public String toString() {
        return "Reserva{" +
                "id='" + id + '\'' +
                ", habitacion=" + habitacion +
                ", pasajero=" + pasajero +
                ", productosConsumidos=" + productosConsumidos +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", opinion='" + opinion + '\'' +
                '}';
    }
}