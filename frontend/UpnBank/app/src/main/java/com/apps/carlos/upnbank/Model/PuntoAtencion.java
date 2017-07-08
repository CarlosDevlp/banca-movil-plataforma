package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos on 29/06/2017.
 */

public class PuntoAtencion {

    @SerializedName("idPuntoAtencion")
    @Expose
    private int mId;

    @SerializedName("descripcion")
    @Expose
    private String mDescripcion;

    @SerializedName("coordenadaLat")
    @Expose
    private float mLat;

    @SerializedName("coordenadaLng")
    @Expose
    private float mLng;

    @SerializedName("TipoPuntoAtencion_idTipoPuntoAtencion")
    @Expose
    private int mIdTipoPuntoAtencion;

    public final static int TIPO_PUNTO_ATENCION_CAJERO=1,
                             TIPO_PUNTO_ATENCION_BANCO=2,
                             TIPO_PUNTO_ATENCION_AGENTE=3;
    //constructors

    public PuntoAtencion() {
    }

    //setters and getters
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getIdTipoPuntoAtencion() {
        return mIdTipoPuntoAtencion;
    }

    public void setIdTipoPuntoAtencion(int idTipoPuntoAtencion) {
        mIdTipoPuntoAtencion = idTipoPuntoAtencion;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }

    public float getLat() {
        return mLat;
    }

    public void setLat(float lat) {
        mLat = lat;
    }

    public float getLng() {
        return mLng;
    }

    public void setLng(float lng) {
        mLng = lng;
    }
}