package alidoran.ir.OnlineStore;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Comment extends LinearLayout {
    public TextView txt_comment_user_name;
    public TextView txt_comment_like_count;
    public TextView txt_comment_dislike_count;
    public TextView txt_comment_title;
    public TextView txt_comment;
    public TextView txt_comment_strong;
    public TextView txt_comment_week;
    public String userid;
    public LinearLayout lin_comment_rate;
    public LinearLayout lin_comment_like;
    public LinearLayout lin_comment_dislike;
    public static String test="";



    public Comment ( Context context ) {
        super ( context );
        init (context);
    }
    public Comment ( Context context , AttributeSet attrs ) {
        super ( context , attrs );
        init (context);
    }
    public Comment ( Context context , AttributeSet attrs , int defStyleAttr ) {
        super ( context , attrs , defStyleAttr );
        init (context);
    }

    private void init ( Context context ) {
        LayoutInflater inflater = ( LayoutInflater ) context.getSystemService ( context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate ( R.layout.comment , this , true );

        txt_comment_user_name  = view.findViewById ( R.id.txt_comment_user_name );
        txt_comment_like_count= view.findViewById ( R.id.txt_comment_like_count );
        txt_comment_dislike_count = view.findViewById ( R.id.txt_comment_dislike_count );
        txt_comment_title = view.findViewById ( R.id.txt_comment_title );
        txt_comment = view.findViewById ( R.id.txt_comment );
        lin_comment_rate= view.findViewById ( R.id.lin_comment_rate );
        txt_comment_strong = view.findViewById ( R.id.txt_comment_strong );
        txt_comment_week = view.findViewById ( R.id.txt_comment_week );
        lin_comment_like = view.findViewById ( R.id.lin_comment_like );
        lin_comment_dislike = view.findViewById ( R.id.lin_comment_dislike );

        lin_comment_like.setOnClickListener ( new OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Toast.makeText ( MainActivity.context,userid+"",Toast.LENGTH_SHORT ).show ();
            }
        } );






    }

}
