package com.apps.carlos.upnbank.ViewHolders;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.apps.carlos.upnbank.Model.Operacion;
import com.apps.carlos.upnbank.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlos on 30/06/2017.
 */

public class OperationViewHolder extends RecyclerView.ViewHolder {
    private Operacion mOperacion;
    @BindView(R.id.txt_operation_type) TextView mTxtOperationType;
    @BindView(R.id.txt_operation_datetime) TextView mTxtOperationDateTime;
    @BindView(R.id.txt_operation_money) TextView mTxtOperationMoney;
    @BindColor(R.color.positive_color) int mPositiveColor;
    @BindColor(R.color.negative_color) int mNegativeColor;

    private static final String LOG_TAG="OperationViewHolder";
    public OperationViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);
    }

    //setters and getter
    public Operacion getOperacion() {
        return mOperacion;
    }

    public void setOperacion(Operacion operacion) {
        mOperacion = operacion;
    }

    public void bind(){
        Log.d(LOG_TAG,mOperacion.toString());
        mTxtOperationType.setText(mOperacion.getTipoOperacion());
        mTxtOperationDateTime.setText(mOperacion.getFechaOperacion());

        String prefixMoney="";
        if(mOperacion.getIdTipoOperacion()==3) {
            mTxtOperationMoney.setTextColor(mPositiveColor);
            prefixMoney="+ S/";
        }else {
            mTxtOperationMoney.setTextColor(mNegativeColor);
            prefixMoney="- S/";
        }

        mTxtOperationMoney.setText(prefixMoney+mOperacion.getMonto());
    }


}
