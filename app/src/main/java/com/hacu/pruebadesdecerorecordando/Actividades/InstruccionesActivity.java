package com.hacu.pruebadesdecerorecordando.Actividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hacu.pruebadesdecerorecordando.MainActivity;
import com.hacu.pruebadesdecerorecordando.R;

public class InstruccionesActivity extends AppCompatActivity {

    private TextView txtParRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones);
        txtParRec = (TextView) findViewById(R.id.txtParRec);

        //Obtiene el dato que se envio por parametro entre actividades
        Bundle miBundle =  this.getIntent().getExtras();

        if (miBundle != null){
            String parametro = miBundle.getString("parametro");
            txtParRec.setText("Mensaje enviado por parametro: "+ parametro);
        }
    }

    public void onClick(View view) {
        Intent miIntent = null;
        switch (view.getId()){
            //Boton para ir a actividad 3
            case R.id.btnNavAct3:
                //Se indica al intent la navegacion o cambio de actividad (actividad actual, actividad a pasar)
                miIntent = new Intent(InstruccionesActivity.this, SaludoActivity.class);
                break;
            //Boton para ir a actividad 1 - actividad inicial
            case R.id.btnNavAct1:
                //Se indica al intent la navegacion o cambio de actividad (actividad actual, actividad a pasar)
                miIntent = new Intent(InstruccionesActivity.this, MainActivity.class);
                break;
        }
        startActivity(miIntent);//se indica que se inicie la actividad indicada en el intent
    }
}
