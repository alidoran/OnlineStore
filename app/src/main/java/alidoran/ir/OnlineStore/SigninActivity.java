package alidoran.ir.OnlineStore;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class SigninActivity extends AppCompatActivity {

    public static String readserver_signin = "";
    SharedPreferences sharedPreferences_signup;

    @Override
    protected void onActivityResult ( int requestCode , int resultCode , @Nullable Intent data ) {
        super.onActivityResult ( requestCode , resultCode , data );

        if (resultCode == 102) {
            String insert_ok = data.getExtras ( ).getString ( "signup_ok" );

            Intent intent = new Intent ( MainActivity.context , MainActivity.class );
            intent.putExtra ( "email" , insert_ok );
            setResult ( 100 , intent );
            finish ();


        }
    }

    EditText etxt_signin_email;
    EditText etxt_signin_pass;
    CheckBox chkbox_signin_showpass;

    @Override
    protected void onCreate ( @Nullable Bundle savedInstanceState ) {
        setContentView ( R.layout.activity_signin );
        super.onCreate ( savedInstanceState );

        etxt_signin_email = findViewById ( R.id.etxt_signin_email );
        etxt_signin_pass = findViewById ( R.id.etxt_signin_pass );

        signup_intent ( );
        user_signin ( );
        show_pass ( );
    }

    private void signup_intent ( ) {
        TextView txt_signup_link = findViewById ( R.id.txt_signup_link );
        txt_signup_link.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                Intent intent = new Intent ( SigninActivity.this , SignupActivity.class );
                startActivityForResult ( intent , 102 );
            }
        } );
    }

    private void user_signin ( ) {
        LinearLayout user_signin = findViewById ( R.id.enterweb );
        user_signin.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                readserver_signin = "";
                String email = etxt_signin_email.getEditableText ( ).toString ( );
                String password = etxt_signin_pass.getText ( ).toString ( );
                ASyncConnect aSyncConnect = new ASyncConnect ( "http://www.alidoran.ir/androiddigi.php" , email , password );
                aSyncConnect.execute ( );


                final ProgressDialog dialog = new ProgressDialog ( SigninActivity.this );
                dialog.setMessage ( "منتظر بمانید" );
                dialog.show ( );

                final Timer timer = new Timer ( );
                timer.scheduleAtFixedRate ( new TimerTask ( ) {
                    @Override
                    public void run ( ) {
                        runOnUiThread ( new Runnable ( ) {
                            @Override
                            public void run ( ) {
                                if (!readserver_signin.equals ( "" )) {
                                    dialog.cancel ( );
                                    if (readserver_signin.equals ( "not exist" )) {
                                        Toast.makeText ( SigninActivity.this , "اطلاعات اشتباه است" , Toast.LENGTH_LONG ).show ( );
                                        timer.cancel ( );
                                    } else {
                                        Toast.makeText ( SigninActivity.this , readserver_signin , Toast.LENGTH_LONG ).show ( );
                                        Intent intent = new Intent ( MainActivity.context , MainActivity.class );
                                        intent.putExtra ( "email" , readserver_signin );
                                        setResult ( 100 , intent );
                                        timer.cancel ( );
                                        finish ( );
                                    }
                                }
                            }
                        } );
                    }
                } , 1 , 1000 );
            }
        } );


    }

    private void show_pass ( ) {

        chkbox_signin_showpass = findViewById ( R.id.chkbox_signin_showpass );
        chkbox_signin_showpass.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (chkbox_signin_showpass.isChecked ( )) {
                    etxt_signin_pass.setInputType ( InputType.TYPE_CLASS_TEXT );
                    etxt_signin_pass.setSelection ( etxt_signin_pass.getText ( ).length ( ) );
                } else if (chkbox_signin_showpass.isChecked ( ) == false) {
                    etxt_signin_pass.setInputType ( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                    etxt_signin_pass.setSelection ( etxt_signin_pass.getText ( ).length ( ) );
                }
            }
        } );
    }

}
