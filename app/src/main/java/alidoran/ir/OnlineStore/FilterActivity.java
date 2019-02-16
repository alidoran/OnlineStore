package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

            }


        } catch (JSONException e) {
            e.printStackTrace ( );
        }


    }
}
