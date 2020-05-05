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
            OkHttpClient client = new OkHttpClient();

            String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
            String strApi = "/v1/connection/check";
/*
            final MediaType JSON
                    = MediaType.parse("application/json; charset=utf-8");

            JSONObject json = new JSONObject();
            json.put("name", userId);
            json.put("pw", userPw);

            RequestBody formBody = RequestBody.create(JSON, json.toString());
*/

            final JSONObject object = new JSONObject();
            object.put("name", "wooyoung");
            object.put("pw", "1q2w3e4r!");

            /*
            RequestBody formBody = new FormBody.Builder()
                    .add("name", "wooyoung")
                    .add("pw", "1q2w3e4r!")
                    .build();
            */

            Request request = new Request.Builder()
                    .url(baseUrl + strApi)
                    //.url("https://api.androidhive.info/contacts/")
                    //.post(formBody)
                    //status":415,"error":"Unsupported Media Type
                    //Content type 'application/x-www-form-urlencoded;charset=UTF-8'
                    //오류 해결을 위해 "application/json"으로 변경
                    //.post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(formBody)))
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
                //Log.d("TEST2", "responseData : " + responseData);

                // json 형식으로 받기 테스트 코드
                // json 테스트 사이트 https://api.androidhive.info/contacts/
                try {
                    JSONObject jsonObject = new JSONObject(responseData);
                    //Log.d("TEST3", "responseData : " + jsonObject);

                    Log.d("TEST4", "responseData : " + jsonObject.toString());
                    /*
                    Log.d("TEST5", "responseData : " + jsonObject.getString("contacts"));
                    JSONArray jsonarray = jsonObject.getJSONArray("contacts");
                    for(int i = 0; i < jsonarray.length(); i++) {
                        JSONObject contactObject = jsonarray.getJSONObject(i);
                        Log.d("json", "id : " + contactObject.getString("id"));
                        Log.d("json", "name : " + contactObject.getString("name"));
                        Log.d("json", "email : " + contactObject.getString("email"));
                    }*/

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Log.d("TEST6", "responseData : " + responseData);
            }
        };

    }



}
