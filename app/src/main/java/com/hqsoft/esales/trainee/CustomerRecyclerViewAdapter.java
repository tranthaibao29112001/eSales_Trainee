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

public class CustomerRecyclerViewAdapter extends RecyclerView.Adapter<CustomerRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Customer> customers;
    private Context mContext;

    public CustomerRecyclerViewAdapter(ArrayList<Customer> customers, Context mContext) {
        this.customers = customers;
        this.mContext = mContext;
    }
    public void setData(ArrayList<Customer> customers){
        this.customers = customers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomerRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.customer_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerRecyclerViewAdapter.ViewHolder holder, int position) {
        Customer customer = customers.get(position);
        holder.order.setText(String.valueOf(position+1));
        holder.customerInfo.setText(customer.getCustID()+"_" + customer.getName() +"\n\n"+"ĐT: "+ customer.getPhone());
        holder.customerAddress.setText(customer.getAddress());
        if(position%2!=0){
            holder.layout.setBackgroundColor(mContext.getResources().getColor(R.color.grey));
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mContext instanceof CustomerListActivity){
                    CustomerListActivity customerListActivity = (CustomerListActivity) mContext;
                    customerListActivity.onCustomerItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return customers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView order, customerInfo, customerAddress;
        private LinearLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order = itemView.findViewById(R.id.order);
            customerAddress = itemView.findViewById(R.id.customerAddress);
            customerInfo = itemView.findViewById(R.id.customerInfo);
            layout = itemView.findViewById(R.id.customerItemLayout);
        }
    }
}
