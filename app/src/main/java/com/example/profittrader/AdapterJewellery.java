package com.example.profittrader;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterJewellery extends RecyclerView.Adapter<AdapterJewellery.ViewHolder> {

    private List<ModelClassJewellery> userListJewellery;

    public AdapterJewellery(List<ModelClassJewellery>userLiuserListJewelleryst){this.userListJewellery=userListJewellery;}


    @NonNull
    @Override
    public AdapterJewellery.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design_jewellery,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterJewellery.ViewHolder holder, int position) {

        int resource= userListJewellery.get(position).getImageView1();
        String name=userListJewellery.get(position).getNameTextView();
        String amount=userListJewellery.get(position).getAmountTextView();
        String date=userListJewellery.get(position).getDateTextView();
        String id=userListJewellery.get(position).getIdTextView();
        String shareId=userListJewellery.get(position).getShareIdTextView();
        String number=userListJewellery.get(position).getNumberTextView();

        holder.setData(resource,name,amount,date,id,shareId,number);
        // holder.nameTextView.setBackgroundColor(255);

        if(Integer.parseInt(number.toString())%2==0)
            holder.itemView.setBackgroundColor(Color.parseColor("#CDCFD5"));
        else
            holder.itemView.setBackgroundColor(Color.parseColor("#838795"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.nameTextView.setText("UI");
                Context u=holder.itemView.getContext();
                Intent iu=new Intent(u,ShareDetails.class);
                iu.putExtra("path",shareId);
                u.startActivity(iu);
                Toast.makeText(u, holder.shareIdTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return userListJewellery.size();
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
