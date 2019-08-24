package com.hacu.pruebadesdecerorecordando;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.hacu.pruebadesdecerorecordando.Actividades.ConsultaComboActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.ConsultarUsuarioActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.ConsultarUsuarioListActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.InstruccionesActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.ListaMascotasActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.ListaPersonasRecyclerActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.MaterialDesignActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.RecyclerPersonalizadoActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.RegistrarUsuarioActivity;
import com.hacu.pruebadesdecerorecordando.Actividades.RegistroMascotaActivity;
import com.hacu.pruebadesdecerorecordando.Entidades.ConexionSQLiteHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView etiTitCom,etiTog,etiSpinner,txtShaPreUsu,txtShaPrePas;
    private EditText txtInfo,txtEnvPar;
    private CheckBox chb1, chb2;
    private RadioButton rad1, rad2;
    private ToggleButton btnTog;
    private Switch btnSwi;
    private Spinner spinnerDias;
    private ListView lisVie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se definen los componentes
        etiTitCom = (TextView) findViewById(R.id.etiTitCom);
        etiTog = (TextView) findViewById(R.id.etiTog);
        etiSpinner = (TextView) findViewById(R.id.etiSpinner);
        txtShaPreUsu = (EditText) findViewById(R.id.txtShaPreUsu);
        txtShaPrePas = (EditText) findViewById(R.id.txtShaPrePas);
        txtInfo = (EditText) findViewById(R.id.txtInfo);
        txtEnvPar = (EditText) findViewById(R.id.txtEnvPar);
        chb1 = (CheckBox) findViewById(R.id.chb1);
        chb2 = (CheckBox) findViewById(R.id.chb2);
        rad1 = (RadioButton) findViewById(R.id.rad1);
        rad2 = (RadioButton) findViewById(R.id.rad2);
        btnTog = (ToggleButton) findViewById(R.id.btnTog);
        btnSwi = (Switch) findViewById(R.id.btnSwi);
        spinnerDias = (Spinner) findViewById(R.id.spinnerDias);
        lisVie = (ListView) findViewById(R.id.lisVie);

        //Agregar datos a spinner (ComboBox)
        agregarDatosSpinner();//con datos fijos

        cargarDatosListView();

        cargarPreferencias();

        conectarBDSQLite();
    }

    public void onClick(View view) {
        Intent miIntent = null;

        switch (view.getId()){
            case R.id.btnEnvTextoTitulo:
                etiTitCom.setText(txtInfo.getText().toString());
                break;

            case R.id.btnChb:
                validarCheckBox();
                break;

            case R.id.btnTog:
                validarEstadoToggle();
                break;
            case R.id.btnSwi:
                validarSwicth();
                break;
            case R.id.btnNavAct2:
                cambiarActividad();
                break;

            case R.id.btnShaPreGua:
                guardarPreferencias();
                break;

            case R.id.btnShaPreCar:
                miIntent = new Intent(MainActivity.this,InstruccionesActivity.class);
                break;

            case R.id.btnSqlRegUsu:
                miIntent = new Intent(MainActivity.this, RegistrarUsuarioActivity.class);
                break;
            case R.id.btnSqlConUsu:
                miIntent = new Intent(MainActivity.this, ConsultarUsuarioActivity.class);
                break;
            case R.id.btnSqlConUsuSpi:
                miIntent = new Intent(MainActivity.this, ConsultaComboActivity.class);
                break;
            case R.id.btnSqlConUsuLis:
                miIntent = new Intent(MainActivity.this, ConsultarUsuarioListActivity.class);
                break;
            case R.id.btnSqlRegMas:
                miIntent = new Intent(MainActivity.this, RegistroMascotaActivity.class);
                break;
            case R.id.btnSqlConMas:
                miIntent = new Intent(MainActivity.this, ListaMascotasActivity.class);
                break;
            case R.id.btnSqlConRec:
                miIntent = new Intent(MainActivity.this, ListaPersonasRecyclerActivity.class);
                break;
            case R.id.btnRecPer:
                miIntent =  new Intent(MainActivity.this, RecyclerPersonalizadoActivity.class);
                break;
            case R.id.btnMatDes:
                miIntent =  new Intent(MainActivity.this, MaterialDesignActivity.class);
                break;
        }

        if(miIntent != null) {
            startActivity(miIntent);
        }
    }

    private void conectarBDSQLite(){
        // Conexion a la BD SQLite
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    private void cargarPreferencias() {
        //objeto preferencias modo privado para solo acceder a este desde la app
        //abrimos el archivo y trabajamos con el solo para lectura
        SharedPreferences preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        String user = preferencias.getString("user","no existe la informacion");
        String pass = preferencias.getString("pass","no existe la informacion");

        imprimirMensaje("SHAREDPREFERENCES \n"+"User: "+user+"\nPass: "+pass);
    }

    private void guardarPreferencias() {
        //Lee la informacion de los campos y los guarda en un archivo
        //objeto preferencias modo privado para solo acceder a este desde la app
        SharedPreferences preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        //tomamos los datos de los campos
        String usuario = txtShaPreUsu.getText().toString();
        String contrasena = txtShaPrePas.getText().toString();

        //Permite editar el archivo
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("user",usuario);//como el dato es string se asigna putString
        editor.putString("pass",contrasena);

        imprimirMensaje("User: "+usuario+"\nPass: "+contrasena);

        //completa el proceso de crear el archivo y almacenar informacion
        editor.commit();
    }

    //Uso de componente ListView y carga de datos con arraylist
    private void cargarDatosListView() {
        //Lista de arreglo con nombres a poblar el listView
        ArrayList<String> listaNombres =  new ArrayList<>();
        listaNombres.add("Harold");
        listaNombres.add("Mariela");
        listaNombres.add("Luis");
        listaNombres.add("Oscar");
        listaNombres.add("Mompi");


        ArrayAdapter adaptador = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,listaNombres);
        lisVie.setAdapter(adaptador);

        //Evento cuando se de un clic
        lisVie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                imprimirMensaje(parent.getItemAtPosition(position).toString());
            }
        });
    }

    private void agregarDatosSpinner() {

        //uso de arrayList para llenado dinamico de datos
        ArrayList<String> comboDiasList = new ArrayList<String>();
        comboDiasList.add("Seleccion");
        for (int i = 0; i<10; i++){
            comboDiasList.add(String.valueOf(i));
        }

        //agregando datos usando arrayList al crear un adapter
        ArrayAdapter<CharSequence> adapter =  new ArrayAdapter(this,android.R.layout.simple_spinner_item,comboDiasList);


        //agregando datos usando archivo de recursos
        //ArrayAdapter<CharSequence> adapter =  ArrayAdapter.createFromResource(this,R.array.comboDias,android.R.layout.simple_spinner_item);

        //asignacion de adaptador al spinner
        spinnerDias.setAdapter(adapter);

        //una vez seleccionado una opcion la enviamos a toast para verla
        spinnerDias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                imprimirMensaje("Seleccionado: "+parent.getItemAtPosition(position).toString() );
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //Encargado de realizar el cambio de actividad y de enviar el parametro entre estas
    private void cambiarActividad() {
        //Se indica al intent la navegacion o cambio de actividad (actividad actual, actividad a pasar)
        Intent miIntent = new Intent(MainActivity.this, InstruccionesActivity.class);

        //ENVIO DE PARAMETRO ENTRE ACTIVIDADES
        Bundle miBundle = new Bundle();
        //(identificador, valor a enviar)
        miBundle.putString("parametro",txtEnvPar.getText().toString());

        //agregamos el bundle al intent para enviarlo
        miIntent.putExtras(miBundle);

        startActivity(miIntent);//se indica que se inicie la actividad indicada en el intent
    }

    private void validarSwicth() {
        if (btnSwi.isChecked()){
            imprimirMensaje("Switch Activado");
        }else{
            imprimirMensaje("Swicth Desactivado");
        }
    }

    private void validarEstadoToggle() {
        //Si el ToggleButton esta chequeado
        if (btnTog.isChecked()){
            etiTog.setText("Boton ON");
        }else{
            etiTog.setText("Boton OFF");
        }
    }

    //Imprime un mensaje indicando cual checkbox fue seleccionado
    private void validarCheckBox() {
        String texto = "Seleccionado \n";

        if (chb1.isChecked()){
            texto += "opcion 1 \n";
        }
        if (chb2.isChecked()){
            texto += "opcion 2 \n";
        }


        if (rad1.isChecked()){
            texto += "Radio 1 \n";
        }
        if (rad2.isChecked()){
            texto += "Radio 2 \n";
        }


        imprimirMensaje(texto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        imprimirMensaje("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        imprimirMensaje("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        imprimirMensaje("onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        imprimirMensaje("onPause");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        imprimirMensaje("onPostResume");
    }

    private void imprimirMensaje(String msj){
        Toast.makeText(getApplicationContext(),msj,Toast.LENGTH_SHORT).show();
    }
}
