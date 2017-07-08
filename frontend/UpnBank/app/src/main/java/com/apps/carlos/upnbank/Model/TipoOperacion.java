package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos on 30/06/2017.
 */

public class TipoOperacion {
    @SerializedName("idTipoOperacion")
    @Expose
    private int mId;
    @SerializedName("descripcion")
    @Expose
    private String mDescripcion;

    //constructor
    public TipoOperacion() {
    }

    //setters and getters

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }
}
