package com.example.sale30;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sale30.data.SaleData;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_SALE = "id";
TextView txtTensanpham, txtMota, txtGia;
ImageView imgHinhsanpham;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTensanpham.findViewById(R.id.tensanpham);
        txtMota.findViewById(R.id.mota);
        txtGia.findViewById(R.id.gia);
        imgHinhsanpham.findViewById(R.drawable.hinh1);
        int id = (Integer)getIntent().getExtras().get(EXTRA_SALE);
        SQLiteOpenHelper starbuzzDatabaseHelper = new SaleData(this);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            Cursor cursor = db.query ("Sale",
                    new String[] {"Name", "Card", "ImageId", "Derection"},
                    "id = ?",
                    new String[] {Integer.toString(id)},
                    null, null,null);
            //di chuyen dau doc toi dau bang
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String cardText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                String motaText = cursor.getString(3);
                //danh sach ten
                TextView name = (TextView)findViewById(R.id.tensanpham);
                name.setText(nameText);

                //Bang gia
                TextView card = (TextView)findViewById(R.id.giaban);
                card.setText(cardText);

                //hinh anh
                ImageView photo = (ImageView)findViewById(R.id.imgsanpham);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}