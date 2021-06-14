package com.company;

import com.company.Personal.Administrador;
import com.company.Personal.Recepcionista;

import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Hotel trivago = new Hotel("Trivago","MariaPaz 3232", 3);

        Administrador Esteban = new Administrador("Esteban","Lopez",60000,"Mañana",30123345,"esteban");

        trivago.agregaEmpleado(Esteban);

        Esteban.crearUnNuevoAdministrador(trivago,"Pedro","Lopez",29323412,60000,"Tarde","pedro");

        Esteban.crearUnNuevoResepcionista(trivago,"Andrea","Gimenez",29312423,50000,"Mañana","andrea");

        Habitacion habitacion1 = new Habitacion(TipoHabitacion.SIMPLE,1,'A');
        Habitacion habitacion2 = new Habitacion(TipoHabitacion.SIMPLE,1,'B');
        Habitacion habitacion3 = new Habitacion(TipoHabitacion.SIMPLE,1,'C');
        Habitacion habitacion4 = new Habitacion(TipoHabitacion.DOBLE_MATRIMONIAL,2,'A');
        Habitacion habitacion5 = new Habitacion(TipoHabitacion.DOBLE_MATRIMONIAL,2,'B');
        Habitacion habitacion6 = new Habitacion(TipoHabitacion.DOBLE_TWIN,2,'C');
        Habitacion habitacion7 = new Habitacion(TipoHabitacion.TRIPLE_MATRIMONIAL,3,'A');
        Habitacion habitacion8 = new Habitacion(TipoHabitacion.TRIPLE_MATRIMONIAL,3,'B');
        Habitacion habitacion9 = new Habitacion(TipoHabitacion.PRESIDENCIAL,3,'C');
        Habitacion habitacion10 = new Habitacion(TipoHabitacion.GOBERNADOR,4,'A');

        trivago.agregaHabitacion(habitacion1);
        trivago.agregaHabitacion(habitacion2);
        trivago.agregaHabitacion(habitacion3);
        trivago.agregaHabitacion(habitacion4);
        trivago.agregaHabitacion(habitacion5);
        trivago.agregaHabitacion(habitacion6);
        trivago.agregaHabitacion(habitacion7);
        trivago.agregaHabitacion(habitacion8);
        trivago.agregaHabitacion(habitacion9);
        trivago.agregaHabitacion(habitacion10);


        Menu menu = new Menu();

        int opcion = 0;


        Recepcionista gestionador = menu.eligeGestionadorSistema(trivago);//Si instanceoff gestionador == Administraodor -> mas opciones

        menu.menuRecepcionista(trivago,gestionador);






    }

}