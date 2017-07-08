package com.apps.carlos.upnbank.Activities;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.PuntoAtencion;
import com.apps.carlos.upnbank.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

//FragmentActivity
public class SearchAttentionPointActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,LocationListener, CompoundButton.OnCheckedChangeListener{

    @BindView(R.id.toolbar)Toolbar mToolbar;
    @BindView(R.id.btn_agente)ToggleButton mBtnAgente;
    @BindView(R.id.btn_banco)ToggleButton mBtnBanco;
    @BindView(R.id.btn_cajero)ToggleButton mBtnCajero;

    private GoogleMap mMap;
    private final String LOG_TAG="SAPActivity",LOG_TAG_ERROR="SAPActivity-Error";
    private GoogleApiClient mGoogleApiClient;
    private LatLng mCurrentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_attention_point);

        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        //
        mGoogleApiClient=new GoogleApiClient.Builder(this)
                         .addApi(LocationServices.API)
                         .addConnectionCallbacks(this)
                         .addOnConnectionFailedListener(this).build();

    }


    /**
     * cuando el mapa está listo, obtengo una referencia
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        //upn focus
        LatLng upn= new LatLng(-11.959777, -77.067715);

        if(mCurrentLocation!=null) mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLocation,15f));
        else mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(upn,15f));

        getPuntosAtencion(0);
    }



    private void addMarker(PuntoAtencion puntoAtencion){
        int resIcon;

        switch(puntoAtencion.getIdTipoPuntoAtencion()){
            case PuntoAtencion.TIPO_PUNTO_ATENCION_AGENTE:
                resIcon=R.drawable.store_icon;
                break;

            case PuntoAtencion.TIPO_PUNTO_ATENCION_BANCO:
                resIcon=R.drawable.bank_icon;
                break;
            //case PuntoAtencion.TIPO_PUNTO_ATENCION_CAJERO:
            default:
                resIcon=R.drawable.atm_icon;

        }

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(puntoAtencion.getLat(), puntoAtencion.getLng()))
                .title(puntoAtencion.getDescripcion())
                .icon(BitmapDescriptorFactory.fromResource(resIcon))
        );
    }

    private void getPuntosAtencion(int idTipoPuntoAtencion){
        //consumir servicio
        UpnBankServiceClient serviceClient= UpnBankServiceClient.getInstance();
        serviceClient.getPuntosAtencion(idTipoPuntoAtencion, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(LOG_TAG,"response-> "+response);
                Gson gson= new GsonBuilder().create();
                List<PuntoAtencion> puntoAtencionList=gson.fromJson(response,new TypeToken<List<PuntoAtencion>>() {}.getType());

                for(PuntoAtencion puntoAtencion:puntoAtencionList) {
                    addMarker(puntoAtencion);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(LOG_TAG_ERROR,"error-> "+error);
            }
        });
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onLocationChanged(Location location) {
        if(mCurrentLocation==null && mMap!=null) {
            mCurrentLocation = new LatLng(location.getLatitude(),location.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCurrentLocation, 15f));
        }
    }


    @OnCheckedChanged({R.id.btn_agente,R.id.btn_banco,R.id.btn_cajero})
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mMap.clear();
        switch(compoundButton.getId()){
            case R.id.btn_agente:
                if(b) {
                    getPuntosAtencion(3);
                    mBtnBanco.setChecked(false);
                    mBtnCajero.setChecked(false);
                }
                break;
            case R.id.btn_banco:
                if(b) {
                    getPuntosAtencion(2);
                    mBtnAgente.setChecked(false);
                    mBtnCajero.setChecked(false);
                }
                break;
            case R.id.btn_cajero:
                if(b) {
                    getPuntosAtencion(1);
                    mBtnAgente.setChecked(false);
                    mBtnBanco.setChecked(false);
                }

        }


    }


}
