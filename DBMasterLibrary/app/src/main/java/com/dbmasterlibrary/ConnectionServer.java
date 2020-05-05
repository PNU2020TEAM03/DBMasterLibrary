package com.dbmasterlibrary;


import android.os.AsyncTask;
import android.util.Log;
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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ConnectionServer {
    // 서버에 사용자 id, pw 전송 후 응답 받음 (응답 결과 확인 필요)


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
                    .add("Id", userId)
                    .add("Pw", userPw)
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




   // public void requestConnection(String userId, String userPw) {

        /*
        // MainActivity에서 테스트한 코드  / 라이브러리에 맞게 변경 필요
        // Instantiate the RequestQueue.
        final RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.google.com";
        //"http://3.34.44.106:8080/";
        // Request a string response from the provided URL.
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0, 500));
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
                System.out.println(error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", "a");
                params.put("pw", "b");
                return params;

            }
        };
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }*/


        /*
        StringBuilder output = new StringBuilder();
        final String urlStr = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn != null) {
                conn.setConnectTimeout(5000); // 타임아웃 5초
                conn.setRequestMethod("POST"); //POST로 연결
                conn.setUseCaches(false);
                conn.setDoInput(true); //서버에서 읽기 모드 지정
                conn.setDoOutput(true); //서버로 쓰기 모드 지정

                //서버에게 웹에서 Form으로 값이 넘어온 것과 같은 방식으로 처리하라는 것을 알려준다. ?
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");


                // 서버로 값 전송 (응답 결과 확인 필요)
                StringBuffer buffer = new StringBuffer();
                buffer.append("ID").append("=").append(userId).append("&"); //변수 구분은 & 사용
                buffer.append("PW").append("=").append(userPw);

                OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream());
                PrintWriter writer = new PrintWriter(outStream);
                writer.write(buffer.toString());
                writer.flush();


                int resCode = conn.getResponseCode(); // 응답코드


                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); // 서버에서 응답받기

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
            Log.d("예외 발생 : ", "Exception: " + ex.toString());
        }
        Log.d("Log", "result: " + output.toString());
*/
   // }


}
