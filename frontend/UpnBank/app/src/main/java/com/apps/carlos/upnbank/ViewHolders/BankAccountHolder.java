package com.apps.carlos.upnbank.ViewHolders;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Helpers.OnItemClickListener;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.R;



/**
 * Created by Carlos on 17/06/2017.
 */
public class BankAccountHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,OnItemClickListener {
    private CuentaBancaria mCuentaBancaria;
    private View     mWholeItemView;
    private TextView mTxtAccountName;
    private TextView mTxtCurrencyType;
    private TextView mTxtAccountNumber;
    private TextView mTxtAccountMoney;
    private Callback mOnItemClickListener;

    private final String LOG_TAG="BankAccountHolder";

    public BankAccountHolder(View itemView) {
        super(itemView);
        mWholeItemView=itemView;
        mTxtAccountName=(TextView) itemView.findViewById(R.id.txt_account_type);
        mTxtAccountNumber =(TextView) itemView.findViewById(R.id.txt_account_number);
        mTxtCurrencyType=(TextView) itemView.findViewById(R.id.txt_currency_type);
        mTxtAccountMoney=(TextView) itemView.findViewById(R.id.txt_account_money);
    }

    //setters and getters
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        mCuentaBancaria = cuentaBancaria;
    }
    public void bind(){
        mTxtAccountName.setText("Cuenta de "+((mCuentaBancaria.getType()==CuentaBancaria.ACCOUNT_SAVING)?"Ahorros":"Crédito"));
        mTxtAccountNumber.setText(mCuentaBancaria.getNumeroCuenta());
        mTxtCurrencyType.setText(((mCuentaBancaria.getCurrencyType()==CuentaBancaria.CURRENCY_DOLAR)?"Dólares":"Soles"));
        mTxtAccountMoney.setText(((mCuentaBancaria.getCurrencyType()==CuentaBancaria.CURRENCY_DOLAR)?"$/":"S/")+mCuentaBancaria.getSaldo());
        mWholeItemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //ir al siguiente formulario con este objeto
        mOnItemClickListener.execute(new CuentaBancaria[]{mCuentaBancaria});
    }

    @Override
    public void setOnItemClickListener(Callback onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
