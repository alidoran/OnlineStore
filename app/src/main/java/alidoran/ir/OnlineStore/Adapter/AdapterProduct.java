package alidoran.ir.OnlineStore.Adapter;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import alidoran.ir.OnlineStore.contorol.ProductListItem;
import alidoran.ir.OnlineStore.R;

public class AdapterProduct extends ArrayAdapter {
    public int recuorceid;
    public Activity context;
    public ArrayList<ProductListItem> object;


    public AdapterProduct ( @NonNull Activity context , int resource , ArrayList objects ) {
        super ( context , resource , objects );

        this.recuorceid=resource;
        this.context=context;
        this.object=objects;
    }

    @NonNull
    @Override
    public View getView ( int position , @Nullable View convertView , @NonNull ViewGroup parent ) {
        View view;
        view=this.context.getLayoutInflater().inflate (this.recuorceid,null);

        ImageView img=view.findViewById ( R.id.img_listview_product );
        TextView txt=view.findViewById ( R.id.txt_listview_product );

        ProductListItem productListitem =object.get(position);
        txt.setText ( productListitem.title );
        txt.setTextColor ( Color.parseColor ( "#000000" ) );
        txt.setTypeface(null, Typeface.BOLD);
        img.setImageResource ( productListitem.img );

        return view;

    }
}
