package com.example.patty.andoridlabs;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;


public class LoginActivitiy extends Activity {
    protected static final String ACTIVITY_NAME = "LoginActivity";
    SharedPreferences prefs;
    SharedPreferences.Editor prefEditor;
    EditText login, pass;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activitiy);

        prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        prefEditor = prefs.edit();

        String loginName = prefs.getString("loginName", "email@domain.com");
        String password = prefs.getString("password", "password");

        login = findViewById(R.id.loginInput);
        pass = findViewById(R.id.passwordInput);

        login.setText(loginName);
        pass.setText(password);

        loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String saveLogin = login.getText().toString();
                String savePass = pass.getText().toString();

                prefEditor.putString("loginName", saveLogin);
                prefEditor.putString("password", savePass);
                prefEditor.commit();

                Intent intent = new Intent(LoginActivitiy.this, StartActivity.class);
                startActivity(intent);
            }
        });


        Log.i(ACTIVITY_NAME, "in onCreate()");
    }
    protected void onResume(){
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");

    }
    protected void onStart(){
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }
    protected void onPause(){
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }
    protected void onStop(){
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}
