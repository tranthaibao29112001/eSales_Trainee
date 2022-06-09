package com.hqsoft.esales.trainee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CustomerListActivity extends AppCompatActivity implements ItemClickListener {
    private RecyclerView customerRecyclerView;
    private CustomerRecyclerViewAdapter customerAdapter;
    private EditText searchEditText;
    private Salesperson currentSalesPerson;
    private ArrayList<Customer> customerArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        customerRecyclerView = findViewById(R.id.customerRecyclerView);
        searchEditText = findViewById(R.id.searchEditTxt);

        Bundle bundle = getIntent().getExtras().getBundle("data");
        if(bundle !=null){
            currentSalesPerson = (Salesperson) bundle.getSerializable("sale_person");
        }
        Log.e("TAG", "onCreate: "+currentSalesPerson.getSlsperID() );
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        customerArrayList = sqLiteHelper.getAllCustomer();
        customerAdapter = new CustomerRecyclerViewAdapter(customerArrayList, this);
        customerRecyclerView.setAdapter(customerAdapter);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                customerArrayList =sqLiteHelper.findListCustomer(editable.toString());
                customerAdapter.setData(customerArrayList);
            }
        });
    }

    @Override
    public void onCustomerItemClick(int position) {
        Customer customer = customerArrayList.get(position);
        Intent intent  = new Intent(this,OrderListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("customer",customer);
        bundle.putSerializable("sale_person",currentSalesPerson);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }
}