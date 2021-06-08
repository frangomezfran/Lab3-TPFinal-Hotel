package com.company;

import com.company.Personal.*;

public class Main {
    public static void main(String[] args) {

        Hotel hotel=new Hotel("hotel trivago",
                "av peralta ramos 2020",4);
        Administrador administrador=new Administrador("tobias",
                "selva",2400,"maniana",3255675);

        hotel.aniadirListaEmpleados(administrador);

        administrador.crearUnNuevoResepcionista("pepe","dolores",
                23323,2223,"maniana");

        administrador.crearUnNuevoResepcionista("roman","riquelme",
                12323,2230,"maniana");

        administrador.crearUnNuevoAdministrador("peke","rugeri",
                55323,2023,"tarde");
        administrador.crearUnNuevoAdministrador("goro","kombat",
                66323,2203,"noche");


        System.out.println(hotel.toString());



    }


}