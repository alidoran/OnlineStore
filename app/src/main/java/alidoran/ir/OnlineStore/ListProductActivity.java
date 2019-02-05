package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ListProductActivity extends AppCompatActivity {

    public static String data="";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_list_product );

        Toast.makeText ( MainActivity.context,data+"",Toast.LENGTH_SHORT).show ();

        RecyclerView product_list_recyclerView;
        LinearLayoutManager product_list_linearmanager;
        product_list_recyclerView = findViewById ( R.id.product_list_recyclerview );
        product_list_linearmanager = new LinearLayoutManager ( this );
        product_list_recyclerView.setHasFixedSize ( true );
        product_list_recyclerView.setLayoutManager ( product_list_linearmanager );


    }
}
