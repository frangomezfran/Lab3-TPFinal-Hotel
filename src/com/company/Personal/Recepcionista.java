package com.company.Personal;

import com.company.Habitacion;
import com.company.Reserva;
import com.company.Hotel;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Recepcionista extends Persona{

    private double sueldo;
    private String turno;

    public Recepcionista(String nombre, String apellido, long dni,double sueldo,String turno) {
        super(nombre, apellido, dni);
        this.sueldo=sueldo;
        this.turno=turno;
    }



    public Reserva CrearNuevaReserva(Habitacion habitacion, Pasajero pasajero, int cantidadDiasHospedaje,
                                  LocalDateTime checkIn, LocalDateTime checkOut)
    {

        LocalDateTime checkIn_=CheckIn(checkIn);
        LocalDateTime checkOut_=CheckOut(checkOut);

        return new Reserva(pasajero,habitacion,cantidadDiasHospedaje);
    }

    public LocalDateTime CheckIn(LocalDateTime checkIn)
    {
        LocalDateTime tiempo_limiteCheckIn = LocalDate.now().atTime(14, 0);
        LocalDateTime tiempoActual=LocalDateTime.now();
        //si la hora actual es menor o igual al minimo podemos proceder al check in
        if(tiempoActual.getHour()<=tiempo_limiteCheckIn.getHour())
        {
            return checkIn;
        }
        else
        {
            //en caso de que no se pueda unicamente se pide que pague la habitacion y luego al otro dia se
            //carga el usuario en el sistema
            System.out.println("Lo siento usted no puede hacer el check in en este momento");
            System.out.println("vuelva a las 14");
        }

        return null;
    }


    public LocalDateTime CheckOut(LocalDateTime checkOut)
    {
        LocalDateTime tiempo_limiteCheckOut = LocalDate.now().atTime(10, 0);
        LocalDateTime tiempoActual=LocalDateTime.now();
        //si la hora actual es menor o igual al minimo podemos proceder al check in
        if(tiempoActual.getHour()<tiempo_limiteCheckOut.getHour())
        {
            return checkOut;
        }
        else
        {
            //en caso de que no se pueda unicamente se pide que pague la habitacion y luego al otro dia se
            //carga el usuario en el sistema
            System.out.println("Lo siento usted no puede hacer el check out en este momento");
            System.out.println("vuelva a las 10");
        }

        return null;
    }
    public void modificarReserva(Reserva reserva)
    {
        //habitacion

        //pasajero

        //retirar un producto cancelado

        //checkIn

        //checkOut
    }

    public void consultarHabitacionesDisponibles(Hotel hotel, char Letra, int piso)
    {

    }
    public Pasajero crearUnNuevoUsuario(String nombre,String apellido,long dni,double saldo
            ,String paisDeOrigen,String domicilio,String medioDePago)//creo al pasajero
    {
        return new Pasajero(nombre,apellido,dni,saldo,paisDeOrigen,domicilio,medioDePago);
    }

   /*
   *     public void mostrarTodosLosUsuarios(Hotel listaDeUsuarios)
    {
        for (Object pasajero:listaDeUsuarios.listaPasajeros) {
            if(pasajero instanceof Pasajero)
            {
                Pasajero pasajeroAmostrar= (Pasajero) pasajero;
                pasajeroAmostrar.toString();
            }
        }
    }
   * */

    public void modificarUsuario(Pasajero pasajero)
    {

        //nombre

        //apellido

        //dni

        //saldo

        //paisDeOrigen

        //domicilio

        //formaDePago





    }
    public void buscarUsuario(long dni,Hotel hotel)
    {
        if(hotel.getListaPasajeros().contains(dni))  //si el dni del pasajero se encuentra en la lista de usuarios
        {
            for (Pasajero pasajeroActual:hotel.getListaPasajeros()) {
                if(pasajeroActual.getDni()==dni)//¿aca tendria que ir un equals?
                {
                    System.out.println(pasajeroActual.toString());
                }
            }
        }

    }
    public void login(Pasajero pasajero,Hotel hotel)
    {
        //si el dni del pasajero se encuentra en la lista de
        // usuarios ingresa sesion de forma correcta
        if(hotel.getListaPasajeros().contains(pasajero.getDni()))  //si el dni del pasajero se encuentra en la lista de usuarios
        {
            System.out.println("Inicio de sesion realizado correctamente :) ");
        }
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
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
