package com.apps.carlos.upnbank.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.apps.carlos.upnbank.Adapters.BankAccountListAdapter;
import com.apps.carlos.upnbank.Helpers.Callback;
import com.apps.carlos.upnbank.Model.CuentaBancaria;
import com.apps.carlos.upnbank.Model.User;
import com.apps.carlos.upnbank.R;

public class ConsultingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //obtener usuario global
        User user=User.getInstance();

        //lista de cuentas (recycle view)
        RecyclerView bankAccountListView=(RecyclerView) findViewById(R.id.bank_account_list);
        bankAccountListView.setLayoutManager(new LinearLayoutManager(this));

        BankAccountListAdapter bankAccountListAdapter=new BankAccountListAdapter(user.getCuentaBancariaList());
        bankAccountListAdapter.setOnItemClickListener(new Callback<CuentaBancaria>() {
            @Override
            public void execute(CuentaBancaria[] args) {
                //mostrar
                showMovementsActivity(args[0]);
            }
        });
        bankAccountListView.setAdapter(bankAccountListAdapter);

    }


    private void showMovementsActivity(CuentaBancaria cuentaBancaria){
        //intent explícito
        Intent intent=new Intent(this,MovementsActivity.class);
        intent.putExtra(MovementsActivity.ARG_BANK_ACCOUNT,cuentaBancaria);
        startActivity(intent);
    }
}
