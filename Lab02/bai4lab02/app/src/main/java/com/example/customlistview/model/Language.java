package com.example.customlistview.model;

public class Language {
    private  int id;
    private  String name;
    private  String h1;

    public  Language(int id, String name) {
        this.id =id;
        this.name=name ;
        this.h1= h1;
    }
    public  int getId(){
        return id;

    }
    public void setId(int id) {
        this.id=id;
    }
    public  String getName(){
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
}
