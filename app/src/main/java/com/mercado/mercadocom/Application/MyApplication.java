package com.mercado.mercadocom.Application;

import android.app.Application;
import android.os.StrictMode;

import com.google.firebase.database.FirebaseDatabase;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class MyApplication extends Application {
    @Override
    public void onCreate()
    {
        super.onCreate();

        FlowManager.init(new FlowConfig.Builder(this).build());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);


    }
}