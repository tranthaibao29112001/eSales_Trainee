package com.hqsoft.esales.trainee;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private EditText userNameEditText;
    private EditText passwordEditText;
    private Button loginBtn, outBtn;
    private TextView versionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userNameEditText = findViewById(R.id.userNameEditTxt);
        passwordEditText = findViewById(R.id.passwordEditTxt);
        loginBtn = findViewById(R.id.loginBtn);
        outBtn = findViewById(R.id.outBtn);
        versionTextView = findViewById(R.id.versionTxt);


        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        sqLiteHelper.initData();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = String.valueOf(userNameEditText.getText());
                String password = String.valueOf(passwordEditText.getText());

                if(userName.equals("")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("Vui lòng nhập thông tin username!")
                            .setPositiveButton(android.R.string.yes, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(true)
                            .show();
                    return;
                }
                if(password.equals("")){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("Vui lòng nhập password!")
                            .setPositiveButton(android.R.string.yes, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(true)
                            .show();
                    return;
                }
                Salesperson salesperson = sqLiteHelper.getSalespersonByID(userName);

                if(salesperson == null || !password.equals(salesperson.getPassword())){
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Error")
                            .setMessage("Tài khoản hoặc mật khẩu không chính xác!")
                            .setPositiveButton(android.R.string.yes, null)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setCancelable(true)
                            .show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this,CustomerListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("sale_person", salesperson);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
        outBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHMM");
        Date d = new Date(Long.parseLong(BuildConfig.BUILD_TIME));
        versionTextView.setText("Phiên bản ngày: "+ dateFormat.format(d)+" \n eSales Version: 2.0");
    }
}