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

    String baseUrl = "https://pnuteam03.herokuapp.com";

    // DB서버 연결 확인
    public String connectionRequest(String userId, String userPw) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

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
            result = "failure: Wrong PW";
        }
        // id, pw 둘다 틀린경우
        if (jsonObject.getString("idValid").equals("unavailable") && jsonObject.getString("connectionValid").equals("unavailable")) {
            result = "failure: Wrong ID";
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

        String strApi = "/v1/table/create";

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
            result = "failure: table already exists";
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
            result = "failure: duplicate ID";
        }

        return result;
    }

    public ArrayList<String> getAllTables(String dbName) throws JSONException, IOException {
        //String result = null;
        ArrayList<String> result = new ArrayList<>();
        OkHttpClient client = new OkHttpClient();

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
            resultMessage = "failure: No existing table";
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


    public String getTableInfo(String tableName, String userId) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/table/get-info";

        final JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("name", userId);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 테이블 정보 받기 성공
        if (jsonObject.getString("result").equals("S01")) {
            result = jsonObject.getString("value");
        }
        // 테이블 정보 받기 실패
        if (jsonObject.getString("result").equals("E01")) {
            result = "failure: data doesn't exist";
        }

        return result;
    }

    public String tableDrop(String userId, String tableName) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/table/drop";

        final JSONObject object = new JSONObject();
        object.put("name", userId);
        object.put("tableName", tableName);


        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 테이블 삭제 성공
        if (jsonObject.getString("result").equals("S01")) {
            result = "DROP success";
        }
        // 테이블 삭제 실패
        if (jsonObject.getString("result").equals("E01")) {
            result = "failure: Unknown table '" + tableName + "'";
        }
        return result;
    }

    public String tableRename(String userId, String tableName, String newTableName) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/table/rename";

        final JSONObject object = new JSONObject();
        object.put("name", userId);
        object.put("tableName", tableName);
        object.put("newName", newTableName);



        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 테이블 이름 변경 성공
        if (jsonObject.getString("result").equals("S01")) {
            result = "success";
        }

        // 테이블 이름 변경 실패
        if (jsonObject.getString("result").equals("E01")) {
            result = "failure: " + tableName + " doesn't exist";
        }
        return result;
    }

    public String tableUpdate(String userId, String tableName, String primary_key_name, String primary_key_value, String update_column_name, String update_value) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/column/update";

        final JSONObject object = new JSONObject();
        object.put("name", userId);
        object.put("tableName", tableName);
        object.put("primary_key_name", primary_key_name);
        object.put("primary_key_value", primary_key_value);
        object.put("update_column_name", update_column_name);
        object.put("update_value ", update_value );


        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 테이블 업데이트 성공
        if (jsonObject.getString("result").equals("S01")) {
            result = "update success";
        }
        // 테이블 업데이트 실패
        if (jsonObject.getString("result").equals("E01")) {
            result = "failure: Unknown column '" + update_value + "'";
        }
        return result;
    }


    public String checkTableName(String userId, String tableName) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/table/duplicate";

        final JSONObject object = new JSONObject();
        object.put("name", userId);
        object.put("tableName", tableName);


        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 테이블 이름이 사용 가능한 경우
        if (jsonObject.getString("result").equals("S01")) {
            result = "success";
        }
        // 테이블이름이 중복된 경우
        if (jsonObject.getString("result").equals("E01") ) {
            result = "failure : Duplicate table name ";
        }

        return result;
    }

    public String tableDataSearch(String userId, String tableName, String keyword) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/table/search";

        final JSONObject object = new JSONObject();
        object.put("tableName", tableName);
        object.put("name", userId);
        object.put("keyword", keyword);


        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 테이블 내 데이터 검색에 성공한 경우
        if (jsonObject.getString("result").equals("S01")) {
            result = jsonObject.getString("value");;
        }
        // 테이블 내 데이터 검색에 실패한 경우
        if (jsonObject.getString("result").equals("E02") ) {
            result = "failure : " + "Table '"  + userId + "." + tableName + "' doesn't exist";
        }

        return result;
    }

    public String insertData(String dbName, String tableName, String insert) throws JSONException, IOException {
        String result = null;
        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/table/insert";

        final JSONObject object = new JSONObject();
        object.put("name", dbName);
        object.put("tableName",tableName);
        object.put("insert", insert);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();

        String responseToString = response.body().string();

        JSONObject jsonObject = new JSONObject(responseToString);

        if(jsonObject.getString("result").equals("S01")) {
            result = jsonObject.getString("message");
        }
        else if(jsonObject.getString("status").equals(500)) {
            result = "failure: Duplicate entry";
        }


        return result;
    }

    public String getTableData(String dbName, String tableName) throws JSONException, IOException {
        String result = null;
        OkHttpClient client = new OkHttpClient();
        
        String strApi = "/v1/column/get-all";

        final  JSONObject object = new JSONObject();
        object.put("name", dbName);
        object.put("tableName", tableName);

        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();

        Response response = client.newCall(request).execute();

        String responseToString = response.body().string();

        JSONObject jsonObject = new JSONObject(responseToString);
      //  JSONArray jsonArray = jsonObject.getJSONArray("value");

        if(jsonObject.getString("result").equals("S01")) {
            result = jsonObject.getString("value");
        }

        else if(jsonObject.getString("result").equals("E02")) {
            result = "failure: Table doesn't exist";
        }

        return result;
    }

    public String userEmailAuth(String email) throws JSONException, IOException {
        String result = null;

        OkHttpClient client = new OkHttpClient();

        String strApi = "/v1/auth/request";

        final JSONObject object = new JSONObject();
        object.put("email", email);


        Request request = new Request.Builder()
                .url(baseUrl + strApi)
                .post(RequestBody.create(MediaType.parse("application/json"), String.valueOf(object)))
                .build();


        Response response = client.newCall(request).execute();
        String responseDataString = response.body().string();
        JSONObject jsonObject = new JSONObject(responseDataString);


        // 이메일에 인증번호 전송을 성공한 경우
        if (jsonObject.getString("result").equals("S01")) {
            result = "메일이 성공적으로 발송되었습니다.";
        }
        // 이메일에 인증번호 전송을 실패한 경우
        if (jsonObject.getString("result").equals("E01") ) {
            result = "failure : 이메일 형식이 잘못되었습니다.";
        }

        return result;
    }

}
