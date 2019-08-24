package com.hacu.pruebadesdecerorecordando.Fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hacu.pruebadesdecerorecordando.Actividades.PersonajeVO;
import com.hacu.pruebadesdecerorecordando.Adaptadores.PersonajesAdapter;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.IComunicaFragments;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaPersonajesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaPersonajesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaPersonajesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    ArrayList<PersonajeVO> lisPer;
    RecyclerView recycler;

    Activity activity;
    IComunicaFragments interfaceComunicaFragments;

    public ListaPersonajesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaPersonajesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaPersonajesFragment newInstance(String param1, String param2) {
        ListaPersonajesFragment fragment = new ListaPersonajesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_lista_personajes, container, false);
        lisPer = new ArrayList<>();

        recycler = vista.findViewById(R.id.recMatDes);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        llenarListaPersonajes();

        PersonajesAdapter adapter = new PersonajesAdapter(lisPer);
        recycler.setAdapter(adapter);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Selecciona: "+lisPer.get(recycler.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
                //se utiliza interfaqz como puente para poder enviar los datos del elemento seleccionado
                interfaceComunicaFragments.enviarPersonaje(lisPer.get(recycler.getChildAdapterPosition(view)));
            }
        });
        return vista;
    }

    //agregamos los objetos que se van a presentar en la lista
    private void llenarListaPersonajes() {
        //SE DEBE PASAR A VALUES STRING
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Necesario para envio de la lista al edtalle
        if (context instanceof Activity){
            this.activity = (Activity) context;
            interfaceComunicaFragments = (IComunicaFragments) this.activity;
        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
