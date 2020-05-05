package com.dbmasterlibrary;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ConnectionServer {

    public static class HttpAsyncTask extends AsyncTask<String, Void, String> {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        public void connectionRequest(String userId, String userPw){
            OkHttpClient client = new OkHttpClient();

            String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
            String strApi = "/v1/connection/check";


            RequestBody formBody = new FormBody.Builder()
                    .add("name", userId)
                    .add("pw", userPw)
                    //.addEncoded("Id", userId)
                    //.addEncoded("Pw", userPw)
                    .build();

            Request request = new Request.Builder()
                    .url(baseUrl + strApi)
                    .post(formBody)
                    .build();

            client.newCall(request).enqueue(connectionRequestCallback);
        }

        private Callback connectionRequestCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TEST", "ERROR Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();

                Log.d("TEST", "responseDatae : " + responseData);
            }
        };

    }



}
