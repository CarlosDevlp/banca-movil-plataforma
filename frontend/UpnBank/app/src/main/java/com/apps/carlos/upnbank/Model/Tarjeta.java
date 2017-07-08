package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Carlos on 30/06/2017.
 */

public class Tarjeta {
    @SerializedName("idTarjeta")
    @Expose
    private int mId;

    @SerializedName("numeroTarjeta")
    @Expose
    private String mNumeroTarjeta;

    @SerializedName("descripcion")
    @Expose
    private String mDescripcion;


    private ArrayList<CuentaBancaria> mCuentaBancariaList;
    //constructor
    public Tarjeta() {
    }

    //setters and getters
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNumeroTarjeta() {
        return mNumeroTarjeta;
    }

    public void setNumeroTarjeta(String numeroTarjeta) {
        mNumeroTarjeta = numeroTarjeta;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }
}
