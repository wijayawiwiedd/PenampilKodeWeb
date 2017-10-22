package com.example.wijaya.penampilkodeweb;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Wijaya on 22-Oct-17.
 */

public class ConnectInternetTask extends AsyncTask<String,Void,String> {

    Context ctx;

    ConnectInternetTask(Context ct) { ctx = ct; }

    @Override
    protected String doInBackground(String... strings) {
        String s1 = strings[0];
        InputStream in;

        try {
            URL myURL = new URL(s1);
            HttpURLConnection myConn = (HttpURLConnection) myURL.openConnection();
            myConn.setReadTimeout(1000);
            myConn.setConnectTimeout(2000);
            myConn.setRequestMethod("GET");
            myConn.connect();

            in = myConn.getInputStream();

            BufferedReader myBuf = new BufferedReader(new InputStreamReader(in));
            StringBuilder st = new StringBuilder();
            String line="";

            while ((line = myBuf.readLine()) != null){
                st.append(line+" \n");
            }

            myBuf.close();
            in.close();

            return st.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {MainActivity.mytext.setText(s);}
}
