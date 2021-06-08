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
                System.out.println("el usuario utilizara efectivo como medio de pago");
                System.out.println("valor actual cambio:100 pesos argentinos por dolar");
                System.out.println("¿Que divisa usted tiene(peso argentino o dolar estadounidense)?");
                Scanner divisa_=new Scanner(System.in);
                if(divisa_.nextLine().equals("dolar estadounidense"))
                {
                    saldo=saldo*100;//paso los dolares a pesos argentinos
                }
                //no pongo otro else porque:como la otra moneda permitida es el peso argentino,
                //no es necesario convertir la divisa
                break;

            default:System.out.println("\n-ERROR-\n por favor escriba alguna de " +
                    "las siguientes opciones: DEBITO CREDITO EFECTIVO");
        }

    }

    public void solicitarUnProducto(String nombreProducto,List<Producto>producto)
    {
        for (Producto productoActual: producto) {
            if(productoActual.getNombre()==nombreProducto)
            {
                if(productoActual.getStock()>0)
                {
                    //aca se pondria que se consumio el producto
                }
            }

        }
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
    @Override
    public String toString() {
        return "Pasajero{" +
                "saldo=" + saldo +
                ", paisDeOrigen='" + paisDeOrigen + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", formaDePago='" + formaDePago + '\'' +
                ", historialEnElHotel=" + historialEnElHotel +
                '}';
    }


}