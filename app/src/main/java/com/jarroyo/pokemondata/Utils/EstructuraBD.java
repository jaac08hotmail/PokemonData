package com.jarroyo.pokemondata.Utils;

public class EstructuraBD {

    //constantes Tabla Berry
    public static final String TABLA_BERRY = "BERRY";


    public static final String CREAR_TABLA_BERRY = "CREATE TABLE BERRY (" +
            " id INTEGER PRIMARY KEY  AUTOINCREMENT, " +
            " name TEXT," +
            " url TEXT," +
            " favorite INTEGER)";

    public static final String DELETE_TABLA_BERRY = "DELETE FROM " + TABLA_BERRY;
}
