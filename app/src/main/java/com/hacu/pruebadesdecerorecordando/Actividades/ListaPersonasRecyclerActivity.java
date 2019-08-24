package com.hacu.pruebadesdecerorecordando.Actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hacu.pruebadesdecerorecordando.Adaptadores.ListaPersonasAdapter;
import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.Entidades.Usuario;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

public class ListaPersonasRecyclerActivity extends AppCompatActivity {


    ArrayList<Usuario> listaUsuario;
    RecyclerView recycler;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personas_recycler);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listaUsuario =  new ArrayList<Usuario>();

        recycler = (RecyclerView) findViewById(R.id.recyclerPersonajes);

        recycler.setLayoutManager(new LinearLayoutManager(this));

        consultarListaPersonas();

        ListaPersonasAdapter adapter = new ListaPersonasAdapter(listaUsuario);

        recycler.setAdapter(adapter);
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;

        String sql = "SELECT * FROM "+ Utilidades.TABLA_USUARIO;

        Cursor cursor = db.rawQuery(sql,null);

        while(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuario.add(usuario);
        }
    }

    public void onClick(View view) {
    }
}
