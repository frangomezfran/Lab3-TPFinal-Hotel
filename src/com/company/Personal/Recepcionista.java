package com.company.Personal;

import com.company.Habitacion;
import com.company.Reserva;
import com.company.Hotel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;


public class Recepcionista extends Persona{

    private double sueldo;
    private String turno;

    public Recepcionista(String nombre, String apellido, long dni,double sueldo,String turno) {
        super(nombre, apellido, dni);
        this.sueldo=sueldo;
        this.turno=turno;
    }

    //luego ver si se puede optimizar esta funcion
    public Recepcionista seleccionarUnTrabajador(Hotel hotel,long dni)
    {
        LocalDateTime turnoManiana = LocalDate.now().atTime(8, 0);
        LocalDateTime turnoManiana2 = LocalDate.now().atTime(12, 59);

        LocalDateTime turnoTarde = LocalDate.now().atTime(13, 0);
        LocalDateTime turnoTarde2 = LocalDate.now().atTime(17, 59);

        //LocalDateTime turnoNoche = LocalDate.now().atTime(18, 00);
        //LocalDateTime turnoNoche2 = LocalDate.now().atTime(7, 59);

        LocalDateTime tiempoActual=LocalDateTime.now();


        //si el tiempo es igual a turnoManiana o
        // si el tiempo actual esta entre turnoManiana Y turnoManiana2
        //o si el tiempo actual es igual a turnoManiana2
        if(tiempoActual.compareTo(turnoManiana)==0||
                tiempoActual.compareTo(turnoManiana)==1&&
                        tiempoActual.compareTo(turnoManiana2)==-1
                ||tiempoActual.compareTo(turnoManiana2)==0)
        {
            System.out.println("estamos en el turno maniana\n");
            for (Recepcionista empleado:hotel.getListaEmpleados()) {
                //si el recepcionista tiene el mismo dni y
                // esta en el turno que le corresponde
                if(empleado.getDni()==dni && empleado.turno=="maniana")
                {
                    return empleado;
                }else
                {
                    System.out.println("el dni ingresado es incorrecto o " +
                            "usted quiere trabajar" +
                            "en un turno que no le corresponde >:(");
                }
            }
        }
        //si el tiempo es igual a turnoTarde o
        // si el tiempo actual esta entre turnoTarde Y turnoTarde2
        //o si el tiempo actual es igual a turnoTarde2

        else if(tiempoActual.compareTo(turnoTarde)==0||
                tiempoActual.compareTo(turnoTarde)==1&&
                        tiempoActual.compareTo(turnoTarde2)==-1
                ||tiempoActual.compareTo(turnoTarde2)==0)
        {
            System.out.println("estamos en el turno tarde\n");
            for (Recepcionista empleado:hotel.getListaEmpleados()) {
                //si el recepcionista tiene el mismo dni y
                // esta en el turno que le corresponde
                if(empleado.getDni()==dni && empleado.turno=="tarde")
                {
                    return empleado;
                }else
                {
                    System.out.println("el dni ingresado es incorrecto o " +
                            "usted quiere trabajar" +
                            "en un turno que no le corresponde >:(");
                }
            }
        }
        //de lo contrario si no estamos ni en el turno maniana ni tarde...
        else
        {
            System.out.println("estamos en el turno noche\n");
            for (Recepcionista empleado:hotel.getListaEmpleados()) {
                //si el recepcionista tiene el mismo dni y
                // esta en el turno que le corresponde
                if(empleado.getDni()==dni && empleado.turno=="noche")
                {
                    return empleado;
                }else
                {
                    System.out.println("el dni ingresado es incorrecto o " +
                            "usted quiere trabajar" +
                            "en un turno que no le corresponde >:(");
                }
            }
        }

       return null;//si no hay ningun empleado para ese turno..
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
        //1=es mayor
        //0=son iguales
        //-1=es menor
        if(tiempoActual.compareTo(tiempo_limiteCheckIn)==-1||
                tiempoActual.compareTo(tiempo_limiteCheckIn)==0)
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
        //si la hora actual es menor o igual al minimo podemos proceder al check out
        //1=es mayor
        //0=son iguales
        //-1=es menor
        if(tiempoActual.compareTo(tiempo_limiteCheckOut)==-1||
                tiempoActual.compareTo(tiempo_limiteCheckOut)==0)
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
    public void crearUnNuevoUsuario(Hotel hotel,String nombre, String apellido,
                                    long dni, double saldo,
                                    String paisDeOrigen, String domicilio,String medioDePago)//creo al pasajero
    {
        hotel.aniadirListaPasajeros(new Pasajero(nombre,apellido,dni,
                saldo,paisDeOrigen,domicilio,medioDePago));

    }


    public void mostrarTodosLosUsuarios(Hotel hotel)
    {
        for (Pasajero pasajero:hotel.getListaPasajeros()) {

                Pasajero pasajeroAmostrar= (Pasajero) pasajero;
                System.out.println(pasajeroAmostrar.toString());

        }
    }


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
        if(hotel.getListaPasajeros().contains(pasajero))  //si el  pasajero se encuentra en la lista de usuarios
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
