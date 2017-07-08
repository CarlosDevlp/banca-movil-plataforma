package com.apps.carlos.upnbank.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.R;

import java.util.ArrayList;

/**
 * Created by Carlos on 30/06/2017.
 */

public class BankAccountSpinnerAdapter extends ArrayAdapter<CuentaBancaria> {
    private ArrayList<CuentaBancaria> mCuentaBancariaArrayList;
    private LayoutInflater mInflater;
    private final String LOG_TAG="ServiceSpinnerAdapter",LOG_TAG_ERROR="ServiceSpinnerAdapt-Err";

    //constructor
    public BankAccountSpinnerAdapter(@NonNull Context context, ArrayList cuentaBancariaArrayList) {
        super(context, android.R.layout.simple_spinner_item, cuentaBancariaArrayList);
        mInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        mCuentaBancariaArrayList = cuentaBancariaArrayList;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v=mInflater.inflate(R.layout.basic_spinner_item,parent,false);
        TextView text=(TextView) v.findViewById(R.id.txt_content);
        text.setText(generateItemString(position));
        return v;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=mInflater.inflate(R.layout.basic_spinner_item_selected,parent,false);
        TextView text=(TextView) v.findViewById(R.id.txt_content);
        text.setText(generateItemString(position));
        return v;
    }

    private String generateItemString(int position ){
        CuentaBancaria cuentaBancaria=mCuentaBancariaArrayList.get(position);
        String saldo=((cuentaBancaria.getCurrencyType()==CuentaBancaria.CURRENCY_DOLAR)?"$/":"S/")+cuentaBancaria.getSaldo();
        String numeroCuenta=cuentaBancaria.getNumeroCuenta();
        return numeroCuenta+" - "+saldo;
    }
}