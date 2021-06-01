import java.util.ArrayList;

public class Habitacion {

    private int cantCamas;
    private int piso;
    private char letra;
    private String tipoHabitacion; //Podriamos crear otra clase q se llame tipo de habitacion q tenga los string predefinidos
    private double precio;
    private enum estadoHabitacion; //todavia nose usar esto
    private ArrayList<Producto> listaProductos =  new ArrayList<>();

    //--------------- Constructor ---------------
    public Habitacion(int cantCamas, int piso, char letra, String tipoHabitacion, double precio, ArrayList<Producto> listaProductos) {
        this.cantCamas = cantCamas;
        this.piso = piso;
        this.letra = letra;
        this.tipoHabitacion = tipoHabitacion;
        this.precio = precio;
        this.listaProductos = listaProductos;
    }

    //--------------- Cant Camas ---------------
    public int getCantCamas() {
        return cantCamas;
    }
    public void setCantCamas(int cantCamas) {
        this.cantCamas = cantCamas;
    }

    //--------------- Piso ---------------
    public int getPiso() {
        return piso;
    }
    public void setPiso(int piso) {
        this.piso = piso;
    }

    //--------------- Letra ---------------
    public char getLetra() {
        return letra;
    }
    public void setLetra(char letra) {
        this.letra = letra;
    }

    //--------------- Tipo Habitacion ---------------
    public String getTipoHabitacion() {
        return tipoHabitacion;
    }
    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    //--------------- Precio ---------------
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //--------------- Lista de productos ---------------
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    //--------------- Metodos ---------------

}
