package alidoran.ir.OnlineStore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductListRecycleAdapter extends RecyclerView.Adapter<viewHolder> {

    ArrayList<RecycleProduct> recycleProducts = new ArrayList <> (  );
    public ProductListRecycleAdapter(ArrayList<RecycleProduct> recycleProducts){
        this.recycleProducts=recycleProducts;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup , int i ) {
        View view=LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.recycle_product_list , viewGroup , false );
        return new viewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull viewHolder viewHolder , int i ) {
        RecycleProduct recycleProduct = recycleProducts.get ( i );


    }

    @Override
    public int getItemCount ( ) {
        return recycleProducts.size ();
    }
}
class viewHolder extends RecyclerView.ViewHolder{

    public ImageView recycle_img;
    public TextView recycle_txt;

    public viewHolder ( @NonNull View itemView ) {
        super ( itemView );
        recycle_img = itemView.findViewById ( R.id.list_product_img_recycle );
        recycle_img = itemView.findViewById ( R.id.list_product_txt_recycle );
    }
}
