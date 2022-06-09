package com.hqsoft.esales.trainee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class NewOrderActivity extends AppCompatActivity implements NewProductDialogListener {
    private Button newProductBtn, newOrderBtn;
    private EditText remark;
    private TextView totalPriceTxt;
    private Toolbar toolbar;
    private RecyclerView newOrderRecyclerView;
    private NewProductDialogFragment fragment;

    private NewOrderRecyclerAdapter newOrderRecyclerAdapter;
    private HashMap<Inventory,Integer> inventoryIntegerHashMap = new HashMap<>();
    private ArrayList<Inventory> inventories = new ArrayList<>();
    private ArrayList<SalesOrdDet> salesOrdDets = new ArrayList<>();
    private Customer currentCustomer;
    private Salesperson currentSalesperson;
    private SalesOrd currentSalesOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        newProductBtn = findViewById(R.id.newProductBtn);
        newOrderBtn = findViewById(R.id.newOrderBtn);
        newOrderRecyclerView = findViewById(R.id.newOrderRecyclerView);
        toolbar = findViewById(R.id.newOrderToolbar);
        remark = findViewById(R.id.remark);
        totalPriceTxt = findViewById(R.id.totalPrice);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras().getBundle("data");
        if(bundle!=null){
            currentCustomer = (Customer) bundle.getSerializable("customer");
            currentSalesperson = (Salesperson) bundle.getSerializable("sale_person");
            Log.e("TAG", "onCreate: "+currentSalesperson.getSlsperID() );
        }
        initSaleOrder();

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        inventories = sqLiteHelper.getAllInventory();

        for(int i=0;i<inventories.size();i++){
            inventoryIntegerHashMap.put(inventories.get(i), 0);
        }
        fragment = new NewProductDialogFragment();

        newProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment.setInventoryIntegerHashMap(inventoryIntegerHashMap);
                fragment.setInventories(inventories);
                fragment.show(getSupportFragmentManager(), "New Product");
            }
        });
        newOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(salesOrdDets.size() == 0){
                    new AlertDialog.Builder(NewOrderActivity.this)
                            .setTitle("Error")
                            .setMessage("Chưa thêm mặt hàng nào")
                            .setPositiveButton(android.R.string.yes, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(true)
                            .show();
                    return;
                }
                currentSalesOrder.setRemark(String.valueOf(remark.getText()));
                double totalAmount = 0;
                double totalPrice = 0;
                for(int i=0;i<salesOrdDets.size();i++){
                    totalAmount +=salesOrdDets.get(i).getLineQty();
                    totalPrice +=salesOrdDets.get(i).getLineAmt();
                }
                currentSalesOrder.setOrdAmt(totalPrice);
                currentSalesOrder.setOrdQty(totalAmount);
                sqLiteHelper.insertSalesOrd(currentSalesOrder);
                for(int i=0;i<salesOrdDets.size();i++){
                    sqLiteHelper.insertSalesOrdDet(salesOrdDets.get(i));
                }
                new AlertDialog.Builder(NewOrderActivity.this)
                        .setTitle("Success")
                        .setMessage("Thêm thành công")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setCancelable(true)
                        .setOnCancelListener(new DialogInterface.OnCancelListener() {
                            @Override
                            public void onCancel(DialogInterface dialogInterface) {
                                finish();
                            }
                        })
                        .show();

            }
        });

        newOrderRecyclerAdapter = new NewOrderRecyclerAdapter(salesOrdDets, this);
        newOrderRecyclerView.setAdapter(newOrderRecyclerAdapter);
        newOrderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void productAmountChange(Inventory inventory, int amount) {
        inventoryIntegerHashMap.put(inventory, amount);
    }

    @Override
    public void newProductClick() {
        salesOrdDets.clear();
        double totalPrice = 0;
        for(int i=0;i<inventories.size();i++){
            if(inventoryIntegerHashMap.get(inventories.get(i)) >0){
                String lineRef = String.format("%04d", salesOrdDets.size()+1);
                String invID = inventories.get(i).getInvtID();
                int lineQty = inventoryIntegerHashMap.get(inventories.get(i));
                double lineAmt =lineQty * Double.parseDouble(inventories.get(i).getPrice());
                SalesOrdDet salesOrdDet = new SalesOrdDet(currentSalesOrder.getOrderNbr(),lineRef,invID,lineAmt ,lineQty);
                salesOrdDets.add(salesOrdDet);
                totalPrice +=lineAmt;
            }
        }
        newOrderRecyclerAdapter.setData(salesOrdDets);

        totalPriceTxt.setText(String.format("%,.2f", totalPrice));
    }

    @Override
    public void cancelNewProduct() {
    }
    public void initSaleOrder(){
        currentSalesOrder  = new SalesOrd();
        String orderNbr = currentCustomer.getCustID() +"_"+ UUID.randomUUID();
        currentSalesOrder.setOrderNbr(orderNbr);
        currentSalesOrder.setCustID(currentCustomer.getCustID());
        currentSalesOrder.setSlsperID(currentSalesperson.getSlsperID());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        currentSalesOrder.setOrderDate(dateFormat.format(date));
    }
}