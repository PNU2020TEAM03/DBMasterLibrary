package com.dbmasterlibrary;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class DBMasterLibrary {

    // DB서버 연결 확인
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

        // id는 맞았는데 비밀번호 틀린경우
        if (jsonObject.getString("idValid").equals("available") && jsonObject.getString("connectionValid").equals("unavailable")) {
            result = "Wrong PW";
        }
        // id, pw 둘다 틀린경우
        if (jsonObject.getString("idValid").equals("unavailable") && jsonObject.getString("connectionValid").equals("unavailable")) {
            result = "Wrong ID";
        }
        // 연결 가능한 경우
        if (jsonObject.getString("idValid").equals("available") && jsonObject.getString("connectionValid").equals("available")) {
            result = "Connection Success";
        }

        return result;
    }

    // 데이터베이스 테이블 생성
    public String createTable(String name, String tableName, String fieldInfo) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0/";
        String strApi = "v1/table/create";

        final JSONObject object = new JSONObject();
        object.put("name", name);
        object.put("tableName", tableName);
        object.put("fieldInfo", fieldInfo);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);

        // E01 : 테이블 이름이 이미 있는 경우(테이블 생성 실패)
        if (jsonObject.getString("result").equals("E01")) {
            result = "table already exists";
        }
        // S01 : 테이블 생성 성공
        if (jsonObject.getString("result").equals("S01")) {
            result = "create success";
        }

        return result;
    }

    // 회원가입
    public String signUp(String userId, String userPw) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
        String strApi = "/v1/sign-up/request";

        final JSONObject object = new JSONObject();
        object.put("name", userId);
        object.put("pw", userPw);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 회원가입을 성공한 경우
        if (jsonObject.getString("result").equals("S01")) {
            result = "success";
        }
        // 회원가입에 실패한 경우
        if (jsonObject.getString("result").equals("E01") ) {
            result = "failure : Duplicate ID ";
        }

        return result;
    }

    // 아이디 중복 확인
    public String checkId(String userId) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
        String strApi = "/v1/sign-up/check-name";

        final JSONObject object = new JSONObject();
        object.put("name", userId);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 사용가능한 ID인 경우
        if (jsonObject.getString("result").equals("S01")) {
            result = "available";
        }
        // 입력한 ID가 이미 서버에 있는 경우
        if (jsonObject.getString("result").equals("E01")) {
            result = "duplicate";
        }

        return result;
    }

    public ArrayList<String> getAllTables(String dbName) throws JSONException, IOException {
        //String result = null;
        ArrayList<String> result = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

        String baseUrl = "http://54.180.95.198:8081/dbmasterspringboot-1.0";
        String strApi = "/v1/table/all-tables";

        final JSONObject object = new JSONObject();
        object.put("name", dbName);


        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();

        String responseToString = response.body().string();

        JSONObject jsonObject = new JSONObject(responseToString);
        JSONArray jsonArray = jsonObject.getJSONArray("value");

        String resultMessage = null;
        if(jsonObject.getString("value").isEmpty()) {
            resultMessage = "No existing table";
            result.add(resultMessage);
        }
        else {
            //result = jsonObject.getString("value");
            for(int i=0; i<jsonArray.length(); i++) {
                result.add(jsonArray.getString(i));
            }

        }

        return result;
    }

}
