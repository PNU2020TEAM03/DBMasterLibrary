package com.dbmasterlibrary;


import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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

        public void connectionRequest(String userId, String userPw) throws JSONException {

            //OkHttpClient client = new OkHttpClient();

            //서버 주소
            String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
            String strApi = "/v1/connection/check";

            //서버에 전송할 json객체 생성
            final JSONObject object = new JSONObject();
            object.put("name", userId);
            object.put("pw", userPw);


            Request request = new Request.Builder()
                    .url(baseUrl + strApi)
                    .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                    .build();

            client.newCall(request).enqueue(connectionRequestCallback);
        }

        private Callback connectionRequestCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("TEST1", "ERROR Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseData = response.body().string();
                Log.d("TEST1", "responseData : " + responseData);

                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    Log.d("TEST2", "jsonObject : " + jsonObject);
                    //Log.d("TEST3", "jsonObject : " + jsonObject.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        };

    }

}
