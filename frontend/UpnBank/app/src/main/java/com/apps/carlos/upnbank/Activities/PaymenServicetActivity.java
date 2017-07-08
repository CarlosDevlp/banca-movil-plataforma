package com.apps.carlos.upnbank.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Adapters.BankAccountSpinnerAdapter;
import com.apps.carlos.upnbank.Adapters.ServiceListAdapter;
import com.apps.carlos.upnbank.Adapters.ServiceSpinnerAdapter;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.Model.Empresa;
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

public class PaymenServicetActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.business_name) TextView mTxtBusinessName;
    @BindView(R.id.spn_service_list) Spinner mSpnServiceList;
    @BindView(R.id.spn_bank_account_list) Spinner mSpnBankAccountList;
    @BindView(R.id.service_description) TextView mTxtServiceDescription;
    @BindView(R.id.cod_suministro_container) View mCodSuministroContainer;
    @BindView(R.id.dni_container) View mDniContainer;


    private User mUser;
    private Empresa mEmpresa;
    private Servicio mSelectedServicio;
    public static final String ARG_BUSINESS="com.apps.carlos.upnbank.Activities.PaymentServiceActivity.Business";
    private final String LOG_TAG="PaymentServiceAct",LOG_TAG_ERROR="PaymentServiceAct-Err";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymen_servicet);
        ButterKnife.bind(this);


        setSupportActionBar(mToolbar);

        //obtener el usuario
        mUser=User.getInstance();
        assignBankAccountAdapter(mUser.getCuentaBancariaList());

        //obtener datos enviados por el activity anterior
        Intent intent=getIntent();
        mEmpresa=(Empresa) intent.getSerializableExtra(ARG_BUSINESS);
        mTxtBusinessName.setText("Servicios de: "+mEmpresa.getNombre());
        getServicios();
    }


    private void getServicios(){
        //consumir servicio
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.getServicio(
                mEmpresa.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(LOG_TAG,"response-> "+response);
                        Gson gson= new GsonBuilder().create();
                        ArrayList<Servicio> servicioList=gson.fromJson(response,new TypeToken<ArrayList<Servicio>>() {}.getType());
                        assignServiceAdapter(servicioList);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG_TAG_ERROR,"error-> "+error);
                    }
                });
    }

    private void assignServiceAdapter(ArrayList servicioList){
        mSpnServiceList.setAdapter(new ServiceSpinnerAdapter(this,servicioList));
        mSpnServiceList.setOnItemSelectedListener(this);
    }

    private void assignBankAccountAdapter(ArrayList bankAccountList){
        mSpnBankAccountList.setAdapter(new BankAccountSpinnerAdapter(this, bankAccountList));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSelectedServicio= (Servicio)adapterView.getItemAtPosition(i);
        mTxtServiceDescription.setText(mSelectedServicio.getDescripcion()+"\nCosto: S/."+mSelectedServicio.getCosto());
        mCodSuministroContainer.setVisibility((mSelectedServicio.getIdTipoServicio()==3?View.GONE:View.VISIBLE));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @OnClick(R.id.btn_pagar)
    public void registrarPago(){
        //consumir servicio
        int currentIndex=mSpnBankAccountList.getSelectedItemPosition();
        float monto=mSelectedServicio.getCosto();
        CuentaBancaria currentCuentaBancaria = mUser.getCuentaBancariaList().get(currentIndex);
        float saldo=currentCuentaBancaria.getSaldo();
        if(saldo>=monto) {
            currentCuentaBancaria.setSaldo(saldo-monto);

            //actualizando el saldo de la cuenta bancaria
            Map<String, String> updateSaldoRequestParams = new HashMap<>();
            updateSaldoRequestParams.put("idCuentaBancaria", currentCuentaBancaria.getId() + "");
            updateSaldoRequestParams.put("saldo", currentCuentaBancaria.getSaldo() + "");


            updateCuentaBancariaSaldo(updateSaldoRequestParams);


            //registrando operacion
            Map<String, String> insertOperacionRequestParams = new HashMap<>();
            insertOperacionRequestParams.put("monto", monto + "");
            insertOperacionRequestParams.put("fechaOperacion", getDateTime());
            insertOperacionRequestParams.put("idCuentaBancaria", currentCuentaBancaria.getId() + "");
            insertOperacionRequestParams.put("idTipoOperacion", "2"); //id de tipo pago de servicio
            insertOperacion(insertOperacionRequestParams);
        }else
            alert("Saldo insuficiente para realizar el pago del servicio",null);
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
        }, new Response.ErrorListener() {
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
