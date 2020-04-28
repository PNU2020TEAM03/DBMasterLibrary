package com.dbmasterlibrary;


import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class ConnectionServer {
    // 서버에 사용자 id, pw 전송 후 응답 받음 (응답 결과 확인 필요)
    public void requestConnection(String userId, String userPw) {
        StringBuilder output = new StringBuilder();
        final String urlStr = "http://54.180.95.198:8081/";
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

    }


}
