package alidoran.ir.OnlineStore;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class CustomFilter extends RecyclerView.Adapter <CustomFilter.ViewHolder> {

    List<FilterListItem> filteritem;
    Context mycontext;

    public CustomFilter(List<FilterListItem> filterlistitem , Context mycontext ){
        this.filteritem = filterlistitem;
        this.mycontext = mycontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup , int i ) {
        View view = LayoutInflater.from ( mycontext ).inflate ( R.layout.filter , viewGroup , false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder ( @NonNull ViewHolder viewHolder , int i ) {
        FilterListItem filterListItem = filteritem.get ( i );
        viewHolder.filter_txtview.setText ( filterListItem.getFilter_item () );
    }

    @Override
    public int getItemCount ( ) {
        return filteritem.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView filter_txtview;
        LinearLayout filter_recycle;

        public ViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            filter_txtview = itemView.findViewById ( R.id.txt_filter_title );
            filter_recycle = itemView.findViewById ( R.id.recycler_filter_title );
        }
    }
}
