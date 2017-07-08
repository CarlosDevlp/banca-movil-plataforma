package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos on 30/06/2017.
 */

public class Operacion {

    @SerializedName("idOperacion")
    @Expose
    private int mId;

    @SerializedName("fechaOperacion")
    @Expose
    private String mFechaOperacion;

    @SerializedName("monto")
    @Expose
    private float mMonto;

    @SerializedName("CuentaBancaria_idCuentaBancaria")
    @Expose
    private int mIdCuentaBancaria;

    @SerializedName("TipoOperacion_idTipoOperacion")
    @Expose
    private int mIdTipoOperacion;

    @SerializedName("descripcion")
    @Expose
    private String mTipoOperacion;
    //constructors
    public Operacion() {

    }


    //setters and getters
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getFechaOperacion() {
        return mFechaOperacion;
    }

    public void setFechaOperacion(String fechaOperacion) {
        mFechaOperacion = fechaOperacion;
    }

    public float getMonto() {
        return mMonto;
    }

    public void setMonto(float monto) {
        mMonto = monto;
    }

    public int getIdCuentaBancaria() {
        return mIdCuentaBancaria;
    }

    public void setIdCuentaBancaria(int idCuentaBancaria) {
        mIdCuentaBancaria = idCuentaBancaria;
    }

    public int getIdTipoOperacion() {
        return mIdTipoOperacion;
    }

    public void setIdTipoOperacion(int idTipoOperacion) {
        mIdTipoOperacion = idTipoOperacion;
    }

    public String getTipoOperacion() {
        return mTipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        mTipoOperacion = tipoOperacion;
    }

    @Override
    public String toString() {
        return "Operacion{" +
                "mId=" + mId +
                ", mFechaOperacion='" + mFechaOperacion + '\'' +
                ", mMonto=" + mMonto +
                ", mIdCuentaBancaria=" + mIdCuentaBancaria +
                ", mIdTipoOperacion=" + mIdTipoOperacion +
                ", mTipoOperacion='" + mTipoOperacion + '\'' +
                '}';
    }
}
