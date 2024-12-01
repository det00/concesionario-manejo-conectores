package database;

//INTERFACE CON TODOS LOS STRINGS DE LA BASE DE DATOS
public interface SchemaDB {
    String DB_NAME = "blxhn5kyfuug5es9elxa";
    String COL_ID = "id";

    String TAB_PAS = "pasajeros";
    String COL_PAS_NAME = "nombre";
    String COL_PAS_AGE = "edad";
    String COL_PAS_WEIGHT = "peso";
    String COL_PAS_CAR = "pasajero_coche";

    String TAB_CAR = "coches";
    String COL_CAR_MAT = "matricula";
    String COL_CAR_MARC = "marca";
    String COL_CAR_MOD = "modelo";
    String COL_CAR_COLOR = "color";
    String COL_PLA_DISP = "plazas_disponibles";
}
