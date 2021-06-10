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
    public void creaReserva(Hotel hotel,Habitacion habitacion,LocalDateTime fecha,int cantDiasHospedaje, Pasajero pasajero){

        if(habitacionOcupada(hotel,fecha.toLocalDate(),cantDiasHospedaje,habitacion)) {

            Reserva nuevaReserva = new Reserva(fecha, pasajero, habitacion, cantDiasHospedaje);

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

    public String muestraHabitacionesDisponibles (Hotel hotel, int cantPersonas,LocalDate fecha, int cantDias){

        ArrayList<Habitacion> habitacionesOcupadasPorFecha = habitacionesOcupadasPorFecha(hotel,fecha,cantDias);

        String habitacionesDisponibles="Habitaciones disponibles para la fecha "+fecha+" hasta "+fecha.plusDays(cantDias)+"\n";

        for(Habitacion habitaciones : hotel.getListaHabitaciones()){

            for(Habitacion habitacionNoMostrar : habitacionesOcupadasPorFecha){

                if(!habitaciones.equals(habitacionNoMostrar) && habitaciones.getTipoHabitacion().getCantPersonas()>=cantPersonas)
                    habitacionesDisponibles += habitaciones.toString();

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

    /*public void modificarReserva(Hotel hotel,long dni)
    {
        //Ojo que si modifico algun atributo cambio el id de la reserva

        Reserva reservaAModificar = hotel.retornaReservadelPasajero(dni);

        Scanner input = new Scanner(System.in);

        int opcion = 0;

        do{
            System.out.println("Que se desea Modificar de la reserva ?\n1-Habitacion\n2-Pasajero\n3-Retirar" +
                    "Un producto canceloado\n4-CheckIn\n5-Cantidad de dias de Hospedaje\n0-Salir");

            switch (opcion=input.nextInt()) {
                case (1):
                    //Modularizar
                    System.out.println("Ingrese fecha para saber las habitaciones disponibles en formato M/d/yyyy ");
                    muestraHabitacionesDisponibles(hotel,dateInput(input.next()),reservaAModificar.getCantDiasReserva());
                    System.out.println("\nIngrese Piso a continuacion de la Letra de la habitacion a modificar ");
                    Habitacion nuevaHabitacion = hotel.retornaHabitacion(input.next().charAt(0),input.nextInt());
                    reservaAModificar.setHabitacion(nuevaHabitacion);
                    //Prints, inputs en parametros, ya veo el profe poniendo un "_" en input y se rompio tuti
                    break;
                case (2):
                    modificarUsuario(hotel);
                    break;
                case (3):
                    //Modularizar
                    int eliminarOpcion = 0;
                    do {
                        reservaAModificar.muestraListaProductos();
                        System.out.println("Ingrese el nombre del producto a eliminar: ");
                        if (reservaAModificar.eliminaProductoConsumido(input.next())){
                            System.out.println("Producto eliminado correctamente");
                        }else{
                            System.out.println("Error al eliminar");
                        }
                        //Si por x motivo se ingresa mal el nombre
                        System.out.println("1-Intentar Nuevamente\n0-Salir");
                        eliminarOpcion = input.nextInt();
                    }while ( eliminarOpcion == 1 );
                    break;
                case (4):
                    //Ver habitaciones Reservadas antes de modificar
                    //Aca se modifica CheckIn
                    break;
                case (5):
                    //Ver habitaciones Reservadas antes de modificar
                    //Aca se modifica Cant dias de hospedaje
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion != 0);
    }

    public void modificarUsuario(Hotel hotel)
    {

        System.out.println("Ingrese dni del usuario a modificar :");

        Scanner input = new Scanner(System.in);

        Pasajero pasajeroaModificar = hotel.retornaPasajero(input.nextLong());

        int opcion = 0;

        do{
            System.out.println("Que desea modificar del pasajero ?\n1-Nombre\n2-Apellido\n3-Dni"
                    +"\n4-Saldo\n5-Pais De Origen\n6-Domicilio\n7-Forma de Pago\n0-Salir");

            switch (opcion=input.nextInt()) {
                case (1):
                    System.out.println("Nombre actualmente: "+pasajeroaModificar.getNombre()+"\nIngrese Nombre a modificar: ");
                    pasajeroaModificar.setNombre(input.next());
                    break;
                case (2):
                    System.out.println("Apellido actualmente: "+pasajeroaModificar.getApellido()+"\nIngrese Apellido a modificar: ");
                    pasajeroaModificar.setApellido(input.next());
                    break;
                case (3):
                    System.out.println("Dni actualmente : "+pasajeroaModificar.getDni()+"\nIngrese Dni a modificar: ");
                    pasajeroaModificar.setDni(input.nextLong());
                    break;
                case (4):
                    System.out.println("Saldo actualmente: "+pasajeroaModificar.getSaldo()+"$\nIngrese Saldo a modificar: ");
                    pasajeroaModificar.setSaldo(input.nextDouble());
                    break;
                case (5):
                    System.out.println("Pais de Origen actualmente: "+pasajeroaModificar.getPaisDeOrigen()+"\nIngrese Pais de origen a modificar: ");
                    pasajeroaModificar.setPaisDeOrigen(input.next());
                    break;
                case (6):
                    System.out.println("Domicilio actualmente: "+pasajeroaModificar.getDomicilio()+"\nIngrese Domicilio a modificar: ");
                    pasajeroaModificar.setDomicilio(input.next());
                    break;
                case (7):
                    System.out.println("Forma de Pago actualmente: "+pasajeroaModificar.getFormaDePago()+"\nIngrese Apellido a modificar: ");
                    System.out.println("1-Debito\n2-Credito\n3-Efectivo");
                    switch (input.nextInt()) {
                        case (1):
                            pasajeroaModificar.setFormaDePago(MedioDePago.DEBITO);
                            break;
                        case (2):
                            pasajeroaModificar.setFormaDePago(MedioDePago.CREDITO);
                            break;
                        case (3):
                            pasajeroaModificar.setFormaDePago(MedioDePago.EFECTIVO);
                            break;
                    }
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion!=0);
    }*/

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