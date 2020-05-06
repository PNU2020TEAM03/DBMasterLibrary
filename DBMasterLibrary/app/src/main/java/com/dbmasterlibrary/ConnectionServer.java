package com.dbmasterlibrary;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ConnectionServer {

    public String connectionRequest(String userId, String userPw) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
        String strApi = "/v1/connection/check";

        final JSONObject object = new JSONObject();
        object.put("name", userId);
        object.put("pw", userPw);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        // 서버 결과 받아오기
        Response response = client.newCall(request).execute();

        // 받아온 결과에서 body를 String 으로 변환
        String responseDataString = response.body().string();

        //받아온 body를  json 객체로 변환
        JSONObject jsonObject = new JSONObject(responseDataString);

        // false = 0  true = 1
        int idCheck = 0;
        int conCheck = 0;

        if (jsonObject.getString("idValid").equals("available")) {
            idCheck = 1;
        }
        if (jsonObject.getString("connectionValid").equals("available")) {

            conCheck = 1;
        }

        // id는 맞았는데 비밀번호 틀린경우
        if (idCheck == 1 && conCheck == 0) {
            result = "Wrong PW";
        }
        // id, pw 둘다 틀린경우
        if (idCheck == 0 && conCheck == 0) {
            result = "Wrong ID";
        }
        // 연결 가능한 경우
        if (idCheck == 1 && conCheck == 1) {
            result = "Connection Success";
        }

        return result;
    }

}
