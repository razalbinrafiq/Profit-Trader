package com.example.profittrader;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DynamicViews {

        String hellooi="lo";
        Context ctx;

        public DynamicViews(Context ctx) {
            this.ctx = ctx;
        }


        public TextView sentText(Context context, String text) {
            final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final TextView textView = new TextView(context);
            textView.setLayoutParams(lparams);
            textView.setTextSize(10);
            textView.setTextColor(Color.rgb(0, 0, 0));
            textView.setText(" " + text + " ");
            textView.setMaxEms(8);
            hellooi = textView.getText().toString();
            textView.setVisibility(View.INVISIBLE);
            return textView;
        }


//        public Button chittymemberButton(final Context context) {
//            final ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            final Button button = new Button(context);
//            button.setLayoutParams(lparams);
//            button.setTextSize(22);
//            button.setTextColor(Color.rgb(0, 0, 0));
//            button.setText(hellooi);
//            button.setBackgroundResource(R.drawable.p1);
//            button.setGravity(Gravity.CENTER_VERTICAL);
//            lparams.width=lparams.MATCH_PARENT;
//            lparams.height=261;
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    String value="Hello world";
////                    Intent i = new Intent(view.getContext(),Test.class);
////                   // i.putExtra("key",hellooi);
////                    context.startActivity(i);
//             //       Intent i=new Intent(ctx.getApplicationContext(),Test.class);
////                   .startActivity(i);
//                    Toast.makeText(context, hellooi, Toast.LENGTH_SHORT).show();
////                    Intent intent = new Intent(context, Test.class);
////                    context.startActivity(new Intent(context, Test.class));
//                    //context.startActivity(intent);
//                }
//            });
//            return button;
//        }




        public TextView nameTextVIew(Context context, String text){
            final ViewGroup.LayoutParams lparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final TextView textView=new TextView(context);
            textView.setLayoutParams(lparams);
            textView.setTextSize(10);
            textView.setTextColor(Color.rgb(0,0,0));
            textView.setText(" "+ text + " ");
            textView.setTextSize(22);
            textView.setMaxEms(8);
            return textView;
        }
        public TextView phoneTextVIew(Context context, String text){
            final ViewGroup.LayoutParams lparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            final TextView textView=new TextView(context);
            textView.setLayoutParams(lparams);
            textView.setTextSize(10);
            textView.setTextColor(Color.rgb(0,0,0));
            textView.setText(" "+ text + " ");
            textView.setTextSize(12);
            textView.setMaxEms(8);
            return textView;
        }


    public TextView datekeyTextView(Context context, String text){
        final ViewGroup.LayoutParams lparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView=new TextView(context);
        textView.setLayoutParams(lparams);
        textView.setTextSize(10);
        textView.setTextColor(Color.rgb(0,0,0));
        textView.setText(" "+ text + " ");
        textView.setTextSize(22);
        textView.setMaxEms(8);
        return textView;
    }
    public TextView datevalueTextView(Context context, String text){
        final ViewGroup.LayoutParams lparams= new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        final TextView textView=new TextView(context);
        textView.setLayoutParams(lparams);
        textView.setTextSize(10);
        textView.setTextColor(Color.rgb(0,0,0));
        textView.setText(" "+ text + " ");
        textView.setTextSize(22);
        textView.setMaxEms(8);
        return textView;
    }


    }