package com.example.sale30.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.TwoLineListItem;

import com.example.sale30.MainActivity;
import com.example.sale30.R;
import com.example.sale30.data.SaleData;

public class SaleAdapter extends Activity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sanpham);
        SQLiteOpenHelper saleData = new SaleData(this);
        @SuppressLint("WrongViewCast") ListView listView = (ListView) findViewById(R.id.sanpham);
        try{
            //Xac dinh dieu kien de doc
            db = saleData.getReadableDatabase();
            cursor = db.query("Sale", new String[]{"id","name"},null,null,null,null,null);
            SimpleCursorAdapter saleAdapter = new SimpleCursorAdapter(this, R.layout.sanpham,cursor, new String[]{"name"}, new int[]{R.id.nameshop});
            listView.setAdapter(saleAdapter);
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        // tao ra su kien lang nghe
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(SaleAdapter.this, MainActivity.class);
                intent.putExtra(MainActivity.EXTRA_SALE, (int) i);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}
