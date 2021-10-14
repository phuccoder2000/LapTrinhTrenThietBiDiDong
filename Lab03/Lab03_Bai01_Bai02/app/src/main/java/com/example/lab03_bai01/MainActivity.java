package com.example.lab03_bai01;

import android.app.Application;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtLogin;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn_Toast);
        button.setOnClickListener(new View.OnClickListener() {
         @Override
        public void onClick(View v) {
            Toast toast = new Toast(MainActivity.this);
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.mycustomtoast_layout));
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.show();
            }
        });

        txtLogin = (TextView) findViewById(R.id.btn_Dialog);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLogin();
            }
        });
    }
    private void DialogLogin()
    {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_danhnhap);

        //anh xa
        final EditText edtUsername = (EditText) dialog.findViewById(R.id.editTextUsername);
        final EditText  edtPasssword = (EditText) dialog.findViewById(R.id.editTextPassword);
        Button btnDangNhap = (Button) dialog.findViewById(R.id.btn_dangNhap);
        Button btnThoat = (Button) dialog.findViewById(R.id.btn_Thoat);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Username = edtUsername.getText().toString().trim();
                String Password = edtPasssword.getText().toString().trim();
                if (Username.equals("Lesyhung") && Password.equals("2612")){
                    Toast.makeText(MainActivity.this,"Bạn đã đăng nhập thành công! Hello Hùng(Black West)",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại.Mấy con gà???",Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
