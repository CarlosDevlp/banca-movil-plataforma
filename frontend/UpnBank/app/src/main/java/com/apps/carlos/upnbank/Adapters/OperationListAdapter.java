package com.apps.carlos.upnbank.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apps.carlos.upnbank.Model.Operacion;
import com.apps.carlos.upnbank.R;
import com.apps.carlos.upnbank.ViewHolders.OperationViewHolder;

import java.util.ArrayList;

/**
 * Created by Carlos on 30/06/2017.
 */

public class OperationListAdapter extends RecyclerView.Adapter<OperationViewHolder> {
    private ArrayList<Operacion> mOperacionArrayList;

    public OperationListAdapter(ArrayList<Operacion> operacionArrayList) {
        mOperacionArrayList = operacionArrayList;
    }

    @Override
    public OperationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view =layoutInflater.inflate(R.layout.operation_item,parent,false);
        return new OperationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(OperationViewHolder holder, int position) {
        holder.setOperacion(mOperacionArrayList.get(position));
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return mOperacionArrayList.size();
    }
}
