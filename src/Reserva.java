import java.time.LocalDateTime;
import java.util.ArrayList;

public class Reserva {

    private static int id = 0; //Podria llegar a generar problemas, pero tengo un registro de el orden de las reservas
    private Habitacion habitacion;
    private int cantDiasReserva;
    private ArrayList<Producto> productosConsumidos =  new ArrayList<>();
    private LocalDateTime fechaReserva;

    //--------------- Constructor ---------------
    public Reserva(Habitacion habitacion, int cantDiasReserva) {
        this.id ++;
        this.habitacion = habitacion;
        this.cantDiasReserva = cantDiasReserva;
        this.fechaReserva = LocalDateTime.now(); //Se guarda el dia que se hizo la reserva
    }

    //--------------- ID ---------------
    public static int getId() {
        return id;
    }
    public static void setId(int id) {
        Reserva.id = id;
    }

    //--------------- Habitacion ---------------
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    //--------------- Cantidad de dias Reserva ---------------
    public int getCantDiasReserva() {
        return cantDiasReserva;
    }
    public void setCantDiasReserva(int cantDiasReserva) {
        this.cantDiasReserva = cantDiasReserva;
    }

    //--------------- Productos Consumidos ---------------
    public ArrayList<Producto> getProductosConsumidos() {
        return productosConsumidos;
    }
    public void setProductosConsumidos(ArrayList<Producto> productosConsumidos) {
        this.productosConsumidos = productosConsumidos;
    }

    //--------------- Fecha de la Reserva ---------------
    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    //--------------- Metodos ---------------

    public LocalDateTime getCheckIn(){



    }

}
