package alidoran.ir.OnlineStore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class CustomFilter extends LinearLayout {

    public static TextView txt_filter_title;
    public boolean active_filter = false;
    LinearLayout lin_filter;



    public CustomFilter ( Context context , AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init(context);
    }
    public CustomFilter ( Context context , AttributeSet attrs ) {
        super ( context , attrs );
        init(context);
    }
    public CustomFilter ( Context context ) {
        super ( context );
        init(context);
    }

    private void init ( Context context ) {
        LayoutInflater inflater = (LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.activity_filter , this ,true );

        txt_filter_title = view.findViewById ( R.id.lin_filter_title );
        lin_filter = view.findViewById ( R.id.lin_filter );



    }
}
