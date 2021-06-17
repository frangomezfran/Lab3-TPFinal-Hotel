package com.company.Personal;

public class Pasajero extends Persona{

    private String paisDeOrigen;
    private String domicilio;

    //--------------Constructor--------------
    public Pasajero(String nombre, String apellido, long dni,
                    String paisDeOrigen, String domicilio) {

        super(nombre, apellido, dni);
        this.paisDeOrigen = paisDeOrigen;
        this.domicilio = domicilio;

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


    //--------------Metodos--------------
    @Override
    public String toString() {
        return "\nPasajero :\n" +
                "Nombre: "+getNombre()+" | Apellido: "+getApellido()+"\n" +
                "DNI: "+getDni()+" | País de Origen: "+getPaisDeOrigen()+"\n" +
                "Domicilio: "+getDomicilio();
    }
}