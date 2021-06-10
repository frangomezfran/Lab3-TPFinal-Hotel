package com.company;

import com.company.Personal.*;

import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);

    public Menu(){}

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

    public int pideEntero(){

       String futuroEntero = "";
        do
        {
            futuroEntero = input.nextLine();

        }while(!esEntero(futuroEntero));

        return Integer.parseInt(futuroEntero);
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
            i=pideOpcion();

        }while( !validaOpcionConSizeArraylist( i , hotel.getListaEmpleados().size() ) );

        System.out.println("Usted es "+hotel.getListaEmpleados().get(i).getNombre());

        return hotel.getListaEmpleados().get(i);

    }


    public void menuAdministrador(Hotel hotel, Administrador gestionador){}

    public void menuRecepcionista(Hotel hotel,Recepcionista gestionador){

        int opcion=0;

        do {

            System.out.println("Que desea hacer "+gestionador.getNombre());
            System.out.println("1- Crear Nueva Reserva\n2- Modificar Reservas\n3- Modificar Usuario\n" +
                    "4- Ver precios habitaciones\n5-");

            switch (pideEntero()){

                case(1):
                    System.out.println("Habitaciones disponibles: \n " +
                            "Ingrese cantidad de personas");
                    int cantPersonas = pideEntero();
                    System.out.println("Ingrese la fecha del posible CheckIn (MM/DD/YYYY)");
                    LocalDate fecha = pideFecha();
                    System.out.println("Ingrese la cantidad de dias a hospedarse");
                    int cantDiasHospedarse = pideEntero();
                    gestionador.muestraHabitacionesDisponibles(hotel,cantPersonas,fecha,cantDiasHospedarse);

                    do {
                        System.out.println("Creamos la reserva ?\n1- Si\n2-No");
                        switch (opcion = pideEntero()) {
                            case (1):
                                System.out.println("Ingrese Piso y Letra de la habitaciona reservar: \nPiso: ");
                                int piso = pideEntero();
                                System.out.println("Letra: ");
                                char letra = input.
                                System.out.println("Ingrese Dni de un Pasajero: ");
                                long auxDni = pideLong();

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
                                        }

                                    }while (opcion<1 || opcion>4);

                                    pasajero = gestionador.crearUnNuevoUsuario(hotel,auxNombre,auxApellido,auxDni,auxPais,auxDomicilio,auxMedioDePago);

                                    System.out.println("El pasajero fue creado Exitosamente");

                                }else {
                                    System.out.println("El pasajero ya se encuentra en la lista de pasajeros");
                                }

                                Habitacion auxHabitacion ()

                                gestionador.creaReserva();

                                break;
                            case (2):

                                break;
                            default:
                                System.out.println("Intentelo nuevamente");

                        }
                    }while (opcion!=2);

                    break;

                case(2):
                    break;

                case(3):
                    break;

                default:
                    System.out.println("Opcion mal ingresada, Intentelo de nuevo");
            }


        }while(true);

    }

}
