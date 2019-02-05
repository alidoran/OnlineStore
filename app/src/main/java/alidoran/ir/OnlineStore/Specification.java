package alidoran.ir.OnlineStore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Specification extends LinearLayout {
    public static TextView txt_spec_title;
    public static TextView txt_spec_value;



    public Specification ( Context context ) {
        super ( context );
        init ( context );
    }

    public Specification ( Context context , AttributeSet attrs ) {
        super ( context , attrs );
        init ( context );
    }

    public Specification ( Context context , AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init ( context );
    }

    private void init ( Context context ) {
        LayoutInflater inflater = ( LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.specification , this , true );


        txt_spec_title=view.findViewById ( R.id.txt_spec_title );
        txt_spec_value = view.findViewById ( R.id.txt_spec_value );

    }
}

