package com.example.customlistview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customlistview.R;
import com.example.customlistview.model.Language;

import java.util.List;

public class LanguageAdapter extends BaseAdapter {
    private Context context;
    private int idLayout;
    private List<Language> listLanguage;
    private int positionSelect = -1;

    public  LanguageAdapter(Context context,int idLayout,List<Language> listLanguage) {
        this.context = context;
        this.idLayout =idLayout;
        this.listLanguage=listLanguage;
    }
    @Override
    public int getCount() {
        if (listLanguage.size() !=0 && !listLanguage.isEmpty()){
            return listLanguage.size();
        }
        return 0;
    }
    @Override
    public  Object getItem(int position)
    {
        return null;
    }
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(idLayout,parent,false);
        }
        TextView tvinformation = (TextView) convertView.findViewById(R.id.tvinformation);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.picture);
        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.idLinerLayout);
        final  Language language = listLanguage.get(position);

        if (listLanguage !=null && !listLanguage.isEmpty()){
            tvinformation.setText(language.getName());
            int idlanguage = language.getId();
            switch (idlanguage)
            {
                case 1:
                    imageView.setImageResource(R.drawable.mercury);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.venus);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.earth);
                    break;
                default:
                    break;
            }
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,language.getName(),Toast.LENGTH_LONG).show();
                positionSelect =position;
                notifyDataSetChanged();
            }
        });
        if (positionSelect == position){
            linearLayout.setBackgroundColor(Color.BLUE);

        }else{
            linearLayout.setBackgroundColor(Color.WHITE);
        }
       return convertView;
    }
}
