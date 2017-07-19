package com.apps.carlos.upnbank.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.Cliente;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.Model.Servicio;
import com.apps.carlos.upnbank.Model.User;
import com.apps.carlos.upnbank.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    @BindView(R.id.edt_dni) EditText mEdtDNI;
    @BindView(R.id.edt_password) EditText mEdtPassword;
    @BindView(R.id.card_field_1) EditText mCardField1;
    @BindView(R.id.card_field_2) EditText mCardField2;
    @BindView(R.id.card_field_3) EditText mCardField3;
    @BindView(R.id.card_field_4) EditText mCardField4;

    private UpnBankServiceClient mServiceClient;
    private User mUser;
    private String LOG_TAG="LoginActivity",LOG_TAG_ERROR="LoginActivity-Error";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonLogin=(Button) findViewById(R.id.btn_login);
        buttonLogin.setOnClickListener(this);

        //------------Obteniendo elementos de la vista---------
        ButterKnife.bind(this);

        mUser=User.getInstance();

        //------------Inicializando helpers y modelo---------------------------
        mServiceClient =UpnBankServiceClient.getInstance(getApplicationContext());

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_login:
                checkUser();
                break;
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void checkUser(){

        String dni=mEdtDNI.getText().toString();
        String password=mEdtPassword.getText().toString();
        //obntener usuario de base de datos
        mServiceClient.getUser(dni, password, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Gson gson= new GsonBuilder().create();

                try {
                    Log.d(LOG_TAG,response);
                    User askedUser=gson.fromJson(response,User.class);

                    mUser=User.getInstance();
                    mUser.setId(askedUser.getId());
                    mUser.setIdUser(askedUser.getIdUser());
                    mUser.setNombre(askedUser.getNombre());
                    mUser.setApellidos(askedUser.getApellidos());
                    mUser.setDni(askedUser.getDni());
                    mUser.setTelefono(askedUser.getTelefono());
                    mUser.setCorreo(askedUser.getCorreo());
                    mUser.setPassword(askedUser.getPassword());


                    Log.d(LOG_TAG,mUser.toString());
                    getCuentasBancarias();



                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                alert("Datos no válidos");
            }
        });

    }

    //obntener cuentas bancarias de base de datos
    private void getCuentasBancarias(){

        mServiceClient.getTarjetaCuentaBancaria(mUser.getId(),new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson= new GsonBuilder().create();
                try {
                    //concatenar los 4 grupos de 4 dígitos para formar un solo número de tarjeta
                    String tarjetaNumber=mCardField1.getText().toString()+mCardField2.getText().toString()+
                            mCardField3.getText().toString()+mCardField4.getText().toString();

                    ArrayList<CuentaBancaria> cuentaBancariaList=gson.fromJson(response,new TypeToken<ArrayList<CuentaBancaria>>() {}.getType());

                    //comprobar si la tarjeta ingresada es correcta
                    boolean exists=false;
                    for(CuentaBancaria cuentaBancaria: cuentaBancariaList)
                        if(cuentaBancaria.getTarjetaNumero().equals(tarjetaNumber)) {
                            exists = true;
                            break;
                        }

                    if(!exists) {
                        alert("Dato de tarjeta no válido");
                        return;
                    }

                    mUser.setCuentaBancariaList(cuentaBancariaList);

                    showMainActivity();
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

    private void showMainActivity(){
        //intent explícito
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab)
    public void callUpnBank(View view){

        Snackbar.make(view,"LLamando al centro de atención UPNBANK",Snackbar.LENGTH_LONG).show();
        try{
            //intent implícito
            //,Uri.fromParts("tel:","+51953395070",null)
            Intent intent=new Intent(Intent.ACTION_DIAL);
            intent.putExtra(Intent.EXTRA_PHONE_NUMBER,"tel:953395070");
            intent=Intent.createChooser(intent,"Elija una aplicación para llamar");
            startActivity(intent);

        }catch(SecurityException err){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle(getString(R.string.app_name))
                    .setMessage("No se pudo usar su teléfono para llamar al centro de atención al cliente")
                    .setPositiveButton("Ok",null);
            AlertDialog alert=builder.create();
            alert.show();
        }
    }


    public void alert(String text){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.app_name))
                .setMessage(text)
                .setPositiveButton("Ok",null);
        AlertDialog alert=builder.create();
        alert.show();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){

    }

    @Override
    public void onRestoreInstanceState(Bundle bundle){

    }

}