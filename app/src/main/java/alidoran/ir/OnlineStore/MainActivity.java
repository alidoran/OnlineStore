package alidoran.ir.OnlineStore;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {

    public static Context context;

    ArrayList <String> urlName;
    SharedPreferences sharedPreferences_signin;
    TextView txt_signin;
    public static String timer = "";
    int hour;
    int min;
    int sec;
    public static String readpromotion = "";
    public static String readnews = "";
    public static String readbestsell = "";
    public static String thisstore = "";
    public static String mainbanner = "";


    //auto sign in
    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult ( requestCode , resultCode , data );

        txt_signin = findViewById ( R.id.txt_signin );
        if (resultCode == 100) {
            String email = data.getExtras ( ).getString ( "email" );
            txt_signin.setText ( email );

            sharedPreferences_signin = PreferenceManager.getDefaultSharedPreferences ( MainActivity.this );
            SharedPreferences.Editor editor = sharedPreferences_signin.edit ( );
            editor.putString ( "email_signin" , email );
            editor.commit ( );
        }
    }

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        context = MainActivity.this;


        timer ( );
        showonlythisstore ( );
        showmainbanner ( );
        showpromotionproduct ( );
        shownewsproduct ( );
        showbestsellproduct ( );
        auto_signin ( );
        permission ( );
        slideshow ( );
        listview_navigation ( );
        menu_main_page ( );
        product_navigation ( );
        setting_navigation ( );
        sign_in ( );
        signout ( );
        showAllProduct();
    }


    @Override
    public void onSliderClick ( BaseSliderView slider ) {
    }

    private void permission ( ) {
        ActivityCompat.requestPermissions ( MainActivity.this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , 1 );
    }

    private void slideshow ( ) {
        SliderLayout sliderShow = findViewById ( R.id.slider );
        ArrayList <String> urlPic = new ArrayList <> ( );
        urlPic.add ( "http://www.alidoran.ir/picture/digital/camera/700D.jpg" );
        urlPic.add ( "http://www.alidoran.ir/picture/digital/camera/750D.jpg" );
        urlPic.add ( "http://www.alidoran.ir/picture/digital/camera/800D.jpg" );

        urlName = new ArrayList <> ( );
        urlName.add ( "700D" );
        urlName.add ( "750D" );
        urlName.add ( "800D" );

        for (int i = 0; i < urlPic.size ( ); i++) {
            TextSliderView textSliderView = new TextSliderView ( this );
            textSliderView.image ( urlPic.get ( i ) )
                    .setScaleType ( BaseSliderView.ScaleType.CenterInside )
                    .description ( urlName.get ( i ) )
                    .setOnSliderClickListener ( this );
            textSliderView.bundle ( new Bundle ( ) );
            textSliderView.getBundle ( )
                    .putString ( "extra" , urlName.get ( i ) );
            sliderShow.addSlider ( textSliderView );
        }
    }

    private void listview_navigation ( ) {
        ListView listView_navigation = findViewById ( R.id.listview_navigation );
        ArrayList <BuyMenuListitem> items = new ArrayList <> ( );
        items.add ( new BuyMenuListitem ( R.mipmap.home , "خانه" ) );
        items.add ( new BuyMenuListitem ( R.mipmap.menu , "لیست محصولات" ) );
        items.add ( new BuyMenuListitem ( R.mipmap.shopping , "سبد خرید" ) );
        listView_navigation.setAdapter ( new BuyMenuAdapter ( MainActivity.this , R.layout.listview_buy_menu , items ) );
    }

    private void product_navigation ( ) {
        ListView listView_product = findViewById ( R.id.listview_product );
        ArrayList <ProductListItem> items = new ArrayList <> ( );
        items.add ( new ProductListItem ( R.mipmap.offer , "پر تخفیف ترین ها" ) );
        items.add ( new ProductListItem ( R.mipmap.bestsell , "پر فروش ترین ها" ) );
        items.add ( new ProductListItem ( R.mipmap.bestvisit , "پر بازدید ترین ها" ) );
        items.add ( new ProductListItem ( R.mipmap.pnew , "جدیدترین ها" ) );
        listView_product.setAdapter ( new ProductAdapter ( MainActivity.this , R.layout.listview_product , items ) );
    }

    private void setting_navigation ( ) {
        ListView listView_navigation = findViewById ( R.id.listview_setting );
        ArrayList <SettingListitem> items = new ArrayList <> ( );
        items.add ( new SettingListitem ( R.mipmap.setting , "تنظیمات" ) );
        items.add ( new SettingListitem ( R.mipmap.question , "سوالات متداول" ) );
        items.add ( new SettingListitem ( R.mipmap.aboutus , "درباره ما" ) );
        listView_navigation.setAdapter ( new SettingAdapter ( MainActivity.this , R.layout.listview_setting , items ) );
    }

    private void menu_main_page ( ) {
        ImageView menu_main_page = findViewById ( R.id.menu_main_page );
        final DrawerLayout drawerlayout_main_page = findViewById ( R.id.drawerlayout_main_page );
        menu_main_page.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                auto_signin ( );
                drawerlayout_main_page.openDrawer ( Gravity.RIGHT );
            }
        } );
    }

    private void auto_signin ( ) {

        txt_signin = findViewById ( R.id.txt_signin );
        sharedPreferences_signin = PreferenceManager.getDefaultSharedPreferences ( MainActivity.this );
        String readPreference_signin = sharedPreferences_signin.getString ( "email_signin" , "ورود/عضویت" );
        txt_signin.setText ( readPreference_signin );
    }

    private void sign_in ( ) {

        final LinearLayout signout = findViewById ( R.id.signout );
        LinearLayout lin_signin = findViewById ( R.id.lin_signin );

        lin_signin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (txt_signin.getText ( ).equals ( "ورود/عضویت" )) {
                    Intent intent = new Intent ( MainActivity.this , SigninActivity.class );
                    startActivityForResult ( intent , 100 );
                } else {
                    if (signout.getVisibility ( ) == View.VISIBLE) {
                        signout.setVisibility ( View.GONE );
                    } else if (signout.getVisibility ( ) == View.GONE) {
                        signout.setVisibility ( View.VISIBLE );
                    }
                }
            }
        } );
    }

    private void signout ( ) {
        final DrawerLayout drawerLayout = findViewById ( R.id.drawerlayout_main_page );
        final LinearLayout signout = findViewById ( R.id.signout );

        signout.setOnClickListener ( new View.OnClickListener ( )

        {
            @Override
            public void onClick ( View v ) {
                sharedPreferences_signin = PreferenceManager.getDefaultSharedPreferences ( MainActivity.this );
                SharedPreferences.Editor editor = sharedPreferences_signin.edit ( );
                editor.putString ( "email_signin" , "ورود/عضویت" );
                editor.commit ( );
                signout.setVisibility ( View.GONE );
                drawerLayout.closeDrawer ( Gravity.RIGHT );
            }
        } );
        if (txt_signin.getText ( ).equals ( "" )) {
            txt_signin.setText ( "ورود/عضویت" );
        }
    }

    private void timer ( ) {
        final Handler handler = new Handler ( );
        final TextView edthourpromotion = findViewById ( R.id.hourPromotion );
        final TextView edtminpromotion = findViewById ( R.id.minPromotion );
        final TextView edtsecpromotion = findViewById ( R.id.secPromotion );

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
                                    edthourpromotion.setText ( "0" + hour );
                                } else {
                                    edthourpromotion.setText ( String.valueOf ( hour ) );
                                }
                                if (min < 10) {
                                    edtminpromotion.setText ( "0" + min );
                                } else {
                                    edtminpromotion.setText ( String.valueOf ( min ) );
                                }
                                if (sec < 10) {
                                    edtsecpromotion.setText ( "0" + sec );
                                } else {
                                    edtsecpromotion.setText ( String.valueOf ( sec ) );
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

    public void showpromotionproduct ( ) {


        try {


            JSONArray jsonArray = new JSONArray ( readpromotion );


            for (int i = 0; i < jsonArray.length ( ); i++) {

                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );




                String id = jsonObject.getString ( "productid" );
                String name = jsonObject.getString ( "name" );
                int price = jsonObject.getInt ( "price" );
                int oldprice = jsonObject.getInt ( "oldprice" );
                String img = jsonObject.getString ( "img" );
                String category = jsonObject.getString ( "category" );
                String type = jsonObject.getString ( "type" );

                Product_promotion_sample ( id , name , price , oldprice , img , category , type );
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    private void Product_promotion_sample ( String id , String promotiontitle , int promotionprice , int promotionoldprice , String img , String category , String type ) {

        LinearLayout lin_product_promotion;
        lin_product_promotion = findViewById ( R.id.lin_product_promotion );
        CustomProduct product = new CustomProduct ( this );

        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
        product.setLayoutParams ( layoutParams );
        lin_product_promotion.addView ( product );



        product.id=id;
        product.title.setText ( promotiontitle );
        product.price.setText ( String.valueOf ( promotionprice ) );
        product.oldprice.setText ( String.valueOf ( promotionoldprice ) );
        Picasso.with ( this ).load ( "http://www.alidoran.ir/picture/" + category + "/" + type + "/" + img ).into ( product.pic );
    }

    public void shownewsproduct ( ) {
        try {
            JSONArray jsonArray = new JSONArray ( readnews );
            for (int i = 0; i < jsonArray.length ( ); i++) {
                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );
                String id = jsonObject.getString ( "productid" );
                String name = jsonObject.getString ( "name" );
                int price = jsonObject.getInt ( "price" );
                int oldprice = jsonObject.getInt ( "oldprice" );
                String img = jsonObject.getString ( "img" );
                String category = jsonObject.getString ( "category" );
                String type = jsonObject.getString ( "type" );

                Product_news_sample ( id, name , price , oldprice , img , category , type );
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    private void Product_news_sample ( String id ,String promotiontitle , int promotionprice , int promotionoldprice , String img , String category , String type ) {

        LinearLayout lin_product_promotion;
        lin_product_promotion = findViewById ( R.id.lin_product_news );
        CustomProduct product = new CustomProduct ( this );

        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
        product.setLayoutParams ( layoutParams );
        lin_product_promotion.addView ( product );


        product.id=id;
        product.title.setText ( promotiontitle );
        product.price.setText ( String.valueOf ( promotionprice ) );
        product.oldprice.setText ( String.valueOf ( promotionoldprice ) );
        Picasso.with ( this ).load ( "http://www.alidoran.ir/picture/" + category + "/" + type + "/" + img ).into ( product.pic );


    }

    public void showbestsellproduct ( ) {


        try {


            JSONArray jsonArray = new JSONArray ( readbestsell );
            for (int i = 0; i < jsonArray.length ( ); i++) {

                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );

                String id = jsonObject.getString ( "productid" );
                String name = jsonObject.getString ( "name" );
                int price = jsonObject.getInt ( "price" );
                int oldprice = jsonObject.getInt ( "oldprice" );
                String img = jsonObject.getString ( "img" );
                String category = jsonObject.getString ( "category" );
                String type = jsonObject.getString ( "type" );

                Product_bestsell_sample ( id , name , price , oldprice , img , category , type );
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    private void Product_bestsell_sample ( String id , String title , int price , int oldprice , String img , String category , String type ) {

        LinearLayout lin_best_sell;
        lin_best_sell = findViewById ( R.id.lin_best_sell );
        CustomProduct product = new CustomProduct ( this );

        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
        product.setLayoutParams ( layoutParams );
        lin_best_sell.addView ( product );


        product.id=id;
        product.title.setText ( title );
        product.price.setText ( String.valueOf ( price ) );
        product.oldprice.setText ( String.valueOf ( oldprice ) );
        Picasso.with ( this ).load ( "http://www.alidoran.ir/picture/" + category + "/" + type + "/" + img ).into ( product.pic );
    }

    public void showonlythisstore ( ) {


        try {


            JSONArray jsonArray = new JSONArray ( thisstore );
            for (int i = 0; i < jsonArray.length ( ); i++) {
                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );

                String id = jsonObject.getString ( "productid" );
                String name = jsonObject.getString ( "name" );
                int price = jsonObject.getInt ( "price" );
                int oldprice = jsonObject.getInt ( "oldprice" );
                String img = jsonObject.getString ( "img" );
                String category = jsonObject.getString ( "category" );
                String type = jsonObject.getString ( "type" );

                Product_thisstore_sample ( id , name , price , oldprice , img , category , type );
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    private void Product_thisstore_sample ( String id , String title , int price , int oldprice , String img , String category , String type ) {

        LinearLayout lin_best_sell;
        lin_best_sell = findViewById ( R.id.lin_this_store );
        CustomProduct product = new CustomProduct ( this );

        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
        product.setLayoutParams ( layoutParams );
        lin_best_sell.addView ( product );

        product.id=id;
        product.title.setText ( title );
        product.price.setText ( String.valueOf ( price ) );
        product.oldprice.setText ( String.valueOf ( oldprice ) );
        Picasso.with ( this ).load ( "http://www.alidoran.ir/picture/" + category + "/" + type + "/" + img ).into ( product.pic );

    }

    public void showmainbanner ( ) {


        try {


            JSONArray jsonArray = new JSONArray ( mainbanner );
            for (int i = 0; i < jsonArray.length ( ); i++) {
                JSONObject jsonObject;
                jsonObject = jsonArray.getJSONObject ( i );

                String id = jsonObject.getString ( "id" );
                String name = jsonObject.getString ( "name" );


                Product_mainbanner_sample ( name , id );
            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    private void Product_mainbanner_sample ( String name , String id ) {


        ImageView imageslect = null;


        ImageView lin_mainbanner1 = findViewById ( R.id.lin_mainbanner1 );
        ImageView lin_mainbanner2 = findViewById ( R.id.lin_mainbanner2 );
        ImageView lin_mainbanner3 = findViewById ( R.id.lin_mainbanner3 );
        ImageView lin_mainbanner4 = findViewById ( R.id.lin_mainbanner4 );
        ImageView lin_mainbanner5 = findViewById ( R.id.lin_mainbanner5 );
        ImageView lin_mainbanner6 = findViewById ( R.id.lin_mainbanner6 );


        CustomBanner banner = new CustomBanner ( this );

        LinearLayout.LayoutParams layoutParams;
        layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
        banner.setLayoutParams ( layoutParams );


        if (id.equals ( "1" )) {
        imageslect=lin_mainbanner1;
        }

        if (id.equals ( "2" ) ) {
            imageslect=lin_mainbanner2;
        }

        if (id.equals ( "3" )) {
            imageslect=lin_mainbanner3;
        }

        if (id.equals ( "4" ) ) {
            imageslect=lin_mainbanner4;
        }

        if (id.equals ( "5" ) ) {
            imageslect=lin_mainbanner5;
        }

        if (id.equals ( "6" )) {
            imageslect=lin_mainbanner6;
        }



        Picasso.with ( this ).load ( "http://www.alidoran.ir/picture/" + "banner" + "/" + name ).into ( imageslect );
    }

    private void showAllProduct(){
        LinearLayout showAllProduct;
        showAllProduct = findViewById ( R.id.showAllProduct );
        showAllProduct.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {

                RecycleListProductActivity.data="";
                new ASyncRecycle ( "http://www.alidoran.ir/list_product_recycle.php" ).execute (  );

                final Timer timer = new Timer ( );
                timer.scheduleAtFixedRate ( new TimerTask ( ) {
                    @Override
                    public void run ( ) {
                        runOnUiThread ( new Runnable ( ) {
                            @Override
                            public void run ( ) {
                                if (!RecycleListProductActivity.data.equals ( "" )) {
                                    Intent intent = new Intent ( MainActivity.this , RecycleListProductActivity.class );
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
}


