package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class CatListActivity extends AppCompatActivity {

    public static String data = "";

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_cat_list );

        Toast.makeText ( MainActivity.context,data+"",Toast.LENGTH_LONG ).show ();
    }
}
