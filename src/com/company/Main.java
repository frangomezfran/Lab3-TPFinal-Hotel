package com.company;

import com.company.Personal.*;

public class Main {
    public static void main(String[] args) {

        Hotel hotel=new Hotel("hotel trivago",
                "av peralta ramos 2020",4);
        Administrador administrador=new Administrador("tobias",
                "selva",2400,"maniana",3255675);

        hotel.aniadirListaEmpleados(administrador);

        administrador.crearUnNuevoResepcionista("xsss","dlskd",
                2323,223,"qsyo");

        administrador.crearUnNuevoResepcionista("xs3s","dlskd",
                2323,223,"qsyo");

        administrador.crearUnNuevoAdministrador("Ys3s","dlskd",
                2323,223,"qsyo");
        administrador.crearUnNuevoAdministrador("AAAYs3s","dlskd",
                2323,223,"qsyo");


        System.out.println(hotel.toString());



    }


}