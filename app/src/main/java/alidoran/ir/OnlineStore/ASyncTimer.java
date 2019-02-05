package alidoran.ir.OnlineStore;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ASyncTimer extends AsyncTask {

    String link;

    ASyncTimer ( String link ){
        this.link=link;
    }
    @Override
    protected Object doInBackground ( Object[] objects ) {
        try {
            URL url = new URL ( link );
            URLConnection connection = url.openConnection ( );

            BufferedReader reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream ( ) ) );
            StringBuilder builder = new StringBuilder ( );

            String line;

            while ((line = reader.readLine ( )) != null) {
                builder.append ( line );
            }
            MainActivity.timer = builder.toString ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;
    }
}
