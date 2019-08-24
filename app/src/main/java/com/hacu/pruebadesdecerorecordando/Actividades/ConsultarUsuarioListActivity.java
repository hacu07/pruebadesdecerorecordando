package com.hacu.pruebadesdecerorecordando.Actividades;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.Entidades.Usuario;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarUsuarioListActivity extends AppCompatActivity {

    ListView listViewPersonas;
    ConexionSQLiteHelper conn;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario_list);

        listViewPersonas = (ListView) findViewById(R.id.listViewPersonas);
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);
        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String informacion = "Id: " + listaUsuarios.get(position).getId();
                informacion += "\nNombre: " + listaUsuarios.get(position).getNombre();
                informacion += "\nTelefono: "+ listaUsuarios.get(position).getTelefono();
                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_SHORT).show();

                //Para enviar este objeto necesita implementar en la clase principal "Serializable"
                Usuario user  = listaUsuarios.get(position);

                Intent miIntent = new Intent(ConsultarUsuarioListActivity.this,DetalleUsuarioActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("usuario",user);
                miIntent.putExtras(bundle);
                startActivity(miIntent);
            }
        });
     }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        listaUsuarios =  new ArrayList<Usuario>();

        String sql = "SELECT * FROM "+ Utilidades.TABLA_USUARIO;

        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            usuario =  new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuarios.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();

        for (int i = 0; i < listaUsuarios.size(); i++){
            listaInformacion.add(listaUsuarios.get(i).getId().toString() + " - "+ listaUsuarios.get(i).getNombre().toString());
        }
    }
}
