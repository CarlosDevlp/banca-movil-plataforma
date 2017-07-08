package com.apps.carlos.upnbank.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Helpers.OnItemClickListener;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.R;
import com.apps.carlos.upnbank.ViewHolders.BankAccountHolder;

import java.util.ArrayList;

/**
 * Created by Carlos on 17/06/2017.
 */
public class BankAccountListAdapter extends RecyclerView.Adapter<BankAccountHolder> implements OnItemClickListener {
    private ArrayList<CuentaBancaria> mCuentaBancariaList;
    private Callback mOnItemClickListener;
    //constructor
    public BankAccountListAdapter(ArrayList<CuentaBancaria> cuentaBancariaList) {
        mCuentaBancariaList = cuentaBancariaList;
    }

    //métodos de adapter
    @Override
    public BankAccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.bank_account_item,parent,false);
        BankAccountHolder bankAccountHolder=new BankAccountHolder(view);
        bankAccountHolder.setOnItemClickListener(mOnItemClickListener);
        return bankAccountHolder;
    }

    @Override
    public void onBindViewHolder(BankAccountHolder holder, int position) {
        holder.setCuentaBancaria(mCuentaBancariaList.get(position));
        holder.bind();
    }


    @Override
    public int getItemCount() {
        return mCuentaBancariaList.size();
    }

    @Override
    public void setOnItemClickListener(Callback onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
