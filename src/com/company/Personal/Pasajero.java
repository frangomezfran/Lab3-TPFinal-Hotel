package com.company.Personal;

import java.util.ArrayList;

public class Pasajero extends Persona{
    private double cantidadDeDinero;


    private String paisDeOrigen;
    private String domicilio;
    private String metodoDePago="efectivo";
    private List<Reserva> historialEnElHotel=new ArrayList<>();

    public Pasajero(String nombre, String apellido, long dni, double cantidadDeDinero,
                    String paisDeOrigen, String domicilio) {
        super(nombre, apellido, dni);
        this.cantidadDeDinero = cantidadDeDinero;
        this.paisDeOrigen = paisDeOrigen;
        this.domicilio = domicilio;
    }



    public void solicitarReenbolsoReservapresencial(Reserva reserva)
    {

    }
}
