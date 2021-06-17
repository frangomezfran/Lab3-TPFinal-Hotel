package com.company;

import com.company.Personal.Pasajero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Reserva {

    private String id; //Podria llegar a generar problemas, pero tengo un registro de el orden de las reservas
    private Habitacion habitacion;
    private Pasajero pasajero;
    private int cantPersonas;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut; //Es un atributo para ver el historial
    private double monto=0; //Hasta que no pague, esto permanecera en 0, cuando quiera ver el historial aparecera cuanto salio
    private String opinion;

    //--------------- Constructor ---------------
    public Reserva(LocalDateTime checkIn,Pasajero pasajero, Habitacion habitacion, int cantDiasReserva,int cantPersonas) {
        this.pasajero = pasajero;
        this.habitacion = habitacion;
        this.checkIn = checkIn.withHour(15).withMinute(0);//Check-in 12 a 15hs
        this.checkOut= checkIn.plusDays(cantDiasReserva).minusHours(3); //Check-out 10 a 12
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

    //--------------- Monto ---------------
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }

//--------------- Metodos ---------------

    public int getCantDiasReserva() {

        LocalDateTime tempDateTime = LocalDateTime.from( this.checkIn );

        return (int) tempDateTime.until( this.checkOut, ChronoUnit.DAYS)+1;
    }

    public double gastoTotal(){
        return ( this.habitacion.getPrecio() * this.getCantDiasReserva() );
    }

    private String creaID(){

        return pasajero.getDni() + "-" + this.getHabitacion().getLetra()+this.getHabitacion().getPiso() +"-" +this.getCheckIn();

    }

    public void actualizaEstadoHabitacion() {

        if (LocalDateTime.now().isAfter(checkIn) && LocalDateTime.now().isBefore(checkOut)){
            this.getHabitacion().setEstadoHabitacion(EstadoHabitacion.RESERVADA);
        }

    }



    @Override
    public String toString() {
        return "\nReserva :" +
                "\nId: " + id +
                "\nHabitacion -> Tipo: "+getHabitacion().getTipoHabitacion().getTipo()+" | Letra: "+ habitacion.getLetra()+" | Piso: "+habitacion.getPiso()+
                "\nPasajero -> Nombre y Apellido: "+ pasajero.getNombre()+" "+pasajero.getApellido()+" | Dni: "+pasajero.getDni()+
                "\nCheckIn: " + checkIn.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - h:m:s")) +
                "\nCheckOut: " + checkOut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy - h:m:s")) +
                "\nMonto Total: "+gastoTotal()+"$"+
                "\nOpinion: " + opinion ;
    }
}