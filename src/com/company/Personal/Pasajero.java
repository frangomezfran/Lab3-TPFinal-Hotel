package com.company.Personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.company.Hotel;
import com.company.Producto;
import com.company.Reserva;


public class Pasajero extends Persona{
    private double cantidadDeDinero;
    private String paisDeOrigen;
    private String domicilio;
    public MedioDePago formaDePago;
    private List<Reserva> historialEnElHotel=new ArrayList<>();

    public Pasajero(String nombre, String apellido, long dni, double cantidadDeDinero,
                    String paisDeOrigen, String domicilio,String comoPague) {
        super(nombre, apellido, dni);
        this.cantidadDeDinero = cantidadDeDinero;
        this.paisDeOrigen = paisDeOrigen;
        this.domicilio = domicilio;

    }




    //en el caso de que el pasajerpo se quiera ir antes de tiempo puede solicitar la devolucion del valor
// de sus dias restantes
    //por ejemplo si paga 600 y se queda 5 dias
    //600:5=120
    //si se va en el dia 3 le devolvemos 240(dia 4 y 5)
    public void solicitarReenbolsoReserva(Reserva reserva)
    {
        //para calcular en el dia en el que estamos restarle al check in la fecha actual
        //check in=20 de diciembre    actual 22 de diciembre
        //y el periodo es 5
        //22-20=2     |5-2=3 es decir estamos en el dia 3

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
                "cantidadDeDinero=" + cantidadDeDinero +
                ", paisDeOrigen='" + paisDeOrigen + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", formaDePago='" + formaDePago + '\'' +
                ", historialEnElHotel=" + historialEnElHotel +
                '}';
    }
}
