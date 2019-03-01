package alidoran.ir.OnlineStore.model;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import alidoran.ir.OnlineStore.R;

public class CustomBanner extends LinearLayout {


    public static ImageView pic;

    public CustomBanner ( Context context ) {
        super ( context );
        init ( context );
    }
    public CustomBanner ( Context context , @Nullable AttributeSet attrs ) {
        super ( context , attrs );
        init ( context );
    }
    public CustomBanner ( Context context , @Nullable AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init ( context );
    }
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.banner , this ,true );

        pic = view.findViewById ( R.id.bannerimg );

    }
}