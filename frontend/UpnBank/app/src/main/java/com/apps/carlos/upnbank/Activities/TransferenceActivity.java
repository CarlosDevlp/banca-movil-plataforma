package com.apps.carlos.upnbank.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Adapters.BankAccountSpinnerAdapter;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.Model.Servicio;
import com.apps.carlos.upnbank.Model.User;
import com.apps.carlos.upnbank.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TransferenceActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.spn_bank_account_list) Spinner mSpnBankAccountList;
    @BindView(R.id.edt_monto)EditText mEdtMonto;
    @BindView(R.id.edt_num_cuenta_destino)EditText mEdtNumCuentaDestino;

    private User mUser;
    private ArrayList<CuentaBancaria> mCuentaBancariaArrayList;

    private final String LOG_TAG="TransferenceAct",LOG_TAG_ERROR="TransferenceAct-Err";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transference);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);


        //obtener el usuario
        mUser= User.getInstance();
        mCuentaBancariaArrayList=mUser.getCuentaBancariaList();
        assignBankAccountAdapter(mCuentaBancariaArrayList);
    }

    private void assignBankAccountAdapter(ArrayList bankAccountList){
        mSpnBankAccountList.setAdapter(new BankAccountSpinnerAdapter(this, bankAccountList));
    }

    @OnClick(R.id.btn_transfer)
    public void transfer(){
        //consumir servicio
        int currentIndex=mSpnBankAccountList.getSelectedItemPosition();


        float monto=0f;
        if(!mEdtMonto.getText().toString().isEmpty())
            monto=Float.parseFloat(mEdtMonto.getText().toString());
        else {
            alert("Complete el campo monto",null);
            return;
        }


        String cuentaDestino="";
        if(!mEdtNumCuentaDestino.getText().toString().isEmpty())
            cuentaDestino=mEdtNumCuentaDestino.getText().toString();
        else {
            alert("Complete el campo cuenta destino",null);
            return;
        }



        CuentaBancaria currentCuentaBancaria=mCuentaBancariaArrayList.get(currentIndex);
        float saldo=currentCuentaBancaria.getSaldo();
        if(saldo>=monto) {
            currentCuentaBancaria.setSaldo(saldo-monto);

            //actualizando el saldo de la cuenta bancaria
            Map<String, String> updateSaldoRequestParams = new HashMap<>();
            updateSaldoRequestParams.put("idCuentaBancaria", currentCuentaBancaria.getId()+"");
            updateSaldoRequestParams.put("saldo", currentCuentaBancaria.getSaldo()+"");


            updateCuentaBancariaSaldo(updateSaldoRequestParams);

            //registrando operacion
            Map<String, String> insertOperacionRequestParams = new HashMap<>();
            insertOperacionRequestParams.put("monto", monto+"");
            insertOperacionRequestParams.put("fechaOperacion", getDateTime());
            insertOperacionRequestParams.put("idCuentaBancaria", currentCuentaBancaria.getId()+"");
            insertOperacionRequestParams.put("idTipoOperacion", "1");
            insertOperacionRequestParams.put("numeroCuentaDestino", cuentaDestino);

            insertOperacion(insertOperacionRequestParams);

            //realizar lo mismo con la cuenta destino

            //actualizar saldo de cuenta destino
            updateCuentaDestinoSiExiste(cuentaDestino,monto);


        }else
            alert("Saldo insuficiente para realizar la transferencia",null);


    }

    private void updateCuentaDestinoSiExiste(String numeroCuentaDestino,final float monto){
        final UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.getCuentaBancaria(numeroCuentaDestino,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson= new GsonBuilder().create();
                try {
                    Log.d(LOG_TAG,"CuentaDestino-> "+response);
                    CuentaBancaria cuentaBancaria= gson.fromJson(response, CuentaBancaria.class);
                    cuentaBancaria.setSaldo(
                            cuentaBancaria.getSaldo()+monto
                    );


                    //actualizando el saldo de la cuenta destino
                    Map<String, String> updateSaldoRequestParams = new HashMap<>();
                    updateSaldoRequestParams.put("idCuentaBancaria", cuentaBancaria.getId()+"");
                    updateSaldoRequestParams.put("saldo", cuentaBancaria.getSaldo()+"");
                    serviceClient.updateCuentaBancariaSaldo(updateSaldoRequestParams,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(LOG_TAG,"cuentaDestino-saldo-response-> "+response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(LOG_TAG_ERROR,"error-> "+error);
                        }
                    });


                    //registrando operacion
                    Map<String, String> insertOperacionRequestParams = new HashMap<>();
                    insertOperacionRequestParams.put("monto", monto+"");
                    insertOperacionRequestParams.put("fechaOperacion", getDateTime());
                    insertOperacionRequestParams.put("idCuentaBancaria", cuentaBancaria.getId()+"");
                    insertOperacionRequestParams.put("idTipoOperacion", "3");//depósito


                    serviceClient.insertOperacion(insertOperacionRequestParams,new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(LOG_TAG,"cuentaDestino-operacion-response-> "+response);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(LOG_TAG_ERROR,"error-> "+error);
                        }
                    });


                }catch(Exception err){
                    Log.d(LOG_TAG_ERROR,"Ha ocurrido un error al momento de obtener la cuenta destino");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG_ERROR,"error-> "+error);
            }
        });
    }

    private void updateCuentaBancariaSaldo(Map<String, String> params ){
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.updateCuentaBancariaSaldo(params,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(LOG_TAG,"saldo-response-> "+response);
                alert("Se ha realizado la transferencia con éxito.\n ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG_ERROR,"error-> "+error);
            }
        });
    }

    private void insertOperacion(Map<String, String> params){
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.insertOperacion(params,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(LOG_TAG,"operacion-response-> "+response);
                //alert("Se ha realizado la transferencia con éxito.\n ");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG_ERROR,"error-> "+error);
            }
        });
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void alert(String text, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name))
                .setMessage(text)
                .setPositiveButton("Ok",listener);
        AlertDialog alert=builder.create();
        alert.show();
    }



}
