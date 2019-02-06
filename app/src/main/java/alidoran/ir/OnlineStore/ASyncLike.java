//package alidoran.ir.OnlineStore;
//
//import android.os.AsyncTask;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.URL;
//import java.net.URLConnection;
//import java.net.URLEncoder;
//
//public class ASyncLike extends AsyncTask {
//
//    public String link = "";
//    public String user_id="";
//
//    public ASyncLike ( String link , String user_id ) {
//        this.link = link;
//        this.user_id=user_id;
//    }
//
//
//    @Override
//    protected Object doInBackground ( Object[] objects ) {
//        try {
//            //user & pass convert to URL
//            String data= URLEncoder.encode("userid","utf8")+"="+URLEncoder.encode ( user_id , "utf8"  );
//
//            //connect to server
//            URL url = new URL ( link );
//            URLConnection connection = url.openConnection ( );
//
//            //send to server
//            connection.setDoOutput ( true );
//            OutputStreamWriter streamWriter =new OutputStreamWriter ( connection.getOutputStream () );
//            streamWriter.write ( data );
//            streamWriter.flush ();
//
//            //read data
//            BufferedReader reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream ( ) ) );
//            StringBuilder builder = new StringBuilder ( );
//
//            String line = null;
//
//            while ((line = reader.readLine ( )) != null) {
//                builder.append ( line );
//            }
//            Comment.test = builder.toString ( );
//
//        } catch (Exception error) {
//        }
//        return "";
//    }
//}
