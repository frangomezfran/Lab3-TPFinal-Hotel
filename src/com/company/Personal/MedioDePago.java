package com.company.Personal;

public enum MedioDePago {
    DEBITO("Debito"),
    CREDITO("Credito"),
    EFECTIVO_DOLAR("Efectivo","Dolar"),
    EFECTIVO_PESOARGENTINO("Efectivo","Peso Argentino");

    public final String medioDePago;
    public final String divisa;

    MedioDePago(String medioDePago){
        this.medioDePago=medioDePago;
        this.divisa=null;
    }
    MedioDePago(String medioDePago,String divisa){
        this.medioDePago=medioDePago;
        this.divisa=divisa;
    }
}


