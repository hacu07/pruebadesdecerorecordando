package com.hacu.pruebadesdecerorecordando.Actividades;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hacu.pruebadesdecerorecordando.Fragments.DetallePersonajeFragment;
import com.hacu.pruebadesdecerorecordando.Fragments.ListaPersonajesFragment;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.IComunicaFragments;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

public class MaterialDesignActivity extends AppCompatActivity implements ListaPersonajesFragment.OnFragmentInteractionListener,
        DetallePersonajeFragment.OnFragmentInteractionListener,IComunicaFragments{

    ListaPersonajesFragment listaFragment;
    DetallePersonajeFragment detalleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        //Si existe id contenedor fragment  (si existe estamos en modo portrait)

        if (findViewById(R.id.contenedorFragment) != null){
            Utilidades.portrait = true;
            if(savedInstanceState!=null){//si la instancia esta almacenada
                return;
            }
            listaFragment =  new ListaPersonajesFragment();
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.contenedorFragment,listaFragment).commit();
        }else {
            Utilidades.portrait = false;
        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void enviarPersonaje(PersonajeVO personaje) {

        //realiza el cambio de datos en el fragment cuando esta en mod landscape
        detalleFragment = (DetallePersonajeFragment)
                this.getSupportFragmentManager().findFragmentById(R.id.fragDetalle);

        //si se genera la instancia del detalle fragment en modo landscape
        // Y Si es verdad que el contenedor_fragment no existe (solo existe en modo portrait)
        if (detalleFragment != null && findViewById(R.id.contenedorFragment) == null){
            detalleFragment.asignarInformacion(personaje);
        }else{
            detalleFragment = new DetallePersonajeFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("objeto",personaje);
            detalleFragment.setArguments(bundle);

            //cargar el fragment en el activity
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.contenedorFragment,detalleFragment).addToBackStack(null).commit();
        }



    }
}
