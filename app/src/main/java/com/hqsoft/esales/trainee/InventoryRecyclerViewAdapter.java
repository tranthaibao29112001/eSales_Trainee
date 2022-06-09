package com.hqsoft.esales.trainee;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryRecyclerViewAdapter extends RecyclerView.Adapter<InventoryRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Inventory> inventories;
    private HashMap<Inventory,Integer> inventoryIntegerHashMap;
    private HashMap<Inventory,TextWatcher> inventoryTextWatcherHashMap = new HashMap<>();
    private Context mContext;
    private NewOrderActivity activity;

    public InventoryRecyclerViewAdapter(ArrayList<Inventory> inventories, HashMap<Inventory, Integer> inventoryIntegerHashMap, Context mContext) {
        this.inventories = inventories;
        this.inventoryIntegerHashMap = inventoryIntegerHashMap;
        this.mContext = mContext;
    }

    public void setData(ArrayList<Inventory> inventories){
        this.inventories = inventories;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.inventory_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inventory inventory = inventories.get(position);
        holder.order.setText(String.valueOf(position+1));
        holder.productName.setText(inventory.getInvtID()+"\n\n" + inventory.getName());
        holder.price.setText(String.format("%,d",Integer.parseInt(inventory.getPrice())));


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable.toString().equals("")){
                    activity.productAmountChange(inventory, 0);
                    return;
                }
                activity.productAmountChange(inventory, Integer.parseInt(editable.toString()));
            }
        };
        if(inventoryTextWatcherHashMap.containsKey(inventory)){
            for(Inventory inv: inventoryTextWatcherHashMap.keySet()){
                holder.amount.removeTextChangedListener(inventoryTextWatcherHashMap.get(inv));
            }
        }
        holder.amount.addTextChangedListener(textWatcher);
        inventoryTextWatcherHashMap.put(inventory, textWatcher);

        holder.amount.setText(String.valueOf(inventoryIntegerHashMap.get(inventory)));
        if(position%2!=0){
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        }


    }

    @Override
    public int getItemCount() {
        return inventories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order, productName,price;
        private EditText amount;
        private LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order);
            productName = itemView.findViewById(R.id.productName);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);
            layout = itemView.findViewById(R.id.inventoryLayout);

            if(mContext instanceof NewOrderActivity){
                activity = (NewOrderActivity) mContext;
            }
        }
    }
}
