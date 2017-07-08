package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos on 29/06/2017.
 */

public class Servicio {
    @SerializedName("idServicio")
    @Expose
    private int mId;

    @SerializedName("nombre")
    @Expose
    private String mNombre;

    @SerializedName("descripcion")
    @Expose
    private String mDescripcion;

    @SerializedName("costo")
    @Expose
    private float mCosto;

    @SerializedName("Empresa_idEmpresa")
    @Expose
    private int mIdEmpresa;

    @SerializedName("TipoServicio_idTipoServicio")
    @Expose
    private int mIdTipoServicio;

    //constructor
    public Servicio() {

    }

    //setters and getters
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNombre() {
        return mNombre;
    }

    public void setNombre(String nombre) {
        mNombre = nombre;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }

    public float getCosto() {
        return mCosto;
    }

    public void setCosto(float costo) {
        mCosto = costo;
    }

    public int getIdEmpresa() {
        return mIdEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        mIdEmpresa = idEmpresa;
    }

    public int getIdTipoServicio() {
        return mIdTipoServicio;
    }

    public void setIdTipoServicio(int idTipoServicio) {
        mIdTipoServicio = idTipoServicio;
    }
}
