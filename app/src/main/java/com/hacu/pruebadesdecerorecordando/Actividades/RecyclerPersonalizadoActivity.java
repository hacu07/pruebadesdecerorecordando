package com.hacu.pruebadesdecerorecordando.Actividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.hacu.pruebadesdecerorecordando.Adaptadores.PersonajesAdapter;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

public class RecyclerPersonalizadoActivity extends AppCompatActivity {

    ArrayList<PersonajeVO> lisPer;
    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_personalizado);

        construirRecycler();
    }

    private void llenarPersonajes() {
        lisPer.add(new PersonajeVO("Celular","CelularCelularCelularCelular","",R.drawable.cel,R.drawable.cel));
        lisPer.add(new PersonajeVO("fav","favfavfavfavfav","",R.drawable.fav,R.drawable.fav));
        lisPer.add(new PersonajeVO("like","likelikelikelikelike","",R.drawable.like,R.drawable.like));
        //lisPer.add(new PersonajeVO("mente","esto es mente",R.drawable.mente));
        lisPer.add(new PersonajeVO("musculo","musculomusculomusculomusculo","",R.drawable.musculo,R.drawable.musculo));
        lisPer.add(new PersonajeVO("ok","esto es ok","",R.drawable.ok,R.drawable.ok));
        lisPer.add(new PersonajeVO("paloma","esto es paloma","",R.drawable.paloma,R.drawable.paloma));
        lisPer.add(new PersonajeVO("pizza","esto es pizza","",R.drawable.pizza,R.drawable.pizza));
        lisPer.add(new PersonajeVO("punio","esto es punio","",R.drawable.punio,R.drawable.punio));


    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnList:
                Utilidades.visualizacion = Utilidades.LIST;
                break;

            case R.id.btnGrid:
                Utilidades.visualizacion = Utilidades.GRID;
                break;
        }
        
        construirRecycler();
    }

    //Genera la logica del recycler y su orden a presentar
    private void construirRecycler() {
        lisPer =  new ArrayList<>();
        recycler = (RecyclerView) findViewById(R.id.recyclerPersonajes);
        //Manera de presentar los datos
        if (Utilidades.visualizacion == Utilidades.LIST){
            //Linear
            recycler.setLayoutManager(new LinearLayoutManager(this));
        }else{
            //Grid
            recycler.setLayoutManager(new GridLayoutManager(this,4));
        }
        llenarPersonajes();

        PersonajesAdapter adapter = new PersonajesAdapter(lisPer);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Seleccion: "+lisPer.get(recycler.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
            }
        });

        recycler.setAdapter(adapter);
    }
}
