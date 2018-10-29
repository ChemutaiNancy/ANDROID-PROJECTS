package com.chemutai.busticketing;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context ctx;

    public Session(Context ctx) {
        this.ctx = ctx;
        this.pref = ctx.getSharedPreferences("busTicketing", Context.MODE_PRIVATE);
        this.editor = pref.edit();
    }

    public void setLoggedin(boolean loggedin){
        editor.putBoolean("loggedinmode", loggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return pref.getBoolean("loggedinmode", false);
    }
}
