package com.hacu.pruebadesdecerorecordando.Actividades;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

public class ConsultarUsuarioActivity extends AppCompatActivity {

    private EditText txtId,txtNombre,txtTelefono;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        txtId = (EditText) findViewById(R.id.txtDocumento);
        txtNombre = (EditText) findViewById(R.id.txtNom);
        txtTelefono = (EditText) findViewById(R.id.txtTel);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBus:
                //consultar();
                consultarSql();
                break;
            case R.id.btnAct:
                actualizarUsuario();
                break;
            case R.id.btnEli:
                eliminarUsuario();
                break;
        }
    }

    private void eliminarUsuario() {
        //Conexion a la BD
        SQLiteDatabase db = conn.getWritableDatabase();
        //parametros a enviar identificador para actualizar
        String[] parametros = {txtId.getText().toString()};

        //(tabla donde eliminamos registro, columna de registro a eliminar, parametro con identificador)
        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino el usuario",Toast.LENGTH_SHORT).show();
        txtId.setText("");
        limpiar();
        db.close();
    }


    private void actualizarUsuario() {
        //Conexion a la BD
        SQLiteDatabase db = conn.getWritableDatabase();
        //parametros a enviar identificador para actualizar
        String[] parametros = {txtId.getText().toString()};

        //contenedor de datos a actualizar
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,txtNombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,txtTelefono.getText().toString());

        //actualizacion en la bd
        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se Actualizo el usuario",Toast.LENGTH_SHORT).show();
        db.close();
    }

    //Realiza consulta usando script sql
    private void consultarSql() {
        //permiso para leer los datos de la BD
        SQLiteDatabase db = conn.getReadableDatabase();
        //parametros a enviar para consultar
        String[] parametros = {txtId.getText().toString()};
        try{
            //Select nombre,telefono from usuario where id =
            Cursor cursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+
                                            " FROM "+ Utilidades.TABLA_USUARIO+" WHERE "+Utilidades.CAMPO_ID+ "=?",parametros);

            cursor.moveToFirst();//apuntamos al primer registro de la respuesta
            txtNombre.setText(cursor.getString(0));
            txtTelefono.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error \n documento no existe",Toast.LENGTH_SHORT).show();
            limpiar();
        }

    }

    //Realiza la consulta a la BD usando CURSOR
    private void consultar() {
        //permiso para leer los datos de la BD
        SQLiteDatabase db = conn.getReadableDatabase();
        //parametros a enviar para consultar
        String[] parametros = {txtId.getText().toString()};
        //campos que se almacenaran del retorno de los datos de la consulta = response
        String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};

        try{
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            //cursor almacena la respuesta de la consulta
            cursor.moveToFirst();//apuntamos al primer registro de la respuesta
            txtNombre.setText(cursor.getString(0));
            txtTelefono.setText(cursor.getString(1));
            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error \n documento no existe",Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    private void limpiar() {
        txtNombre.setText("");
        txtTelefono.setText("");
    }
}
