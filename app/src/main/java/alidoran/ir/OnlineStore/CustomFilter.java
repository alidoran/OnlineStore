package alidoran.ir.OnlineStore;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomFilter extends RecyclerView.Adapter <CustomFilter.ViewHolder> {

    private List<FilterListItem> filteritem;
    private Context mycontext;

    CustomFilter(List<FilterListItem> filterlistitem , Context mycontext ){
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
    public void onBindViewHolder (@NonNull final ViewHolder viewHolder , int i ) {
        FilterListItem filterListItem = filteritem.get ( i );
        viewHolder.filter_txtview.setText ( filterListItem.getFilter_item () );

        viewHolder.lin_recycle.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Toast.makeText ( mycontext, viewHolder.getAdapterPosition() ,Toast.LENGTH_SHORT).show ();
            }
        } );
    }

    @Override
    public int getItemCount ( ) {
        return filteritem.size ();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView filter_txtview;
        LinearLayout filter_recycle;

        LinearLayout lin_recycle;

        ViewHolder ( @NonNull View itemView ) {
            super ( itemView );
            filter_txtview = itemView.findViewById ( R.id.txt_filter_title );
            filter_recycle = itemView.findViewById ( R.id.recycler_filter_title );

            lin_recycle = itemView.findViewById ( R.id.lin_filter );
        }
    }
}
