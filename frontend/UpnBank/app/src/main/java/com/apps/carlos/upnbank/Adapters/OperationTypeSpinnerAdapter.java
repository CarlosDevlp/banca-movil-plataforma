package com.apps.carlos.upnbank.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.apps.carlos.upnbank.Model.TipoOperacion;
import com.apps.carlos.upnbank.R;

import java.util.ArrayList;

/**
 * Created by Carlos on 30/06/2017.
 */

public class OperationTypeSpinnerAdapter extends ArrayAdapter<TipoOperacion> {

        private ArrayList<TipoOperacion> mTipoOperacionArrayList;
        private LayoutInflater mInflater;
        private final String LOG_TAG="ServiceSpinnerAdapter",LOG_TAG_ERROR="ServiceSpinnerAdapt-Err";

        //constructor
        public OperationTypeSpinnerAdapter(@NonNull Context context, ArrayList tipoOperacionArrayList) {
            super(context, android.R.layout.simple_spinner_item, tipoOperacionArrayList);
            mInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            mTipoOperacionArrayList = tipoOperacionArrayList;
        }


        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v=mInflater.inflate(R.layout.basic_spinner_item,parent,false);
            TextView text=(TextView) v.findViewById(R.id.txt_content);
            text.setText(mTipoOperacionArrayList.get(position).getDescripcion());
            return v;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v=mInflater.inflate(R.layout.basic_spinner_item_selected,parent,false);
            TextView text=(TextView) v.findViewById(R.id.txt_content);
            text.setText(mTipoOperacionArrayList.get(position).getDescripcion());
            return v;
        }

}