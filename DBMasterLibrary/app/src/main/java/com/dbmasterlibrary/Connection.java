package com.dbmasterlibrary;


import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Connection {
    // http://54.180.95.198:8081/
    // id pw
    public void request(String urlStr) {
        StringBuilder output = new StringBuilder();

        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(5000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);

                int resCode = conn.getResponseCode();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String line = null;
                while(true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    output.append(line).append("\n");
                }
                reader.close();
                conn.disconnect();
            }

        } catch (Exception ex) {
            Log.d("예외 발생 : ", "request: " + ex.toString());
        }
        Log.d("Log", "request: " + output.toString());

    }


}
