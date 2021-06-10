package com.company;

public enum TipoHabitacion {

    SIMPLE ("Simple",1,1,0),
    DOBLE_MATRIMONIAL("Doble-Matrimonial",2,0,1),
    DOBLE_TWIN("Doble_Twin",2,2,0),
    TRIPLE_MATRIMONIAL("Triple-Matrimonial",3,1,1),
    TRIPLE_TWIN("Triple-Twin",3,3,0),
    CUADRUPLE_MATRIMONIAL("Cuadruple-Matrimonial",4,2,1),
    CUADRUPLE_TWIN("Cuadruple-Twin",4,4,0),
    CORNER  ("Corner",2,0,1),
    COMUNICANTES_MATRIMONIAL ("Comunicantes-Matrimonial",4,0,2),
    COMUNICANTES_TWIN("Comunicantes-Twin",4,4,0),
    EJECUTIVA ("Ejecutiva",2,0,1,1),
    ESTUDIO  ("TipoEstudio",2,0,1,1),
    SUITE  ("Suite",3,0,1,1),
    JUNIORSUITE("JuniorSuite",4,0,1,1),
    DOBLESUITE ("DobleSuite",4,0,2),
    GOBERNADOR ("Gobernador",4,2,4),
    PRESIDENCIAL ("Presidencial",6,4,1);

    private final String tipo;
    private final int cantPersonas;
    private final int camasIndiviudales;
    private final int camasMatrimoniales;
    private final int sofaCama;


    TipoHabitacion(String tipo, int cantPersonas, int camasIndiviudales, int camasMatrimoniales) {
        this.tipo = tipo;
        this.cantPersonas = cantPersonas;
        this.camasIndiviudales = camasIndiviudales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.sofaCama=0;
    }
    TipoHabitacion(String tipo, int cantPersonas, int camasIndiviudales, int camasMatrimoniales, int sofaCama) {
        this.tipo = tipo;
        this.cantPersonas = cantPersonas;
        this.camasIndiviudales = camasIndiviudales;
        this.camasMatrimoniales = camasMatrimoniales;
        this.sofaCama = sofaCama;
    }

    public String getTipo() {
        return tipo;
    }
    public int getCantPersonas() {
        return cantPersonas;
    }
    public int getCamasIndiviudales() {
        return camasIndiviudales;
    }
    public int getCamasMatrimoniales() {
        return camasMatrimoniales;
    }
    public int getSofaCama() {
        return sofaCama;
    }

    @Override
    public String toString() {
        return "TipoHabitacion{" +
                "tipo='" + tipo + '\'' +
                ", cantPersonas=" + cantPersonas +
                ", camasIndiviudales=" + camasIndiviudales +
                ", camasMatrimoniales=" + camasMatrimoniales +
                ", sofaCama=" + sofaCama +
                '}';
    }
}
