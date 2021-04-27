package com.example.profittrader;

import android.view.Display;

public class ModelClass {

    private  int imageView1;
    private String nameTextView;
    private String amountTextView;
    private String dateTextView;

    ModelClass(int imageView1, String nameTextView,String amountTextView,String dateTextView){

        this.imageView1=imageView1;
        this.nameTextView=nameTextView;
        this.amountTextView=amountTextView;
        this.dateTextView=dateTextView;

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
}
