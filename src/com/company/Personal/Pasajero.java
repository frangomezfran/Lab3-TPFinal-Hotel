package com.company.Personal;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.company.*;


public class Pasajero extends Persona{

    private String paisDeOrigen;
    private String domicilio;
    private MedioDePago formaDePago;

    //--------------Constructor--------------
    public Pasajero(String nombre, String apellido, long dni,
                    String paisDeOrigen, String domicilio, MedioDePago medioDePago) {

        super(nombre, apellido, dni);
        this.paisDeOrigen = paisDeOrigen;
        this.domicilio = domicilio;
        this.formaDePago=medioDePago;

    }

    //--------------Pais de Origen--------------
    public String getPaisDeOrigen() {
        return paisDeOrigen;
    }
    public void setPaisDeOrigen(String paisDeOrigen) {
        this.paisDeOrigen = paisDeOrigen;
    }

    //--------------Domicilio--------------
    public String getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    //--------------Forma de Pago--------------
    public MedioDePago getFormaDePago() {
        return formaDePago;
    }
    public void setFormaDePago(MedioDePago formaDePago) {
        this.formaDePago = formaDePago;
    }


    //--------------Metodos--------------
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

    public void escribeOpinionReserva (Hotel hotel,String opinion){

        hotel.retornaReservadelPasajero(this.getDni()).setOpinion(opinion);

    }

    @Override
    public String toString() {
        return "Pasajero{" +
                ", paisDeOrigen='" + paisDeOrigen + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", formaDePago='" + formaDePago + '\'' +
                '}';
    }


}