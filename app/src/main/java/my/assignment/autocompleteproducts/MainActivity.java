package my.assignment.autocompleteproducts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ASQLiteHelper db=new ASQLiteHelper(this);
    List<Products> plist=new ArrayList<Products>();
    List<String> item=new ArrayList<String>();
    ArrayAdapter<String> adapter;
    Products products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // drop this database if already exists
        db.onUpgrade(db.getWritableDatabase(), 1, 2);
        products=new Products();
        products.setName("HP Leserjet Printer");
        db.insertProduct(products);
        products.setName("HP Inkjet Printer");
        db.insertProduct(products);
        products.setName("HP Inkjet Printer");
        db.insertProduct(products);
        products.setName("Canon Printer");
        db.insertProduct(products);
        products.setName("Lexmark Printer");
        db.insertProduct(products);
        products.setName("Canon Pixma Printer");
        db.insertProduct(products);
        products.setName("Lexmark All in one Printer");
        db.insertProduct(products);

        plist=db.getAllProducts();
        for(int i=0;i<plist.size();i++){
            item.add(plist.get(i).getName());
        }
        Log.d("databas rows:",plist.size()+"");
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,item);
        AutoCompleteTextView autotv=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        autotv.setAdapter(adapter);
        autotv.setThreshold(1);


    }
}
