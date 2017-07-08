package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Carlos on 28/06/2017.
 */

public class Cliente {
    @SerializedName("idCliente")
    @Expose
    private int mId;

    @SerializedName("nombre")
    @Expose
    private String mNombre;

    @SerializedName("apellidos")
    @Expose
    private String mApellidos;

    @SerializedName("dni")
    @Expose
    private String mDni;

    @SerializedName("telefono")
    @Expose
    private String mTelefono;

    @SerializedName("correo")
    @Expose
    private String mCorreo;

    //constructor
    public Cliente() {
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

    public String getApellidos() {
        return mApellidos;
    }

    public void setApellidos(String apellidos) {
        mApellidos = apellidos;
    }

    public String getDni() {
        return mDni;
    }

    public void setDni(String dni) {
        mDni = dni;
    }

    public String getTelefono() {
        return mTelefono;
    }

    public void setTelefono(String telefono) {
        mTelefono = telefono;
    }

    public String getCorreo() {
        return mCorreo;
    }

    public void setCorreo(String correo) {
        mCorreo = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "mId=" + mId +
                ", mNombre='" + mNombre + '\'' +
                ", mApellidos='" + mApellidos + '\'' +
                ", mDni='" + mDni + '\'' +
                ", mTelefono='" + mTelefono + '\'' +
                ", mCorreo='" + mCorreo + '\'' +
                '}';
    }
}
