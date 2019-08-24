package com.hacu.pruebadesdecerorecordando.Actividades;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

public class RegistrarUsuarioActivity extends AppCompatActivity {
    private EditText txtId,txtNombre,txtTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);
        txtId = (EditText) findViewById(R.id.txtIdSql);
        txtNombre = (EditText) findViewById(R.id.txtNomSql);
        txtTelefono = (EditText) findViewById(R.id.txtTelSql);
    }
    
    public void onClick(View view){
        //registrarUsuariosSql();
        registrarUsuarios();
    }

    //Usando script SQL
    private void registrarUsuariosSql() {
        //Conectamos a la BD
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        //ABRE LA BD  Para ejecutar scripts
        SQLiteDatabase db = conn.getWritableDatabase();

        //INSERT INTO usuario (id,nombre,telefono) values (2,'Pipe','456')
        String insert = "INSERT INTO "+Utilidades.TABLA_USUARIO+" ("+Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+") values" +
                " ("+txtId.getText().toString()+",'"+txtNombre.getText().toString()+"','"+txtTelefono.getText().toString()+"')";

        db.execSQL(insert);

        db.close();
    }

    //Usando contentValues
    private void registrarUsuarios() {
        //Conectamos a la BD
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        //ABRE LA BD  Para ejecutar scripts
        SQLiteDatabase db = conn.getWritableDatabase();

        //Almancenamos los datos a insertar en la BD en un contentvalues
        ContentValues values =  new ContentValues();
        //("clave identificadora","dato")
        values.put(Utilidades.CAMPO_ID,txtId.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,txtNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,txtTelefono.getText().toString());

        //Enviamos los datos para la insercion
        //(nombre de la tabla donde vamos a insertar, campo que queremos que retorne al insertar, datos a insertar)
        //retorna un dato long al insertar en la BD
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();

        db.close();
    }

}
