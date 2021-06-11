package com.company;

import com.company.Personal.Pasajero;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Reserva {

    private String id; //Podria llegar a generar problemas, pero tengo un registro de el orden de las reservas
    private Habitacion habitacion;
    private Pasajero pasajero;
    private int cantPersonas;
    private ArrayList<Producto> productosConsumidos =  new ArrayList<>();
    private LocalDateTime checkIn;
    private LocalDateTime checkOut; //Es un atributo para ver el historial
    private String opinion;

    //--------------- Constructor ---------------
    public Reserva(LocalDateTime checkIn,Pasajero pasajero, Habitacion habitacion, int cantDiasReserva,int cantPersonas) {
        this.pasajero = pasajero;
        this.habitacion = habitacion;
        this.checkIn = checkIn.withHour(14).withMinute(0);
        this.checkOut= checkIn.plusDays(cantDiasReserva).minusHours(10);
        this.cantPersonas=cantPersonas;
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
    public void setProductosConsumidos(ArrayList<Producto> productosConsumidos) {
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

    //--------------- ID ---------------
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    //--------------- Cant Personas ---------------
    public int getCantPersonas() {
        return cantPersonas;
    }
    public void setCantPersonas(int cantPersonas) {
        this.cantPersonas = cantPersonas;
    }



    //--------------- Metodos ---------------

    public int getCantDiasReserva() {

        return (int) Duration.between(checkIn.toLocalDate(), checkOut.toLocalDate()).toDays();
    }

    public String muestraListaProductosConsumidos(){

        String listaProductosConsumidos="";

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
                ", Habitacion: Letra: " + habitacion.getLetra() + " Piso: "+habitacion.getPiso()+
                ", Pasajero: NombreyApellido: "+ pasajero.getNombre()+pasajero.getApellido()+" Dni: "+pasajero.getDni()+
                ", Productos Consumidos: " + muestraListaProductosConsumidos() +
                ", CheckIn:" + checkIn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - h:m:s")) +
                ", CheckOut:" + checkOut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - h:m:s")) +
                ", Monto Total: "+gastoTotal()+"$"+
                ", Opinion: '" + opinion + '\'' +
                '}';
    }
}