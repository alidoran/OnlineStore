package alidoran.ir.OnlineStore.Async;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import alidoran.ir.OnlineStore.view.SignupActivity;

public class ASyncInsertUser extends AsyncTask {

    public String link = "";
    public String email="";
    public String password="";
    public String phonenumber="";

    public ASyncInsertUser ( String link , String email , String password , String phonenumber ) {
        this.link = link;
        this.email=email;
        this.password=password;
        this.phonenumber=phonenumber;
    }

    @Override
    protected Object doInBackground ( Object[] objects ) {
        try {
            //user & pass convert to URL
            String data= URLEncoder.encode("email","utf8")+"="+URLEncoder.encode ( email , "utf8"  );
            data+="&"+URLEncoder.encode ( "pass","utf8" )+"="+URLEncoder.encode ( password , "utf8" );
            data+="&"+URLEncoder.encode ( "phonenumber", "utf8" )+"="+URLEncoder.encode ( phonenumber , "utf8" );

            //connect to server
            URL url = new URL ( link );
            URLConnection connection = url.openConnection ( );

            //send to server
            connection.setDoOutput ( true );
            OutputStreamWriter streamWriter =new OutputStreamWriter ( connection.getOutputStream () );
            streamWriter.write ( data );
            streamWriter.flush ();

            //read data
            BufferedReader reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream ( ) ) );
            StringBuilder builder = new StringBuilder ( );

            String line = null;

            while ((line = reader.readLine ( )) != null) {
                builder.append ( line );
            }
            SignupActivity.readserver_signup = builder.toString ( );
        } catch (Exception error) {
        }
        return "";
    }
}
