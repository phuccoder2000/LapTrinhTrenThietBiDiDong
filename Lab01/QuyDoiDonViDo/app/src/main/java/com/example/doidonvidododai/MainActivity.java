package com.example.doidonvidododai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    //khai báo biến lưu danh sách đơn vị đo
    private String[] units = {
            "Hải Lý", "Dặm", "Kilometer", "Lý", "Mét", "Yard", "Foot", "Inches"};
    private  double[][] ratio = {
            {1.00000000, 1.15077945, 1.8520000, 20.2537183, 1852.0000, 2025.37183, 6076.11549, 72913.38583},
            {0.86897624, 1.00000000, 1.6093440, 17.6000000, 1609.3440, 1760.00000, 5280.00000, 63360.00000},
            {0.53995680, 0.62137119, 1.0000000, 10.9361330, 1000.0000, 1093.61330, 3280.83990, 39370.07874},
            {0.04937365, 0.05681818, 0.0914400,  1.0000000,   91.4400,  100.00000,  300.00000,  3600.00000},
            {0.00053996, 0.00062137, 0.0010000,  0.0109361,    1.0000,    1.09361,    3.28084,    39.37008},
            {0.00049374, 0.00056818, 0.0009144,  0.0100000,    0.9144,    1.00000,    3.00000,    36.00000},
            {0.00016458, 0.00018939, 0.0003048,  0.0033333,    0.3048,    0.33333,    1.00000,    12.00000},
            {0.00001371, 0.00001578, 0.0000254,  0.0002778,    0.0254,    0.02778,    0.08333,     1.00000},
    };
    //khai báo các đối tượng View
    private EditText txtNumber;
    private Spinner spnUnits;
    private TextView[] lblResults;

    //hàm đổi đơn vị tiền tệ
    private void changeMoneyUnit() {
        //lấy vị trí của đơn vị được chọn
        int rowIdx = spnUnits.getSelectedItemPosition();
        if (rowIdx < 0) rowIdx = 0;

        //lấy giá trị từ ô nhập
        String input = txtNumber.getText().toString();
        if (input.isEmpty()) input = "0";

        //đổi giá trị nhập sang số thực
        double  number = Double.valueOf(input);

        //tính giá trị quy đổi với từng loại tiền
        for (int i = 0; i < lblResults.length; i++){
            double temp = number * ratio[rowIdx][i];

            //hiển thị kết quả lên TextView tương ứng
            lblResults[i].setText(String.valueOf(temp));
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //kết nối các đối tượng view
        txtNumber = (EditText) findViewById(R.id.txtNumber);
        spnUnits = (Spinner) findViewById(R.id.lstAnimal);
        lblResults = new TextView[] {
                (TextView)findViewById(R.id.lblHL),
                (TextView)findViewById(R.id.lblD),
                (TextView)findViewById(R.id.lblKLMT),
                (TextView)findViewById(R.id.lblL),
                (TextView)findViewById(R.id.lblM),
                (TextView)findViewById(R.id.lblY),
                (TextView)findViewById(R.id.lblF),
                (TextView)findViewById(R.id.lblIc),

        };

        //khởi tạo giá trị trung chuyển
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, units);
        //MoneyActivity.this, android.R.layout.simple_spinner_item, units);

        //thiết lặp cách hiển thị của spinner
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);

        //gắn adapter vào spinner
        spnUnits.setAdapter(adapter);


        //Thiết lập hàm xủ lý sự kiện thay item được chọn trong spinner
        spnUnits.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                changeMoneyUnit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        //thiết lập hàm xử lý sự kiện thay đổi nội dung ô nhập
        txtNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                changeMoneyUnit();
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });

    }
}