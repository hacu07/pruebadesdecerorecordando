package com.hacu.pruebadesdecerorecordando.Entidades;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

/**
 * Created by hacu1 on 29/07/2018.
 */

public class ConexionSQLiteHelper extends SQLiteOpenHelper{

    //Script de creacion de tabla

    public ConexionSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /*
    * Se encarga de crear las tablas o ejecutar scripts en la BD
    * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_USUARIO);//Ejecutamos script de creacion de tabla en bd
        db.execSQL(Utilidades.CREAR_TABLA_MASCOTA);//Ejecutamos script de creacion de tabla en bd
    }

    /*
    * Cada vez que ejecutamos la app verifica si existe una BD anterior
    * */
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_USUARIO);//elimina la tabla si existe en una version anterior
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_MASCOTA);//elimina la tabla si existe en una version anterior
        onCreate(db); //crea la tabla actualizada
    }
}
