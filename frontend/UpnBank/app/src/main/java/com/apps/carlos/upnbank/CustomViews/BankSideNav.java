package com.apps.carlos.upnbank.CustomViews;

import android.app.Activity;
import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;

import com.apps.carlos.upnbank.R;

/**
 * Created by Carlos on 17/06/2017.
 */
public class BankSideNav {
    private DrawerLayout drawer;
    private Context mActivity;
    //constructor

    public BankSideNav(Activity activity, DrawerLayout drawer, Toolbar toolbar) {
        mActivity=activity;
        this.drawer = drawer;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }
}
