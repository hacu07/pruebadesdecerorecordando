package com.hacu.pruebadesdecerorecordando.Actividades;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.Entidades.Usuario;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

public class RegistroMascotaActivity extends AppCompatActivity {
    EditText txtNomMas,txtRazMas;
    Spinner comboDuenio;

    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mascota);

        txtNomMas = (EditText) findViewById(R.id.txtNomMas);
        txtRazMas = (EditText) findViewById(R.id.txtRazMas);
        comboDuenio = (Spinner) findViewById(R.id.comboDuenio);

        //Conexion a la BD
        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adapter =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);
        comboDuenio.setAdapter(adapter);

        comboDuenio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long l) {
                Toast.makeText(getApplicationContext(),"Selecciono:"+parent.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegiMas:
                registrarMascota();
                break;
        }
    }

    private void registrarMascota() {
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE_MASCOTA,txtNomMas.getText().toString());
        values.put(Utilidades.CAMPO_RAZA_MASCOTA,txtRazMas.getText().toString());

        int idCombo = (int) comboDuenio.getSelectedItemId();

        /*
        * Valida la seleccion del combo de duenios, si el usuario elige "seleccione" entonces
        * se retorna el id 0 ya que la palabra "seleccione" se encuentra en la posicion 0 del combo
        * sino entonces se retorna la posicion del combo para consultar el usuario almacenado
        * */
        if (idCombo != 0){
            int idDuenio = personasList.get(idCombo-1).getId();

            values.put(Utilidades.CAMPO_ID_DUENIO,idDuenio);
            Long idResultante = db.insert(Utilidades.TABLA_MASCOTA,Utilidades.CAMPO_ID_MASCOTA,values);

            Toast.makeText(getApplicationContext(),"Registrado "+idResultante.toString(),Toast.LENGTH_SHORT).show();

            db.close();

            limpiar();
        }else{
            Toast.makeText(getApplicationContext(),"SELECCIONE UN DUENIO",Toast.LENGTH_SHORT).show();
        }


    }

    private void limpiar() {
        txtRazMas.setText("");
        txtNomMas.setText("");
        comboDuenio.setSelection(0);
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        personasList = new ArrayList<Usuario>();

        String sql = "Select * from " + Utilidades.TABLA_USUARIO;

        Cursor cursor = db.rawQuery(sql,null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            personasList.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");
        for (int i = 0; i < personasList.size(); i++){
            listaPersonas.add(personasList.get(i).getId().toString()+" - "+personasList.get(i).getNombre());
        }
    }


}
