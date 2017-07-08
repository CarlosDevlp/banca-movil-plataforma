package com.apps.carlos.upnbank.Helpers;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.apps.carlos.upnbank.R;

import java.util.Map;

/**
 * Created by Carlos on 28/06/2017.
 */

public class UpnBankServiceClient {
    private RequestQueue mQueue;
    private static UpnBankServiceClient mUpnBankServiceClient;
    private Context mContext;

    //constantes rutas
    private String mBaseUrl;
    private final String GET_CLIENT_USER ="api/cliente/usuario";
    private final String GET_USERS="api/usuario/listar";
    private final String GET_PUNTOS_ATENCION="api/punto-atencion/listar";
    private final String GET_EMPRESAS="api/empresa/listar";
    private final String GET_SERVICIO="api/servicio";
    private final String GET_TARJETA_CUENTA_BANCARIA="api/tarjeta/cuenta-bancaria";
    private final String GET_TIPOS_OPERACION="api/tipo-operacion/listar";
    private final String GET_OPERACION="api/operacion";
    private final String POST_OPERACION="api/operacion";
    private final String PUT_CUENTA_BANCARIA_SALDO="api/cuenta-bancaria/saldo";
    private final String GET_CUENTA_BANCARIA="api/cuenta-bancaria";

    private final String LOG_TAG="UpnBankServiceClient";

    //constructor
    private UpnBankServiceClient (){
    }

    private UpnBankServiceClient (Context context){
        mQueue = Volley.newRequestQueue(context);
        mContext= context;
        mBaseUrl=mContext.getString(R.string.url_base);
    }


    public static  UpnBankServiceClient getInstance(Context context){
        if(mUpnBankServiceClient==null)
            mUpnBankServiceClient=new UpnBankServiceClient(context);
        return  mUpnBankServiceClient;
    }

    public static  UpnBankServiceClient getInstance(){
        if(mUpnBankServiceClient==null)
            mUpnBankServiceClient=new UpnBankServiceClient();
        return  mUpnBankServiceClient;
    }

    //setters and getters
    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        mBaseUrl = baseUrl;
    }

    //otros métodos
    public void addRequest(Request request){
        mQueue.add(request);
    }

    public void getUsers(Response.Listener<String> onSuccess,Response.ErrorListener onError){
        addRequest(new StringRequest(Request.Method.GET,mBaseUrl+GET_USERS,onSuccess,onError));
    }

    public void getUser(String dni,String password,Response.Listener<String> onSuccess,Response.ErrorListener onError){
        Uri url=Uri.parse(mBaseUrl+ GET_CLIENT_USER).buildUpon()
                    .appendQueryParameter("dni",dni)
                    .appendQueryParameter("password",password)
                .build();
        addRequest(new StringRequest(Request.Method.GET,url.toString(),onSuccess,onError));
    }


    public void getPuntosAtencion(int idTipoPuntoAtencion,Response.Listener<String> onSuccess,Response.ErrorListener onError){
        String strUrl;

        if(idTipoPuntoAtencion>0) {
            Uri url = Uri.parse(mBaseUrl + GET_PUNTOS_ATENCION).buildUpon()
                    .appendQueryParameter("idTipoPuntoAtencion", idTipoPuntoAtencion+"")
                    .build();
            strUrl=url.toString();
        }
        else strUrl=mBaseUrl+ GET_PUNTOS_ATENCION;

        Log.d(LOG_TAG,strUrl);
        addRequest(new StringRequest(Request.Method.GET,strUrl,onSuccess,onError));
    }


    //
    public void getEmpresas(Response.Listener<String> onSuccess,Response.ErrorListener onError){
        addRequest(new StringRequest(Request.Method.GET,mBaseUrl+GET_EMPRESAS,onSuccess,onError));
    }

    public void getServicio(int idEmpresa,Response.Listener<String> onSuccess,Response.ErrorListener onError){
        String strUrl;

        if(idEmpresa>0) {
            Uri url = Uri.parse(mBaseUrl + GET_SERVICIO).buildUpon()
                    .appendQueryParameter("idEmpresa", idEmpresa+"")
                    .build();
            strUrl=url.toString();
        }
        else strUrl=mBaseUrl+ GET_SERVICIO;

        Log.d(LOG_TAG,strUrl);
        addRequest(new StringRequest(Request.Method.GET,strUrl,onSuccess,onError));
    }

    public void getTarjetaCuentaBancaria(int idCliente,Response.Listener<String> onSuccess,Response.ErrorListener onError){
        String strUrl;
        if(idCliente>0) {
            Uri url = Uri.parse(mBaseUrl + GET_TARJETA_CUENTA_BANCARIA).buildUpon()
                    .appendQueryParameter("idCliente", idCliente+"")
                    .build();
            strUrl=url.toString();
        }
        else strUrl=mBaseUrl+ GET_TARJETA_CUENTA_BANCARIA;
        Log.d(LOG_TAG,strUrl);
        addRequest(new StringRequest(Request.Method.GET,strUrl,onSuccess,onError));
    }

    public void getTiposOperacion(Response.Listener<String> onSuccess,Response.ErrorListener onError){
        addRequest(new StringRequest(Request.Method.GET,mBaseUrl+GET_TIPOS_OPERACION,onSuccess,onError));
    }

    public void getOperacion(int idCuentaBancaria,Response.Listener<String> onSuccess,Response.ErrorListener onError){
        String strUrl;
        if(idCuentaBancaria>0) {
            Uri url = Uri.parse(mBaseUrl + GET_OPERACION).buildUpon()
                    .appendQueryParameter("idCuentaBancaria", idCuentaBancaria+"")
                    .build();
            strUrl=url.toString();
        }
        else strUrl=mBaseUrl+ GET_OPERACION;

        Log.d(LOG_TAG,strUrl);
        addRequest(new StringRequest(Request.Method.GET,strUrl,onSuccess,onError));
    }


    public void insertOperacion(final Map<String, String> params, Response.Listener<String> onSuccess, Response.ErrorListener onError){
        addRequest(new StringRequest(Request.Method.POST,mBaseUrl+POST_OPERACION,onSuccess,onError){
            //pasar lo parámetros
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        });
    }

    public void updateCuentaBancariaSaldo(final Map<String, String> params, Response.Listener<String> onSuccess, Response.ErrorListener onError){
        addRequest(new StringRequest(Request.Method.PUT,mBaseUrl+PUT_CUENTA_BANCARIA_SALDO,onSuccess,onError){
            //pasar lo parámetros
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        });
    }

    public void getCuentaBancaria(String numeroCuentaDestino,Response.Listener<String> onSuccess,Response.ErrorListener onError){
        String strUrl;
        if(!numeroCuentaDestino.isEmpty()) {
            Uri url = Uri.parse(mBaseUrl + GET_CUENTA_BANCARIA).buildUpon()
                    .appendQueryParameter("numeroCuenta", numeroCuentaDestino+"")
                    .build();
            strUrl=url.toString();
        }
        else strUrl=mBaseUrl+ GET_CUENTA_BANCARIA;

        Log.d(LOG_TAG,strUrl);
        addRequest(new StringRequest(Request.Method.GET,strUrl,onSuccess,onError));
    }


}