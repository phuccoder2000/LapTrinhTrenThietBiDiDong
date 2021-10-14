package com.example.maytinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnCong(View v)
    {
        EditText txtNumberX=(EditText) findViewById(R.id.txtNumberX);
        int x = Integer.parseInt(txtNumberX.getText()+"");
        EditText txtNumberY=(EditText) findViewById(R.id.txtNumberY);
        int y = Integer.parseInt(txtNumberY.getText()+"");
        TextView txtOutputKQ=(TextView) findViewById(R.id.txtOutputKQ);
        txtOutputKQ.setText((x + y)+"");
        return;
    }
    public void btnTru(View v)
    {
        EditText txtNumberX=(EditText) findViewById(R.id.txtNumberX);
        int x = Integer.parseInt(txtNumberX.getText()+"");
        EditText txtNumberY=(EditText) findViewById(R.id.txtNumberY);
        int y = Integer.parseInt(txtNumberY.getText()+"");
        TextView txtOutputKQ=(TextView) findViewById(R.id.txtOutputKQ);
        txtOutputKQ.setText((x - y)+"");
    }
    public void btnNhan(View v)
    {
        EditText txtNumberX=(EditText) findViewById(R.id.txtNumberX);
        int x = Integer.parseInt(txtNumberX.getText()+"");
        EditText txtNumberY=(EditText) findViewById(R.id.txtNumberY);
        int y = Integer.parseInt(txtNumberY.getText()+"");
        TextView txtOutputKQ=(TextView) findViewById(R.id.txtOutputKQ);
        txtOutputKQ.setText((x * y)+"");
    }
    public void btnChia(View v)
    {
        EditText txtNumberX=(EditText) findViewById(R.id.txtNumberX);
        int x = Integer.parseInt(txtNumberX.getText()+"");
        EditText txtNumberY=(EditText) findViewById(R.id.txtNumberY);
        int y = Integer.parseInt(txtNumberY.getText()+"");
        TextView txtOutputKQ=(TextView) findViewById(R.id.txtOutputKQ);
        txtOutputKQ.setText((x / y)+"");
    }
    public void  btnChiaLayDu(View v)
    {
        EditText txtNumberX=(EditText) findViewById(R.id.txtNumberX);
        int x = Integer.parseInt(txtNumberX.getText()+"");
        EditText txtNumberY=(EditText) findViewById(R.id.txtNumberY);
        int y = Integer.parseInt(txtNumberY.getText()+"");
        TextView txtOutputKQ=(TextView) findViewById(R.id.txtOutputKQ);
        txtOutputKQ.setText((x % y)+"");
    }

}