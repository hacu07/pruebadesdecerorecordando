package com.hacu.pruebadesdecerorecordando.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hacu.pruebadesdecerorecordando.Entidades.Usuario;
import com.hacu.pruebadesdecerorecordando.R;

import java.util.ArrayList;

/**
 * Created by hacu1 on 07/08/2018.
 */

public class ListaPersonasAdapter extends RecyclerView.Adapter<ListaPersonasAdapter.PersonasViewHolder> {
    ArrayList<Usuario> listaUsuario;

    public ListaPersonasAdapter(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    @Override
    public PersonasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personas,null,false);
        return new PersonasViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(PersonasViewHolder holder, int position) {
        holder.doc.setText(listaUsuario.get(position).getId().toString());
        holder.nom.setText(listaUsuario.get(position).getNombre().toString());
        holder.tel.setText(listaUsuario.get(position).getTelefono());
    }

    @Override
    public int getItemCount() {
        return listaUsuario.size();
    }

    public class PersonasViewHolder extends RecyclerView.ViewHolder {
        TextView doc,nom,tel;

        public PersonasViewHolder(View itemView) {
            super(itemView);
            doc = (TextView) itemView.findViewById(R.id.txtDoc);
            nom = (TextView) itemView.findViewById(R.id.txtNom);
            tel = (TextView) itemView.findViewById(R.id.txtTel);
        }
    }
}
