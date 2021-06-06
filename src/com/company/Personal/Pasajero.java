package com.company.Personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.company.Hotel;
import com.company.Producto;
import com.company.Reserva;


public class Pasajero extends Persona{
    private double saldo;
    private String paisDeOrigen;
    private String domicilio;
    public MedioDePago formaDePago;
    private List<Reserva> historialEnElHotel=new ArrayList<>();

    public Pasajero(String nombre, String apellido, long dni, double saldo,
                    String paisDeOrigen, String domicilio,String medioDePago) {
        super(nombre, apellido, dni);
        this.saldo = saldo;
        this.paisDeOrigen = paisDeOrigen;
        this.domicilio = domicilio;
        formaDePago=MedioDePago.valueOf(medioDePago.toUpperCase());//me aseguro de que siempre el texto medioDePago se
        // lee como todo en mayuscula
        switch (formaDePago)
        {
            case DEBITO:
                System.out.println("el usuario utilizara su tarjeta de debito como medio de pago");
                formaDePago=formaDePago.DEBITO;
                break;
            case CREDITO:
                System.out.println("el usuario utilizara su tarjeta de credito como medio de pago");
                formaDePago=formaDePago.CREDITO;
                break;
            case EFECTIVO:
                formaDePago=formaDePago.EFECTIVO;
                System.out.println("el usuario utilizara efectivo como medio de pago\n");
                System.out.println("valor actual cambio:100 pesos argentinos por dolar\n");
                System.out.println("¿Que divisa usted tiene(peso argentino o " +
                        "dolar estadounidense\n)?");

                Scanner divisa_=new Scanner(System.in);
                System.out.println("1-peso argentino \n" +
                        "2-dolar estadounidense \n");


                if(divisa_.nextInt()==1)
                {
                    System.out.println("\n**********************************\n");
                    System.out.println("usted eligio el peso argentino como moneda \n");
                    System.out.println("\n**********************************\n");

                }
                else {
                    System.out.println("\n**********************************\n");
                    System.out.println("usted eligio el dolar estadounidense como moneda \n");
                    System.out.println("\n**********************************\n");
                    this.saldo = saldo * 100;
                }
                break;
            default:System.out.println("\n-ERROR-\n por favor escriba alguna de " +
                    "las siguientes opciones: DEBITO CREDITO EFECTIVO");
        }

    }

    public boolean solicitarUnProducto(String nombreProducto,List<Producto>producto,int cantidadAsolicitar)
    {   boolean operacionExitosa=false;
        for (Producto productoActual: producto) {
            if(productoActual.getNombre()==nombreProducto)
            {
                if(productoActual.getStock()>cantidadAsolicitar)
                {
                    productoActual.setStock(cantidadAsolicitar);
                    operacionExitosa=true;
                    return operacionExitosa;

                }else
                {
                    //si no contamos con la cantidad solicitada le damos la opcion al pasajero de
                    //elegir la cantidad disponible actualmente
                    int cantidadEnStock=cantidadAsolicitar-productoActual.getStock();
                    System.out.println("cantidad disponible actual--> "+(cantidadEnStock));
                    System.out.println("si usted desea puede solicitar esta cantidad o seleccionar otro" +
                            "producto sepa disculpar ");
                    Scanner opcionElegida=new Scanner(System.in);
                    System.out.println("\n1-continuar \n" +
                            " 2-volver al menu\n");
                    if(opcionElegida.nextInt()==1) {
                        //le resto al stock la cantidad disponible
                        productoActual.setStock(cantidadEnStock);
                        operacionExitosa=true;
                        return operacionExitosa;
                    }
                    else
                    {
                        break;//para parar de buscar
                    }
                    //si el empleado escribe un 2 automaticamente volvemos
                    //al menu prinicipal
                }
            }

        }
        return operacionExitosa;//false
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
        }

        return null;


    }
    @Override
    public String toString() {
        return "Pasajero{" +
                "nombre= " + this.getNombre() +
                " apellido= " + this.getApellido() +
                " dni= " + getDni() +
                " saldo= " + saldo +
                ", paisDeOrigen='" + paisDeOrigen + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", formaDePago='" + formaDePago + '\'' +
                ", historialEnElHotel=" + historialEnElHotel +
                '}';
    }


}
