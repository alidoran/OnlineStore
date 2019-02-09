package alidoran.ir.OnlineStore;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleAdapterListProduct extends RecyclerView.Adapter<viewHolder> {

    ArrayList<RecycleProduct> recycleProducts = new ArrayList <> (  );
    public RecycleAdapterListProduct ( ArrayList<RecycleProduct> recycleProducts){
        this.recycleProducts=recycleProducts;
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder ( @NonNull ViewGroup viewGroup , int i ) {
        View view=LayoutInflater.from ( viewGroup.getContext () ).inflate ( R.layout.recycle_product_list , viewGroup , false );
        return new viewHolder ( view );
    }

    @Override
    public void onBindViewHolder ( @NonNull viewHolder viewholder , int i ) {
        RecycleProduct recycleProduct = recycleProducts.get ( i );
        viewholder.recycle_txt.setText ( recycleProduct.recycle_name );
        Toast.makeText ( MainActivity.context,"http://www.alidoran.ir/picture/type/" + recycleProduct.recycle_img,Toast.LENGTH_SHORT ).show ();
        Picasso.with ( MainActivity.context ).load ( "http://www.alidoran.ir/picture/type/" + recycleProduct.recycle_img ).fit ().into ( viewholder.recycle_img );



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
        recycle_img = itemView.findViewById ( R.id.recycle_list_product_img );
        recycle_txt = itemView.findViewById ( R.id.recycle_list_product_txt );
    }
}