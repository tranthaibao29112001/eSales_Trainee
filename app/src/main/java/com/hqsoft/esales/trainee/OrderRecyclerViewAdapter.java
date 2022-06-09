package com.hqsoft.esales.trainee;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.ViewHolder> {

    private ArrayList<SalesOrd> ordArrayList;
    private Context mContext;

    public OrderRecyclerViewAdapter(ArrayList<SalesOrd> ordArrayList, Context mContext) {
        this.ordArrayList = ordArrayList;
        this.mContext = mContext;
    }
    public void setData(ArrayList<SalesOrd> ordArrayList){
        this.ordArrayList = ordArrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.order_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SalesOrd salesOrd = ordArrayList.get(position);

        holder.order.setText(String.valueOf(position+1));
        holder.orderNbr.setText(salesOrd.getOrderNbr());
        holder.orderAmt.setText(String.format("%,.2f", salesOrd.getOrdAmt()));

        if(position%2!=0){
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        }
    }

    @Override
    public int getItemCount() {
        return ordArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order, orderNbr, orderAmt;
        private LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order);
            orderNbr = itemView.findViewById(R.id.orderNbr);
            orderAmt = itemView.findViewById(R.id.orderAmt);
            layout = itemView.findViewById(R.id.orderItemLayout);

        }
    }
}
