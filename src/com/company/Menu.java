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

    public double pideDouble(){
        String futuroDouble = "";
        do
        {
            futuroDouble = input.nextLine();

        }while(!esDouble(futuroDouble));

        return Double.parseDouble(futuroDouble);
    }
    public static boolean esDouble(String aValidar){
        double esDouble;
        try {
            esDouble=Double.parseDouble(aValidar);
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

        char inputChar ;
        do
        {
            inputChar = (input.nextLine()).charAt(0);

        }while(!Character.isLetter(inputChar));

        return Character.toUpperCase(inputChar);
    }

    public void enterParaContinuar(){
        System.out.println("\nEnter para continuar");
        input.reset();
        input.nextLine();
        input.reset();
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

    public void muestraHabitacionesConEstado(ArrayList<Habitacion> habitaciones) {//Cambiar de clase

        for (Habitacion auxHabitacion : habitaciones) {
            System.out.println(auxHabitacion.toString());
        }
    }

    public TipoHabitacion menuEstablecerPrecioPorTipoHabitacion(ArrayList<TipoHabitacion> tiposHabitacion){

        System.out.println("Ingrese Tipo de Habitacion a Cambiar Precio : \n");
        int i = 0;
        int opcion;
        for (TipoHabitacion aux : tiposHabitacion){
            System.out.println( (i+1) + "- " + aux.getTipo() );
            i++;
        }
        System.out.println("0- Volver");
        do {
            opcion=pideEntero();
            opcion--;
        }while (opcion<-1 || opcion>=i);

        if(opcion==-1){
            return null;//Volviendo
        }else {
            return tiposHabitacion.get(opcion);
        }
    }

    public static void clearScreen() {
        System.out.print("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
                "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n" +
                "\r\n--------------- Hotel Trivago ---------------\r\n\r\n");
    }

    public EstadoHabitacion menuEstadoHabitacion(Habitacion auxHabitacion){

        int opcion;

        do {
            System.out.println(auxHabitacion.toString());

            System.out.print("\nIngrese el nuevo Estado para la habitacion : ");

            System.out.println("\n\n1- Reservada\n2- Disponible\n3- En Reparacion\n4- En Desinfección;");

            switch (opcion=pideEntero()) {

                case (1):
                    return EstadoHabitacion.RESERVADA;
                case (2):
                    return EstadoHabitacion.DISPONIBLE;
                case (3):
                    return EstadoHabitacion.EN_REPARACION;
                case (4):
                    return EstadoHabitacion.EN_DESINFECCION;
                default:
                    break;
            }

            clearScreen();

        }while (true);


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


                                pasajero = gestionador.crearUnNuevoUsuario(hotel, auxNombre, auxApellido, auxDni, auxPais, auxDomicilio);

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

                    //opcion=1;

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
                            System.out.println("\nNo se encontró la reserva: \n\n1- Intentarlo nuevamente \n0- Volver");
                            opcion = pideEntero();
                        }
                    } while (opcion != 0 && auxReserva == null);

                    if (opcion != 0) {
                        clearScreen();
                        System.out.println("\nReserva encontrada ");
                        System.out.println("\nEl pasajero desea dar una opinion de su hospedaje ?\n1- Si\n2- No");
                        String opinion = "";
                        if (pideEntero() == 1) {
                            System.out.print("\nEscriba la opinion del Pasajero :");
                            opinion = input.nextLine();
                        }

                        double montoTotal = gestionador.terminaReserva(auxReserva, opinion);

                        if (montoTotal != auxReserva.getHabitacion().getPrecio() * auxReserva.getCantDiasReserva()) {
                            System.out.println("Se le cobrará medio dia mas en la habitacion por hacer el Check-out fuera del horario");
                        }

                        clearScreen();
                        System.out.println("\n" + auxReserva.getHabitacion().muestraHabitacion());
                        System.out.println("\nEl monto total que debe pagar es de : " + montoTotal + "$");
                        enterParaContinuar();//Se me buguea aca
                    }
                    opcion=1;
                    break;

                case (3)://Ver Reserva

                    do {
                        clearScreen();
                        System.out.println("Ver Reservas : \n\n1- Todas las reservas\n2- Reserva en particular\n0- Volver");
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
                                System.out.println("Buscar la reserva a ver por :\n\n1- Dni Pasajero\n2- Check-in\n0- Volver");
                                switch (pideEntero()) {

                                    case (1):// Buscar Reserva por DNI
                                        clearScreen();
                                        opcion = 1;
                                        do {
                                            System.out.print("\nIngrese Dni del Pasajero de la Reserva a Ver : ");
                                            aVer = hotel.retornaReservadelPasajero(pideLong());
                                            if (aVer == null) {
                                                System.out.println("\nEl dni ingresado NO es correcto :\n1- Intentar de nuevo\n0- Volver");
                                                opcion = pideEntero();
                                            }
                                        } while (aVer == null && opcion != 0);

                                        if (opcion != 0) {//me voy atras
                                            System.out.println(aVer.toString());

                                            enterParaContinuar();
                                        }
                                        break;

                                    case (2):// Buscar Reserva por Check-in
                                        opcion = 1;
                                        do {
                                            clearScreen();
                                            System.out.print("\nIngrese Check-In de la Reserva : ");
                                            aVer = hotel.retornaReservaPorCheckIn(pideFechaPasada());//Necesito usar este metodo
                                            if (aVer == null) {
                                                System.out.println("\nLa fecha ingresada NO es correcta :\n\n1- Intentar de nuevo\n0- Volver");
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
                    do {
                        clearScreen();
                        System.out.print("Ingrese Dni del Pasajero de la Reserva: ");
                        aModificar = hotel.retornaReservaVigentedelPasajero(pideLong());
                        if (aModificar == null) {
                            System.out.println("\nEl dni ingresado NO es correcto :\n\n1- Intentar de nuevo\n0- Volver");
                            opcion = pideEntero();
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
                    break;
            }

        }while (opcion!=0);
    }

    public void menuHabitaciones(Hotel hotel, Recepcionista gestionador){

        int opcion=0;
        long auxDni=0;
        LocalDate fecha;
        Habitacion auxHabitacion;
        ArrayList<Habitacion> auxHabitaciones;

        do{
            clearScreen();
            System.out.println("Habitaciones :\n\n1- Ver Habitaciones\n2- Estados de la habitacion");

            if(gestionador instanceof Administrador){
                System.out.println("3- Modificar Precios");
            }
            System.out.println("0- Volver");

            switch (opcion = opcionMenuPrincipal(gestionador,2)){

                case(1)://Ver todas las habitaciones
                    clearScreen();
                    System.out.println("Todas las Habitaciones : \n");
                    muestraHabitacionesConEstado(hotel.getListaHabitaciones());
                    enterParaContinuar(); //Se me buguea aca
                    break;
                case(2)://Estados de la habitacion

                    clearScreen();
                    System.out.println("Estados de una habitacion : \n\n1- Cambiar estado de una habitacion\n2- Habitaciones Ocupadas\n3- Habitaciones Disponibles\n0- Volver");
                    switch (opcion=pideEntero()){
                        case(1)://Cambiar un estado de una habitacion, Solo ingresar por Piso y Letra
                            int auxPiso;
                            char auxLetra;
                            do {
                                clearScreen();
                                opcion=1;
                                System.out.print("Ingrese El piso y la Letra de la habitacion a cambiar el Estado :\n\nPiso : ");
                                auxPiso=pideEntero();
                                System.out.print("Letra : ");
                                auxLetra=pideChar();
                                auxHabitacion = hotel.retornaHabitacion(auxPiso,auxLetra);
                                if(auxHabitacion!=null){

                                    clearScreen();
                                    gestionador.modificaEstadoHabitacion(menuEstadoHabitacion(auxHabitacion),auxHabitacion);

                                }else{
                                    System.out.println("\nNo se encontro la habitacion\n\n1- Intentar nuevamente\n0- Volver");
                                    opcion=pideEntero();
                                }
                            }while (auxHabitacion==null && opcion!=0);
                            //opcion=1;
                            break;
                        case(2)://Habitaciones Ocupadas, Ingresar Fecha, CantPersonas=0

                            clearScreen();
                            System.out.println("Ingrese la fecha para Ver las Habitaciones Ocupadas : ");
                            fecha = pideFecha();
                            auxHabitaciones = gestionador.retornaHabitacionesNoDisponibles(hotel, 0, fecha, 1);
                            System.out.println("Habitaciones No Disponibles en el dia "+fecha+" : ");
                            muestraHabitacionesConEstado(auxHabitaciones);
                            enterParaContinuar();

                            break;
                        case(3)://Habitaciones Disponibles, Ingresar Fecha, CantPersonas=0


                            clearScreen();
                            System.out.println("Ingrese la fecha para Ver las Habitaciones Disponibles : ");
                            fecha = pideFecha();
                            auxHabitaciones = gestionador.retornaHabitacionesDisponibles(hotel, 0, fecha, 1);
                            System.out.println("Habitaciones Disponibles en el dia "+fecha+" : ");
                            muestraHabitacionesConEstado(auxHabitaciones);
                            enterParaContinuar();

                            break;
                    }
                    opcion=1;
                    break;
                case(3)://Modificar Precios
                    do {
                        clearScreen();
                        System.out.println("Modificar Precios :\n1- Por Tipo\n2- Por Piso y Letra\n0- Volver");
                        switch (opcion = pideEntero()) {
                            case (1)://Por Tipo
                                TipoHabitacion auxTipoHabitacion;
                                clearScreen();
                                auxTipoHabitacion=menuEstablecerPrecioPorTipoHabitacion(hotel.retornaTiposHabitacionesDeUnHotel());
                                if(auxTipoHabitacion != null){
                                    clearScreen();
                                    System.out.print("Ingrese el nuevo Precio para las habitaciones "+auxTipoHabitacion.getTipo()+" : ");
                                    ((Administrador)gestionador).establecePrecioHabitacionPorTipo(hotel,auxTipoHabitacion,input.nextDouble());
                                    enterParaContinuar();
                                }
                                break;
                            case (2)://Por Piso y Letra
                                int auxPiso;
                                char auxLetra;
                                do {
                                    clearScreen();
                                    opcion=1;
                                    System.out.print("Ingrese El piso y la Letra de la habitacion a cambiar el Precio : \nPiso : ");
                                    auxPiso=pideEntero();
                                    System.out.print("\nLetra : ");
                                    auxLetra=pideChar();
                                    auxHabitacion = hotel.retornaHabitacion(auxPiso,auxLetra);
                                    if(auxHabitacion!=null){
                                        System.out.println(auxHabitacion.muestraHabitacion());
                                        System.out.print("\nIngrese el nuevo Precio para esta habitacion : ");
                                        ((Administrador)gestionador).establecePrecioHabitacion(auxHabitacion,input.nextDouble());
                                        enterParaContinuar();
                                    }else{
                                        System.out.println("\nNo se encontro la habitacion\n\n1- Intentar nuevamente\n0- Volver");
                                        opcion=pideEntero();
                                    }
                                }while (auxHabitacion==null && opcion!=0);
                                break;
                            case (0)://Volver
                                break;
                        }
                        break;
                    }while(opcion!=0);

                case(0):
                    break;

                default:
                    break;

            }

        }while(opcion!=0);
    }

    public void menuPasajeros(Hotel hotel,Recepcionista gestionador){

        int opcion=0;
        Pasajero auxPasajero=null;

        do{
            clearScreen();
            System.out.println("Pasajeros : \n\n1- Modificar\n2- Ver\n3- Historial");
            if(gestionador instanceof Administrador)
                System.out.println("4- Eliminar");

            System.out.println("0- Volver");

            switch (opcion=opcionMenuPrincipal(gestionador,3)){

                case(1)://Modificar Pasajero
                    do{
                        clearScreen();
                        System.out.print("Modificar un Pasajero : \n\nIngrese Dni del Pasajero a modificar : ");
                        auxPasajero = hotel.retornaPasajero(pideLong());
                        if (auxPasajero==null) {
                            System.out.println("El dni ingresado no es correcto\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }
                    }while(auxPasajero==null && opcion!=0);

                    if(opcion==0){
                        opcion=1;
                        break;
                    }

                    menuModificarUsuario(auxPasajero,gestionador);

                    break;

                case(2)://Ver los pasajeros
                    clearScreen();
                    System.out.println("Ver Pasajeros :\n\n1- Todos\n2- DNI");
                    switch (opcion=pideEntero()){
                        case(1)://Todos los pasajeros

                            clearScreen();
                            System.out.println("Todos los Pasajeros :\n");
                            gestionador.verPasajeros(hotel.getListaPasajeros());
                            enterParaContinuar();
                            break;

                        case(2)://Ver pasajero por DNI
                            do{
                                clearScreen();
                                System.out.print("Ver un Pasajero por DNI : \n\nIngrese Dni del Pasajero a ver : ");
                                auxPasajero = hotel.retornaPasajero(pideLong());
                                if (auxPasajero==null) {
                                    System.out.println("\nEl dni ingresado no es correcto\n\n1- Intentar de nuevo\n0- Volver");
                                    opcion=pideEntero();
                                }
                            }while(auxPasajero==null && opcion!=0);

                            if (opcion==0){
                                opcion=1;
                                break;
                            }else{

                                clearScreen();
                                System.out.println("Pasajero encontrado :\n");
                                System.out.println(auxPasajero.toString());
                                enterParaContinuar();

                            }

                            break;
                        default:
                            break;
                    }
                    break;
                case(3)://Historial de un Pasajero por Dni
                    do{
                        clearScreen();
                        System.out.print("Historial de un Pasajero : \n\nIngrese Dni del Pasajero a ver : ");
                        auxPasajero = hotel.retornaPasajero(pideLong());
                        if (auxPasajero==null) {
                            System.out.println("\nEl dni ingresado no es correcto\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }
                    }while(auxPasajero==null && opcion!=0);

                    if (opcion==0){
                        opcion=1;
                        break;
                    }else{
                        clearScreen();
                        System.out.println("Historial de "+auxPasajero.getNombre()+" "+auxPasajero.getApellido()+" :\n");
                        gestionador.verReservas(hotel.historialDeUnPasajero(auxPasajero.getDni()));
                        enterParaContinuar();
                    }

                    break;

                case(4)://Eliminar un pasajero
                    do{
                        clearScreen();
                        System.out.print("Eliminar un Pasajero : \n\nIngrese Dni del Pasajero a eliminar : ");
                        auxPasajero = hotel.retornaPasajero(pideLong());
                        if (auxPasajero==null) {
                            System.out.println("\nEl dni ingresado no es correcto\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }
                    }while(auxPasajero==null && opcion!=0);

                    if (opcion==0){
                        opcion=1;
                        break;
                    }else{
                        clearScreen();
                        ((Administrador)gestionador).eliminarPasajero(hotel,auxPasajero.getDni());
                        System.out.println("El Pasajero "+auxPasajero.getNombre()+" "+auxPasajero.getApellido()+" fue eliminado");
                        enterParaContinuar();
                    }
                    break;

                case(0):
                    break;

                default:
                    break;


            }

        }while(opcion!=0);

    }

    public void menuModificarEmpleado(Recepcionista aModificar,Administrador gestionador){

        int opcion = 0;

        do{//Funcionará modificar un Administrador ?
            clearScreen();
            System.out.println("Que desea modificar del Empleado ?\n1- Nombre ("+aModificar.getNombre()+")\n2- Apellido ("+aModificar.getApellido()+")"
                    +"\n3- Dni ("+aModificar.getDni()+")\n4- Sueldo ("+aModificar.getSueldo()+"$)\n5- Contrasenia ("+aModificar.getContrasenia()+")"
                    +" \n0- Volver");

            switch (opcion=pideEntero()) {
                case (1):
                    System.out.print("\nIngrese Nombre a modificar: ");
                    gestionador.modificaEmpleado(aModificar,input.nextLine(),aModificar.getApellido(),
                                                    aModificar.getDni(),aModificar.getSueldo(),aModificar.getContrasenia());

                    break;
                case (2):
                    System.out.print("\nIngrese Apellido a modificar: ");
                    gestionador.modificaEmpleado(aModificar,aModificar.getNombre(),input.nextLine(),aModificar.getDni(),
                                                    aModificar.getSueldo(),aModificar.getContrasenia());
                    break;
                case (3):
                    System.out.print("\nIngrese Dni a modificar: ");
                    gestionador.modificaEmpleado(aModificar,aModificar.getNombre(),aModificar.getApellido(),pideLong(),
                                                    aModificar.getSueldo(),aModificar.getContrasenia());
                    break;
                case (4):
                    System.out.print("\nIngrese Sueldo a modificar: ");
                    gestionador.modificaEmpleado(aModificar,aModificar.getNombre(),aModificar.getApellido(),
                                                    aModificar.getDni(),pideDouble(),aModificar.getContrasenia());
                    break;
                case (5):
                    System.out.print("\nIngrese Contrasenia a modificar: ");
                    gestionador.modificaEmpleado(aModificar,aModificar.getNombre(),aModificar.getApellido(),
                                                    aModificar.getDni(),aModificar.getSueldo(),input.nextLine());
                    break;

                case(0):
                    break;

                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion!=0);

    }

    public void menuEmpleados(Hotel hotel,Administrador gestionador){

        int opcion = 0;

        do{
            clearScreen();
            System.out.println("Empleados : \n\n1- Agregar\n2- Modificar\n3- Ver\n0- Volver");
            switch (opcion=pideEntero()){

                case(1)://Agregar un empleado
                    do {
                        clearScreen();
                        System.out.println("Agregar un empleado:\n\n1- Recepcionista\n2- Administrador\n0- Volver");
                        long auxDni;
                        String auxNombre;
                        String auxApellido;
                        double sueldo;
                        String contrasenia;
                        switch (opcion = pideEntero()) {
                            case (1)://Agregar Recepcionista
                                clearScreen();
                                System.out.println("Creando un Recepcionista :");
                                System.out.print("\nIngrese DNI del Recepcionista: ");
                                auxDni = pideLong();
                                System.out.print("Ingrese el Nombre del Recepcionista: ");
                                auxNombre = input.nextLine();
                                System.out.print("Ingrese el Apellido del Recepcionista: ");
                                auxApellido = input.nextLine();
                                System.out.print("Ingrese el Sueldo del Recepcionista: ");
                                sueldo = pideDouble();
                                System.out.print("Ingrese la Contrasenia del Recepcionista: ");
                                contrasenia = input.nextLine();
                                gestionador.crearUnNuevoRecepcionista(hotel,auxNombre,auxApellido,auxDni,sueldo,contrasenia);
                                System.out.println("\n\nEl Recepcionista fue creado Exitosamente");
                                enterParaContinuar();
                                break;
                            case (2)://Agregar Administrador
                                clearScreen();
                                System.out.println("Creando un Administrador :");
                                System.out.print("\nIngrese DNI del Administrador: ");
                                auxDni = pideLong();
                                System.out.print("Ingrese el Nombre del Administrador: ");
                                auxNombre = input.nextLine();
                                System.out.print("Ingrese el Apellido del Administrador: ");
                                auxApellido = input.nextLine();
                                System.out.print("Ingrese el Sueldo del Administrador: ");
                                sueldo = pideDouble();
                                System.out.print("Ingrese la Contrasenia del Administrador: ");
                                contrasenia = input.nextLine();
                                gestionador.crearUnNuevoAdministrador(hotel,auxNombre,auxApellido,auxDni,sueldo,contrasenia);
                                System.out.println("\n\nEl Administrador fue creado Exitosamente");
                                enterParaContinuar();
                                break;
                            case (0)://Volver
                                break;
                            default:
                                break;
                        }
                    }while (opcion!=0);
                    opcion=1;
                    break;
                case(2)://Modificar un Empleado

                    Recepcionista aModificar;
                    do{
                        clearScreen();
                        System.out.print("Modificar un Empleado : \n\nIngrese Dni del Empleado a modificar : ");
                        aModificar = hotel.retornaEmpleado(pideLong());
                        if (aModificar==null) {
                            System.out.println("El dni ingresado no es correcto\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }
                    }while(aModificar==null && opcion!=0);

                    if(opcion==0){
                        opcion=1;
                        break;
                    }

                    menuModificarEmpleado(aModificar,gestionador);

                    break;

                case(3)://Ver Empleados Todos - Dni
                    Recepcionista aVer;

                    do {
                        clearScreen();
                        System.out.println("Ver Empleados:\n\n1- Todos\n2- Por Dni\n0- Volver");
                        switch (opcion = pideEntero()) {

                            case (1)://Todos
                                clearScreen();
                                System.out.println("Todos los Empleados :");
                                gestionador.verEmpleados(hotel.listaEmpleados);
                                enterParaContinuar();
                                break;

                            case (2)://Por dni
                                do{
                                    clearScreen();
                                    System.out.print("Ver un Empleado : \n\nIngrese Dni del Empleado a ver: ");
                                    aVer = hotel.retornaEmpleado(pideLong());
                                    if (aVer==null) {
                                        System.out.println("\nEl dni ingresado no es correcto\n\n1- Intentar de nuevo\n0- Volver");
                                        opcion=pideEntero();
                                    }
                                }while(aVer==null && opcion!=0);

                                if(opcion==0){
                                    opcion=1;
                                    break;
                                }else{
                                    clearScreen();
                                    System.out.println("Empleado encontrado :");
                                    System.out.println(aVer.toString());
                                    enterParaContinuar();
                                }

                                break;

                            case (0)://Volver
                                break;

                            default:
                                break;

                        }
                    }while (opcion!=0);

                    opcion=1;

                    break;

                case(0):
                    break;

            }



        }while(opcion!=0);
    }

    public void menuPrincipal(Hotel hotel,Recepcionista gestionador){

        int opcion=0;

        do {
            clearScreen();
            System.out.println("Que desea hacer "+gestionador.getNombre());
            System.out.println("\n1- Ver Check-Out's de hoy\n2- Reservas\n3- Habitaciones\n4- Pasajeros");

            if(gestionador instanceof Administrador){
                System.out.println("5- Empleados");
            }

            System.out.println("0- Cerrar Sesion");

            switch (opcion=opcionMenuPrincipal(gestionador,4)){

                case(1)://Ver Check-outs de hoy

                    clearScreen();
                    System.out.println("Check-Outs De hoy :\n");
                    gestionador.verReservas(gestionador.checkearCheckOutsDia(hotel));
                    enterParaContinuar();
                    break;

                case(2)://Reservas

                    menuReservas(hotel,gestionador);
                    break;

                case(3)://Habitaciones

                    menuHabitaciones(hotel,gestionador);
                    break;

                case(4)://Pasajeros

                    menuPasajeros(hotel,gestionador);
                    break;

                case(5)://Empleados - Administrador
                    menuEmpleados(hotel,(Administrador) gestionador);
                    break;

                case(0):
                    break;


                default:
                    System.out.print("Opcion mal ingresada, Intentelo nuevamente : ");
                    break;
            }


        }while(opcion!=0);

    }

    public void menuModificarReserva(Reserva aModificar,Recepcionista gestionador,Hotel hotel)
    {
        //Ojo que si modifico algun atributo cambio el id de la reserva

        int opcion = 0;

        do{//Podria agregar eliminar reserva
            clearScreen();
            System.out.println("Que se desea Modificar de la reserva ?\n1- Habitacion (P: "+aModificar.getHabitacion().getPiso()+
                            " | L: "+aModificar.getHabitacion().getLetra()+")\n2- Pasajero (DNI:"+aModificar.getPasajero().getDni()+")" +
                            "\n3- Check-In ("+aModificar.getCheckIn().toLocalDate()+")" + "\n4- Cantidad de dias de Hospedaje ("
                            +aModificar.getCantDiasReserva()+")\n5- El pasajero entro a la habitacion (Estado:"
                            +aModificar.getHabitacion().getEstadoHabitacion()+"\n0- Volver");

            switch (opcion=pideEntero()) {
                case (1):

                    int piso;
                    char letra;
                    Habitacion habitacionACambiar = null;
                    do {
                        clearScreen();
                        System.out.print("Modificando la Habitacion \n\nIngrese Piso y Letra de la habitacion a cambiar: \nPiso: ");
                        piso = pideEntero();
                        System.out.print("Letra: ");
                        letra = pideChar();
                        habitacionACambiar = gestionador.retornaHabitacionDeUnaLista(letra, piso, gestionador.retornaHabitacionesDisponibles(hotel,
                                                                                            aModificar.getCantPersonas(), aModificar.getCheckIn().toLocalDate(),
                                                                                                aModificar.getCantDiasReserva()));

                        if (habitacionACambiar == null) {
                            System.out.println("La habitacion ingresada no esta disponible\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }

                    } while (habitacionACambiar == null && opcion!=0);

                    if (opcion == 0) {
                        opcion=1;
                        break;//Volvemos al menu principal
                    }

                    gestionador.modificaReserva(aModificar,habitacionACambiar,aModificar.getPasajero(),aModificar.getCheckIn(),
                                                    aModificar.getCantDiasReserva(), aModificar.getCantPersonas());

                    break;
                case (2):

                    menuModificarUsuario(aModificar.getPasajero(),gestionador);

                    break;

                case (3)://Aca se modifica CheckIn

                    LocalDate fecha;
                    boolean ocupada;
                    opcion=0;
                    do{
                        System.out.print("\nModificando el Check-In\n\nIngresar fecha nueva para el Check-in (MM/DD/YYYY): ");
                        fecha = pideFecha();
                        ocupada = gestionador.habitacionOcupada(hotel,aModificar.getCantPersonas(),fecha,aModificar.getCantDiasReserva(),aModificar.getHabitacion());
                        if(ocupada){
                            System.out.println("La habitacion ingresada no esta disponible\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }
                    }while(opcion!=0 && ocupada);

                    if(opcion==0){
                        opcion=1;
                        break;
                    }else{

                        gestionador.modificaReserva(aModificar,aModificar.getHabitacion(),aModificar.getPasajero(),
                                                        fecha.atTime(15,0),aModificar.getCantDiasReserva(),
                                                            aModificar.getCantPersonas());
                    }
                    break;

                case (4)://Cambiamos cantidad de dias
                    boolean validaDias;
                    int cantDias=0;
                    do{
                        System.out.print("\nCambiando la cantidad de dias a hospedarse\n\nIngresar Cantidad de dias para la reserva: ");
                        cantDias = pideEntero();
                        validaDias = gestionador.habitacionOcupada(hotel,aModificar.getCantPersonas(),aModificar.getCheckIn().toLocalDate(),cantDias,aModificar.getHabitacion());
                        if(validaDias){
                            System.out.println("La habitacion ingresada no esta disponible\n\n1- Intentar de nuevo\n0- Volver");
                            opcion=pideEntero();
                        }
                    }while(opcion!=0 && validaDias);

                    if(opcion==0){
                        opcion=1;
                        break;
                    }else{

                        gestionador.modificaReserva(aModificar,aModificar.getHabitacion(),aModificar.getPasajero(),
                                pideFecha().atTime(15,0),cantDias,aModificar.getCantPersonas());

                    }
                    break;

                case(5)://El pasajero entró a la habitacion
                    aModificar.actualizaEstadoHabitacion();
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
                    +" \n0- Volver");

            switch (opcion=pideEntero()) {
                case (1):
                    System.out.print("\nIngrese Nombre a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,input.nextLine(),pasajeroaModificar.getApellido(),
                                                    pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                        pasajeroaModificar.getDni());

                    break;
                case (2):
                    System.out.print("\nIngrese Apellido a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),input.nextLine(),
                                                    pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                         pasajeroaModificar.getDni());
                    break;
                case (3):
                    System.out.print("\nIngrese Dni a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                     pasajeroaModificar.getPaisDeOrigen(),pasajeroaModificar.getDomicilio(),
                                                        pideLong());
                    break;
                case (4):
                    System.out.print("\nIngrese Pais de origen a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                    input.nextLine(),pasajeroaModificar.getDomicilio(), pasajeroaModificar.getDni());
                    break;
                case (5):
                    System.out.print("\nIngrese Domicilio a modificar: ");
                    gestionador.modificaPasajero(pasajeroaModificar,pasajeroaModificar.getNombre(),pasajeroaModificar.getApellido(),
                                                     pasajeroaModificar.getPaisDeOrigen(),input.nextLine(), pasajeroaModificar.getDni());
                    break;

                case(0):
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion!=0);
    }

}
