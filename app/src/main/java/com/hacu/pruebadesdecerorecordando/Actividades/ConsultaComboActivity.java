package com.hacu.pruebadesdecerorecordando.Actividades;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.Entidades.Usuario;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaComboActivity extends AppCompatActivity {

    private Spinner comboPersonas;
    private TextView lblCod,lblNom,lblTel;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);

        comboPersonas = (Spinner) findViewById(R.id.comboPersonas);
        lblCod = (TextView) findViewById(R.id.lblDoc);
        lblNom = (TextView) findViewById(R.id.lblNom);
        lblTel = (TextView) findViewById(R.id.lblTel);

        conn =  new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        consultarListaPersonas();
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario persona = null;
        personasList = new ArrayList<Usuario>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona = new Usuario();//empezar a asignar en este objeto los resultados de la BD
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            personasList.add(persona);
        }

        obtenerLista();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaPersonas);

        comboPersonas.setAdapter(adaptador);

        //EVENTO PARA MOSTRAR INFORMACION DEL ELEMENTO SELECCIONADO DEL SPINNER
        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0){
                    lblCod.setText(personasList.get(position-1).getId().toString());
                    lblNom.setText(personasList.get(position-1).getNombre().toString());
                    lblTel.setText(personasList.get(position-1).getTelefono().toString());
                }else{
                    lblCod.setText("");
                    lblNom.setText("");
                    lblTel.setText("");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //lista a mostrar en el combo
    private void obtenerLista() {

        listaPersonas =  new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for (int i = 0; i<personasList.size(); i++){
            //obtiene de la lista el id y nombre
            listaPersonas.add(personasList.get(i).getId()+ " - " + personasList.get(i).getNombre());
        }
    }


}
