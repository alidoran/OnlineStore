package alidoran.ir.OnlineStore;

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

public class BuyMenuAdapter extends ArrayAdapter {

    public int recuorceid;
    public Activity context;
    public ArrayList<BuyMenuListitem> object;


    public BuyMenuAdapter ( @NonNull Activity context , int resource , ArrayList objects ) {
        super ( context , resource , objects );

        this.recuorceid=resource;
        this.context=context;
        this.object=objects;
         }

    @NonNull
    @Override
    public View getView ( int position , @Nullable View convertView , @NonNull ViewGroup parent ) {
        View view=convertView;
        view=this.context.getLayoutInflater().inflate (this.recuorceid,null);

        ImageView img=view.findViewById ( R.id.img_listview_navigation );
        TextView txt=view.findViewById ( R.id.txt_listview_navigation );

        BuyMenuListitem buyMenuListitem =object.get(position);
        txt.setText ( buyMenuListitem.title );
        txt.setTextColor ( Color.parseColor ( "#000000" ) );
        txt.setTypeface(null, Typeface.BOLD);
        img.setImageResource ( buyMenuListitem.img );

        return view;

    }
}
