package alidoran.ir.OnlineStore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class WaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_wait );

        ShowActivity.data="";
        ShowActivity.details="";
        Bundle bundle = getIntent ( ).getExtras ( );
        final String id = bundle.getString ( "id" );

        new ASyncProductPictures ( "http://www.alidoran.ir/ProductPictures.php" , id  ).execute (  );
        new ASyncProductDetails ( "http://www.alidoran.ir/ProductDetails.php",id ).execute (  );

        final Timer timer = new Timer ( );
        timer.scheduleAtFixedRate ( new TimerTask ( ) {
            @Override
            public void run ( ) {
                runOnUiThread ( new Runnable ( ) {
                    @Override
                    public void run ( ) {
                        if ((!ShowActivity.data.equals ( "" ))&&(!MainActivity.timer.equals ( "" ))&&(!ShowActivity.details.equals ( "" )) ) {
                            Intent intent = new Intent ( WaitActivity.this , ShowActivity.class );
                            intent.putExtra ( "id" , id);
                            startActivity ( intent );
                            timer.cancel ( );
                            finish ( );
                        }
                    }
                } );

            }
        } , 1 , 1000 );
    }
}

