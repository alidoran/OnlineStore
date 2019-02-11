package alidoran.ir.OnlineStore;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static alidoran.ir.OnlineStore.MainActivity.timer;

public class ShowActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {

    int hour;
    int min;
    int sec;
    String time[];
    ArrayList <String> urlName;
    TextSliderView textSliderView;
    ArrayList <String> urlPic;
    public static String data = "";
    public static String details = "";
    TextView txtDescription;
    LinearLayout rateLinearLayout;
    LinearLayout.LayoutParams layoutParams;


    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_show );

        rateLinearLayout = findViewById ( R.id.rateLinearLayout );

        txtDescription = findViewById ( R.id.txtDescription );
        urlPic = new ArrayList <> ( );

        Bundle bundle = getIntent ( ).getExtras ( );
        String id = bundle.getString ( "id" );


        timer ( );
        slideShowPicture ( );
        fillDetails ( );
        continueDetails ( );
        specifications ( id );
        comment ( id );
    }

    private void timer ( ) {
        final Handler handler = new Handler ( );
        final TextView edtshowhourpromotion = findViewById ( R.id.showhourPromotion );
        final TextView edtshowminpromotion = findViewById ( R.id.showminPromotion );
        final TextView edtshowsecpromotion = findViewById ( R.id.showsecPromotion );

        String time[] = (timer.split ( ":" ));
        hour = Integer.valueOf ( time[0] );
        min = Integer.valueOf ( time[1] );
        sec = Integer.valueOf ( time[2] );

        Thread thread_time = new Thread ( new Runnable ( ) {
            @Override
            public void run ( ) {

                try {
                    while (true) {
                        Thread.sleep ( 1000 );
                        if (sec == 0) {
                            if (!(min == 0)) {
                                sec = 59;
                                min--;
                            }
                            if (min == 0) {
                                sec = 59;
                                min = 59;
                                hour--;
                            }
                        } else {
                            sec--;
                        }

                        handler.post ( new Runnable ( ) {
                            @Override
                            public void run ( ) {
                                if (hour < 10) {
                                    edtshowhourpromotion.setText ( "0" + hour );
                                } else {
                                    edtshowhourpromotion.setText ( String.valueOf ( hour ) );
                                }
                                if (min < 10) {
                                    edtshowminpromotion.setText ( "0" + min );
                                } else {
                                    edtshowminpromotion.setText ( String.valueOf ( min ) );
                                }
                                if (sec < 10) {
                                    edtshowsecpromotion.setText ( "0" + sec );
                                } else {
                                    edtshowsecpromotion.setText ( String.valueOf ( sec ) );
                                }


                            }
                        } );
                    }
                } catch (InterruptedException e) {
                }

            }
        } );
        thread_time.start ( );
    }

    private void slideShowPicture ( ) {
        SliderLayout sliderShow = ( SliderLayout ) findViewById ( R.id.slidershow );
        try {


            JSONArray jsonArray = new JSONArray ( data );
            for (int i = 0; i < jsonArray.length ( ); i++) {
                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );


//                String img = jsonObject.getString ( "pic" );

                String type = jsonObject.getString ( "type" );
                String category = jsonObject.getString ( "category" );


                JSONArray pictures = jsonObject.getJSONArray ( "pic" );
                for (int j = 0; j < pictures.length ( ); j++) {
                    String img = String.valueOf ( pictures.get ( j ) );
                    urlPic.add ( "http://www.alidoran.ir/picture/" + category + "/" + type + "/" + img );
                }

            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
        for (int i = 0; i < urlPic.size ( ); i++) {
            textSliderView = new TextSliderView ( this );
            textSliderView.image ( urlPic.get ( i ) )
                    .setScaleType ( BaseSliderView.ScaleType.CenterInside )
                    .setOnSliderClickListener ( this );
            textSliderView.bundle ( new Bundle ( ) );
            sliderShow.addSlider ( textSliderView );
        }

    }

    private void fillDetails ( ) {
        String productName = "";
        String productColor = "";
        String productGuarantee = "";
        String productPrice = "";
        String productDescription = "";
        String id = "";


        try {

            JSONArray jsonArray = new JSONArray ( details );
            for (int i = 0; i < jsonArray.length ( ); i++) {
                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );

                //Toast.makeText ( MainActivity.context , jsonObject + "" , Toast.LENGTH_SHORT ).show ( );

                id = jsonObject.getString ( "id" );
                productName = jsonObject.getString ( "productName" );
                productColor = jsonObject.getString ( "productColor" );
                productGuarantee = jsonObject.getString ( "productGuarantee" );
                productPrice = jsonObject.getString ( "productPrice" );
                productDescription = jsonObject.getString ( "productDescription" );

                //fill Rate Title By Array
                JSONArray rateTitle = jsonObject.getJSONArray ( "rateTitle" );
                JSONArray ratebar = jsonObject.getJSONArray ( "rate" );

                for (int j = 0; j < rateTitle.length ( ); j++) {
                    String titleRate = String.valueOf ( rateTitle.get ( j ) );
                    String rate = String.valueOf ( ratebar.get ( j ) );
                    CustomRateStar rateStar = new CustomRateStar ( MainActivity.context );
                    rateStar.titleProgress.setText ( titleRate );
                    rateStar.progressBar.setProgress ( Integer.valueOf ( rate ) );
                    layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                    rateLinearLayout.addView ( rateStar );
                }

                //fill satr
                //fill 5 star
                String star = jsonObject.getString ( "star" );
                RatingBar ratestar = findViewById ( R.id.ratingstar );
                ratestar.setRating ( Float.valueOf ( star ) );
                //fill star number
                TextView star_num = findViewById ( R.id.txt_star_num );
                star_num.setText ( star );

                //fill id;


            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
        TextView txtProductColor = findViewById ( R.id.txtProductColor );
        TextView txtProductGuarantee = findViewById ( R.id.txtProductGuarantee );
        TextView txtproductTitle = findViewById ( R.id.txtproductTitle );
        TextView txtProductPrice = findViewById ( R.id.txtProductPrice );
        TextView txtDescription = findViewById ( R.id.txtDescription );

        txtProductColor.setText ( productColor );
        txtproductTitle.setText ( productName );
        txtProductGuarantee.setText ( productGuarantee );
        txtProductPrice.setText ( productPrice );
        txtDescription.setText ( Html.fromHtml ( productDescription ) );
    }

    private void continueDetails ( ) {
        final TextView continueDetails;
        continueDetails = findViewById ( R.id.txtContinueDetails );
        continueDetails.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (continueDetails.getText ( ).equals ( "ادامه مطلب" )) {
                    txtDescription.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT ) );
                    continueDetails.setText ( "بستن" );
                } else {
                    txtDescription.setLayoutParams ( new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , 200 ) );
                    continueDetails.setText ( "ادامه مطلب" );
                }
            }
        } );
    }

    private void specifications ( final String id ) {
        LinearLayout speclin = findViewById ( R.id.btn_spec );
        speclin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                new ASyncspec ( "http://www.alidoran.ir/specifications.php" , id ).execute ( );
                final Timer timer = new Timer ( );
                timer.scheduleAtFixedRate ( new TimerTask ( ) {
                    @Override
                    public void run ( ) {
                        runOnUiThread ( new Runnable ( ) {
                            @Override
                            public void run ( ) {
                                if (!SpecActivity.data.equals ( "" )) {

                                    Intent intent = new Intent ( ShowActivity.this , SpecActivity.class );
                                    startActivity ( intent );
                                    timer.cancel ( );
                                    finish ( );
                                }
                            }
                        } );

                    }
                } , 1 , 1000 );

            }
        } );

    }

    private void comment ( final String id ) {
        LinearLayout commentlin = new LinearLayout ( MainActivity.context );
        commentlin=findViewById ( R.id.btn_comment );
        commentlin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                new ASyncComment ( "http://www.alidoran.ir/comment.php" , id ).execute ( );
                final Timer timer = new Timer ( );
                timer.scheduleAtFixedRate ( new TimerTask ( ) {
                    @Override
                    public void run ( ) {
                        runOnUiThread ( new Runnable ( ) {
                            @Override
                            public void run ( ) {
                                if (!CommentActivity.data.equals ( "" )) {
                                    Intent intent = new Intent ( ShowActivity.this , CommentActivity.class );
                                    startActivity ( intent );
                                    timer.cancel ( );
                                    //finish ( );
                                }
                            }
                        } );

                    }
                } , 1 , 1000 );

            }
        } );
    }

    @Override
    public void onSliderClick ( BaseSliderView slider ) {

    }
}
