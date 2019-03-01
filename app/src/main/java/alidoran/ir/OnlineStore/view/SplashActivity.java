package alidoran.ir.OnlineStore.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

import alidoran.ir.OnlineStore.Async.ASyncBestSell;
import alidoran.ir.OnlineStore.Async.ASyncMainBanner;
import alidoran.ir.OnlineStore.Async.ASyncNews;
import alidoran.ir.OnlineStore.Async.ASyncPromotion;
import alidoran.ir.OnlineStore.Async.ASyncThisStore;
import alidoran.ir.OnlineStore.Async.ASyncTimer;
import alidoran.ir.OnlineStore.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_splash );
        timer ( );
        promotion ( );
        news ( );
        bestsell ( );
        thisstore ( );
        mainbanner ( );


        final Timer timer = new Timer ( );
        timer.scheduleAtFixedRate ( new TimerTask ( ) {
            @Override
            public void run ( ) {
                runOnUiThread ( new Runnable ( ) {
                    @Override
                    public void run ( ) {
                        if ((!MainActivity.timer.equals ( "" )) && (!MainActivity.readpromotion.equals ( "" ))&&(!MainActivity.readnews.equals ( "" ))&&(!MainActivity.readbestsell.equals ( "" ))&&(!MainActivity.mainbanner.equals ( "" ))&&(!MainActivity.thisstore.equals ( "" ))) {
                            Intent intent = new Intent ( SplashActivity.this , MainActivity.class );
                            startActivity ( intent );
                            timer.cancel ( );
                            finish ( );
                        }
                    }
                } );

            }
        } , 1 , 1000 );
    }


    public void timer ( ) {
        ASyncTimer aSyncTimer = new ASyncTimer ( "http://www.alidoran.ir/androidtimer.php" );
        aSyncTimer.execute ( );
    }

    public void promotion ( ) {
        ASyncPromotion promotion = new ASyncPromotion ( "http://alidoran.ir/readPromotion.php" );
        promotion.execute ( );
    }

    public void news ( ) {
        ASyncNews news = new ASyncNews ( "http://alidoran.ir/readNewsProduct.php" );
        news.execute ( );
    }

    public void bestsell ( ) {
        ASyncBestSell bestSell = new ASyncBestSell ( "http://alidoran.ir/readBestSell.php" );
        bestSell.execute ( );
    }

    public void thisstore ( ) {
        ASyncThisStore thisstore = new ASyncThisStore ( "http://alidoran.ir/readThisStore.php" );
        thisstore.execute ( );
    }

    public void mainbanner ( ) {
        ASyncMainBanner mainBanner = new ASyncMainBanner ( "http://alidoran.ir/MainPageBanner.php" );
        mainBanner.execute ( );
    }

}
