package com.hqsoft.esales.trainee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NewOrderRecyclerAdapter extends RecyclerView.Adapter<NewOrderRecyclerAdapter.ViewHolder> {
    private ArrayList<SalesOrdDet> salesOrdDets;
    private Context mContext;

    public NewOrderRecyclerAdapter(ArrayList<SalesOrdDet> salesOrdDets, Context mContext) {
        this.salesOrdDets = salesOrdDets;
        this.mContext = mContext;
    }

    public void setData(ArrayList<SalesOrdDet> salesOrdDets){
        this.salesOrdDets = salesOrdDets;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.product_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SalesOrdDet salesOrdDet = salesOrdDets.get(position);

        SQLiteHelper sqLiteHelper = new SQLiteHelper(mContext);
        Inventory inventory = sqLiteHelper.getInventoryByID(salesOrdDet.getInvtID());
        if(inventory == null){
            return;
        }
        holder.order.setText(String.valueOf(position+1));
        holder.invtId.setText(salesOrdDet.getInvtID());
        holder.invtName.setText(inventory.getName());
        holder.price.setText(String.format("%,d",Integer.parseInt(inventory.getPrice())));
        holder.amount.setText(String.valueOf((int)salesOrdDet.getLineQty()));
        holder.sumPrice.setText(String.format("%,.2f", salesOrdDet.getLineAmt()));

        if(position%2!=0){
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        }
    }

    @Override
    public int getItemCount() {
        return salesOrdDets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order,invtId, invtName, price, amount,sumPrice;
        private LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order);
            invtId = itemView.findViewById(R.id.invtID);
            invtName = itemView.findViewById(R.id.invtName);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);
            sumPrice = itemView.findViewById(R.id.sumPrice);
            layout = itemView.findViewById(R.id.productLayout);
        }
    }
}
