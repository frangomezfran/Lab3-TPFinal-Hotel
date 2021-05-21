package com.company.Personal;

public interface atenderAunCliente {

   public void reserva(Pasajero pasajero);
   public void consultarHabitacionesDisponibles();
   public void checkIn(String nombre,String apellido,long dni);//creo al pasajero
   public void checkOut(Pasajero pasajero);
   public void buscarUsuario(Pasajero pasajero);
   public void login(Pasajero pasajero);


}
