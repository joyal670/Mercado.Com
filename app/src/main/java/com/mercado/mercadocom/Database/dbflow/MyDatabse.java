package com.mercado.mercadocom.Database.dbflow;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = MyDatabse.NAME, version = MyDatabse.VERSION)

public class MyDatabse {

    public static final String NAME = "test";
    public static final int VERSION = 3;
    public String getDBName()
    {
        return NAME;
    }
}
