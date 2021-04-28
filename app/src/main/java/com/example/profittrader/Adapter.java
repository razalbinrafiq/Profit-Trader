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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;

    public Adapter(List<ModelClass>userList){this.userList=userList;}


    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        int resource= userList.get(position).getImageView1();
        String name=userList.get(position).getNameTextView();
        String amount=userList.get(position).getAmountTextView();
        String date=userList.get(position).getDateTextView();
        String id=userList.get(position).getIdTextView();
        String shareId=userList.get(position).getShareIdTextView();
        String number=userList.get(position).getNumberTextView();

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
//                Intent iu=new Intent(u,UserDetails.class);
//                u.startActivity(iu);
                Toast.makeText(u, holder.shareIdTextView.getText(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
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
