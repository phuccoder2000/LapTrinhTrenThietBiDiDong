package com.example.customlistview.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.customlistview.R;
import com.example.customlistview.adapter.LanguageAdapter;
import com.example.customlistview.model.Language;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Language> listLanguage;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.idListView);
        listLanguage = new ArrayList<>();
        listLanguage.add(new Language(1,"Mecury A small, rocky planet"));
        listLanguage.add(new Language(2,"Venus A small, rocky planet blacketed in a thick layer of yellowish clouds"));
        listLanguage.add(new Language(3,"Earth A small, rocky planet which support a variety od life"));

        LanguageAdapter adapter = new LanguageAdapter(this, R.layout.item_custom_list_view,listLanguage);
        listView.setAdapter(adapter);
    }
}