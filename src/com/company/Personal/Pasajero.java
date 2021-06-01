package com.company.Personal;

import java.util.ArrayList;

public class Pasajero extends Persona {
    private double cantidadDeDinero;


    private String paisDeOrigen;
    private String domicilio;
    private String metodoDePago="efectivo";
    private ArrayList<Reserva> historialEnElHotel=new ArrayList<>();

    public Pasajero(String nombre, String apellido, long dni, double cantidadDeDinero,
                    String paisDeOrigen, String domicilio) {
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
    public void solicitarUnProducto(String nombreProducto,ArrayList<Producto>producto)
    {
        for (Producto productoActual: producto) {
            if(productoActual.getNombre().equals(nombreProducto))
            {
                if(productoActual.getStock()>0)
                {
                    //aca se pondria que se consumio el producto
                }
            }

        }
    }

    @Override
    public String toString() {
        return "com.company.Personal.Pasajero{" +
                "cantidadDeDinero=" + cantidadDeDinero +
                ", paisDeOrigen='" + paisDeOrigen + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", metodoDePago='" + metodoDePago + '\'' +
                ", historialEnElHotel=" + historialEnElHotel +
                '}';
    }
}
