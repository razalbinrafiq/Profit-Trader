package com.example.profittrader;

import android.view.Display;

public class ModelClass {

    private  int imageView1;
    private String nameTextView;
    private String amountTextView;
    private String dateTextView;
    private String idTextView;
    private String numberTextView;

    ModelClass(int imageView1, String nameTextView,String amountTextView,String dateTextView, String idTextView, String numberTextView){

        this.imageView1=imageView1;
        this.nameTextView=nameTextView;
        this.amountTextView=amountTextView;
        this.dateTextView=dateTextView;
        this.idTextView=idTextView;
        this.numberTextView=numberTextView;

    }

    public int getImageView1() {
        return imageView1;
    }

    public String getNameTextView() {
        return nameTextView;
    }

    public String getAmountTextView() {
        return amountTextView;
    }

    public String getDateTextView() {
        return dateTextView;
    }

    public String getIdTextView() { return idTextView; }

    public String getNumberTextView() { return numberTextView; }
}
