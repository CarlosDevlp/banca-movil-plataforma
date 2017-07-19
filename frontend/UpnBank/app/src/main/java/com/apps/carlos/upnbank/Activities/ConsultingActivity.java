package com.apps.carlos.upnbank.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Adapters.BankAccountListAdapter;
import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.Model.User;
import com.apps.carlos.upnbank.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;

public class ConsultingActivity extends AppCompatActivity {
    @BindView(R.id.bank_account_list)RecyclerView mBankAccountListView;
    private User mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //obtener usuario global
        mUser=User.getInstance();

        getCuentasBancarias();

    }


    private void showMovementsActivity(CuentaBancaria cuentaBancaria){
        //intent explícito
        Intent intent=new Intent(this,MovementsActivity.class);
        intent.putExtra(MovementsActivity.ARG_BANK_ACCOUNT,cuentaBancaria);
        startActivity(intent);
    }


    private void getCuentasBancarias(){

        UpnBankServiceClient.getInstance()
        .getTarjetaCuentaBancaria(mUser.getId(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson= new GsonBuilder().create();
                try {
                    //concatenar los 4 grupos de 4 dígitos para formar un solo número de tarjeta
                    ArrayList<CuentaBancaria> cuentaBancariaList=gson.fromJson(response,new TypeToken<ArrayList<CuentaBancaria>>() {}.getType());

                    //comprobar si la tarjeta ingresada es correcta
                    mUser.setCuentaBancariaList(cuentaBancariaList);

                    assignAdapter();

                }catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alert("No se pudieron obtener cuentas bancarias");
            }
        });
    }

    private void assignAdapter(){
        //lista de cuentas (recycle view)
        mBankAccountListView =(RecyclerView) findViewById(R.id.bank_account_list);
        mBankAccountListView.setLayoutManager(new LinearLayoutManager(this));

        BankAccountListAdapter bankAccountListAdapter=new BankAccountListAdapter(mUser.getCuentaBancariaList());
        bankAccountListAdapter.setOnItemClickListener(new Callback<CuentaBancaria>() {
            @Override
            public void execute(CuentaBancaria[] args) {
                //mostrar
                showMovementsActivity(args[0]);
            }
        });
        mBankAccountListView.setAdapter(bankAccountListAdapter);
    }

    public void alert(String text){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name))
                .setMessage(text)
                .setPositiveButton("Ok",null);
        AlertDialog alert=builder.create();
        alert.show();
    }
}
