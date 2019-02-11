package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FilterActivity extends AppCompatActivity {
    public static String data="";
    LinearLayout lin_filter_title;
    LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_filter );

        lin_filter_title =findViewById ( R.id.lin_filter_title );

        try {
            JSONObject jsonObject = new JSONObject ( data );
            JSONArray json_title = jsonObject.getJSONArray ( "title" );

            for(int i=0;i<json_title.length ();i++){
                String title = json_title.getString ( i );



                CustomFilter customFilter = new CustomFilter ( MainActivity.context );
                customFilter.txt_filter_title.setText ( title );

                layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                lin_filter_title.setLayoutParams ( layoutParams );
                lin_filter_title.addView ( customFilter );
            }


        } catch (JSONException e) {
            e.printStackTrace ( );
        }


    }
}
