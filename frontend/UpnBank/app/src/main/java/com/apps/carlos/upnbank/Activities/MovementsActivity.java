package com.apps.carlos.upnbank.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Adapters.OperationListAdapter;
import com.apps.carlos.upnbank.Adapters.OperationTypeSpinnerAdapter;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.Model.Operacion;
import com.apps.carlos.upnbank.Model.Servicio;
import com.apps.carlos.upnbank.Model.TipoOperacion;
import com.apps.carlos.upnbank.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovementsActivity extends AppCompatActivity implements  AdapterView.OnItemSelectedListener{

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.txt_account_type) TextView mTxtAccountType;
    @BindView(R.id.txt_account_money) TextView mTxtAccountMoney;
    @BindView(R.id.txt_currency_type) TextView mTxtCurrencyType;
    @BindView(R.id.txt_account_number) TextView mTxtAccountNumber;
    @BindView(R.id.txt_account_cci_number) TextView mTxtAccountCCINumber;
    @BindView(R.id.txt_card_number) TextView mTxtCardNumber;
    @BindView(R.id.spn_operation_type_list) Spinner mSpnOperationTypeList;
    @BindView(R.id.movements_list) RecyclerView mMovementListView;


    private ArrayList<Operacion> mOperacionList=new ArrayList<>();
    public static final String ARG_BANK_ACCOUNT="com.apps.carlos.upnbank.Activities.bank_account";
    private static final String LOG_TAG="MovementsActivity",LOG_TAG_ERROR="MovementsActivity-Err";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movements);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        //RecyclerView listMovements=(RecyclerView) findViewById(R.id.movements_list);
        //listMovements.setLayoutManager(new LinearLayoutManager(this));

        //obteniendo id del back account desde el activity anterior
        Intent intentFrom=getIntent();
        CuentaBancaria currentCuentaBancaria=(CuentaBancaria) intentFrom.getSerializableExtra(ARG_BANK_ACCOUNT);



        mTxtAccountType.setText("Cuenta de "+((currentCuentaBancaria.getType()==CuentaBancaria.ACCOUNT_SAVING)?"Ahorros":"Crédito"));
        mTxtAccountMoney.setText(((currentCuentaBancaria.getCurrencyType()==CuentaBancaria.CURRENCY_DOLAR)?"$/":"S/")+currentCuentaBancaria.getSaldo());
        mTxtCurrencyType.setText(((currentCuentaBancaria.getCurrencyType()==CuentaBancaria.CURRENCY_DOLAR)?"Dólares":"Soles"));
        mTxtAccountNumber.setText("N°cuenta:"+currentCuentaBancaria.getNumeroCuenta());
        mTxtAccountCCINumber.setText("CCI:"+currentCuentaBancaria.getCCI());
        mTxtCardNumber.setText("N°tarjeta:"+currentCuentaBancaria.getTarjetaNumero());


        mMovementListView.setLayoutManager(new LinearLayoutManager(this));

        //lista de tipo de operaciones
        getTipoOperaciones();

        getOperaciones(currentCuentaBancaria.getId());
    }


    private void getTipoOperaciones(){
        //consumir servicio
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.getTiposOperacion(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(LOG_TAG,"response-> "+response);
                        Gson gson= new GsonBuilder().create();

                        ArrayList<TipoOperacion> businessList=gson.fromJson(response,new TypeToken<ArrayList<TipoOperacion>>() {}.getType());
                        TipoOperacion todos=new TipoOperacion();
                        todos.setId(0);
                        todos.setDescripcion("Todos");
                        businessList.add(0,todos);

                        assignOperationTypeAdapter(businessList);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG_TAG_ERROR,"error-> "+error);
                    }
                });
    }

    private void getOperaciones(int idCuentaBancaria){
        //consumir servicio
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.getOperacion(idCuentaBancaria,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(LOG_TAG,"response2-> "+response);
                        Gson gson= new GsonBuilder().create();

                        mOperacionList=gson.fromJson(response,new TypeToken<ArrayList<Operacion>>() {}.getType());

                        assignOperationAdapter(mOperacionList);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(LOG_TAG_ERROR,"error-> "+error);
                    }
                });
    }

    private void assignOperationTypeAdapter(ArrayList<TipoOperacion> tipoOperacionList){
        mSpnOperationTypeList.setAdapter(new OperationTypeSpinnerAdapter(this,tipoOperacionList));
        mSpnOperationTypeList.setOnItemSelectedListener(this);
    }


    private void assignOperationAdapter(ArrayList<Operacion> operacionList){
        mMovementListView.setAdapter(new OperationListAdapter(operacionList));
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(mOperacionList!=null && mOperacionList.size()>0) {
            TipoOperacion selectedTipoOperacion = (TipoOperacion) adapterView.getItemAtPosition(i);
            int idOperacion = selectedTipoOperacion.getId();
            ArrayList<Operacion> newOperacionList = new ArrayList<>();

            if (idOperacion > 0) {
                for (Operacion operacion : mOperacionList)
                    if (operacion.getIdTipoOperacion() == idOperacion)
                        newOperacionList.add(operacion);
            } else {
                newOperacionList = mOperacionList;
            }

            assignOperationAdapter(newOperacionList);
        }
        else Log.d(LOG_TAG,"vacío");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
