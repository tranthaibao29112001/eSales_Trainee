package com.hqsoft.esales.trainee;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class NewProductDialogFragment extends DialogFragment {
    private RecyclerView inventoryRecyclerview;
    private EditText searchEditTxt;
    private InventoryRecyclerViewAdapter inventoryRecyclerViewAdapter;
    private ArrayList<Inventory> inventories;
    private HashMap<Inventory,Integer> inventoryIntegerHashMap = new HashMap<>();
    private NewOrderActivity activity;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.new_product_fragmet_dialog, null);
        builder.setView(view)
                .setNegativeButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.cancelNewProduct();
                    }
                })
                .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        activity.newProductClick();
                    }
                });

        SQLiteHelper sqLiteHelper = new SQLiteHelper(getActivity());

        inventoryRecyclerview = view.findViewById(R.id.inventoryRecyclerview);
        searchEditTxt = view.findViewById(R.id.searchEditTxt);

        inventoryRecyclerViewAdapter = new InventoryRecyclerViewAdapter(inventories,inventoryIntegerHashMap,getActivity());
        inventoryRecyclerview.setAdapter(inventoryRecyclerViewAdapter);
        inventoryRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchEditTxt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ArrayList<Inventory> filteredInventory = sqLiteHelper.findListInventory(editable.toString());
                inventoryRecyclerViewAdapter.setData(filteredInventory);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if(context instanceof  NewOrderActivity){
            activity = (NewOrderActivity) context;
        }
        super.onAttach(context);
    }

    public void setInventoryIntegerHashMap(HashMap<Inventory,Integer> inventoryIntegerHashMap){
        this.inventoryIntegerHashMap = inventoryIntegerHashMap;
    }
    public void setInventories(ArrayList<Inventory> inventories){
        this.inventories = inventories;
    }


}
