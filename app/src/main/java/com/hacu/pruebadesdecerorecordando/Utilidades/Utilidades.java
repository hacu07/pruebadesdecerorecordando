package com.hacu.pruebadesdecerorecordando.Utilidades;

/**
 * Created by hacu1 on 29/07/2018.
 */

public class Utilidades {
    //verifica la orientacion del celular
    public static boolean portrait = true;


    //INDICA COMO SE PRESENTA AL LAYOUT DEL RECYCLER PERSONAJES
    public static final int LIST = 1;
    public static final int GRID = 2;

    public static int visualizacion = LIST;


    //Constantes campos tabla usuario
    //Se dejan publicos y estaticos para que puedan ser accedido por otra parte en el codigo (metodos,clases,etc.).
    public static String TABLA_USUARIO = "usuario";
    public static String CAMPO_ID = "id";
    public static String CAMPO_NOMBRE = "nombre";
    public static String CAMPO_TELEFONO = "telefono";


    //Contantes campos tabla MASCOTA
    public static String TABLA_MASCOTA = "mascota";
    public static String CAMPO_ID_MASCOTA = "id_mascota";
    public static String CAMPO_NOMBRE_MASCOTA = "nombreid_mascota";
    public static String CAMPO_RAZA_MASCOTA = "razaid_mascota";
    public static String CAMPO_ID_DUENIO = "id_duenio";


    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER ," +
            " "+CAMPO_NOMBRE+" TEXT, " +
            ""+CAMPO_TELEFONO+" TEXT)";

    public static final String CREAR_TABLA_MASCOTA = "CREATE TABLE "+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            " "+CAMPO_NOMBRE_MASCOTA+" TEXT, "
            +CAMPO_RAZA_MASCOTA+" TEXT, " +
            ""+CAMPO_ID_DUENIO+" INTEGER)";

}
