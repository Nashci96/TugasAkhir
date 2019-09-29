package com.okedroid.apktaichsan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String ID = "ID";
    public static final String LEVEL = "LEVEL";

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("PREF_NAME" , PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String name,String id,String level){
        editor.putBoolean(LOGIN,true);
        editor.putString(NAME,name);
        editor.putString(ID,id);
        editor.putString(LEVEL,level);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void checkLogin(){
        if (!this.isLogin()){
            Intent i = new Intent(context,MainActivity.class);
            context.startActivity(i);

            if (LEVEL.equals("1")){
                ((MhsMainMenu)context).finish();
            } else {
                if (LEVEL.equals("2")){
                    ((DsnMainMenu)context).finish();
                } else {
                    if (LEVEL.equals("3")){
                        ((AdmMainMenu)context).finish();
                    } else {
                        ((KpdMainMenu)context).finish();
                    }
                }
            }
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME,null));
        user.put(ID, sharedPreferences.getString(ID,null));
        user.put(LEVEL,sharedPreferences.getString(LEVEL,null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(context,MainActivity.class);
        context.startActivity(i);

        if (LEVEL.equals("1")){
            ((MhsMainMenu)context).finish();
        } else {
            if (LEVEL.equals("2")){
                ((DsnMainMenu)context).finish();
            } else {
                if (LEVEL.equals("3")){
                    ((AdmMainMenu)context).finish();
                } else {
                    ((KpdMainMenu)context).finish();
                }
            }
        }
    }



}
