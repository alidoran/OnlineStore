package alidoran.ir.OnlineStore;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class WaitFilterActivity extends AppCompatActivity {

    String id;
    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_wait );


        Bundle bundle = getIntent ( ).getExtras ( );
        id = bundle.getString ( "id" );


        new ASyncFilter ( "http://www.alidoran.ir/filter.php" , id ).execute ( );

        final Timer timer = new Timer ( );
        timer.scheduleAtFixedRate ( new TimerTask ( ) {
            @Override
            public void run ( ) {
                runOnUiThread ( new Runnable ( ) {
                    @Override
                    public void run ( ) {
                        if (!FilterActivity.data.equals ( "" )) {
                            Intent intent = new Intent ( WaitFilterActivity.this , FilterActivity.class );
                            startActivity ( intent );
                            timer.cancel ( );
                            finish ();
                        }
                    }
                } );

            }
        } , 1 , 500 );
    }
}
