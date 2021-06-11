package com.company.Personal;

import com.company.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class Recepcionista extends Persona{

    private double sueldo;
    private String turno;

    //--------------- Constructor ---------------
    public Recepcionista(String nombre, String apellido, long dni,double sueldo,String turno) {
        super(nombre, apellido, dni);
        this.sueldo=sueldo;
        this.turno=turno;
    }

    //--------------- Sueldo ---------------
    public double getSueldo() {
        return sueldo;
    }
    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    //--------------- Turno ---------------
    public String getTurno() {
        return turno;
    }
    public void setTurno(String turno) {
        this.turno = turno;
    }

    //--------------- Metodos ---------------
    public void creaReserva(Hotel hotel,Habitacion habitacion,LocalDateTime fecha,int cantDiasHospedaje, Pasajero pasajero,int cantPersonas){

        if(habitacionOcupada(hotel,fecha.toLocalDate(),cantDiasHospedaje,habitacion)) {

            Reserva nuevaReserva = new Reserva(fecha, pasajero, habitacion, cantDiasHospedaje,cantPersonas);

            hotel.agregaReserva(nuevaReserva);



            //Cuando se cree la reserva añadir el pasajero a la lista de usuarios del hotel
        }
    }

    public static LocalDate dateInput(String userInput) {
        //Es para ingresar LocalDate por consola, para el metodo Modificar Reserva

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(userInput, dateFormat);

        return date ;
    }

    public boolean habitacionOcupada (Hotel hotel, LocalDate fecha, int cantDias, Habitacion habitacion){

        ArrayList<Habitacion> habitacionesOcupadas = habitacionesOcupadasPorFecha(hotel,fecha,cantDias);

        for(Reserva aux : hotel.getListaReservas()){
            if(aux.getHabitacion().getLetra() == habitacion.getLetra()
                && aux.getHabitacion().getPiso() == habitacion.getPiso()){
                return false;
            }
        }
        return true;

    }

    public ArrayList<Habitacion> habitacionesOcupadasPorFecha(Hotel hotel, LocalDate fecha, int cantDias){

        LocalDate copiaDeFecha = fecha ;

        //Creo un arreglo con los dias que quiere quedarse el nuevo pasajero
        LocalDate[] diasAComparar = diasDeReservaAArray(fecha,cantDias);

        ArrayList<Habitacion> habitacionesOcupadasFecha = new ArrayList<>();

        for(Reserva aux : hotel.getListaReservas()){

            if(diasAComparar[0].getMonthValue() == aux.getCheckIn().getMonthValue()
                || diasAComparar[0].getMonthValue() == aux.getCheckIn().getMonthValue()+1 ){
                //Suponiendo que un pasajero no se va a quedar mas de 1 mes en una habitacion

                LocalDate[] diasReserva = diasDeReservaAArray(aux.getCheckIn().toLocalDate(),aux.getCantDiasReserva());

                for ( int i = 0; i< cantDias ; i++){
                    for ( int j=0; j< aux.getCantDiasReserva()-1 ; j++) {
                        //la ultima celda no se compara porque ese dia, es cuando el q reservo hace el chekout.Por eso el -1

                        if (diasAComparar[i] == diasReserva[j]) {
                            habitacionesOcupadasFecha.add(aux.getHabitacion());//Tengo que mostrar todas las habitaciones menos estas
                            break;
                        }

                    }
                }
            }
        }
        return habitacionesOcupadasFecha;
    }

    public ArrayList<Habitacion> retornaHabitacionesDisponibles (Hotel hotel, int cantPersonas,LocalDate fecha, int cantDias){

        ArrayList<Habitacion> habitacionesOcupadasPorFecha = habitacionesOcupadasPorFecha(hotel,fecha,cantDias);

        ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();

        //String habitacionesDisponibles="Habitaciones disponibles para la fecha "+fecha+" hasta "+fecha.plusDays(cantDias)+"\n";

        for(Habitacion habitaciones : hotel.getListaHabitaciones()){

            for(Habitacion habitacionNoMostrar : habitacionesOcupadasPorFecha){

                if(!habitaciones.equals(habitacionNoMostrar) &&
                        habitaciones.getTipoHabitacion().getCantPersonas()>=cantPersonas)

                    habitacionesDisponibles.add(habitaciones);

            }
        }

        return habitacionesDisponibles;

    }

    public LocalDate[] diasDeReservaAArray(LocalDate checkIn , int cantDiasHospedaje ){ //Metodo para habitacionesDisponibles

        LocalDate diasDeEstadias[]= new LocalDate [cantDiasHospedaje];

        for(int i = 0 ; i<cantDiasHospedaje ; i++){
            diasDeEstadias[i]=checkIn;
            checkIn.plusDays(1);
        }

        return diasDeEstadias;

    }

    public Pasajero crearUnNuevoUsuario(Hotel hotel,String nombre,String apellido,long dni,
                                    String paisDeOrigen,String domicilio,MedioDePago medioDePago)//creo al pasajero
    {
       Pasajero pasajero = new Pasajero(nombre,apellido,dni,paisDeOrigen,domicilio,medioDePago);

       hotel.agregaNuevoPasajero(pasajero);

       return pasajero;

    }



    public String checkearCheckOutsDia(Hotel hotel){

        String reservasQueFinalizanHoy = "Habitaciones de reservas que finalizan hoy: ("+LocalDate.now()+")\n";
        int i = 1;

        for(Reserva aux : hotel.getListaReservas()){

            if(aux.getCheckOut().toLocalDate().equals(LocalDate.now())){

                reservasQueFinalizanHoy += i+") Piso: "+aux.getHabitacion().getPiso()+" | Letra: "+aux.getHabitacion().getLetra();
                i++;

            }

        }

        return reservasQueFinalizanHoy;

    }

    public void terminaReserva(Hotel hotel,long dni){

        Reserva aCobrar = hotel.retornaReservadelPasajero(dni);

        if(LocalDateTime.now().isBefore(aCobrar.getCheckOut())){
            System.out.println("El total es de : "+aCobrar.gastoTotal());
        }else{
            //Cobramos una dia mas de la habitacion
        }

    }

    @Override
    public String toString() {


        return  "Empleado{" +
                "nombre='" + getNombre() + '\n' +
                "apellido='" + getApellido() + '\n' +
                "dni='" + getDni() + '\n' +
                "sueldo=" + sueldo +
                ", turno='" + turno + '\n' +
                '}';


    }


}