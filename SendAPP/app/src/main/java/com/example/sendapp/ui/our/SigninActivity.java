package com.example.sendapp.ui.our;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sendapp.R;

public class SigninActivity extends AppCompatActivity {
    private EditText mAccount; //账号
    private EditText mPassword; //密码
    private Button  mBtnLogin;//登录按钮

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

    }
    /**
     * 注册触发
     */
    public void signIn(View view) {
        // Do something in response to button
        //todo  注册信息验证

//        EditText accountText = (EditText) findViewById(R.id.account_txt);
//        String mAccount = accountText.getText().toString();//账号
//        EditText passwordText = (EditText) findViewById(R.id.password_txt);
//        String mPassword = passwordText.getText().toString();//密码
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
