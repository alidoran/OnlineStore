package alidoran.ir.OnlineStore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommentActivity extends AppCompatActivity {

    public static String data = "";

    LinearLayout lin_comment;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_comment );

        filldata ();
    }
    public void filldata(){
        lin_comment = findViewById ( R.id.lin_comment );

        String titles = "";
        String comments = "";
        String strongs = "";
        String weeks = "";
        String likes = "";
        String dislikes = "";
        String userName = "";
        String rate_title="";
        String rate="";
        String user_id="";
        JSONArray rate_array;

        try {
            //receive from json data
            JSONObject jsonObject = new JSONObject ( data );
            JSONArray comment_rate_title = jsonObject.getJSONArray ( "rate_title" );
            JSONArray comment_title = jsonObject.getJSONArray ( "title" );
            JSONArray comment_comment = jsonObject.getJSONArray ( "comment" );
            JSONArray comment_strong = jsonObject.getJSONArray ( "strong" );
            JSONArray comment_week = jsonObject.getJSONArray ( "week" );
            JSONArray comment_like = jsonObject.getJSONArray ( "like" );
            JSONArray comment_dislike = jsonObject.getJSONArray ( "dislike" );
            JSONArray comment_user_name = jsonObject.getJSONArray ( "user" );
            JSONArray comment_rate = jsonObject.getJSONArray ( "rate" );
            JSONArray comment_user_id = jsonObject.getJSONArray ( "userid" );

            //receive from array to string
            for (int j = 0; j < comment_title.length ( ); j++) {
                titles = comment_title.getString ( j );
                comments = comment_comment.getString ( j );
                strongs = comment_strong.getString ( j );
                weeks = comment_week.getString ( j );
                likes = comment_like.getString ( j );
                dislikes = comment_dislike.getString ( j );
                userName = comment_user_name.getString ( j );
                rate_array = comment_rate.getJSONArray ( j );
                user_id = comment_user_id.getString ( j );



                //create custom comment
                CustomComment comment = new CustomComment ( MainActivity.context );
                comment.txt_comment_title.setText ( titles );
                comment.txt_comment.setText ( comments );
                comment.txt_comment_like_count.setText ( likes );
                comment.txt_comment_dislike_count.setText ( dislikes );
                comment.txt_comment_user_name.setText ( userName );
                comment.txt_comment_strong.setText ( strongs );
                comment.txt_comment_week.setText ( weeks );
                comment.userid = user_id;

                //create rate
                for (int k=0;k<rate_array.length ();k++){
                    rate = rate_array.getString ( k );
                    rate_title = comment_rate_title.getString ( k );
                    CustomRateStar rateStar = new CustomRateStar ( MainActivity.context );
                    rateStar.progressBar.setProgress ( Integer.valueOf ( rate ) );
                    rateStar.titleProgress.setText ( rate_title );
                    comment.lin_comment_rate.addView ( rateStar );
                }
                //build activity
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams ( LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT );
                comment.setLayoutParams ( layoutParams );
                lin_comment.addView ( comment );


            }
        } catch (JSONException e) {
            e.printStackTrace ( );
        }
    }

    private void like(){

    }
}
