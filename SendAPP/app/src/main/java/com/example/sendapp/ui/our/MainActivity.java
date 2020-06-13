package com.example.sendapp.ui.our;

import android.content.Intent;
import android.os.Bundle;

import com.example.sendapp.R;
import com.example.sendapp.ui.app.AppConText;
import com.example.sendapp.ui.util.OkHttpClientManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.okhttp.Request;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;




public class MainActivity extends AppCompatActivity  {
    private String m_account;
    private String m_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent it = getIntent();
        m_account = it.getStringExtra("account");
        m_password = it.getStringExtra("password");
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    public class MyCallBack extends OkHttpClientManager.ResultCallback<String>{

        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(String response) {

            if(response.length()>0){
               //获取权限列表
        }
        }
    }
}
