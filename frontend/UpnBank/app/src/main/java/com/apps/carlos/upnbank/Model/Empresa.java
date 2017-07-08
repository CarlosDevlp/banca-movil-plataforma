package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Carlos on 29/06/2017.
 */

public class Empresa implements Serializable{
    @SerializedName("idEmpresa")
    @Expose
    private int mId;

    @SerializedName("nombre")
    @Expose
    private String mNombre;

    @SerializedName("logoUrl")
    @Expose
    private String mLogoUrl;

    //constructor
    public Empresa() {
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

    public String getLogoUrl() {
        return mLogoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        mLogoUrl = logoUrl;
    }
}
