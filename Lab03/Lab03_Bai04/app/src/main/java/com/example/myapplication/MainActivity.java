package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Locale nMyLocale;
    EditText edtAccount,edtPassword;
    Button btnDangnhap;
    ImageView imgAnh,imgViet,imgPhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        //loai bo title
        setContentView(R.layout.activity_main);
        Anhxa();

        imgAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("en");
            }
        });
        imgViet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("vi");
            }
        });
        imgPhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("fr");
            }
        });
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public  void  ganNgonNgu(String language){
    Locale locale = new Locale(language);
    //cau tao ali he thong
    Configuration config = new Configuration();
    //cau hinh lai ngon ngu
    config.locale = locale;
    //cap nhat lai resources
    getBaseContext().getResources().updateConfiguration(
            config,
            getBaseContext().getResources().getDisplayMetrics()
    );
    //load lại view
    Intent intent = new Intent(MainActivity.this,MainActivity.class);
    startActivity(intent);
    }
    //anh xa
    public void Anhxa(){
        edtAccount = (EditText)findViewById(R.id.edtAccount);
        edtPassword = (EditText)findViewById(R.id.edt_Password);
        btnDangnhap = (Button)findViewById(R.id.btn_DangNhap);
        imgAnh = (ImageView)findViewById(R.id.imgAnh);
        imgViet = (ImageView)findViewById(R.id.imgViet);
        imgPhap =(ImageView)findViewById(R.id.imgPhap);
    }

}