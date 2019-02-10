package alidoran.ir.OnlineStore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomListProduct extends LinearLayout {
    public static String id_custom;
    public static TextView txt_cutom_name;
    public static TextView txt_custom_desc;
    public static TextView txt_custom_price;
    public ImageView img_custom;




    public CustomListProduct ( Context context ) {
        super ( context );
        init ( context );
    }

    public CustomListProduct ( Context context , AttributeSet attrs ) {
        super ( context , attrs );
        init ( context );
    }

    public CustomListProduct ( Context context , AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init ( context );
    }

    private void init ( Context context ) {
        LayoutInflater inflater = ( LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.custom_list_product , this , true );


        txt_cutom_name=view.findViewById ( R.id.txt_custom_name );
        txt_custom_desc = view.findViewById ( R.id.txt_custom_desc );
        txt_custom_price = view.findViewById ( R.id.txt_custom_price );
        img_custom = view.findViewById ( R.id.img_custom );

    }
}
