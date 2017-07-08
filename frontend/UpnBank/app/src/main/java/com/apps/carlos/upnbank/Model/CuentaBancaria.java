package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Carlos on 17/06/2017.
 */
public class CuentaBancaria implements Serializable{

    @SerializedName("idCuentaBancaria")
    @Expose
    private int mId;

    @SerializedName("numeroCuenta")
    @Expose
    private String mNumeroCuenta;

    @SerializedName("numeroCCI")
    @Expose
    private String mCCI;

    @SerializedName("saldo")
    @Expose
    private float mSaldo;

    @SerializedName("tipoMoneda")
    @Expose
    private int mTipoMoneda;

    @SerializedName("Cliente__idCliente")
    @Expose
    private int mIdCliente;


    @SerializedName("TipoCuenta__idTipoCuenta")
    @Expose
    private int mIdTipoCuenta;

    @SerializedName("Tarjeta__idTarjeta")
    @Expose
    private int mIdTarjeta;

    @SerializedName("numeroTarjeta")
    @Expose
    private String mTarjetaNumero;

    @SerializedName("descripcion")
    @Expose
    private String mDescripcion;


    /**
     * ACCOUNT_CREDIT: cuenta tipo crédito
     * ACCOUNT_SAVING: cuenta tipo ahorro
     *
     */
    public static final int ACCOUNT_CREDIT=2,ACCOUNT_SAVING=1;

    /**
     * CURRENCY_DOLAR: moneda tipo dolar
     * CURRENCY_SOLES: moneda tipo soles
     *
     */
    public static final int CURRENCY_DOLAR=2,CURRENCY_SOLES=1;


    //constructores
    public CuentaBancaria() {
    }

    public CuentaBancaria(int id, String CCI, int type, int currencyType) {
        mId = id;
        mCCI = CCI;
        mIdTipoCuenta = type;
        mTipoMoneda = currencyType;
        mSaldo = 0;
    }

    //setters and getters
    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getCCI() {
        return mCCI;
    }

    public void setCCI(String CCI) {
        mCCI = CCI;
    }

    public int getType() {
        return mIdTipoCuenta;
    }

    public void setType(int type) {
        mIdTipoCuenta = type;
    }

    public int getCurrencyType() {
        return mTipoMoneda;
    }

    public void setCurrencyType(int currencyType) {
        mTipoMoneda = currencyType;
    }

    public float getSaldo() {
        return mSaldo;
    }

    public void setSaldo(float saldo) {
        mSaldo = saldo;
    }

    public String getNumeroCuenta() {
        return mNumeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        mNumeroCuenta = numeroCuenta;
    }

    public int getTipoMoneda() {
        return mTipoMoneda;
    }

    public void setTipoMoneda(int tipoMoneda) {
        mTipoMoneda = tipoMoneda;
    }

    public int getIdCliente() {
        return mIdCliente;
    }

    public void setIdCliente(int idCliente) {
        mIdCliente = idCliente;
    }

    public int getIdTipoCuenta() {
        return mIdTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        mIdTipoCuenta = idTipoCuenta;
    }

    public int getIdTarjeta() {
        return mIdTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        mIdTarjeta = idTarjeta;
    }

    public String getTarjetaNumero() {
        return mTarjetaNumero;
    }

    public void setTarjetaNumero(String tarjetaNumero) {
        mTarjetaNumero = tarjetaNumero;
    }

    public String getDescripcion() {
        return mDescripcion;
    }

    public void setDescripcion(String descripcion) {
        mDescripcion = descripcion;
    }

    //métodos mágicos

    @Override
    public String toString() {
        return  "CCI:" + mCCI +
                "\nCuenta de "+((mIdTipoCuenta ==1)?"Ahorros":"Crédito")+
                "\nTipo de moneda: " + (mTipoMoneda ==1?"Dólares":"Soles")+
                "\nSaldo:" + mSaldo;
    }
}
