package alidoran.ir.OnlineStore;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class ASyncFilter extends AsyncTask {
    public String link = "";
    String id;

    public ASyncFilter ( String link , String id ) {
        this.link = link;
        this.id = id;
    }


    @Override
    protected Object doInBackground ( Object[] objects ) {
        FilterActivity.data = "";
        try {
            //user & pass convert to URL
            String data = URLEncoder.encode ( "id" , "utf8" ) + "=" + URLEncoder.encode ( id , "utf8" );

            //connect to server
            URL url = new URL ( link );
            URLConnection connection = url.openConnection ( );

            //send to server
            connection.setDoOutput ( true );
            OutputStreamWriter streamWriter = new OutputStreamWriter ( connection.getOutputStream ( ) );
            streamWriter.write ( data );
            streamWriter.flush ( );

            //read data
            BufferedReader reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream ( ) ) );
            StringBuilder builder = new StringBuilder ( );

            String line = null;

            while ((line = reader.readLine ( )) != null) {
                builder.append ( line );
            }
            FilterActivity.data = builder.toString ( );

        } catch (Exception error) {
        }
        return "";
    }
}