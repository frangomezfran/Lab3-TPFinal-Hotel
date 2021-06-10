package com.company;

import com.company.Personal.Administrador;
import com.company.Personal.Recepcionista;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Hotel trivago = new Hotel("Trivago","MariaPaz 3232", 3);

        Administrador Esteban = new Administrador("Esteban","Lopez",60000,"Mañana",30123345);

        trivago.agregaEmpleado(Esteban);

        Esteban.crearUnNuevoAdministrador(trivago,"Pedro","Lopez",29323412,60000,"Tarde");

        Esteban.crearUnNuevoResepcionista(trivago,"Andrea","Gimenez",29312423,50000,"Mañana");


        Menu menu = new Menu();

        int opcion = 0;


        System.out.println("------- Hotel "+trivago.getNombreHotel()+" --------");

        Recepcionista gestionador = menu.eligeGestionadorSistema(trivago);//Si instanceoff gestionador == Administraodor -> mas opciones

        menu.menuRecepcionista(trivago,gestionador);






    }

}