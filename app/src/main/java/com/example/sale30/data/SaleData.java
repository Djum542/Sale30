package com.example.sale30.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.example.sale30.R;

public class SaleData extends SQLiteOpenHelper {

    private static final String DB_NAME = "sale";
    private static final int DB_VERSION = 2;
//    SaleData(Context context){
//        super(context, DB_NAME, DB_VERSION, null);
//    }
    public SaleData(@Nullable Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        updateMyDatabase(sqLiteDatabase, i, i1);
    }
    private static void insertSale(SQLiteDatabase db, String name, String card,
                                    int imageId, String description) {
        ContentValues saleValues = new ContentValues();
        saleValues.put("NAME", name);
        saleValues.put("CARD", card);
        saleValues.put("IMAGE_RESOURCE_ID", imageId);
        saleValues.put("DERECTION", description);
        db.insert("Sale", null, saleValues);
    }

    private void updateMyDatabase(SQLiteDatabase db, int i, int i1) {
        if (i < 1) {
            db.execSQL("CREATE TABLE Sale (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "CARD TEXT, "
                    + "IMAGE_RESOURCE_ID INTEGER,"
                    + "DERECTION);");

            //Thêm dữ liệu cho bảng
            insertSale(db, "Sua tam", "50", R.drawable.hinh1, "Giup loai bo 99,9% vi khuan");
            insertSale(db, "Sua rua mat", "35",
                    R.drawable.hinh2, "Giup lam sach sau");
            insertSale(db, "Nuoc rua tay", "45", R.drawable.hinh3, "Ngan ngua vi khuan");
        }
        if (i1 < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
