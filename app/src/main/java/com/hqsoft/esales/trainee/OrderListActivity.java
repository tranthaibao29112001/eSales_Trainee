package com.hqsoft.esales.trainee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderListActivity extends AppCompatActivity {

    private Customer currentCustomer;
    private Salesperson currentSalesperson;
    private RecyclerView orderRecyclerView;
    private EditText dateEditTxt;
    private TextView sumTextView;
    private Toolbar toolbar;
    private OrderRecyclerViewAdapter orderRecyclerViewAdapter;
    private ArrayList<SalesOrd> salesOrdArrayList = new ArrayList<>();
    private Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        orderRecyclerView = findViewById(R.id.orderRecyclerView);
        dateEditTxt = findViewById(R.id.dateEditTxt);
        sumTextView = findViewById(R.id.sumMoney);
        toolbar = findViewById(R.id.orderListToolBar);

        Bundle bundle = getIntent().getExtras().getBundle("data");
        if(bundle!=null){
            currentCustomer = (Customer) bundle.getSerializable("customer");
            currentSalesperson = (Salesperson) bundle.getSerializable("sale_person");
        }

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

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        salesOrdArrayList  = sqLiteHelper.getOrdersByCustIDAndSlsperID(currentCustomer.getCustID(), currentSalesperson.getSlsperID());

        orderRecyclerViewAdapter = new OrderRecyclerViewAdapter(salesOrdArrayList, this);
        orderRecyclerView.setAdapter(orderRecyclerViewAdapter);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateOrder();

        DatePickerDialog.OnDateSetListener date =new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH,month);
                myCalendar.set(Calendar.DAY_OF_MONTH,day);
                updateOrder();
            }
        };
        dateEditTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(OrderListActivity.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.order_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.addOrder:{
                Intent intent = new Intent(this,NewOrderActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sale_person", currentSalesperson);
                bundle.putSerializable("customer",currentCustomer);
                intent.putExtra("data",bundle);
                startActivity(intent);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        salesOrdArrayList  = sqLiteHelper.getOrdersByCustIDAndSlsperID(currentCustomer.getCustID(), currentSalesperson.getSlsperID());
        updateOrder();
    }

    private void updateOrder() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = myCalendar.getTime();
        dateEditTxt.setText(dateFormat.format(date));
        ArrayList<SalesOrd> filteredOrders = orderDateFilter(dateFormat.format(date), salesOrdArrayList);
        double sum = 0;
        for(int i=0;i<filteredOrders.size();i++){
            sum +=filteredOrders.get(i).getOrdAmt();
        }
        sumTextView.setText(String.format("%,.2f",sum));
        orderRecyclerViewAdapter.setData(filteredOrders);
    }

    public ArrayList<SalesOrd> orderDateFilter(String date, ArrayList<SalesOrd> salesOrdArrayList){
        ArrayList<SalesOrd> filterArray = new ArrayList<>();
        for(int i=0;i<salesOrdArrayList.size();i++){
            String[] tokens = salesOrdArrayList.get(i).getOrderDate().split("-");
            String year = tokens[0];
            String month = tokens[1];
            String day = tokens[2];
            String formatStr = day + "/" + month + "/" + year;
            if(date.equals(formatStr)){
                filterArray.add(salesOrdArrayList.get(i));
            }
        }
        return filterArray;
    }
}