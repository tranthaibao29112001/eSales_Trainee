package com.hqsoft.esales.trainee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper  {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "eSales1.db3";
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE AR_Salesperson (SlsperID TEXT PRIMARY KEY,Password TEXT,FullName TEXT,Address TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE AR_Customer (CustID TEXT PRIMARY KEY,Name TEXT,Address TEXT,Phone TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE IN_Inventory (InvtID TEXT PRIMARY KEY,Name TEXT,Unit TEXT,Price TEXT)");
        sqLiteDatabase.execSQL("CREATE TABLE OM_SalesOrd (OrderNbr TEXT PRIMARY KEY,SlsperID TEXT,CustID TEXT,OrdAmt REAL,OrdQty REAL, OrderDate DATETIME,Remark TEXT )");
        sqLiteDatabase.execSQL("CREATE TABLE OM_SalesOrdDet  (OrderNbr TEXT ,LineRef TEXT ,InvtID TEXT,LineAmt REAL,LineQty REAL, PRIMARY KEY ( OrderNbr, LineRef))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AR_Salesperson");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AR_Customer");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS IN_Inventory");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OM_SalesOrd");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS OM_SalesOrdDet");
        onCreate(sqLiteDatabase);
    }
    public void initData(){
        Salesperson salesperson1 = new Salesperson("SlsperID1","sa1","Nguyễn Văn A","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM");
        Salesperson salesperson2 = new Salesperson("SlsperID2","sa2","Nguyễn Văn B","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM");
        Salesperson salesperson3 = new Salesperson("SlsperID3","sa3","Nguyễn Văn C","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM");
        Salesperson salesperson4 = new Salesperson("SlsperID4","sa4","Nguyễn Văn D","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM");
        Salesperson salesperson5 = new Salesperson("SlsperID6","sa5","Nguyễn Văn F","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM");


        insertSalesperson(salesperson1);
        insertSalesperson(salesperson2);
        insertSalesperson(salesperson3);
        insertSalesperson(salesperson4);
        insertSalesperson(salesperson5);

        Customer customer1 = new Customer("CustID1","Khách Hàng A","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM","1234567890");
        Customer customer2 = new Customer("CustID2","Khách Hàng B","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM","1234567891");
        Customer customer3 = new Customer("CustID3","Khách Hàng C","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM","1234567892");
        Customer customer4 = new Customer("CustID4","Khách Hàng D","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM","1234567893");
        Customer customer5 = new Customer("CustID5","Khách Hàng E","72/24 Phan Đăng Lưu, P5, Phú Nhuận, HCM","1234567894");
        Customer customer6 = new Customer("CustID6","Trần Thái Bảo","KTX Khu B, Đông Hòa, Dĩ An, Bình Dương","0842227693");
        Customer customer7 = new Customer("CustID7","Nguyễn Công Minh","227 Đ. Nguyễn Văn Cừ, Phường 4, Quận 5, Thành phố Hồ Chí Minh","01202396269");

        insertCustomer(customer1);
        insertCustomer(customer2);
        insertCustomer(customer3);
        insertCustomer(customer4);
        insertCustomer(customer5);
        insertCustomer(customer6);
        insertCustomer(customer7);

        Inventory inventory1 = new Inventory("SP1","Sản phẩm 1","CHAI","10000");
        Inventory inventory2 = new Inventory("SP2","Sản phẩm 2","LON","15000");
        Inventory inventory3 = new Inventory("SP3","Sản phẩm 3","CHAI","20000");
        Inventory inventory4 = new Inventory("SP4","Sản phẩm 4","LON","17000");
        Inventory inventory5 = new Inventory("SP5","Sản phẩm 5","LON","32000");
        insertInventory(inventory1);
        insertInventory(inventory2);
        insertInventory(inventory3);
        insertInventory(inventory4);
        insertInventory(inventory5);

        SalesOrd salesOrd1 = new SalesOrd("SlsperID10001","SlsperID1","CustID1",35000,3,"2018-08-09","Ghi chú");
        SalesOrd salesOrd2 = new SalesOrd("SlsperID10002","SlsperID1","CustID1",50000,5,"2018-08-09","Ghi chú");
        SalesOrd salesOrd3 = new SalesOrd("SlsperID20001","SlsperID2","CustID2",40000,2,"2018-08-09","Ghi chú");
        SalesOrd salesOrd4 = new SalesOrd("SlsperID30001","SlsperID3","CustID3",51000,3,"2018-08-09","Ghi chú");
        SalesOrd salesOrd5 = new SalesOrd("SlsperID40001","SlsperID4","CustID4",128000,4,"2018-08-09","Ghi chú");
        insertSalesOrd(salesOrd1);
        insertSalesOrd(salesOrd2);
        insertSalesOrd(salesOrd3);
        insertSalesOrd(salesOrd4);
        insertSalesOrd(salesOrd5);

        SalesOrdDet salesOrdDet1 = new SalesOrdDet("SlsperID10001","00001","SP1",20000,2);
        SalesOrdDet salesOrdDet2 = new SalesOrdDet("SlsperID10001","00002","SP2",15000,1);
        SalesOrdDet salesOrdDet3 = new SalesOrdDet("SlsperID10002","00001","SP1",50000,5);
        SalesOrdDet salesOrdDet4 = new SalesOrdDet("SlsperID30001","00001","SP3",40000,2);
        SalesOrdDet salesOrdDet5 = new SalesOrdDet("SlsperID20001","00001","SP4",51000,3);
        SalesOrdDet salesOrdDet6 = new SalesOrdDet("SlsperID40001","00001","SP5",128000,4);
        insertSalesOrdDet(salesOrdDet1);
        insertSalesOrdDet(salesOrdDet2);
        insertSalesOrdDet(salesOrdDet3);
        insertSalesOrdDet(salesOrdDet4);
        insertSalesOrdDet(salesOrdDet5);
        insertSalesOrdDet(salesOrdDet6);



    }
    public boolean insertSalesperson(Salesperson salesperson){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("SlsperID",salesperson.getSlsperID() );
        values.put("Password", salesperson.getPassword());
        values.put("FullName", salesperson.getFullName());
        values.put("Address", salesperson.getAddress());

        long newRowId = db.insert("AR_Salesperson", null, values);
        if(newRowId ==-1){
            return  false;
        }
        return true;
    }
    public boolean insertCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("CustID",customer.getCustID() );
        values.put("Name", customer.getName());
        values.put("Address", customer.getAddress());
        values.put("Phone", customer.getPhone());

        long newRowId = db.insert("AR_Customer", null, values);
        if(newRowId ==-1){
            return  false;
        }
        return true;
    }
    public boolean insertInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("InvtID",inventory.getInvtID() );
        values.put("Name", inventory.getName());
        values.put("Unit", inventory.getUnit());
        values.put("Price", inventory.getPrice());

        long newRowId = db.insert("IN_Inventory", null, values);
        if(newRowId ==-1){
            return  false;
        }
        return true;
    }
    public boolean insertSalesOrd(SalesOrd salesOrd){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OrderNbr",salesOrd.getOrderNbr() );
        values.put("SlsperID", salesOrd.getSlsperID());
        values.put("CustID", salesOrd.getCustID());
        values.put("OrdAmt", salesOrd.getOrdAmt());
        values.put("OrdQty", salesOrd.getOrdQty());
        values.put("OrderDate", salesOrd.getOrderDate());
        values.put("Remark", salesOrd.getRemark());


        long newRowId = db.insert("OM_SalesOrd", null, values);
        if(newRowId ==-1){
            return  false;
        }
        return true;
    }
    public boolean insertSalesOrdDet(SalesOrdDet salesOrdDet){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("OrderNbr",salesOrdDet.getOrderNbr() );
        values.put("LineRef", salesOrdDet.getLineRef());
        values.put("InvtID", salesOrdDet.getInvtID());
        values.put("LineAmt", salesOrdDet.getLineAmt());
        values.put("LineQty", salesOrdDet.getLineQty());

        long newRowId = db.insert("OM_SalesOrdDet", null, values);
        if(newRowId ==-1){
            return  false;
        }
        return true;
    }
    public boolean deleteSalesperson(Salesperson salesperson){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM AR_Salesperson WHERE SlsperID =?",new String[]{salesperson.getSlsperID()});
        if(cursor.getCount()>0){
            String selection = "SlsperID =  ?";

            String[] selectionArgs = { salesperson.getSlsperID() };
            int deletedRows = db.delete("AR_Salesperson", selection, selectionArgs);
            if(deletedRows ==-1){
                return  false;
            }
            return true;
        }
        return false;
    }
    public boolean deleteCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM AR_Customer  WHERE CustID =?",new String[]{customer.getCustID()});
        if(cursor.getCount()>0){
            String selection = "CustID =  ?";

            String[] selectionArgs = { customer.getCustID() };
            int deletedRows = db.delete("AR_Customer", selection, selectionArgs);
            if(deletedRows ==-1){
                return  false;
            }
            return true;
        }
        return false;
    }
    public boolean deleteInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM IN_Inventory  WHERE InvtID =?",new String[]{inventory.getInvtID()});
        if(cursor.getCount()>0){
            String selection = "InvtID =  ?";

            String[] selectionArgs = { inventory.getInvtID() };
            int deletedRows = db.delete("IN_Inventory", selection, selectionArgs);
            if(deletedRows ==-1){
                return  false;
            }
            return true;
        }
        return false;
    }
    public boolean deleteSalesOrd(SalesOrd salesOrd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM OM_SalesOrd  WHERE OrderNbr =?",new String[]{salesOrd.getOrderNbr()});
        if(cursor.getCount()>0){
            String selection = "OrderNbr =  ?";
            String[] selectionArgs = { salesOrd.getOrderNbr() };
            int deletedRows = db.delete("OM_SalesOrd", selection, selectionArgs);
            if(deletedRows ==-1){
                return  false;
            }
            return true;
        }
        return false;
    }
    public boolean deleteSalesOrdDet(SalesOrdDet salesOrdDet){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM OM_SalesOrdDet WHERE OrderNbr =? AND LineRef=?",new String[]{salesOrdDet.getOrderNbr(),salesOrdDet.getLineRef()});
        if(cursor.getCount()>0){
            String selection = "OrderNbr =  ? AND LineRef = ?";
            String[] selectionArgs = { salesOrdDet.getOrderNbr() ,salesOrdDet.getInvtID()};
            int deletedRows = db.delete("OM_SalesOrdDet ", selection, selectionArgs);
            if(deletedRows ==-1){
                return  false;
            }
            return true;
        }
        return false;
    }
    public boolean updateSalesperson(Salesperson salesperson){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM AR_Salesperson WHERE SlsperID =?",new String[]{salesperson.getSlsperID()});
        if(cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("Password", salesperson.getPassword());
            values.put("FullName", salesperson.getFullName());
            values.put("Address", salesperson.getAddress());

            String selection = "SlsperID = ?";
            String[] selectionArgs = { salesperson.getSlsperID() };

            int count = db.update(
                    "AR_Salesperson",
                    values,
                    selection,
                    selectionArgs);
            if(count==-1){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean updateCustomer(Customer customer){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM AR_Customer WHERE CustID =?",new String[]{customer.getCustID()});
        if(cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("Name", customer.getName());
            values.put("Address", customer.getAddress());
            values.put("Phone", customer.getPhone());

            String selection = "CustID = ?";
            String[] selectionArgs = { customer.getCustID() };

            int count = db.update(
                    "AR_Customer",
                    values,
                    selection,
                    selectionArgs);
            if(count==-1){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean updateInventory(Inventory inventory){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM IN_Inventory  WHERE InvtID =?",new String[]{inventory.getInvtID()});
        if(cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("Name", inventory.getName());
            values.put("Unit", inventory.getUnit());
            values.put("Price", inventory.getPrice());

            String selection = "InvtID = ?";
            String[] selectionArgs = { inventory.getInvtID() };

            int count = db.update(
                    "IN_Inventory",
                    values,
                    selection,
                    selectionArgs);
            if(count==-1){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean updateSalesOrd(SalesOrd salesOrd){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM OM_SalesOrd   WHERE OrderNbr =?",new String[]{salesOrd.getOrderNbr()});
        if(cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("SlsperID", salesOrd.getSlsperID());
            values.put("CustID", salesOrd.getCustID());
            values.put("OrdAmt", salesOrd.getOrdAmt());
            values.put("OrdQty", salesOrd.getOrdQty());
            values.put("OrderDate", salesOrd.getOrderDate());
            values.put("Remark", salesOrd.getRemark());

            String selection = "OrderNbr = ?";
            String[] selectionArgs = { salesOrd.getOrderNbr() };

            int count = db.update(
                    "OM_SalesOrd",
                    values,
                    selection,
                    selectionArgs);
            if(count==-1){
                return false;
            }
            return true;
        }
        return false;
    }
    public boolean updateSalesOrdDet(SalesOrdDet salesOrdDet){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT* FROM OM_SalesOrdDet WHERE OrderNbr =? AND LineRef =?",new String[]{salesOrdDet.getOrderNbr(),salesOrdDet.getLineRef()});
        if(cursor.getCount()>0){
            ContentValues values = new ContentValues();
            values.put("LineRef", salesOrdDet.getLineRef());
            values.put("InvtID", salesOrdDet.getInvtID());
            values.put("LineAmt", salesOrdDet.getLineAmt());
            values.put("LineQty", salesOrdDet.getLineQty());

            String selection = "OrderNbr = ? AND LineRef = ?";
            String[] selectionArgs = { salesOrdDet.getOrderNbr(),salesOrdDet.getLineRef() };

            int count = db.update(
                    "OM_SalesOrdDet",
                    values,
                    selection,
                    selectionArgs);
            if(count==-1){
                return false;
            }
            return true;
        }
        return false;
    }
    public Salesperson getSalespersonByID(String slsperID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "SlsperID",
                "Password",
                "FullName",
                "Address",
        };

        String selection = "SlsperID = ?";
        String[] selectionArgs = { slsperID };


        Cursor cursor = db.query(
                "AR_Salesperson",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        ArrayList<Salesperson> salespersonArrayList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String password = cursor.getString(cursor.getColumnIndexOrThrow("Password"));
            String fullName = cursor.getString(cursor.getColumnIndexOrThrow("FullName"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            Salesperson salesperson = new Salesperson(slsperID, password, fullName, address);
            salespersonArrayList.add(salesperson);
        }
        cursor.close();
        if(salespersonArrayList.size() >0){
            return salespersonArrayList.get(0);
        }
        else{
            return null;
        }
    }
    public Inventory getInventoryByID(String invtID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "InvtID",
                "Name",
                "Unit",
                "Price",
        };

        String selection = "InvtID = ?";
        String[] selectionArgs = { invtID };


        Cursor cursor = db.query(
                "IN_Inventory",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        ArrayList<Inventory> inventories = new ArrayList<>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("Unit"));
            String price = cursor.getString(cursor.getColumnIndexOrThrow("Price"));
            Inventory inventory = new Inventory(invtID, name, unit, price);
            inventories.add(inventory);
        }
        cursor.close();
        if(inventories.size() >0){
            return inventories.get(0);
        }
        else{
            return null;
        }
    }
    public ArrayList<Customer> getAllCustomer(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "CustID",
                "Name",
                "Address",
                "Phone",
        };
        Cursor cursor = db.query(
                "AR_Customer",
                projection,
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String custID = cursor.getString(cursor.getColumnIndexOrThrow("CustID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            Customer customer = new Customer(custID, name, address,phone);
            customerArrayList.add(customer);
        }
        cursor.close();
        return customerArrayList;
    }
    public ArrayList<Customer> findListCustomer(String keyWord){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "CustID",
                "Name",
                "Address",
                "Phone",
        };

        String selection = "CustID LIKE ? OR Name LIKE ? OR Address LIKE ? OR Phone LIKE ?";
        String[] selectionArgs = {"%"+ keyWord+ "%","%"+ keyWord+ "%","%"+ keyWord+ "%","%"+ keyWord+ "%"};


        Cursor cursor = db.query(
                "AR_Customer",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String custID = cursor.getString(cursor.getColumnIndexOrThrow("CustID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("Address"));
            String phone = cursor.getString(cursor.getColumnIndexOrThrow("Phone"));
            Customer customer = new Customer(custID, name, address,phone);
            customerArrayList.add(customer);
        }
        cursor.close();
        return customerArrayList;
    }
    public ArrayList<Inventory> findListInventory(String keyWord){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "InvtID",
                "Name",
                "Unit",
                "Price",
        };

        String selection = "InvtID LIKE ? OR Name LIKE ? OR Price = ?";
        String[] selectionArgs = {"%"+ keyWord+ "%","%"+ keyWord+ "%",keyWord};

        Cursor cursor = db.query(
                "IN_Inventory",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        ArrayList<Inventory> inventories = new ArrayList<>();
        while(cursor.moveToNext()) {
            String invtID = cursor.getString(cursor.getColumnIndexOrThrow("InvtID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("Unit"));
            String price = cursor.getString(cursor.getColumnIndexOrThrow("Price"));
            Inventory inventory = new Inventory(invtID, name, unit,price);
            inventories.add(inventory);
        }
        cursor.close();
        return inventories;
    }
    public ArrayList<SalesOrd> getOrdersByCustIDAndSlsperID(String custID, String slsperID){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "OrderNbr",
                "SlsperID",
                "CustID",
                "OrdAmt",
                "OrdQty",
                "OrderDate",
                "Remark",
        };
        String selection = "SlsperID = ? AND CustID = ?";
        String[] selectionArgs = { slsperID,custID };

        Cursor cursor = db.query(
                "OM_SalesOrd",
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        ArrayList<SalesOrd> ordArrayList = new ArrayList<>();
        while(cursor.moveToNext()) {
            String orderNbr = cursor.getString(cursor.getColumnIndexOrThrow("OrderNbr"));
            double ordAmt = cursor.getDouble(cursor.getColumnIndexOrThrow("OrdAmt"));
            double ordQty = cursor.getDouble(cursor.getColumnIndexOrThrow("OrdQty"));
            String orderDate = cursor.getString(cursor.getColumnIndexOrThrow("OrderDate"));
            String remark = cursor.getString(cursor.getColumnIndexOrThrow("Remark"));
            SalesOrd salesOrd = new SalesOrd(orderNbr,slsperID,custID,ordAmt,ordQty,orderDate,remark);

            ordArrayList.add(salesOrd);
        }
        cursor.close();
        return ordArrayList;
    }
    public ArrayList<Inventory> getAllInventory(){
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "InvtID",
                "Name",
                "Unit",
                "Price",
        };
        Cursor cursor = db.query(
                "IN_Inventory",
                projection,
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Inventory> inventories = new ArrayList<>();
        while(cursor.moveToNext()) {
            String invtID = cursor.getString(cursor.getColumnIndexOrThrow("InvtID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            String unit = cursor.getString(cursor.getColumnIndexOrThrow("Unit"));
            String price = cursor.getString(cursor.getColumnIndexOrThrow("Price"));
            Inventory inventory = new Inventory(invtID, name, unit,price);
            inventories.add(inventory);
        }
        cursor.close();
        return inventories;
    }
}
