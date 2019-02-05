package alidoran.ir.OnlineStore;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ASyncRecycle extends AsyncTask {

    public String link = "";


    public ASyncRecycle ( String link  ) {
        this.link = link;

    }


    @Override
    protected Object doInBackground ( Object[] objects ) {
        try {



            //connect to server
            URL url = new URL ( link );
            URLConnection connection = url.openConnection ( );


            //read data
            BufferedReader reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream ( ) ) );
            StringBuilder builder = new StringBuilder ( );

            String line = null;

            while ((line = reader.readLine ( )) != null) {
                builder.append ( line );
            }
            ListProductActivity.data = builder.toString ( );

        } catch (Exception error) {
        }
        return "";
    }
}
