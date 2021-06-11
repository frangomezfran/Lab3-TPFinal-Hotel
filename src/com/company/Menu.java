package com.company;

import com.company.Personal.*;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

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

            System.out.println("ERROR acaba de ingresar un valor incorrecto");

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
                //Si el profe le pinta poner el 31 de febrero con esto safo
                System.out.println("Ingreso mal la fecha, Intente de nuevo");
                return null;
            }
            return fecha;
        }catch (DateTimeParseException e){
            System.out.println("Ingreso mal la fecha, Intente de nuevo");
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

        long esEntero;
        try {
            esEntero=Long.parseLong(aValidar);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("ERROR acaba de ingresar un valor incorrecto");
            return false;
        }

    }

    public char pideChar(){

        Character inputChar ;
        do
        {
            inputChar = (input.next()).charAt(0);

        }while(!Character.isLetter(inputChar));

        return inputChar;
    }




    public boolean validaOpcionConSizeArraylist(int opcion , int size){

        if(opcion < size && opcion > -1){
            return true;
        }
        return false;

    }

    public Recepcionista eligeGestionadorSistema(Hotel hotel){

       int i = 0;

        System.out.println("Seleccione el Recepcionista que gestiona el sistema: ");

        for(Recepcionista gestionador : hotel.getListaEmpleados()){

            System.out.println(i+")"+gestionador.getNombre()+" "+gestionador.getApellido());
            i++;
        }

        do{
            i=pideEntero();

        }while( !validaOpcionConSizeArraylist( i , hotel.getListaEmpleados().size() ) );

        System.out.println("Usted es "+hotel.getListaEmpleados().get(i).getNombre());

        return hotel.getListaEmpleados().get(i);

    }

    public void muestraHabitaciones(ArrayList<Habitacion> habitaciones){

        for(Habitacion auxHabitacion : habitaciones){
            System.out.println(auxHabitacion.toString());
        }
    }

    public Habitacion retornaHabitacionDisponible(char letra,int piso,ArrayList<Habitacion> habitaciones){
        //Es para verificar el piso la letra en la lista de las habitaciones disponibles
        for(Habitacion aux: habitaciones){
            if(aux.getPiso() == piso && aux.getLetra()==letra){
                return aux;
            }
        }
        return null;
    }

    public void menuAdministrador(Hotel hotel, Administrador gestionador){

        menuRecepcionista(hotel,gestionador);
        System.out.println("N- Modificar precios de las habitaciones\nN- Agregar un nuevo recepcionista\n" +
                "N- Agregar un nuevo Administrador\nN- Eliminar algun empleado\nN- Editar un Empleado\n" +
                "N- ");

    }

    public void menuRecepcionista(Hotel hotel,Recepcionista gestionador){

        int opcion=0;

        do {
            //En algun momento cuando el pasajero este en la habitacion, el estado de la habitacion tiene q estar Reservada,
            //Cuando se termina la reserva, esa habitacion tiene q estar en Disponible

            System.out.println("Que desea hacer "+gestionador.getNombre());
            System.out.println("1- Crear Nueva Reserva\n2- Terminar una reserva\n3- Ver/Modificar Reservas\n4- Modificar Usuario\n" +
                    "5- Ver precios habitaciones\n6- Ver Habitaciones disponibles para hoy\n7- Ver Checkouts de hoy" +
                    "\n8- Actualizar lista de productos de una habitacion\n9- Modificar estado de una habitacion de hoy" +
                    "\n10-");

            switch (pideEntero()){

                case(1)://Crear Reserva
                    System.out.println("Habitaciones disponibles: \n " +
                            "Ingrese cantidad de personas");
                    int cantPersonas = pideEntero();
                    System.out.println("Ingrese la fecha del posible CheckIn (MM/DD/YYYY)");
                    LocalDate fecha = pideFecha();
                    System.out.println("Ingrese la cantidad de dias a hospedarse");
                    int cantDiasHospedarse = pideEntero();

                    ArrayList<Habitacion> auxHabitacionesDisponibles = gestionador.retornaHabitacionesDisponibles(hotel,cantPersonas,fecha,cantDiasHospedarse);
                    muestraHabitaciones( auxHabitacionesDisponibles );

                    System.out.println("Creamos la reserva ?\n1- Si\n2-No");
                    switch (opcion = pideEntero()) {
                        case (1):
                            int piso;
                            char letra;
                            Habitacion habitacionAReservar;
                            do {
                                System.out.println("Ingrese Piso y Letra de la habitacion a reservar: \nPiso: ");
                                piso = pideEntero();
                                System.out.println("Letra: ");
                                letra = pideChar();
                                habitacionAReservar = retornaHabitacionDisponible(letra,piso,auxHabitacionesDisponibles);
                            }while (habitacionAReservar==null);

                            System.out.println("Ingrese Dni de un Pasajero: ");
                            long auxDni = pideLong();
                            System.out.println(auxDni);

                            Pasajero pasajero = hotel.retornaPasajero(auxDni);

                            if(pasajero==null){

                                System.out.println("El pasajero debe ser creado :");
                                System.out.println("Ingrese el Nombre del Pasajero");
                                String auxNombre = input.nextLine();
                                //El hijo de elon musk se llama "X Æ a XII" Musk,
                                // asique se permite caracteres extraños
                                System.out.println("Ingrese el Apellido del Pasajero");
                                String auxApellido = input.nextLine();
                                System.out.println("Ingrese el Pais de origen del Pasajero");
                                String auxPais = input.nextLine();
                                System.out.println("Ingrese el Domicilio del Pasajero");
                                String auxDomicilio=input.nextLine();
                                System.out.println("Ingrese el Medio de pago del Pasajero: \n1-Credito\n2-Debito\n3-Efectivo-Dolar\n4-Efectivo-PesoArgentino");
                                MedioDePago auxMedioDePago = MedioDePago.DEBITO ;//Solo es para inicializar
                                do{
                                    switch (opcion=pideEntero()) {
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

                                }while (opcion<1 || opcion>4);

                                pasajero = gestionador.crearUnNuevoUsuario(hotel,auxNombre,auxApellido,auxDni,auxPais,auxDomicilio,auxMedioDePago);

                                System.out.println("El pasajero fue creado Exitosamente");

                            }else {
                                System.out.println("El pasajero ya se encuentra en la lista de pasajeros");
                            }

                            LocalDateTime fechaConHora = fecha.atTime(14,0);//Momento del check in
                            gestionador.creaReserva(hotel,habitacionAReservar,fechaConHora,cantDiasHospedarse,pasajero,cantPersonas);

                            System.out.println("Reserva Creada");
                            break;

                            case (2):// Np creamos la reserva
                                System.out.println("Volviendo al menu principal");
                                break;

                            default://Numero mal ingresado
                                System.out.println("Intentelo nuevamente");
                                break;

                        }

                    break;

                case(2)://Termina reserva

                    //gestionador.terminaReserva(hotel);

                    break;

                case(3)://Modifica-Ver Reservas Vigentes

                    long auxDni;
                    Reserva aModificar;
                    do {
                        System.out.println("Ingrese Dni del Pasajero de la Reserva (0 para salir)");
                        auxDni=pideLong();
                        System.out.println(auxDni);
                        aModificar = hotel.retornaReservaVigentedelPasajero(auxDni);
                        if(aModificar==null && auxDni!=0 ){
                            System.out.println("El dni ingresado no es correcto");
                        }
                    }while(aModificar==null && auxDni!=0);

                    if(auxDni==0){//Me voy al menu principal
                        break;
                    }

                    modificarReserva(aModificar,gestionador,hotel);


                    break;

                case(4)://Modificar un usuario

                    Pasajero pasajeroAModificar=null;
                    long dniAux=0;
                    do{
                        System.out.println("Ingrese Dni del Pasajero a modificar (0 para salir)");
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
                    modificarUsuario(hotel,pasajeroAModificar);
                    break;

                case(5)://Ver precios de las habitaciones
                    break;

                case(6)://Ver habitaciones disponibles para hoy
                    break;

                case(7)://Ver Check-Outs para Hoy
                    break;

                case(8)://Actualizar lista de productos de una habitacion q esta ocupada
                    break;

                case(9)://Modificar estado de una habitacion
                    break;

                case(10):
                    break;

                default:
                    System.out.println("Opcion mal ingresada, Intentelo de nuevo");
            }


        }while(true);

    }

    public void modificarReserva(Reserva aModificar,Recepcionista gestionador,Hotel hotel)
    {
        //Ojo que si modifico algun atributo cambio el id de la reserva

        int opcion = 0;

        do{//Podria agregar eliminar reserva
            System.out.println("Que se desea Modificar de la reserva ?\n1-Habitacion (P:"+aModificar.getHabitacion().getPiso()+
                            "L:"+aModificar.getHabitacion().getLetra()+")\n2-Pasajero (DNI:"+aModificar.getPasajero().getDni()+")" +
                            "\n3-Cancelar un producto supuestamente consumido\n4-CheckIn ("+aModificar.getCheckIn().toLocalDate()+")" +
                            "\n5-Cantidad de dias de Hospedaje ("+aModificar.getCantDiasReserva()+")\n0-Salir");

            switch (opcion=pideEntero()) {
                case (1):

                    int piso;
                    char letra;
                    Habitacion habitacionACambiar = null;
                    do {
                        System.out.println("Ingrese Piso y Letra de la habitacion a cambiar: (-1 para salir) \nPiso: ");
                        piso = pideEntero();
                        if (piso != -1) {
                            System.out.println("Letra: ");
                            letra = pideChar();
                            habitacionACambiar = retornaHabitacionDisponible(letra, piso, gestionador.retornaHabitacionesDisponibles(hotel,
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

                    aModificar.setHabitacion(habitacionACambiar);

                    break;
                case (2):

                    long auxDni;
                    Pasajero pasajeroaModificar = null;
                    do {
                        System.out.println("Ingrese el dni del Pasajero a modificar (0 para salir)");
                        auxDni = pideLong();
                        if (auxDni != 0) {
                            pasajeroaModificar = hotel.retornaPasajero(auxDni);
                            if (pasajeroaModificar == null) {
                                System.out.println("No se encontro al usuario");
                            }
                        }
                    } while (pasajeroaModificar == null && auxDni != 0);

                    if (auxDni == 0) {//Volvemos al menu principal
                        break;
                    }

                    modificarUsuario(hotel, pasajeroaModificar);

                    break;
                case (3):
                    //Modularizar

                    int eliminarOpcion = 0;
                    do {
                        aModificar.muestraListaProductosConsumidos();
                        System.out.println("Ingrese el nombre del producto a eliminar: ");
                        if (aModificar.eliminaProductoConsumido(input.next())) {//Tengo que validar esto ?
                            System.out.println("Producto eliminado correctamente");
                        } else {
                            System.out.println("Error al eliminar");
                        }
                        //Si por x motivo se ingresa mal el nombre
                        System.out.println("1-Eliminar otro Producto\n0-Salir");
                        eliminarOpcion = input.nextInt();
                    } while (eliminarOpcion == 1);
                    break;


                case (4)://Aca se modifica CheckIn
                    //Ver habitaciones Reservadas antes de modificar
                    //Aca se modifica CheckIn

                    LocalDate fecha;
                    boolean valida;
                    opcion=0;
                    do{
                        System.out.println("Ingresar fecha nueva para el Check-in");
                        fecha = pideFecha();
                        valida = gestionador.habitacionOcupada(hotel,fecha,aModificar.getCantDiasReserva(),aModificar.getHabitacion());
                        if(!valida){
                            System.out.println("La habitacion para esa fecha esta ocupada");
                            System.out.println("Para intentar de nuevo Ingrese 1, para Salir 0");
                            opcion=pideEntero();
                        }
                    }while(opcion!=0 && !valida);

                    if(opcion==0){
                        break;
                    }else{
                        aModificar.setCheckIn(pideFecha().atTime(14,0));
                    }
                    break;
                case (5)://Cambiamos cantidad de dias
                    boolean validaDias;
                    int cantDias=0;
                    do{
                        System.out.println("Ingresar Cantidad de dias para la reserva");
                        cantDias = pideEntero();
                        validaDias = gestionador.habitacionOcupada(hotel,aModificar.getCheckIn().toLocalDate(),cantDias,aModificar.getHabitacion());
                        if(!validaDias){
                            System.out.println("La habitacion para esas fechas estan ocupadas");
                            System.out.println("Para intentar de nuevo Ingrese 1, para Salir 0");
                            opcion=pideEntero();
                        }
                    }while(opcion!=0 && !validaDias);

                    if(opcion==0){
                        break;
                    }else{
                        aModificar.setCheckOut(aModificar.getCheckIn().plusDays(cantDias).withHour(10).withMinute(0));
                    }
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion != 0);
    }

    public void modificarUsuario(Hotel hotel,Pasajero pasajeroaModificar)
    {
        int opcion = 0;

        do{
            System.out.println("Que desea modificar del pasajero ?\n1-Nombre ("+pasajeroaModificar.getNombre()+")\n2-Apellido ("+pasajeroaModificar.getApellido()+")"
                    +"\n3-Dni ("+pasajeroaModificar.getDni()+")\n4-Pais De Origen ("+pasajeroaModificar.getPaisDeOrigen()+")\n5-Domicilio ("+pasajeroaModificar.getDomicilio()+")"
                    +"\n6-Forma de Pago ("+pasajeroaModificar.getFormaDePago().toString()+") \n0-Salir");

            switch (opcion=input.nextInt()) {
                case (1):
                    System.out.println("Ingrese Nombre a modificar: ");
                    pasajeroaModificar.setNombre(input.next());
                    break;
                case (2):
                    System.out.println("Ingrese Apellido a modificar: ");
                    pasajeroaModificar.setApellido(input.next());
                    break;
                case (3):
                    System.out.println("Ingrese Dni a modificar: ");
                    pasajeroaModificar.setDni(input.nextLong());
                    break;
                case (4):
                    System.out.println("Ingrese Pais de origen a modificar: ");
                    pasajeroaModificar.setPaisDeOrigen(input.next());
                    break;
                case (5):
                    System.out.println("Ingrese Domicilio a modificar: ");
                    pasajeroaModificar.setDomicilio(input.next());
                    break;
                case (6):
                    System.out.println("Ingrese Modo de pago a modificar: ");
                    System.out.println("1-Debito\n2-Credito\n3-Efectivo-Peso\n4-Efectivo-Dolar");
                    switch (pideEntero()) {
                        case (1):
                            pasajeroaModificar.setFormaDePago(MedioDePago.DEBITO);
                            break;
                        case (2):
                            pasajeroaModificar.setFormaDePago(MedioDePago.CREDITO);
                            break;
                        case (3):
                            pasajeroaModificar.setFormaDePago(MedioDePago.EFECTIVO_PESOARGENTINO);
                            break;
                        case (4):
                            pasajeroaModificar.setFormaDePago(MedioDePago.EFECTIVO_DOLAR);
                            break;
                    }
                    break;
                case(0):
                    break;
                default:
                    System.out.println("Opcion mal ingresada");
            }

        }while(opcion!=0);
    }

}
