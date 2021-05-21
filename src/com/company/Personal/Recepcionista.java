package com.company.Personas;

import java.util.Scanner;

public class Recepcionista extends Persona{

    private double sueldo;
    private String turno;
    public Recepcionista(String nombre, String apellido, long dni,double sueldo,String turno) {
        super(nombre, apellido, dni);
        this.sueldo=sueldo;
        this.turno=turno;
    }

    public  String obtenerFeedback()
    {
        return "rr";
    }
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
