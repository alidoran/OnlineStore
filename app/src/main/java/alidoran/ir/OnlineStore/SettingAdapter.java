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

public class SettingAdapter extends ArrayAdapter {
    public int recuorceid;
    public Activity context;
    public ArrayList<SettingListitem> object;


    public SettingAdapter ( @NonNull Activity context , int resource , ArrayList objects ) {
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

        ImageView img=view.findViewById ( R.id.img_setting_list );
        TextView txt=view.findViewById ( R.id.txt_setting_list );

        SettingListitem settingListitem =object.get(position);
        txt.setText ( settingListitem.title );
        txt.setTextColor ( Color.parseColor ( "#000000" ) );
        txt.setTypeface(null, Typeface.BOLD);
        img.setImageResource ( settingListitem.img );

        return view;

    }
}
