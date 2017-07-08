package com.apps.carlos.upnbank.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Adapters.ServiceListAdapter;
import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.Empresa;
import com.apps.carlos.upnbank.Model.PuntoAtencion;
import com.apps.carlos.upnbank.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceListActivity extends AppCompatActivity {

    @BindView(R.id.business_list)RecyclerView mBusinessListView;
    private final String LOG_TAG="ServiceListActivity",LOG_TAG_ERROR="ServiceListActivity-Err";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        ButterKnife.bind(this);

        mBusinessListView.setLayoutManager(new LinearLayoutManager(this));

        getEmpresas();
    }


    private void getEmpresas(){
        //consumir servicio
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.getEmpresas(
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(LOG_TAG,"response-> "+response);
                Gson gson= new GsonBuilder().create();
                ArrayList<Empresa> businessList=gson.fromJson(response,new TypeToken<ArrayList<Empresa>>() {}.getType());
                assignServiceListAdapter(businessList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG_ERROR,"error-> "+error);
            }
        });
    }


    private void assignServiceListAdapter(ArrayList businessList){
        ServiceListAdapter serviceListAdapter=new ServiceListAdapter(businessList);
        //cuando se haga click en algún item de la lista
        serviceListAdapter.setOnItemClickListener(new Callback<Empresa>() {
            @Override
            public void execute(Empresa[] empresaArr) {
                //mostrar el siguiente activity mandando un objeto empresa
                showPaymentServiceActivity(empresaArr[0]);
            }
        });
        mBusinessListView.setAdapter(serviceListAdapter);
    }

    private void showPaymentServiceActivity(Empresa empresa){
        Intent intent= new Intent(this,PaymenServicetActivity.class);
        intent.putExtra(PaymenServicetActivity.ARG_BUSINESS,empresa);
        startActivity(intent);
    }

}
