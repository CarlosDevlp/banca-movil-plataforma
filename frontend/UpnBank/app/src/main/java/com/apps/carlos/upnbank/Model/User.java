package com.apps.carlos.upnbank.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 *
 * Created by Carlos on 16/06/2017.
 */
public class User extends Cliente{

    @SerializedName("idUsuario")
    @Expose
    private String mIdUser;
    @SerializedName("password")
    @Expose
    private String mPassword;


    private static User mUser;
    private ArrayList<CuentaBancaria> mCuentaBancariaList;

    public static User getInstance(){
        if(mUser==null)
            mUser=new User();
        return mUser;
    }

    //constructors

    public User() {
        super();
        mCuentaBancariaList =new ArrayList<>();
    }


    //setters and getters


    public String getIdUser() {
        return mIdUser;
    }

    public void setIdUser(String idUser) {
        mIdUser = idUser;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public ArrayList<CuentaBancaria> getCuentaBancariaList() {
        return mCuentaBancariaList;
    }

    public void setCuentaBancariaList(ArrayList<CuentaBancaria> cuentaBancariaList) {
        mCuentaBancariaList = cuentaBancariaList;
    }



    @Override
    public String toString() {
        return super.toString()+
                "\nUser{" +
                "mId='" + mIdUser + '\'' +
                ", mPassword='" + mPassword+ '\'' +
                '}';
    }
}
