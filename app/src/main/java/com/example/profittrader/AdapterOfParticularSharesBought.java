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

public class AdapterOfParticularSharesBought extends RecyclerView.Adapter<AdapterOfParticularSharesBought.ViewHolder> {

    private List<ModelClassOfParticularSharesBought> userListOfParticularSharesBought;

    public AdapterOfParticularSharesBought(List<ModelClassOfParticularSharesBought>userListOfParticularSharesBought){this.userListOfParticularSharesBought=userListOfParticularSharesBought;}


    @NonNull
    @Override
    public AdapterOfParticularSharesBought.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_particular_shares_bought,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfParticularSharesBought.ViewHolder holder, int position) {

        int resource= userListOfParticularSharesBought.get(position).getImageView1();
        String name=userListOfParticularSharesBought.get(position).getNameTextView();
        String amount=userListOfParticularSharesBought.get(position).getAmountTextView();
        String date=userListOfParticularSharesBought.get(position).getDateTextView();
        String id=userListOfParticularSharesBought.get(position).getIdTextView();
        String shareId=userListOfParticularSharesBought.get(position).getShareIdTextView();
        String number=userListOfParticularSharesBought.get(position).getNumberTextView();

        holder.setData(resource,name,amount,date,id,shareId,number);
        // holder.nameTextView.setBackgroundColor(255);

        if(Integer.parseInt(number.toString())%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("#e3e3e3"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#e9f2f0"));

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
        return userListOfParticularSharesBought.size();
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
