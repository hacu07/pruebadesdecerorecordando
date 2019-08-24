package com.hacu.pruebadesdecerorecordando.Adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hacu.pruebadesdecerorecordando.Actividades.PersonajeVO;
import com.hacu.pruebadesdecerorecordando.R;
import com.hacu.pruebadesdecerorecordando.Utilidades.Utilidades;

import java.util.ArrayList;

/**
 * Created by hacu1 on 07/08/2018.
 */

public class PersonajesAdapter
        extends RecyclerView.Adapter<PersonajesAdapter.ViewHolderPersonajes>
        implements View.OnClickListener{

    ArrayList<PersonajeVO> lisPer;
    private View.OnClickListener listener;

    public PersonajesAdapter(ArrayList<PersonajeVO> lisPer) {
        this.lisPer = lisPer;
    }

    @Override
    public ViewHolderPersonajes onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout = 0;
        layout = R.layout.lista_personajes;
        //inflamos el view
        //Manera de presentar los datos
        /*if (Utilidades.visualizacion == Utilidades.LIST){
            //modo lineal

        }else{
            //modo grid
            layout = R.layout.item_grid_personajes;
        }*/
        View vista = LayoutInflater.from(parent.getContext()).inflate(layout,null,false);

        //escucha el evento de seleccion (clic)
        vista.setOnClickListener(this);

        return new ViewHolderPersonajes(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderPersonajes holder, int position) {
        //Manera de presentar los datos
        /*if (Utilidades.visualizacion == Utilidades.LIST){
            holder.txtDes.setText(lisPer.get(position).getDescripcion());
        }*/
        if (Utilidades.portrait == true){//video material design
            holder.txtDes.setText(lisPer.get(position).getDescripcion());
        }
            holder.txtNombre.setText(lisPer.get(position).getNombre());
            holder.img.setImageResource(lisPer.get(position).getImg());//las imagenes las traemos del drawable


    }

    @Override
    public int getItemCount() {
        return lisPer.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!= null){
            listener.onClick(view);
        }
    }

    public class ViewHolderPersonajes extends RecyclerView.ViewHolder {

        ImageView img;
        TextView txtNombre,txtDes;

        public ViewHolderPersonajes(View itemView) {
            super(itemView);
            //img = (ImageView) itemView.findViewById(R.id.img);//si es grid
            img = (ImageView) itemView.findViewById(R.id.imgLis);//si es list, reemplaza el anterior
            if (Utilidades.portrait == true){
                txtDes = (TextView) itemView.findViewById(R.id.txtDes);
            }

                txtNombre = (TextView) itemView.findViewById(R.id.txtNom);

        }
    }
}
