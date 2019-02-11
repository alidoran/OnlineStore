package alidoran.ir.OnlineStore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CatListActivity extends AppCompatActivity {

    public static String data = "";
    LinearLayout.LayoutParams layoutParams;
    LinearLayout custom_list_product;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cat_list );

        fill_custom_list ();
        sort ();


        LinearLayout lin_cat_sort = findViewById ( R.id.lin_cat_sort );

    }

    private void sort(){
        LinearLayout lin_cat_filter = findViewById ( R.id.lin_cat_filter );
        lin_cat_filter.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( MainActivity.context , FilterActivity.class );
                startActivity ( intent );
            }
        } );
    }

    private void fill_custom_list (){
        custom_list_product =findViewById ( R.id.linear_custom_list_product );
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT );
        try {
            JSONObject jsonObject = new JSONObject ( data );
            JSONArray jsonArray_id = jsonObject.getJSONArray ( "id" );
            JSONArray jsonArray_price = jsonObject.getJSONArray ( "price" );
            JSONArray jsonArray_name = jsonObject.getJSONArray ( "name" );
            JSONArray jsonArray_pic = jsonObject.getJSONArray ( "img" );
            JSONArray jsonArray_type = jsonObject.getJSONArray ( "type" );
            JSONArray jsonArray_category = jsonObject.getJSONArray ( "category" );

            for (int i= 0;i<jsonArray_price.length ();i++){
                String product_id = jsonArray_id.getString ( i );
                String price = jsonArray_price.getString ( i );
                String name = jsonArray_name.getString ( i );
                String pic =jsonArray_pic.getString ( i );
                String type =jsonArray_type.getString ( i );
                String category =jsonArray_category.getString ( i );
                CustomListProduct customListProduct = new CustomListProduct ( MainActivity.context );
                customListProduct.txt_cutom_name.setText ( name );
                customListProduct.txt_custom_desc.setText ( name );
                customListProduct.txt_custom_price.setText ( price );
                customListProduct.id_custom = product_id;
                Picasso.with ( MainActivity.context ).load ( "http://www.alidoran.ir/picture/" + category + "/" + type + "/" + pic ).fit ().into ( customListProduct.img_custom );
                custom_list_product.addView ( customListProduct );
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }
}
