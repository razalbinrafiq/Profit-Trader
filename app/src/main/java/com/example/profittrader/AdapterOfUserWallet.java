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

public class AdapterOfUserWallet extends RecyclerView.Adapter<AdapterOfUserWallet.ViewHolder> {

    private List<ModelClassOfUserWallet> userListOfUserWallet;
    public AdapterOfUserWallet(List<ModelClassOfUserWallet>userListOfUserWallet){this.userListOfUserWallet=userListOfUserWallet;}


    @NonNull
    @Override
    public AdapterOfUserWallet.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_user_wallet,parent,false);
        return new AdapterOfUserWallet.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterOfUserWallet.ViewHolder holder, int position) {

        int resource= userListOfUserWallet.get(position).getImageView1();
        String name=userListOfUserWallet.get(position).getNameTextView();
        String amount=userListOfUserWallet.get(position).getAmountTextView();
        String date=userListOfUserWallet.get(position).getDateTextView();
        String id=userListOfUserWallet.get(position).getIdTextView();
        String shareId=userListOfUserWallet.get(position).getShareIdTextView();
        String number=userListOfUserWallet.get(position).getNumberTextView();

        holder.setData(resource,name,amount,date,id,shareId,number);
        // holder.nameTextView.setBackgroundColor(255);

        if(Integer.parseInt(number.toString())%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("#3d4a5e"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#637899"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.nameTextView.setText("UI");
                Context u=holder.itemView.getContext();
                Intent iu=new Intent(u,RedeemPoints.class);
                iu.putExtra("path",shareId);
                u.startActivity(iu);
                //Toast.makeText(u, holder.shareIdTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return userListOfUserWallet.size();
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
