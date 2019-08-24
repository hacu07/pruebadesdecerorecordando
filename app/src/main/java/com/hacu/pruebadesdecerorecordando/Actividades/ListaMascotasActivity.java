package com.hacu.pruebadesdecerorecordando.Actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.Entidades.Mascota;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

public class ListaMascotasActivity extends AppCompatActivity {
    ListView listaMascotas;
    ArrayList<String> listaInformacion;
    ArrayList<Mascota> mascotasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
        listaMascotas =  (ListView) findViewById(R.id.listaMascotas);
        conn =  new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        consultarListaMascotas();

        ArrayAdapter adapter =  new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaInformacion);
        listaMascotas.setAdapter(adapter);
    }

    private void consultarListaMascotas() {
        SQLiteDatabase db =  conn.getReadableDatabase();

        Mascota mascota = null;
        mascotasList =  new ArrayList<Mascota>();

        String sql = "SELECT  * FROM "+ Utilidades.TABLA_MASCOTA;
        Cursor cursor = db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            mascota =  new Mascota();
            mascota.setIdMascota(cursor.getInt(0));
            mascota.setNombreMascota(cursor.getString(1));
            mascota.setRaza(cursor.getString(2));
            mascota.setIdDuenio(cursor.getInt(3));

            mascotasList.add(mascota);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i<mascotasList.size();i++){
            listaInformacion.add(mascotasList.get(i).getIdMascota()+" - "+mascotasList.get(i).getNombreMascota());
        }
    }
}
