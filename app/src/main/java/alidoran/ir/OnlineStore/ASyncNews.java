package alidoran.ir.OnlineStore;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ASyncNews extends AsyncTask {
    public String link="";

    public ASyncNews(String link){
        this.link=link;
    }

    @Override
    protected Object doInBackground ( Object[] objects ) {
        try {
            URL url=new URL ( link );
            URLConnection connection = url.openConnection ();

            BufferedReader reader =new BufferedReader ( new InputStreamReader ( connection.getInputStream () ) );
            StringBuilder builder=new StringBuilder (  );

            String line = null;

            while ((line=reader.readLine ())!=null){
                builder.append ( line );
            }

            MainActivity.readnews = builder.toString ();


        } catch (MalformedURLException e) {
            e.printStackTrace ( );
        } catch (IOException e) {
            e.printStackTrace ( );
        }



        return null;
    }
}