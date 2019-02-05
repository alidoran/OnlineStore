package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SpecActivity extends AppCompatActivity {

    public static String data = "";
    LinearLayout.LayoutParams layoutParams;
    LinearLayout linearspec;
    TextView specname;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_spec );

        linearspec = findViewById ( R.id.lin_setspec );
        String title = "";
        String value = "";
        specname = findViewById ( R.id.txt_spec_name );


        try {
            JSONObject jsonObject = new JSONObject ( data );
            String name = jsonObject.getString ( "name" );
            specname.setText ( name );
            JSONArray spec_title = jsonObject.getJSONArray ( "title" );
            JSONArray spec_value = jsonObject.getJSONArray ( "value" );
            for (int j = 0; j < spec_title.length ( ); j++) {
                title = spec_title.getString ( j );
                value = spec_value.getString ( j );
                Specification specification = new Specification ( MainActivity.context );
                specification.txt_spec_title.setText ( title );
                specification.txt_spec_value.setText ( value );
                layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                linearspec.addView ( specification );

            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }

    }
}
