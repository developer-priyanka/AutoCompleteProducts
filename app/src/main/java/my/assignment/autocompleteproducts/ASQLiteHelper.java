package my.assignment.autocompleteproducts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/15/16.
 */


public class ASQLiteHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION=1;

    private static final String DB_NAME="ProductDB";
    private static final String DB_TABLE="products";
    private static final String DB_COL1="id";
    private static final String DB_COL2="product_name";

    private static final String[] columns={DB_COL1,DB_COL2};

    public ASQLiteHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table="CREATE TABLE "+DB_TABLE+" ("+DB_COL1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ DB_COL2+" TEXT ) ";
        db.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists "+DB_TABLE);
        this.onCreate(db);

    }

    public Products readProducts(int id){
        Products p= new Products();
        return p;

    }

    public void insertProduct(Products p) {
        // get reference of the BookDB database
        SQLiteDatabase db = this.getWritableDatabase();

        // make values to be inserted
        ContentValues values = new ContentValues();
        values.put(DB_COL2, p.getName());


        // insert book
        db.insert(DB_TABLE, null, values);

        // close database transaction
        db.close();
    }

    public List<Products>getAllProducts(){

        List<Products> p_List=new ArrayList<Products>();
        String query ="select * from "+DB_TABLE;
        // get reference of the ProductDB database
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Products products=null;
        if(cursor.moveToFirst()){
            do {
                products = new Products();
                products.setId(Integer.parseInt(cursor.getString(0)));
                products.setName(cursor.getString(1));
              ;
                // Add products to list
                p_List.add(products);
            } while (cursor.moveToNext());
        }

        return p_List;
    }
}
