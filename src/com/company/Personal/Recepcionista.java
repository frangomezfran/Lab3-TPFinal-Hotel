package com.company.Personal;

<<<<<<< HEAD
=======
import com.company.Personal.Pasajero;
import com.company.Personal.Persona;

>>>>>>> origin/ramaFran
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class Recepcionista extends Persona {

    private double sueldo;
    private String turno;

    public Recepcionista(String nombre, String apellido, long dni,double sueldo,String turno) {
        super(nombre, apellido, dni);
        this.sueldo=sueldo;
        this.turno=turno;
    }

    public  String obtenerFeedback()
    {
        System.out.println("si usted desea dejenos su opinion acerca de como se sintio en nuestro establecimiento");
        Scanner quieroOpinar=new Scanner(System.in);
        System.out.println("¿Desea opinar?--->si/no");

        if(quieroOpinar.nextLine()=="si")
        {
            Scanner opinion=new Scanner(System.in);
            return opinion.nextLine();
        }else
        {
            return null;
        }

    }

<<<<<<< HEAD
    public Reserva CrearNuevaReserva(UUID id, Habitacion habitacion, Pasajero pasajero, int cantidadDiasHospedaje,
                                  LocalDateTime checkIn,LocalDateTime checkOut)
=======
    public void CrearNuevaReserva(UUID id, Habitacion habitacion, Pasajero pasajero, int cantidadDiasHospedaje,
                                  LocalDate checkIn, LocalDate checkOut)
>>>>>>> origin/ramaFran
    {


        LocalDateTime checkIn_=CheckIn(checkIn);
        LocalDateTime checkOut_=CheckOut(checkOut);

    }

    public LocalDateTime CheckIn(LocalDateTime checkIn)
    {
        LocalDateTime tiempo_limiteCheckIn = LocalDate.now().atTime(10, 0);
        LocalDateTime tiempoActual=LocalDateTime.now();
        //si la hora actual es menor o igual al minimo podemos proceder al check in
        if(tiempoActual.getHour()<tiempo_limiteCheckIn.getHour())
        {
            return checkIn;
        }
        else
        {
            //en caso de que no se pueda unicamente se pide que pague la habitacion y luego al otro dia se
            //carga el usuario en el sistema
            System.out.println("Lo siento usted no puede hacer el check in en este momento");
            System.out.println("vuelva a las 10");
        }

        return null;
    }


    public LocalDateTime CheckOut(LocalDateTime checkOut)
    {
        LocalDateTime tiempo_limiteCheckOut = LocalDate.now().atTime(12, 0);
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
            System.out.println("vuelva a las 12");
        }

        return null;
    }
    public void modificarReserva(Reserva reserva)
    {

    }

    public void consultarHabitacionesDisponibles(Hotel hotel, char Letra, int piso)
    {

    }
    public void crearUnNuevoUsuario(String nombre,String apellido,long dni)//creo al pasajero
    {

    }
    public void mostrarTodosLosUsuarios(Hotel listaDeUsuarios)
    {
        for (Object pasajero:listaDeUsuarios) {
            if(pasajero instanceof Pasajero)
            {
                Pasajero pasajeroAmostrar= (Pasajero) pasajero;
                pasajeroAmostrar.toString();
            }
        }
    }
    public void modificarUsuario(Pasajero pasajero)
    {
       //aca usar getter y setter
    }
    public boolean buscarUsuario(long dni, Hotel hotel)
    {
        if(true)  //si el dni del pasajero se encuentra en la lista de usuarios
        {
            return true;
        }
        return false;
    }
    public void login(Pasajero pasajero, Hotel hotel)
    {
        //si el dni del pasajero se encuentra en la lista de usuarios ingresa sesion de forma correcta

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
        return "Empleado{" +
                "sueldo=" + sueldo +
                ", turno='" + turno + '\'' +
                '}';
    }
}
