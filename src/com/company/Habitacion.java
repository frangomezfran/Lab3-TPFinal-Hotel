package com.company;

public class Habitacion {

    private TipoHabitacion tipoHabitacion;
    private int piso;
    private char letra;
    private double precio;
    private EstadoHabitacion estadoHabitacion;


    //--------------- Constructor ---------------
    public Habitacion(TipoHabitacion tipoHabitacion, int piso, char letra) {
        this.tipoHabitacion=tipoHabitacion;
        this.piso = piso;
        this.letra = letra;
        this.estadoHabitacion=EstadoHabitacion.DISPONIBLE;
    }

    //--------------- Piso ---------------
    public int getPiso() {
        return piso;
    }
    public void setPiso(int piso) {
        this.piso = piso;
    }

    //--------------- Letra ---------------
    public char getLetra() {
        return letra;
    }
    public void setLetra(char letra) {
        this.letra = letra;
    }

    //--------------- Precio ---------------
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //--------------- Estado de la Habitacion ---------------
    public EstadoHabitacion getEstadoHabitacion() {
        return estadoHabitacion;
    }
    public void setEstadoHabitacion(EstadoHabitacion estadoHabitacion) {
        this.estadoHabitacion = estadoHabitacion;
    }

    //--------------- Tipo de Habitacion ---------------
    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }
    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }


    //--------------- Metodos ---------------

    public String muestraHabitacion(){

        return "[ Tipo: "+tipoHabitacion.getTipo()+" | CantMax de Personas: "+tipoHabitacion.getCantPersonas()+" | Piso: "+getPiso()+" | Letra: "+getLetra()+" | " +
                "Precio: "+getPrecio()+" ]";

    }

    @Override
    public String toString() {
        return "[ Tipo: "+tipoHabitacion.getTipo()+" | CantMax de Personas: "+tipoHabitacion.getCantPersonas()+" | Piso: "+getPiso()+" | Letra: "+getLetra()+" | " +
                "Precio: "+getPrecio()+" | Estado: "+estadoHabitacion+" ]";
    }
}