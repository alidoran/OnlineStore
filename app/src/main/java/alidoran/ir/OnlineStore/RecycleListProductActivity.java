package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecycleListProductActivity extends AppCompatActivity {

    public static String data="";
    ArrayList<RecycleProduct> recycleProducts;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_list_product );


        RecyclerView recyclerView;
        LinearLayoutManager product_list_linearmanager;
        recyclerView = findViewById ( R.id.recyclerview_product_list );
        product_list_linearmanager = new LinearLayoutManager ( this );
        recyclerView.setHasFixedSize ( true );
        recyclerView.setLayoutManager ( product_list_linearmanager );

        recycleProducts = new ArrayList <> (  );



        try {

            //load json object from data
            JSONObject jsonObject = new JSONObject ( data );
            JSONArray jsonArray_type_id = jsonObject.getJSONArray ( "id" );
            JSONArray jsonArray_type_name = jsonObject.getJSONArray ( "typename" );
            JSONArray jsonArray_type_img = jsonObject.getJSONArray ( "type_img" );

            //get type name and img from JSON
            for(int i=0;i<jsonArray_type_name.length ();i++){
                String id = jsonArray_type_id.getString ( i );
                String name = jsonArray_type_name.getString ( i );
                String img = jsonArray_type_img.getString ( i );

                RecycleProduct recycleProduct = new RecycleProduct ();
                recycleProduct.recycle_id = id;
                recycleProduct.recycle_name = name;
                recycleProduct.recycle_img =img;
                recycleProducts.add ( recycleProduct );

            }
            recyclerView.setAdapter ( new AdapterRecycleListProduct ( recycleProducts ) );

        } catch (JSONException e) {
            e.printStackTrace ( );
        }

    }
}