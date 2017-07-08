package com.apps.carlos.upnbank.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Helpers.OnItemClickListener;
import com.apps.carlos.upnbank.Helpers.UpnBankServiceClient;
import com.apps.carlos.upnbank.Model.Empresa;
import com.apps.carlos.upnbank.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Carlos on 17/06/2017.
 */
public class ServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,OnItemClickListener {
    private Context mContext;
    private View     mWholeItemView;
    private TextView mTxtServiceName;
    private ImageView mImgServiceLogo;
    private Callback mOnItemClickListener;
    private Empresa mEmpresa;

    //@BindView(R.id.txt_service_name) TextView mTxtServiceName;
    private static final String LOG_TAG="ServiceHolder",LOG_TAG_ERROR="ServiceHolder";

    public ServiceHolder(View itemView) {
        super(itemView);
        //ButterKnife.bind(itemView);
        mContext=itemView.getContext();
        mWholeItemView=itemView;
        mTxtServiceName=(TextView) itemView.findViewById(R.id.txt_service_name);
        mImgServiceLogo=(ImageView)itemView.findViewById(R.id.img_service_logo);
    }

    //setters and getters
    public Empresa getEmpresa() {
        return mEmpresa;
    }

    public void setEmpresa(Empresa empresa) {
        mEmpresa = empresa;
    }

    public void bind(){
        String baseUrl=UpnBankServiceClient.getInstance().getBaseUrl();
        mTxtServiceName.setText(mEmpresa.getNombre());
        Picasso.with(mContext).load(baseUrl+mEmpresa.getLogoUrl()).into(mImgServiceLogo);
        mWholeItemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        mOnItemClickListener.execute(new Empresa[]{mEmpresa});
    }

    @Override
    public void setOnItemClickListener(Callback onItemClickListener) {
        mOnItemClickListener=onItemClickListener;
    }
}
