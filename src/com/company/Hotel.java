import com.company.Personal.Pasajero;
import com.company.Personal.Recepcionista;

import java.util.ArrayList;

public class Hotel {
    private String nombreHotel;
    private String direccion;
    private int categoria;
    private int cantidadEmpleados;
    private ArrayList<Habitacion> listaHabitaciones = new ArrayList<>();
    private ArrayList<Pasajero> listaPasajeros = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private ArrayList<Recepcionista> listaEmpleados = new ArrayList<>();

    public Hotel(String nombreHotel, String direccion, int categoria, int cantidadEmpleados) {
        this.nombreHotel = nombreHotel;
        this.direccion = direccion;
        this.categoria = categoria;
        this.cantidadEmpleados = cantidadEmpleados;
    }

    public boolean buscarReservaPasajero(Pasajero pasajero){
        for(Pasajero auxPasajero: this.listaPasajeros){ //La idea es comparar el pasajero de la lista de pasajeros  con los pasajeros de lista de reservas
            if(auxPasajero.getDni()==(pasajero.getDni()));// getNombrePasajero es temporal hasta saber el metodo de obtener pasajero en reserva.
            return true;
        }
        return false;
    }
}
