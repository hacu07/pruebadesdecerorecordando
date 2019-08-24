package com.hacu.pruebadesdecerorecordando.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hacu.pruebadesdecerorecordando.MainActivity;
import com.hacu.pruebadesdecerorecordando.R;

public class SaludoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()) {
            //Boton para ir a actividad 2
            case R.id.btnaAct3nav2:
                //Se indica al intent la navegacion o cambio de actividad (actividad actual, actividad a pasar)
                miIntent = new Intent(SaludoActivity.this, InstruccionesActivity.class);
                startActivity(miIntent);//se indica que se inicie la actividad indicada en el intent
                break;
            //Boton para Salir del programa
            case R.id.btnAct3Salir:
                //Termina el proceso y regresa a la actividad que la invoco (actvidad anterior)
                finish();
                break;

        }

    }
}
