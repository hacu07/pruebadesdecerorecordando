package com.hacu.pruebadesdecerorecordando.Actividades;

import java.io.Serializable;

/**
 * Clase usada para alimentar la lista usada en el recyclerview personalizado
 * Created by hacu1 on 07/08/2018.
 */

public class PersonajeVO implements Serializable {

    private String nombre;
    private String descripcion;
    private int img;

    //Detalle Fragment
    private  String descripcionDetalle;
    private int imgDetalle;

    public PersonajeVO() {
    }

    public PersonajeVO(String nombre, String descripcion,String descripcionDetalle, int img, int imgDetalle) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descripcionDetalle = descripcionDetalle;
        this.img = img;
        this.imgDetalle = imgDetalle;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImg() {
        return img;
    }

    public String getDescripcionDetalle() {
        return descripcionDetalle;
    }

    public void setDescripcionDetalle(String descripcionDetalle) {
        this.descripcionDetalle = descripcionDetalle;
    }

    public int getImgDetalle() {
        return imgDetalle;
    }

    public void setImgDetalle(int imgDetalle) {
        this.imgDetalle = imgDetalle;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
