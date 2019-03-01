package alidoran.ir.OnlineStore.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/alidoran/ir/OnlineStore/FilterActivity.java
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
=======
import android.widget.LinearLayout;
>>>>>>>     2/12/2019 8:20AM:app/src/main/java/alidoran/ir/OnlineStore/view/FilterActivity.java

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

<<<<<<< HEAD:app/src/main/java/alidoran/ir/OnlineStore/FilterActivity.java
import java.util.ArrayList;
import java.util.List;
=======
import alidoran.ir.OnlineStore.R;
import alidoran.ir.OnlineStore.model.CustomFilter;
>>>>>>>     2/12/2019 8:20AM:app/src/main/java/alidoran/ir/OnlineStore/view/FilterActivity.java

public class FilterActivity extends AppCompatActivity {
    public static String data = "";


    List <FilterListItem> filterListItemList = new ArrayList <> ( );
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_filter );

        recyclerView = findViewById ( R.id.recycler_filter_title );
        adapter = new CustomFilter ( filterListItemList , this );
        recyclerView.setLayoutManager ( new LinearLayoutManager ( MainActivity.context ) );
        recyclerView.setAdapter ( adapter );
        setdata ( );

        //lin_filter_title =findViewById ( R.id.lin_filter_title );


    }

    private void setdata ( ) {
        try {
            JSONObject jsonObject = new JSONObject ( data );
            JSONArray json_title = jsonObject.getJSONArray ( "title" );

            for (int i = 0; i < json_title.length ( ); i++) {
                String title = json_title.getString ( i );

                FilterListItem filterListItem = new FilterListItem ();
                filterListItemList.add ( filterListItem );
                filterListItem.setFilter_item ( title );
                adapter.notifyDataSetChanged ();

<<<<<<< HEAD:app/src/main/java/alidoran/ir/OnlineStore/FilterActivity.java
=======

                CustomFilter customFilter = new CustomFilter ( MainActivity.context );
                //customFilter.txt_filter_title.setText ( title );

                layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.WRAP_CONTENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                lin_filter_title.setLayoutParams ( layoutParams );
                lin_filter_title.addView ( customFilter );
>>>>>>>     2/12/2019 8:20AM:app/src/main/java/alidoran/ir/OnlineStore/view/FilterActivity.java
            }


        } catch (JSONException e) {
            e.printStackTrace ( );
        }


    }
}
