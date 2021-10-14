package com.example.customgridview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewStub stubGrid;
    private Context context;
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private List<Product> productList;
    private int positonSelect = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stubGrid =findViewById(R.id.stub_grid);

        stubGrid.inflate();

        gridView=findViewById(R.id.mygridview);

        getProductList();

        setAdapter();

    }
    private  void setAdapter(){
        gridViewAdapter =new GridViewAdapter(this,R.layout.grid_item,productList);
        gridView.setAdapter(gridViewAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = gridView.getItemAtPosition(position);
                Product product = (Product) o;
                Toast.makeText(MainActivity.this,product.getTitle()+"\n"+"Sold:"+product.getDescription(),Toast.LENGTH_LONG).show();

            }
        });


    }


    private List<Product> getProductList() {
        productList =new ArrayList<>();
        productList.add(new Product(R.drawable.maboo,"Manboo","283,297"));
        productList.add(new Product(R.drawable.samoldshawn,"SameOldShawn","252,443"));
        productList.add(new Product(R.drawable.magnitude901,"Magnitude901","164,935"));
        productList.add(new Product(R.drawable.brandon,"Brandon","100,466"));
        productList.add(new Product(R.drawable.clement_rgf,"Clement_RGF","93,932"));
        productList.add(new Product(R.drawable.nebja,"Nebja","84,187"));
        productList.add(new Product(R.drawable.bbd,"BBDS","81,762"));
        productList.add(new Product(R.drawable.modme,"PlesaseDe-ModMe","79,243"));
        productList.add(new Product(R.drawable.dzlize,"Dlizzle","76,331"));
        productList.add(new Product(R.drawable.paplacelight,"palacelight","75,497"));
        productList.add(new Product(R.drawable.thedraknight,"TheDarkKnight","69,138"));
        productList.add(new Product(R.drawable.hellrel,"hellrel","68,903"));
        return productList;
    }
}