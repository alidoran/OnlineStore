package alidoran.ir.OnlineStore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RateStar extends LinearLayout {
    TextView titleProgress;
    ProgressBar progressBar;

    public RateStar ( Context context ,AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init(context);
    }
    public RateStar ( Context context , AttributeSet attrs ) {
        super ( context , attrs );
        init(context);
    }
    public RateStar ( Context context ) {
        super ( context );
        init(context);
    }

    private void init ( Context context ) {
        LayoutInflater inflater = (LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.activity_rate_star , this ,true );
        progressBar=view.findViewById ( R.id.progressbar );
        titleProgress=view.findViewById ( R.id.titleProgress );
    }
}



