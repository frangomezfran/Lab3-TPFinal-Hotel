package com.company;

import com.company.Personal.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Menu {

    private Scanner input = new Scanner(System.in);
    //Cuando tengo problemas con el Scanner uso este


    public Menu(){}

    public int pideEntero(){

        String futuroEntero = "";
        do
        {
            futuroEntero = input.nextLine();

        }while(!esEntero(futuroEntero));

        return Integer.parseInt(futuroEntero);
    }
    public static boolean esEntero(String aValidar) {
        int esEntero;
        try {
            esEntero=Integer.parseInt(aValidar);
            return true;
        } catch (NumberFormatException ex) {

            System.out.print("Mal ingresado, Intente nuevamente : ");

            return false;
        }
    }

    public LocalDate pideFecha(){

        String fechaString = "";
        LocalDate fecha;

        do {
            fechaString = this.input.nextLine();
            fecha=esFecha(fechaString);

        }while(!fechaString.matches("\\d[0-3]\\d[0-9]/\\d[0-1]\\d[0-9]/\\d[2]\\d[0]\\d[2]\\d[1-5]$") &&
                fecha==null );

        return esFecha(fechaString);

    }
    public static LocalDate esFecha(String fechaString){ //Solo para fechas futuras

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate fecha;

        try {
            fecha  = LocalDate.parse(fechaString, dateFormat);
            if(fecha.getDayOfMonth() != Integer.parseInt(fechaString.substring(0,2)) || fecha.isBefore(LocalDate.now())){
                //Importante q la fecha ingresada no haya pasado, si quiero buscar historial de reservas por fecha no
                //puedo usar este metodo
                //Si el profe le pinta poner el 31 de febrero con esto safo
                System.out.print("Ingreso mal la fecha, Intente nuevamente : ");
                return null;
            }
            return fecha;
        }catch (DateTimeParseException e){
            System.out.println("Fecha invalida, Intente nuevamente : ");
            return null;
        }
    }

    public LocalDate pideFechaPasada(){

        String fechaString = "";
        LocalDate fecha;

        do {
            fechaString = this.input.nextLine();
            fecha=esFechaPasada(fechaString);

        }while(!fechaString.matches("\\d[0-3]\\d[0-9]/\\d[0-1]\\d[0-9]/\\d[2]\\d[0]\\d[2]\\d[1-5]$") &&
                fecha==null );

        return esFecha(fechaString);

    }
    public static LocalDate esFechaPasada(String fechaString){ //Solo para fechas futuras

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate fecha;

        try {
            fecha  = LocalDate.parse(fechaString, dateFormat);
            if(fecha.getDayOfMonth() != Integer.parseInt(fechaString.substring(0,2))){
                System.out.print("Ingreso mal la fecha, Intente nuevamente : ");
                return null;
            }
            return fecha;
        }catch (DateTimeParseException e){
            System.out.println("Ingreso mal la fecha, Intente nuevamente : ");
            return null;
        }
    }

    public long pideLong(){

        String futuroLong = "";
        do
        {
            futuroLong = input.nextLine();

        }while(!esLong(futuroLong));

        return Long.parseLong(futuroLong);


    }
    public static boolean esLong(String aValidar) {

        long esLong;
        try {
            esLong=Long.parseLong(aValidar);
            return true;
        } catch (NumberFormatException exe) {
            System.out.print("Mal ingresado, Intente nuevamente : ");
            return false;
        }

    }

    public char pideChar(){

        Character inputChar ;
        do
        {
            inputChar = (input.nextLine()).charAt(0);

        }while(!Character.isLetter(inputChar));

        return inputChar;
    }

    public void enterParaContinuar(){
        System.out.println("Enter para continuar");
        input.reset();
        input.nextLine();
    }


    public Recepcionista login (Hotel hotel){

        Recepcionista gestionador = null;

        do {
            clearScreen();
            System.out.print("Iniciar Sesion\n\nUsuario (DNI) : ");
            long usuario = pideLong();
            System.out.print("\nContraseña : ");

            String contrasenia = input.nextLine();

            for (Recepcionista empleado : hotel.getListaEmpleados()) {

                if (empleado.getDni() == usuario && empleado.getContrasenia().equals(contrasenia)) {
                    gestionador = empleado;
                }

            }

            if(gestionador == null) {
                System.out.print("\nEl usuario y la contraseña fueron mal ingresados (Pulse ENTER para continuar)");
                input.nextLine();
            }

        }while(gestionador==null);

        return gestionador;

    }

    public void muestraHabitaciones(ArrayList<Habitacion> habitaciones){//Cambiar de clase

        for(Habitacion auxHabitacion : habitaciones){
            System.out.println(auxHabitacion.muestraHabitacion());
        }
    }

    public static void clearScreen() {
        System.out.print("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
                "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
                "\r\n--------------- Hotel Trivago ---------------\r\n\r\n");
    }

    public void menuAdministrador(Administrador gestionador){

        System.out.println("5- Empleados");

    }

    public int opcionMenuPrincipal(Recepcionista gestionador, int indiceMax){

        int opcion = pideEntero();

        if( !(gestionador instanceof Administrador) ){
            //Es recepcionista
            if(opcion>indiceMax){
                opcion=-1;//-1 nunca va a estar en el switch
            }
        }

        return opcion;

    }

    public void menuReservas(Hotel hotel, Recepcionista gestionador){

        int opcion=0;
        long auxDni=0;

        do {

            clearScreen();
            System.out.println("Reservas :\n\n1- Crear Reserva\n2- Terminar Reserva\n3- Ver Reserva\n4- Modificar Reserva\n0- Volver");

            switch (opcion=pideEntero()) {

                case (1)://Crear
                    clearScreen();

                    System.out.print("Creando una reserva: \n\n" +
                            "Ingrese cantidad de Personas a hospedarse: ");
                    int cantPersonas = pideEntero();
                    System.out.print("Ingrese la Fecha del posible CheckIn (MM/DD/YYYY) : ");
                    LocalDate fecha = pideFecha();
                    System.out.print("Ingrese la cantidad de Dias a hospedarse : ");
                    int cantDiasHospedarse = pideEntero();

                    clearScreen();

                    System.out.println("Check-In: " + fecha + " | Cant Personas: " + cantPersonas + " | Check-Out: " + fecha.plusDays(cantDiasHospedarse) + "\n\n\nHabitaciones disponibles para el nuevo pasajero: \n");

                    ArrayList<Habitacion> auxHabitacionesDisponibles = gestionador.retornaHabitacionesDisponibles(hotel, cantPersonas, fecha, cantDiasHospedarse);
                    muestraHabitaciones(auxHabitacionesDisponibles);

                    System.out.println("\nCreamos la reserva ?\n1- Si\n2- No");
                    switch (pideEntero()) {
                        case (1)://Creamos la reserva
                            clearScreen();
                            int piso;
                            char letra;
                            Habitacion habitacionAReservar;
                            opcion = 1;
                            do {
                                System.out.print("Ingrese Piso y Letra de la habitacion a reservar: \n\nPiso: ");
                                piso = pideEntero();
                                System.out.print("Letra: ");
                                letra = pideChar();
                                habitacionAReservar = gestionador.retornaHabitacionDeUnaLista(letra, piso, auxHabitacionesDisponibles);
                                if (habitacionAReservar == null) {
                                    System.out.println("\nLa habitacion ingresada no es valida, ENTER para intentar de nuevo");
                                    input.nextLine();
                                    clearScreen();
                                }
                            } while (habitacionAReservar == null);

                            System.out.print("\nIngrese Dni del Pasajero: ");
                            auxDni = pideLong();

                            Pasajero pasajero = hotel.retornaPasajero(auxDni);

                            clearScreen();

                            if (pasajero == null) {

                                System.out.println("El pasajero debe ser creado :");
                                System.out.print("\nIngrese el Nombre del nuevo Pasajero: ");
                                String auxNombre = input.nextLine();
                                //El hijo de elon musk se llama "X Æ a XII" Musk, permito caracteres extraños
                                System.out.print("Ingrese el Apellido del Pasajero: ");
                                String auxApellido = input.nextLine();
                                System.out.print("Ingrese el Pais de origen del Pasajero: ");
                                String auxPais = input.nextLine();
                                System.out.print("Ingrese el Domicilio del Pasajero: ");
                                String auxDomicilio = input.nextLine();
                                System.out.println("Ingrese el Medio de pago del Pasajero: \n1- Credito\n2- Debito\n3- Efectivo-Dolar\n4- Efectivo-PesoArgentino");
                                MedioDePago auxMedioDePago = MedioDePago.DEBITO;//Solo es para inicializar
                                do {
                                    switch (opcion = pideEntero()) {
                                        case (1):
                                            auxMedioDePago = MedioDePago.CREDITO;
                                            break;
                                        case (2):
                                            auxMedioDePago = MedioDePago.DEBITO;
                                            break;
                                        case (3):
                                            auxMedioDePago = MedioDePago.EFECTIVO_DOLAR;
                                            break;
                                        case (4):
                                            auxMedioDePago = MedioDePago.EFECTIVO_PESOARGENTINO;
                                            break;
                                        default:
                                            System.out.println("Numero mal ingresado, intente nuevamente");
                                            break;
                                    }

                                } while (opcion < 1 || opcion > 4);

                                pasajero = gestionador.crearUnNuevoUsuario(hotel, auxNombre, auxApellido, auxDni, auxPais, auxDomicilio, auxMedioDePago);

                                clearScreen();
                                System.out.println("El pasajero fue creado Exitosamente");

                            } else {
                                clearScreen();
                                System.out.println("El pasajero ya se encuentra en la lista de pasajeros");
                            }

                            LocalDateTime fechaConHora = fecha.atTime(15, 0);//Momento maximo del check in
                            gestionador.creaReserva(hotel, habitacionAReservar, fechaConHora, cantDiasHospedarse, pasajero, cantPersonas);

                            System.out.print("Reserva Creada\n");
                            enterParaContinuar();
                            break;

                        case (2):// No creamos la reserva
                            System.out.println("Volviendo al menu principal");
                            break;

                        default://Numero mal ingresado
                            System.out.println("Intentelo nuevamente");
                            break;

                    }

                    opcion=1;

                    break;

                case (2)://Terminar Reserva

                    Reserva auxReserva;
                    opcion = 1;
                    do {
                        clearScreen();
                        System.out.print("Terminar Reserva : \n\nIngrese DNI del Pasajero dueño de la reserva : ");
                        auxDni = pideLong();
                        auxReserva = hotel.retornaReservaVigentedelPasajero(auxDni);
                        if (auxReserva == null) {
                            System.out.println("\nNo se encontró la reserva: \n1- Intentarlo nuevamente \n0- Salir");
                            opcion = pideEntero();
                        }
                    } while (opcion != 0 && auxReserva == null);

                    if (opcion != 0) {
                        System.out.println("\nReserva encontrada ");
                        System.out.println("\nEl pasajero desea dar una opinion de su hospedaje ?\n1- Si\n2- No");
                        String opinion = "";
                        if (pideEntero() == 1) {
                            System.out.println("Escriba la opinion del Pasajero :");
                            opinion = input.nextLine();
                        }

                        double montoTotal = gestionador.terminaReserva(auxReserva, opinion);

                        if (montoTotal != auxReserva.getHabitacion().getPrecio() * auxReserva.getCantDiasReserva()) {
                            System.out.println("Se le cobrará medio dia mas en la habitacion por hacer el Check-out fuera del horario");
                        }

                        System.out.println("\n" + auxReserva.getHabitacion().muestraHabitacion());
                        System.out.println("\nEl monto total que debe pagar es de : " + montoTotal + "$");
                        enterParaContinuar();
                    }
                    opcion=1;
                    break;

                case (3)://Ver Reserva

                    do {
                        clearScreen();
                        System.out.println("Ver Reservas : \n\n1- Todas las reservas\n2- Reserva en particular\n0- Salir");
                        Reserva aVer;
                        switch (opcion = pideEntero()) {
                            case (1)://Todas las reservas
                                clearScreen();
                                System.out.println("Todas las reservas :\n\n1- Vigentes\n2- No Vigentes");
                                switch (pideEntero()) {
                                    case (1):
                                        clearScreen();
                                        System.out.println("Reservas Vigentes :");
                                        gestionador.verReservas(hotel.retornaListaReservasVigentes());
                                        enterParaContinuar();
                                        break;
                                    case (2):
                                        clearScreen();
                                        System.out.println("Reservas NO Vigentes :");
                                        gestionador.verReservas(hotel.retornaListaReservasNoVigentes());
                                        enterParaContinuar();
                                        break;
                                    default:
                                        System.out.println("Opcion mal ingresada");
                                }

                                break;
                            case (2)://Reserva en particular
                                clearScreen();
                                System.out.println("Buscar la reserva a ver por :\n\n1- Dni Pasajero\n2- Check-in\n0- Salir");
                                switch (pideEntero()) {

                                    case (1):// Buscar Reserva por DNI
                                        clearScreen();
                                        opcion = 1;
                                        do {
                                            System.out.print("\nIngrese Dni del Pasajero de la Reserva a Ver : ");
                                            aVer = hotel.retornaReservadelPasajero(pideLong());
                                            if (aVer == null) {
                                                System.out.println("\nEl dni ingresado NO es correcto :\n1- Intentar de nuevo\n0- Salir");
                                                opcion = pideEntero();
                                            }
                                        } while (aVer == null && opcion != 0);

                                        if (opcion != 0) {//me voy atras
                                            System.out.println(aVer.toString());

                                            enterParaContinuar();
                                        }
                                        break;

                                    case (2):// Buscar Reserva por Check-in
                                        clearScreen();
                                        opcion = 1;
                                        do {
                                            System.out.print("\nIngrese Check-In de la Reserva : ");
                                            aVer = hotel.retornaReservaPorCheckIn(pideFechaPasada());//Necesito usar este metodo
                                            if (aVer == null) {
                                                System.out.println("La fecha ingresada NO es correcta :\n1- Intentar de nuevo\n0- Salir");
                                                opcion = pideEntero();
                                            }
                                        } while (aVer == null && opcion != 0);

                                        if (opcion != 0) {//me voy atras
                                            System.out.println(aVer.toString());

                                            enterParaContinuar();
                                        }
                                        break;

                                    case (0)://Volver
                                        break;

                                    default:
                                        System.out.println("Opcion mal ingresada, Intente nuevamente");
                                        break;

                                }

                                break;

                            case (0)://Salir
                                break;

                            default:
                                System.out.println("Intentelo nuevamente");
                                break;
                        }
                    } while (opcion != 0);

                    opcion=1;

                    break;

                case (4)://Modificar Reserva

                    Reserva aModificar;
                    clearScreen();
                    do {
                        System.out.print("Ingrese Dni del Pasajero de la Reserva: ");
                        aModificar = hotel.retornaReservaVigentedelPasajero(pideLong());
                        if (aModificar == null) {
                            System.out.println("\nEl dni ingresado NO es correcto :\n1- Intentar de nuevo\n0- Salir");
                            opcion = pideEntero();
                            clearScreen();
                        }
                    } while (aModificar == null && opcion != 0);

                    if (opcion == 0) {//Me voy al menu principal
                        break;
                    }

                    menuModificarReserva(aModificar, gestionador, hotel);

                    opcion=1;

                    break;


                case(0):
                    break;

                default:
                    opcion=1;
                    break;
            }

        }while (opcion!=0);
    }

    public void menuPrincipal(Hotel hotel,Recepcionista gestionador){

        int opcion=0;
        long auxDni=0;

        do {
            //En algun momento cuando el pasajero este en la habitacion, el estado de la habitacion tiene q estar Reservada,
            //Cuando se termina la reserva, esa habitacion tiene q estar en Disponible

            clearScreen();
            System.out.println("Que desea hacer "+gestionador.getNombre());
            System.out.println("\n1- Ver Check-Out's de hoy\n2- Reservas\n3- Habitaciones\n4- Pasajeros");

            if(gestionador instanceof Administrador){
                menuAdministrador( (Administrador) gestionador);
            }

            System.out.println("0- Cerrar Sesion");

            switch (opcionMenuPrincipal(gestionador,4)){

                case(1)://Ver Check-outs de hoy

                    gestionador.verReservas(gestionador.checkearCheckOutsDia(hotel));
                    enterParaContinuar();
                    break;

                case(2)://Reservas

                    menuReservas(hotel,gestionador);
                    break;

                case(3)://Habitaciones



                case(4)://Pasajeros

                    //Modificar un Pasajero

                    Pasajero pasajeroAModificar=null;
                    long dniAux=0;
                    clearScreen();
                    do{
                        System.out.print("Buscando Pasajero a Modificar (0 para salir)\n\nIngrese Dni del Pasajero a modificar : ");
                        dniAux=pideLong();
                        if(dniAux!=0) {
                            pasajeroAModificar = hotel.retornaPasajero(dniAux);
                            if (pasajeroAModificar==null) {
                                System.out.println("El dni ingresado no es correcto");
                            }
                        }
                    }while(pasajeroAModificar==null && dniAux!=0);

                    if(dniAux==0){//Me voy al menu principal
                        break;
                    }
                    menuModificarUsuario(pasajeroAModificar,gestionador);
                    break;

                case(5)://Empleados - Administrador
                    break;

                case(0):
                    break;


                default:
                    System.out.print("Opcion mal ingresada, Intentelo nuevamente : ");
            }


        }while(true);

    }

    public void menuModificarReserva(Reserva aModificar,Recepcionista gestionador,Hotel hotel)
    {
        //Ojo que si modifico algun atributo cambio el id de la reserva

        int opcion = 0;

        do{//Podria agregar eliminar reserva
            clearScreen();
            System.out.println("Que se desea Modificar de la reserva ?\n1- Habitacion (P: "+aModificar.getHabitacion().getPiso()+
                            " | L: "+aModificar.getHabitacion().getLetra()+")\n2- Pasajero (DNI:"+aModificar.getPasajero().getDni()+")" +
                            "\n3- Cancelar un producto supuestamente consumido\n4- Check-In ("+aModificar.getCheckIn().toLocalDate()+")" +
                            "\n5- Cantidad de dias de Hospedaje ("+aModificar.getCantDiasReserva()+")\n0- Salir");

            switch (opcion=pideEntero()) {
                case (1):

                    int piso;
                    char letra;
                    Habitacion habitacionACambiar = null;
                    do {
                        System.out.print("Modificando la Habitacion (-1 para salir)\n\nIngrese Piso y Letra de la habitacion a cambiar: \nPiso: ");
                        piso = pideEntero();
                        if (piso != -1) {
                            System.out.print("Letra: ");
                            letra = pideChar();
                            habitacionACambiar = gestionador.retornaHabitacionDeUnaLista(letra, piso, gestionador.retornaHabitacionesDisponibles(hotel,
                                                                                            aModificar.getCantPersonas(), aModificar.getCheckIn().toLocalDate(),
                                                                                                aModificar.getCantDiasReserva()));

                            if (habitacionACambiar == null) {
                                System.out.println("La habitacion ingresada no esta disponible");
                            }
                        }
                    } while (habitacionACambiar == null && piso != -1);

                    if (piso == -1) {
                        break;//Volvemos al menu principal
                    }

                    gestionador.modificaReserva(aModificar,habitacionACambiar,aModificar.getPasajero(),aModificar.getCheckIn(),
                                                    aModificar.getCantDiasReserva(), aModificar.getCantPersonas());

                    break;
                case (2):

                    menuModificarUsuario(aModificar.getPasajero(),gestionador);

                    break;
                case (3):
                    //Modularizar

                case (4)://Aca se modifica CheckIn

                    LocalDate fecha;
                    boolean ocupada;
                    opcion=0;
                    do{
                        System.out.print("\nModificando el Check-In\n\nIngresar fecha nueva para el Check-in (MM/DD/YYYY): ");
                        fecha = pideFecha();
                        ocupada = gestionador.habitacionOcupada(hotel,aModificar.getCantPersonas(),fecha,aModificar.getCantDiasReserva(),aModificar.getHabitacion());
                        if(ocupada){
                            System.out.println(" - La habitacion para esa fecha esta ocupada - ");
                            System.out.println("Para intentar de nuevo Ingrese 1, para Salir 0");
                            opcion=pideEntero();
                        }
                    }while(opcion!=0 && ocupada);

                    if(opcion==0){
                        break;
                    }else{

                        gestionador.modificaReserva(aModificar,aModificar.getHabitacion(),aModificar.getPasajero(),
                                                        fecha.atTime(14,0),aModificar.getCantDiasReserva(),
                                                            aModificar.getCantPersonas());
                    }
                    break;

                case (5)://Cambiamos cantidad de dias
                    boolean validaDias;
                    int cantDias=0;
                    do{
                        System.out.print("\nCambiando la cantidad de dias a hospedarse\n\nIngresar Cantidad de dias para la reserva: ");
                        cantDias = pideEntero();
                        validaDias = gestionador.habitacionOcupada(hotel,aModificar.getCantPersonas(),aModificar.getCheckIn().toLocalDate(),cantDias,aModificar.getHabitacion());
                        if(validaDias){
                            System.out.println(" - La habitacion para esas fechas estan ocupadas - ");
                            System.out.println("Para intentar de nuevo Ingrese 1, para Salir 0");
                            opcion=pideEntero();
                        }
                    }while(opcion!=0 && validaDias);

                    if(opcion==0){
                        break;
                    }else{

                        gestionador.modificaReserva(aModificar,aModificar.getHabitacion(),aModificar.getPasajero(),
                                pideFecha().atTime(14,0),cantDias,aModificar.getCantPersonas());

                    }
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion != 0);
    }

    public void menuModificarUsuario(Pasajero pasajeroaModificar,Recepcionista gestionador)
    {
        int opcion = 0;

        do{
            clearScreen();
            System.out.println("Que desea modificar del pasajero ?\n1- Nombre ("+pasajeroaModificar.getNombre()+")\n2- Apellido ("+pasajeroaModificar.getApellido()+")"
                    +"\n3- Dni ("+pasajeroaModificar.getDni()+")\n4- Pais De Origen ("+pasajeroaModificar.getPaisDeOrigen()+")\n5- Domicilio ("+pasajeroaModificar.getDomicilio()+")"
                    +"\n6- Forma de Pago ("+pasajeroaModificar.getFormaDePago().toString()+") \n0- Salir");

            switch (opcion=pideEntero()) {
                case (1):
                    System.out.print("\nIngrese Nombre a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,input.nextLine(),pasajeroaModificar.getApellido(),
                                                    pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                        pasajeroaModificar.getDni(), pasajeroaModificar.getFormaDePago());

                    break;
                case (2):
                    System.out.print("\nIngrese Apellido a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),input.nextLine(),
                                                    pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                         pasajeroaModificar.getDni(), pasajeroaModificar.getFormaDePago());
                    break;
                case (3):
                    System.out.print("\nIngrese Dni a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                     pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                        pideLong(), pasajeroaModificar.getFormaDePago());
                    break;
                case (4):
                    System.out.print("\nIngrese Pais de origen a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                    input.nextLine(),pasajeroaModificar.getDomicilio(), pasajeroaModificar.getDni(),
                                                        pasajeroaModificar.getFormaDePago());
                    break;
                case (5):
                    System.out.print("\nIngrese Domicilio a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                     pasajeroaModificar.getPaisDeOrigen(),input.nextLine(), pasajeroaModificar.getDni(),
                                                            pasajeroaModificar.getFormaDePago());
                    break;
                case (6):
                    System.out.println("\nIngrese Modo de pago a modificar: ");
                    System.out.println("1- Debito\n2- Credito\n3- Efectivo-Peso\n4- Efectivo-Dolar");
                    MedioDePago auxMedioDePago = MedioDePago.DEBITO;//Solo para inicializar
                    switch (pideEntero()) {
                        case (1):
                            break;
                        case (2):
                            auxMedioDePago = MedioDePago.CREDITO;
                            break;
                        case (3):
                            auxMedioDePago = MedioDePago.EFECTIVO_PESOARGENTINO;
                            break;
                        case (4):
                            auxMedioDePago = MedioDePago.EFECTIVO_DOLAR;
                            break;
                    }
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                    pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                        pasajeroaModificar.getDni(), auxMedioDePago);
                    break;
                case(0):
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion!=0);
    }

}
