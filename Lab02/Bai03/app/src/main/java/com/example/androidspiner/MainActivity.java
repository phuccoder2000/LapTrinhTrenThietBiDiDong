package com.example.androidspiner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;

import android.bluetooth.BluetoothClass;
import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;


public class MainActivity extends AppCompatActivity {

    ListView lvMonHoc;
    ArrayList<String> arrayCourse;
    Spinner spinner;
    GridView gridView;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Danh sách các hệ điều hành trên android bằng listview
        lvMonHoc = (ListView) findViewById(R.id.Device);
        arrayCourse = new ArrayList<>();
        String arr[] = {
                "Android",
                "IOS",
                "WindowsPhone",
                "bOS",
                "Blackberry",
                "Symbian"};
        TextView Selection;


        arrayCourse.add("Android");
        arrayCourse.add("IOS");
        arrayCourse.add("WindowsPhone");
        arrayCourse.add("bOS");
        arrayCourse.add("Blackberry");
        arrayCourse.add("Symbian");


        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayCourse);
        lvMonHoc.setAdapter(arrayAdapter);
        lvMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(MainActivity.this, arrayCourse.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        //Danh sách các hệ điều hành trên android bằng spinner
        list = new ArrayList<>();
        list.add("Android");
        list.add("IOS");
        list.add("WindowsPhone");
        list.add("bOS");
        list.add("Blackberry");
        list.add("Symbian");
        spinner = (Spinner) findViewById(R.id.id_spinner);
        ArrayAdapter spinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, list);
        spinner.setAdapter(spinnerAdapter);

        //Bắt sự kiện lên spinner khi nào nhấn vào thì hiển thị lên toast
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Đối số position là địa chỉ của của phần tử trong list data
                String msg = list.get(position);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "onNothingSeclected", Toast.LENGTH_SHORT).show();
            }
        });
        //Hiển thị danh sách các hệ điều hành dưới dạng gridview
        gridView = (GridView) findViewById(R.id.gridview);
        final List<String> list1 = new ArrayList<>();
        list1.add("Android");
        list1.add("IOS");
        list1.add("WindowsPhone");
        list1.add("bOS");
        list1.add("Blackberry");
        list1.add("Symbian");

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        gridView.setAdapter(adapter);
        //Sự kiện click vào các list trong gridview
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, list1.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}