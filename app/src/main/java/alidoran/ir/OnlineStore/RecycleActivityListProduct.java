package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecycleActivityListProduct extends AppCompatActivity {

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
            JSONArray jsonArray_type_name = jsonObject.getJSONArray ( "typename" );
            JSONArray jsonArray_type_img = jsonObject.getJSONArray ( "type_img" );
            JSONArray jsonArray_products_name = jsonObject.getJSONArray ( "name" );
            JSONArray jsonArray_products_img = jsonObject.getJSONArray ( "img" );


            //get type name and img from JSON
            for(int i=0;i<jsonArray_products_name.length ();i++){
                String name = jsonArray_type_name.getString ( i );
                String img = jsonArray_type_img.getString ( i );

                RecycleProduct recycleProduct = new RecycleProduct ();
                recycleProduct.recycle_name = name;
                recycleProduct.recycle_img =img;
                recycleProducts.add ( recycleProduct );

            }
            recyclerView.setAdapter ( new RecycleAdapterListProduct ( recycleProducts ) );

//            //get pname and pimg from json
//            for (int i = 0; i < jsonArray_products_name.length ( ); i++) {
//                JSONArray jsonArray_product_name = jsonArray_products_name.getJSONArray ( i );
//                JSONArray jsonArray_product_img = jsonArray_products_img.getJSONArray ( i );
//
//                for(int j =0;j<jsonArray_product_name.length ();j++){
//                    String pname = jsonArray_product_name.getString ( j );
//                    String pimg = jsonArray_product_img.getString ( j );
//                    Toast.makeText ( MainActivity.context , pname + "=" + pimg , Toast.LENGTH_SHORT ).show ();
//
//                }
//                Toast.makeText ( MainActivity.context , "next cat" , Toast.LENGTH_SHORT ).show ();
//            }






        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }
}