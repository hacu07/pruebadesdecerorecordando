package com.hacu.pruebadesdecerorecordando.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.hacu.pruebadesdecerorecordando.Entidades.Usuario;
import com.hacu.pruebadesdecerorecordando.R;

public class DetalleUsuarioActivity extends AppCompatActivity {

    TextView txtId,txtNom,txtTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);
        txtId = (TextView) findViewById(R.id.txtId);
        txtNom = (TextView) findViewById(R.id.txtNom);
        txtTel = (TextView) findViewById(R.id.txtTel);

        //Obtenemos parametro enviado
        Bundle objetoEnviado = getIntent().getExtras();
        Usuario user = null;

        if (objetoEnviado != null){
            user = (Usuario) objetoEnviado.getSerializable("usuario");
            txtId.setText(user.getId().toString());
            txtNom.setText(user.getNombre().toString());
            txtTel.setText(user.getTelefono().toString());
        }

    }
}
