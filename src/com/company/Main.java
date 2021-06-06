package com.company;

import com.company.Personal.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Hotel hotel=new Hotel("hotel trivago",
                "av peralta ramos 2020",4);
        Administrador administrador=new Administrador("tobias",
                "selva",2400,"maniana",3255675);

        hotel.aniadirListaEmpleados(administrador);

        hotel.crearUnNuevoResepcionista("pepe","dolores",
                23323,2223,"maniana");

        hotel.crearUnNuevoResepcionista("el goma","ramarazo",
                12323,2230,"tarde");
        hotel.crearUnNuevoResepcionista("roman","riquelme",
                12323,2230,"tarde");

        hotel.crearUnNuevoResepcionista("romana","riquelmef",
                1232223,2230,"noche");

        hotel.crearUnNuevoAdministrador("peke","rugeri",
                55323,"tarde",2023);
        hotel.crearUnNuevoAdministrador("goro","kombat",
                66323,"noche",2203);


        System.out.println(hotel.toString());




        Pasajero pasajero1=new Pasajero("juan","riquelmito",262,
                230,"AR","jara 2030","EFECTIVO");
        System.out.println(pasajero1.toString());
        Pasajero pasajero2=new Pasajero("juan","riquelmito",262,
                230,"AR","jara 2030","EFECTIVO");
        System.out.println(pasajero2.toString());
        Pasajero pasajero3=new Pasajero("pedro","sanchez",88232,
                230,"EEUU","55 Fruit St, Boston, MA 02114",
                "EFECTIVO");

        System.out.println(pasajero3.toString());



    }


}