package com.example.profittrader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterOfPeopleBought extends RecyclerView.Adapter<AdapterOfPeopleBought.ViewHolder> {

    private List<ModelClassOfPeopleBought> userListOfPeopleBought;

    public AdapterOfPeopleBought(List<ModelClassOfPeopleBought>userListOfPeopleBought){this.userListOfPeopleBought=userListOfPeopleBought;}


    @NonNull
    @Override
    public AdapterOfPeopleBought.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_customers_bought,parent,false);
        return new AdapterOfPeopleBought.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfPeopleBought.ViewHolder holder, int position) {

        int resource= userListOfPeopleBought.get(position).getImageView1();
        String name=userListOfPeopleBought.get(position).getNameTextView();
        String amount=userListOfPeopleBought.get(position).getAmountTextView();
        String date=userListOfPeopleBought.get(position).getDateTextView();
        String id=userListOfPeopleBought.get(position).getIdTextView();
        String shareId=userListOfPeopleBought.get(position).getShareIdTextView();
        String number=userListOfPeopleBought.get(position).getNumberTextView();

        holder.setData(resource,name,amount,date,id,shareId,number);
        // holder.nameTextView.setBackgroundColor(255);

        if(Integer.parseInt(number.toString())%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("#08a12c"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#72f289"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.nameTextView.setText("UI");
                Context u=holder.itemView.getContext();
                Intent iu=new Intent(u,ParticularSharesBought.class);
                iu.putExtra("path",shareId);
                u.startActivity(iu);
                //Toast.makeText(u, holder.shareIdTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return userListOfPeopleBought.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView nameTextView;
        private TextView amountTextView;
        private TextView dateTextView;
        private TextView idTextView;
        private TextView shareIdTextView;
        private TextView numberTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.imageView1);
            nameTextView=itemView.findViewById(R.id.nameTextView);
            amountTextView=itemView.findViewById(R.id.amountTextView);
            dateTextView=itemView.findViewById(R.id.dateTextView);
            idTextView=itemView.findViewById(R.id.idTextView);
            shareIdTextView=itemView.findViewById(R.id.shareIdTextView);
            numberTextView=itemView.findViewById(R.id.numberTextView);

        }

        public void setData(int resource, String name, String amount, String date, String id,String shareId, String number) {

            imageView.setImageResource(resource);
            nameTextView.setText(name);
            amountTextView.setText(amount);
            dateTextView.setText(date);
            idTextView.setText(id);
            shareIdTextView.setText(shareId);
            numberTextView.setText(number);
        }
    }
}
