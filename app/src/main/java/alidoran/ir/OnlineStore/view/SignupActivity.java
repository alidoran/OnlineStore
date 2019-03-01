package alidoran.ir.OnlineStore.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import alidoran.ir.OnlineStore.Async.ASyncInsertUser;
import alidoran.ir.OnlineStore.R;

public class SignupActivity extends AppCompatActivity {

    private EditText edt_signup_email;
    private EditText edt_signup_pass;
    private EditText edt_signup_repass;
    private CheckBox chkbox_signup_showpass;
    public static String readserver_signup = "";
    private EditText edt_signup_phonenumber;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_signup );

        signup ( );
        chkbox_password ( );
    }

    public void chkbox_password ( ) {
        edt_signup_pass = findViewById ( R.id.etxt_signup_pass );
        edt_signup_repass = findViewById ( R.id.etxt_signup_repass );
        chkbox_signup_showpass = findViewById ( R.id.chkbox_signup_showpass );

        chkbox_signup_showpass.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                if (chkbox_signup_showpass.isChecked ( )) {
                    edt_signup_pass.setInputType ( InputType.TYPE_CLASS_TEXT );
                    edt_signup_pass.setSelection ( edt_signup_pass.getText ( ).length ( ) );
                    edt_signup_repass.setVisibility ( View.GONE );
                } else if (chkbox_signup_showpass.isChecked ( ) == false) {
                    edt_signup_pass.setInputType ( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                    edt_signup_pass.setSelection ( edt_signup_pass.getText ( ).length ( ) );
                    edt_signup_repass.setVisibility ( View.VISIBLE );
                }
            }
        } );
    }

    public void signup ( ) {
        edt_signup_email = findViewById ( R.id.etxt_signup_email );
        edt_signup_pass = findViewById ( R.id.etxt_signup_pass );
        edt_signup_repass = findViewById ( R.id.etxt_signup_repass );
        edt_signup_phonenumber = findViewById ( R.id.etxt_signup_phonenumber );
        LinearLayout lin_signup = findViewById ( R.id.lin_signup );


        lin_signup.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick ( View v ) {
                readserver_signup = "";
                final String email = edt_signup_email.getText ( ).toString ( );
                String pass = edt_signup_pass.getText ( ).toString ( );
                String repass = edt_signup_repass.getText ( ).toString ( );
                String phonenumber = edt_signup_phonenumber.getText ( ).toString ( );

                if (pass.equals ( repass ) || (chkbox_signup_showpass.isChecked ( ))) {

                    new ASyncInsertUser( "http://www.alidoran.ir/InsertUser.php" , email , pass , phonenumber ).execute ( );


                    final ProgressDialog dialog = new ProgressDialog ( SignupActivity.this );
                    dialog.setMessage ( "منتظر بمانید" );
                    dialog.show ( );

                    final Timer timer = new Timer ( );
                    timer.scheduleAtFixedRate ( new TimerTask ( ) {
                        @Override
                        public void run ( ) {
                            runOnUiThread ( new Runnable ( ) {
                                @Override
                                public void run ( ) {
                                    if (!readserver_signup.equals ( "" )) {
                                        dialog.cancel ( );
                                        if (readserver_signup.equals ( "notok" )) {
                                            Toast.makeText ( SignupActivity.this , "ارتباط با سرور برقرار نشد" , Toast.LENGTH_SHORT ).show ( );
                                            timer.cancel ( );
                                        }else if(readserver_signup.equals ( "exist" )){
                                            Toast.makeText ( SignupActivity.this , "این آدرس ایمیل تکراری است" , Toast.LENGTH_SHORT ).show ( );
                                            timer.cancel ( );
                                        }
                                        else {
                                            Intent intent = new Intent ( MainActivity.context , SigninActivity.class );
                                            intent.putExtra ( "signup_ok" , readserver_signup );
                                            setResult ( 102 , intent );
                                            timer.cancel ( );
                                            finish ( );
                                        }
                                    }
                                }
                            } );
                        }
                    } , 1 , 1000 );
                } else {
                    Toast.makeText ( SignupActivity.this , "کلمه عبور با تکرار مطابقت ندارد" , Toast.LENGTH_LONG ).show ( );
                }
            }
        } );
    }
}
