package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static alidoran.ir.OnlineStore.ShowActivity.details;

public class ListProductActivityRecycle extends AppCompatActivity {

    public static String data="";
    ArrayList<RecycleProduct> recycleProducts;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_list_product );



        Toast.makeText ( MainActivity.context , data + "" , Toast.LENGTH_SHORT ).show ( );

        RecyclerView product_list_recyclerView;
        LinearLayoutManager product_list_linearmanager;
        product_list_recyclerView = findViewById ( R.id.product_list_recyclerview );
        product_list_linearmanager = new LinearLayoutManager ( this );
        product_list_recyclerView.setHasFixedSize ( true );
        product_list_recyclerView.setLayoutManager ( product_list_linearmanager );

        recycleProducts = new ArrayList <> (  );



        try {

            JSONObject jsonObject = new JSONObject ( data );
            JSONArray jsonArray_type_name = jsonObject.getJSONArray ( "typename" );
            JSONArray jsonArray_type_img = jsonObject.getJSONArray ( "type_img" );
            JSONArray jsonArray_products_name = jsonObject.getJSONArray ( "name" );
            JSONArray jsonArray_products_img = jsonObject.getJSONArray ( "img" );

            for (int i = 0; i < jsonArray_products_name.length ( ); i++) {
                JSONArray jsonArray_product_name = jsonArray_products_name.getJSONArray ( i );
                JSONArray jsonArray_product_img = jsonArray_products_img.getJSONArray ( i );
            }

            for(int i=0;i< jsonArray_type_name.length ();i++){
                type_name_array [i] = jsonArray_type_name.getString ( i );

            }




        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }
}