package com.apps.carlos.upnbank.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Helpers.OnItemClickListener;
import com.apps.carlos.upnbank.Model.Empresa;
import com.apps.carlos.upnbank.R;
import com.apps.carlos.upnbank.ViewHolders.ServiceHolder;

import java.util.ArrayList;


/**
 * Created by Carlos on 17/06/2017.
 */
public class ServiceListAdapter  extends RecyclerView.Adapter<ServiceHolder> implements OnItemClickListener {
    ArrayList<Empresa> mServices;
    private Callback mOnItemClickListener;
    public ServiceListAdapter(ArrayList<Empresa> services) {
        mServices = services;
    }

    @Override
    public ServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.service_item,parent,false);
        return new ServiceHolder(view);
    }

    @Override
    public void onBindViewHolder(ServiceHolder holder, int position) {
        holder.setEmpresa(mServices.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mServices.size();
    }

    @Override
    public void setOnItemClickListener(Callback onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
