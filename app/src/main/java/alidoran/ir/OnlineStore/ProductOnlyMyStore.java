package alidoran.ir.OnlineStore;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class ProductOnlyMyStore extends LinearLayout {

    public ProductOnlyMyStore ( Context context ) {
        super ( context );
        init ( context );
    }
    public ProductOnlyMyStore ( Context context , @Nullable AttributeSet attrs ) {
        super ( context , attrs );
        init ( context );
    }
    public ProductOnlyMyStore ( Context context , @Nullable AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init ( context );
    }
    private void init(Context context){
        LayoutInflater inflater = ( LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        inflater.inflate ( R.layout.product_onlymystore , this,true );
    }
}
