package alidoran.ir.OnlineStore;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CustomProduct extends LinearLayout {


    public String id;
    public  ImageView pic;
    public  TextView title;
    public  TextView price;
    public  TextView oldprice;
    public LinearLayout productlayout;



    public CustomProduct ( Context context ) {
        super ( context );
        init ( context );
    }
    public CustomProduct ( Context context , @Nullable AttributeSet attrs ) {
        super ( context , attrs );
        init ( context );
    }
    public CustomProduct ( Context context , @Nullable AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init ( context );
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.product , this ,true );

        pic = view.findViewById ( R.id.imgproduct );
        title = view.findViewById ( R.id.titleproduct );
        price = view.findViewById ( R.id.priceproduct );
        oldprice = view.findViewById ( R.id.oldpriceproduct );
        productlayout = view.findViewById ( R.id.Productlayout );

        productlayout.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( MainActivity.context,WaitActivity.class );
                intent.putExtra ( "id",id );
                MainActivity.context.startActivity ( intent );
            }
        } );



    }
}
