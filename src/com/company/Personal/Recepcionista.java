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
    private String contrasenia;

    //--------------- Constructor ---------------
    public Recepcionista(String nombre, String apellido, long dni,double sueldo,String turno,String contrasenia) {
        super(nombre, apellido, dni);
        this.sueldo=sueldo;
        this.turno=turno;
        this.contrasenia=contrasenia;
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

    //--------------- Contraseña ---------------
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    //--------------- Metodos ---------------
    public void creaReserva(Hotel hotel,Habitacion habitacion,LocalDateTime fecha,int cantDiasHospedaje, Pasajero pasajero,int cantPersonas){

        if(habitacionOcupada(hotel,cantPersonas,fecha.toLocalDate(),cantDiasHospedaje,habitacion)) {

            Reserva nuevaReserva = new Reserva(fecha, pasajero, habitacion, cantDiasHospedaje,cantPersonas);

            hotel.agregaReserva(nuevaReserva);


            //Cuando se cree la reserva añadir el pasajero a la lista de usuarios del hotel
        }
    }

    public boolean habitacionOcupada (Hotel hotel, int cantPersonas ,LocalDate fecha, int cantDias, Habitacion habitacion){

        //La uso para validar en el menu

        ArrayList<Habitacion> habitacionesDisponibles = retornaHabitacionesDisponibles(hotel,cantPersonas,fecha,cantDias);

        for(Habitacion aux : habitacionesDisponibles){

            if(aux.getLetra() == habitacion.getLetra()
                && aux.getPiso() == habitacion.getPiso()){
                return true; //La habitacion esta ocupada o son muchas personas para la misma
            }
        }
        return false;//La habitacion esta disponible para el nuevo pasajero

    }

    public ArrayList<Habitacion> habitacionesNoDisponiblesPorCantDePersonas(Hotel hotel,int cantPersonas){

        ArrayList<Habitacion> habitacionesNoDisponibles = new ArrayList<>();

        for (Habitacion aux : hotel.getListaHabitaciones()){

            if( aux.getTipoHabitacion().getCantPersonas() < cantPersonas){

                habitacionesNoDisponibles.add(aux);

            }

        }

        return habitacionesNoDisponibles;

    }

    public ArrayList<Habitacion> retornaHabitacionesNoDisponibles (Hotel hotel, int cantPersonas ,LocalDate fecha, int cantDias){

        //Creo un arreglo de fechas de los dias del nuevo pasajero
        LocalDate[] nuevoPasajero = creaArraydeFechas(fecha,cantDias);

        ArrayList<Habitacion> habitacionesNoDisponibles = habitacionesNoDisponiblesPorCantDePersonas(hotel,cantPersonas);

        //Utilizo este flag para dejar de buscar cuando se encuentra una habitacion ocupada
        boolean flag = false;


        for(Reserva aux : hotel.getListaReservas()){

            flag = false;

            if (nuevoPasajero[0].getMonthValue() == aux.getCheckIn().getMonthValue()
                    || nuevoPasajero[0].getMonthValue() == aux.getCheckIn().getMonthValue() + 1) {
                //Esta condicion la hago para que no busque en todas las reservas
                //Suponiendo que un pasajero no se va a quedar mas de 1 mes en una habitacion

                //Arreglo de fechas pero de las reservas
                LocalDate[] fechaReserva = creaArraydeFechas(aux.getCheckIn().toLocalDate(), aux.getCantDiasReserva());

                for (int i = 0; i < cantDias; i++) {

                    for (int j = 0; j < aux.getCantDiasReserva() - 1; j++) {
                        //la ultima celda no se compara porque ese dia, es cuando el q reservo hace el chekout.Por eso el -1

                        if (nuevoPasajero[i].equals(fechaReserva[j]) ) {

                            //Habitaciones que no se muestran,estan ocupadas para la fecha del nuevo pasajero

                            if(!habitacionesNoDisponibles.contains(aux.getHabitacion())) {
                                habitacionesNoDisponibles.add(aux.getHabitacion());
                            }

                            flag = true;

                            break;
                        }

                    }

                    //Se sigue comparando, tengo q arreglar aca
                    //Si se añadio la habitacion, tengo q seguir con otra reserva

                    if (flag) {
                        break;
                    }

                }
            }


        }

        return habitacionesNoDisponibles;
    }

    public ArrayList<Habitacion> retornaHabitacionesDisponibles (Hotel hotel, int cantPersonas,LocalDate fecha, int cantDias){

        ArrayList<Habitacion> habitacionesNoDisponibles = retornaHabitacionesNoDisponibles(hotel,cantPersonas,fecha,cantDias);

        ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();


        if(!habitacionesNoDisponibles.isEmpty()) {

            for (Habitacion disponible : hotel.getListaHabitaciones()) {

                for (Habitacion habitacionNoMostrar : habitacionesNoDisponibles) {

                   if(habitacionNoMostrar.equals(disponible)){
                       break;
                   }

                   if(habitacionesNoDisponibles.size() == habitacionesNoDisponibles.indexOf(habitacionNoMostrar)+1){
                       habitacionesDisponibles.add(disponible);
                   }

                }

            }

        }

        return habitacionesDisponibles;

    }

    public LocalDate[] creaArraydeFechas(LocalDate checkIn , int cantDiasHospedaje ){ //Metodo para habitacionesDisponibles

        LocalDate[] diasDeEstadias= new LocalDate [cantDiasHospedaje];

        for(int i = 0 ; i<cantDiasHospedaje ; i++){

            diasDeEstadias[i]=checkIn;
            checkIn=checkIn.plusDays(1);
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

    public Habitacion retornaHabitacionDeUnaLista(char letra,int piso,ArrayList<Habitacion> habitaciones){
        //Es para verificar el piso la letra en la lista de las habitaciones disponibles
        for(Habitacion aux: habitaciones){
            if(aux.getPiso() == piso && aux.getLetra()==letra){
                return aux;
            }
        }
        return null;
    }

    public double terminaReserva(Reserva aCobrar){

        double total=0;

        if(LocalDateTime.now().isBefore(aCobrar.getCheckOut())){
            total = aCobrar.gastoTotal();
        }else{
            //Si se va despues del checkOut, se cobra un dia mas
            total = aCobrar.gastoTotal() + aCobrar.getHabitacion().getPrecio();
        }
        //Decirle al pasajero que escriba una opinion

        return total;

    }

    public void modificaReserva (Reserva aModificar,Habitacion habitacion,Pasajero pasajero,
                                            LocalDateTime checkin,int cantDias,int cantPersonas){

        if(!aModificar.getHabitacion().equals(habitacion))
            aModificar.setHabitacion(habitacion);

        if(!aModificar.getPasajero().equals(pasajero))
            aModificar.setPasajero(pasajero);

        if(!aModificar.getCheckIn().equals(checkin))
            aModificar.setCheckIn(checkin);

        if(aModificar.getCantDiasReserva() != cantDias)
            aModificar.setCheckOut(checkin.plusDays(cantDias).withHour(10).withMinute(0));

        if(aModificar.getCantPersonas() != cantPersonas)
            aModificar.setCantPersonas(cantPersonas);

    }

    public void modificaPasajero(Pasajero pasajero, String nombre, String apellido, String pais,String domicilio,
                                    long dni,MedioDePago medioDePago){

        if(!pasajero.getNombre().equals(nombre))
            pasajero.setNombre(nombre);

        if(!pasajero.getApellido().equals(apellido))
            pasajero.setApellido(apellido);

        if(!pasajero.getPaisDeOrigen().equals(pais))
            pasajero.setPaisDeOrigen(pais);

        if(!pasajero.getDomicilio().equals(domicilio))
            pasajero.setDomicilio(domicilio);

        if(pasajero.getDni()!=dni)
            pasajero.setDni(dni);

        if(pasajero.getFormaDePago().equals(medioDePago))
            pasajero.setFormaDePago(medioDePago);

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