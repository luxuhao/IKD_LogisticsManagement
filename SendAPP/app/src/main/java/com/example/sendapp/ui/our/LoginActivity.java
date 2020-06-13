package com.example.sendapp.ui.our;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sendapp.R;
import com.example.sendapp.ui.entity.HttpResponse;
import com.example.sendapp.ui.util.OkHttpClientManager;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    final static String URL = "http://10.2.4.162:8080/SSMBase_war_exploded/login";
    private MyCallBack callback= new MyCallBack();
    Boolean isOk = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

    }

    /**
     * 登录按钮触发事件
     * @param view
     */
    public void logIn(View view) throws IOException {
        // Do something in response to button
        //todo  登录信息验证
//        EditText accountText = (EditText) findViewById(R.id.account_txt);
//        String mAccount = accountText.getText().toString();//账号
//        EditText passwordText = (EditText) findViewById(R.id.password_txt);
//        String mPassword = passwordText.getText().toString();//密码
//
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//
//                //发送请求
//                OkHttpClientManager.Param[] params = null;
//                OkHttpClientManager.Param account = new OkHttpClientManager.Param("username",mAccount);
//                OkHttpClientManager.Param password = new OkHttpClientManager.Param("password",mPassword);
//                Response response = null;
//                try {
//                    response = OkHttpClientManager.post(URL,account,password);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(response.code());
//                if(response.code() == 0){
//                    System.out.println(response.body().toString());
//                    response.body().toString();
//                }else{
//                    response.message();
//                }
//            }
//        }).start();
//
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);

        EditText accountText = (EditText) findViewById(R.id.account_txt);
        String mAccount = accountText.getText().toString();//账号
        EditText passwordText = (EditText) findViewById(R.id.password_txt);
        String mPassword = passwordText.getText().toString();//密码
        Map<String,String> map = new HashMap<>();
        map.put("account",mAccount);
        map.put("password",mPassword);
        //todo 验证登陆信息
//        OkHttpClientManager.postAsyn(URL,callback,map);
//        if(isOk){
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("account", mAccount);
        intent.putExtra("password", mPassword);
        startActivity(intent);
    }

    /**
     * 注册触发
     */
    public void signIn(View view) throws IOException {
        // Do something in response to button
        //todo  登录信息验证

        EditText accountText = (EditText) findViewById(R.id.account_txt);
        String mAccount = accountText.getText().toString();//账号
        EditText passwordText = (EditText) findViewById(R.id.password_txt);
        String mPassword = passwordText.getText().toString();//密码
        Map<String,String> map = new HashMap<>();
        map.put("account",mAccount);
        map.put("password",mPassword);
        OkHttpClientManager.postAsyn(URL,callback,map);
        if(isOk){
            Intent intent = new Intent(this, SigninActivity.class);
            intent.putExtra("account", mAccount);
            intent.putExtra("password", mPassword);
            startActivity(intent);
        }
    }

    public class MyCallBack extends OkHttpClientManager.ResultCallback<String>{

        @Override
        public void onError(Request request, Exception e) {

        }

        @Override
        public void onResponse(String response) {

            if(response.length()>0){
                isOk = true;
            }
        }
    }
}
