# DBMasterLibrary

----

## changelog
* 2020.05.10 첫 등록

----
## 목차
* [DB 서버 연결](#DB-서버-연결)
* [테이블 생성 함수](#테이블-생성-함수)
* [회원가입 함수](#회원가입-함수)
* [아이디 중복 확인 함수](#아이디-중복-확인-함수)
* [테이블 모든 이름 조회 함수](#테이블-모든-이름-조회-함수)
* [테이블 정보를 받는 함수](#테이블-정보를-받는-함수)
* [테이블 이름 변경 함수](#테이블-이름-변경-함수)
* [테이블 데이터 갱신 함수](#테이블-데이터-갱신-함수)
* [테이블 이름 중복 확인 함수](#테이블-이름-중복-확인-함수)
* [테이블 내 데이터 검색 함수](#테이블-내-데이터-검색-함수)
* [테이블 칼럼 insert 함수](#테이블-칼럼-insert-함수)
* [테이블 데이터 delete 함수](#테이블-데이터-delete-함수)
* [테이블 데이터 전부 불러오기 함수](#테이블-데이터-전부-불러오기-함수)
* [사용자 이메일 인증 요청 함수](#사용자-이메일-인증-요청-함수)
* [사용자 테이블 데이터를 파일로 추출하는 함수](#사용자-테이블-데이터를-파일로-추출하는-함수)
* [사용자 이메일 인증 확인 함수](#사용자-이메일-인증-확인-함수)
* [테이블 데이터의 특정 index의 value값 추출 함수](#테이블-데이터의-특정-index의-value값-추출-함수)
* [테이블 칼럼 기준 정렬 함수](#테이블-칼럼-기준-정렬-함수)
* [테이블 join 함수](#테이블-join-함수)


---
## 라이브러리 사용 방법
DBMaster 라이브러리를 사용하기 위한 방법입니다.

> 프로젝트 수준의 gradle

    maven { url 'https://jitpack.io' }
    
코드를 allprojects의 repositories안에 추가합니다.



> app 수준의 gradle

    implementation 'com.github.PNU2020TEAM03:DBMasterLibrary:0.0.1'

코드를 dependencies 안에 추가합니다.




----

## DB 서버 연결
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.




**입력 값**

* 사용자 ID (String)
* 사용자 PassWord (String)

> 사용법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();
    dbMasterLibrary.connectionRequest("userId", "userPw");



**응답 결과**
  
* 리턴 타입 : JSONObject

> 연결 성공
    

    {"result":"S01","message":"DB 서버와 연결에 성공했습니다."}


>PW가 틀린경우


    {"result":"E01","message":"잘못된 비밀번호입니다."}



>ID, PW가 틀린경우


    {"result":"E02","message":"가입하지 않은 아이디이거나, 잘못된 비밀번호입니다."}
    
----


## 테이블 생성 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.




**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* 테이블 정보 (String)

> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String name = "uuzaza";
    String tableName = "test2Table3";
    String fieldInfo = "sno int(11) NOT NULL, name char(10) DEFAULT NULL, PRIMARY KEY (sno)";

    dbMasterLibrary.createTable(name, tableName, fieldInfo);


**응답 결과**
  
* 리턴 타입 : JSONObject

>테이블 생성  성공
    

    {"result":"S01","message":"테이블 생성에 성공하였습니다."}


>테이블 이름이 이미 존재하는 경우


    {"result":"E01","message":"테이블 생성에 실패했습니다. 테이블 이름이 이미 존재합니다. 새로운 테이블 이름을 입력해주세요."}


----


## 회원가입 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.




**입력 값**

* 사용자 아이디 (String)
* 사용자 비밀번호 (String)


> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "uuzaza";
    String userPw = "test2Table3";
    
    dbMasterLibrary.signUp(userId , userPw);


**응답 결과**
  

>회원가입 성공
    

    {"result":"S01","message":"회원가입 성공."}


>아이디 중복으로 회원가입 실패


    {"result":"E01","error":"중복되는 ID입니다."}


----
## 아이디 중복 확인 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.



**입력 값**

* 사용자 아이디 (String)



> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary ();

    String userId = "uuzaza";
    
    dbMasterLibrary .checkId(userId);


**응답 결과**
  

>사용 가능한 ID인 경우
    

    {"result":"S01","message":"사용 가능한 ID입니다."}


>서버에 ID가 이미 존재하는 경우


    {"result":"E01","error":"중복되는 ID입니다."}


----

## 테이블 모든 이름 조회 함수

네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

**입력 값**

* 사용자 아이디 (String)

**사용 방법**

    DBMasterLibrary dbMaster = new DBMasterLibrary();
    String userId = "uuzaza";

>전체 테이블 목록 조회

    dbMaster.getAllTables(userId);

> 특정 index 테이블 조회

    int index = 1;
    dbMaster.getAllTables(userId).get(index);

**응답 결과**
  

>전체 테이블 목록 조회 성공
    

    [test1, test2, test2Table, test2Table2, test2Table3]

>특정 index 테이블 조회

    test2


>테이블 이름이 이미 존재하는 경우


    No existing table
    
-----

# 테이블 정보를 받는 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 지정하는 테이블의 정보를 받아옵니다.

**입력 값**

* 테이블 이름 (String)
* 사용자 아이디 (String)




> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary ();

    String tableName= "test1";
    String userId = "uuzaza";
    
    dbMasterLibrary .getTableInfo(tableName, userId );


**응답 결과**
  

>테이블 정보를 받아온 경우
    

    [{"columnName":"sno","datatype":"4","columnsize":"10","decimaldigits":null},{"columnName":"name","datatype":"1","columnsize":"10","decimaldigits":null}]


>테이블 정보를 받아오는데 실패한 경우


    failure


----

# 테이블 삭제 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 지정하는 테이블을 삭제합니다.

**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)





> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary ();

    String userId = "uuzaza";
    String tableName= "test1";
    
    dbMasterLibrary.tableDrop(userId, tableName);


**응답 결과**
  

>테이블을 삭제한 경우
    

    DROP success


>테이블 삭제에 실패한 경우


    Unknown table: 'test1'


----

# 테이블 이름 변경 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 지정하는 테이블의 이름을 변경합니다.

**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* 새로운 테이블 이름 (String)




> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary ();

    String userId = "test";
    String tableName= "test2Table";
    String newTableName= "test2TableNewName";
    
    dbMasterLibrary.tableRename(userId, tableName, newTableName);


**응답 결과**
  

>테이블 이름 변경에 성공한 경우
    

    success


>테이블 이름 변경에 실패한 경우


    Error: 'test2Table' doesn't exist


----

# 테이블 데이터 갱신 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 지정하는 테이블의 데이터를 갱신합니다.

**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* 기본키 이름 (String)
* 기본키 값 (String)
* 업데이트 열 이름 (String)
* 업데이트 값 (String)





> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary ();

    String userId = "uuzaza";
    String tableName= "test1";
    String primary_key_name = "sno";
    String primary_key_value = "1";
    String update_column_name = "name";
    String update_value = "'업데이트적용'";
    
    dbMasterLibrary.tableUpdate(userId, tableName, primary_key_name, primary_key_value, update_column_name, update_value);


**응답 결과**
  

>테이블 데이터를 갱신한 경우
    

    update success


>테이블 데이터 갱신에 실패한 경우


    Unknown column: '업데이트적용'


----

## 테이블 이름 중복 확인 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 입력한 테이블 이름의 중복여부를 확인합니다.


**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)

> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "uuzaza";
    String tableName= "test1";

    dbMasterLibrary.checkTableName(userId, tableName);



**응답 결과**
  

> 테이블 이름이 사용 가능한 경우
    

    success


> 테이블 이름이 중복인 경우


    failure : Duplicate table name


    
----


## 테이블 내 데이터 검색 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 입력한 테이블 내 데이터를 검색합니다.


**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* 키워드 (String)


> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName= "testTable";
    String keyword= "3";
    

    dbMasterLibrary.tableDataSearch(userId, tableName, keyword);



**응답 결과**
  

> 테이블 내 데이터 검색에 성공한 경우
    

    [{"sno":"3","name":"테스트3"}]


> 테이블 내 데이터 검색에 실패한 경우


    failure : Table 'test.testTable' doesn't exist



----

## 테이블 칼럼 insert 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* insert값 (String)


> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName= "testTable";
    String insert= "7, '테스트7'";
    

    dbMasterLibrary.insetData(userId, tableName, insert);



**응답 결과**
  

> 테이블 칼럼 insert 성공

    insert 성공했습니다.


> 테이블 내 중복되는 insert 값이 존재하는 경우


    failure : Duplicate entry
   
----

## 테이블 데이터 delete 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* key name (String)
* key value (String)


> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName= "testTable";
    String keyName = "sno";
    String keyValue = "7";
    

    dbMasterLibrary.deleteData(userId, tableName, keyName, keyValue);



**응답 결과**
  

> 테이블 데이터 delete 성공

    table data deleted


> 테이블 데이터 delete 실패


    Failure: Unknown Data
   
----

## 테이블 데이터 전부 불러오기 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)



> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName= "testTable";

    dbMasterLibrary.getTableData(userId, tableName);



**응답 결과**
  

> 테이블 데이터 불러오기 성공

    [{"sno":1,"name":"테스트1"},{"sno":2,"name":"테스트2"},{"sno":3,"name":"테스트3"},{"sno":4,"name":"테스트4"},{"sno":5,"name":"테스트5"},{"sno":6,"name":"테스트6"},{"sno":8,"name":"테스트8"}]


> 해당 테이블이 존재하지 않는 경우

    failure: Data doesn't exist
    
-----

## 사용자 이메일 인증 요청 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 입력한 이메일에 인증번호를 전송합니다.


**입력 값**

* 사용자 이메일 (String)



> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String email= "uuzaza74@gmail.com";

    dbMasterLibrary.userEmailAuth(email);



**응답 결과**
  

> 이메일에 인증번호 전송을 성공한 경우
    

    메일이 성공적으로 발송되었습니다.


> 이메일에 인증번호 전송을 실패한 경우


    failure : 이메일 형식이 잘못되었습니다.



----


## 사용자 테이블 데이터를 파일로 추출하는 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자의 테이블 데이터를 파일로 추출하여 이메일로 전송합니다.


**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* 사용자 이메일 (String)




> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName = "testTable";
    String email= "uuzaza74@gmail.com";

    dbMasterLibrary.tableDataExport(userId, tableName , email);



**응답 결과**
  

> 파일을 메일로 전송 성공한 경우
    

    파일이 이메일로 전송되었습니다.


> 파일을 메일로 전송 실패한 경우


    Table 'test2.testTable' doesn't exist



----


## 사용자 이메일 인증 확인 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 입력한 이메일에 전송된 인증번호를 받아서 디비값과 비교하여 일치하는지 확인합니다.


**입력 값**


* 사용자 이메일 (String)
* 인증번호 (String)




> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String email= "uuzaza74@gmail.com";
    String authNum= "877387";

    dbMasterLibrary.userEmailCheck(email, authNum);



**응답 결과**
  

> 이메일 인증에 성공한 경우
    

    인증 되었습니다.


> 이메일 인증에 실패한 경우


    인증에 실패했습니다. 번호가 일치하지 않습니다.



----

## 테이블 데이터의 특정 index의 value값 추출 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

이 함수는 다른 함수의 리턴값에 대한 특정 value값을 추출해내기 위해 사용됩니다.

함수의 리턴값은 ArrayList 타입이므로 함수를 호출할 때 ArrayList 타입으로 호출해야 합니다.

**입력 값**

* 테이블 데이터 목록 (String)
* index값 (int)


**사용 방법**

> 테이블 데이터 불러오는 함수 호출

String값의 리턴값을 반환한다.

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName= "testTable";

    String tableDataList = dbMasterLibrary.getTableData(userId, tableName);

    // tableDataList의 리턴값: [{"sno":1,"name":"테스트1"},{"sno":2,"name":"테스트2"},{"sno":3,"name":"테스트3"},{"sno":4,"name":"테스트4"},{"sno":5,"name":"테스트5"},{"sno":6,"name":"테스트6"},{"sno":8,"name":"테스트8"}]

> 특정 index에 대한 value값 추출

    ArrayList<String> result = dbLibrary.getIndexData(tableDataList, 0);


**응답 결과**
  

> SELECT문 처리 성공

    [1, 테스트1]


------

## 테이블 칼럼 기준 정렬 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

사용자가 입력한 테이블의 특정 칼럼을 정렬합니다.


**입력 값**

* 사용자 아이디 (String)
* 테이블 이름 (String)
* 칼럼 이름 (String)
* 정렬 방향 (String)




> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName = "testTable";
    String sortColumn = "name";
    String direction = "DESC;


    dbMasterLibrary.tableColumnSort(userId, tableName, sortColumn, direction);



**응답 결과**
  

> 테이블 특정 칼럼 정렬에 성공한 경우
    

    [{"sno":9,"name":"테스트9","testColumn1":1,"testColumn2":2},{"sno":8,"name":"테스트8","testColumn1":1,"testColumn2":2},{"sno":6,"name":"테스트6","testColumn1":1,"testColumn2":2},{"sno":5,"name":"테스트5","testColumn1":1,"testColumn2":2},{"sno":4,"name":"테스트4","testColumn1":1,"testColumn2":2},{"sno":3,"name":"테스트3","testColumn1":1,"testColumn2":2},{"sno":2,"name":"테스트2","testColumn1":1,"testColumn2":2},{"sno":12,"name":"테스트12","testColumn1":1,"testColumn2":2},{"sno":11,"name":"테스트11","testColumn1":1,"testColumn2":2},{"sno":10,"name":"테스트10","testColumn1":1,"testColumn2":2},{"sno":1,"name":"테스트1","testColumn1":1,"testColumn2":2}]


> 테이블 특정 칼럼 정렬에 실패한 경우


    failure : You have an error in your SQL syntax



----

## 테이블 join 함수
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.

join할 두 개의 테이블 모두 기준값이 되는 column값이 존재해야 합니다.

**입력 값**

* 사용자 아이디 (String)
* join 테이블 이름1 (String)
* join 테이블 이름2 (String)
* 기준 테이블 column (String)



> 사용 방법

    DBMasterLibrary dbMasterLibrary = new DBMasterLibrary();

    String userId = "test";
    String tableName= "testA";
    String joinTable = "testB"
    String joiningColumn = "id"
    
    dbMasterLibrary.join(userId, tableName, joinTable, joiningColumn);



**응답 결과**
  

> 테이블 join 성공

    [{"address":"xfds","phone":"01029302","name":"fewg","payment":"1239","id":"10293039","dept":"pop","hobby":"eng"},{"address":"few","phone":"01029382938","name":"awef","payment":"10","id":"19920392","dept":"sw","hobby":"wfa"},{"address":"awef","phone":"01023231232","name":"jeijfe","payment":"12899","id":"201524447","dept":"qwd","hobby":"qwr1"},{"address":"idonkwn","phone":"01012341234","name":"Kim","payment":"80000","id":"201724447","dept":"sdw","hobby":"???"}]



> 존재하지 않는 joining column

    failure: Unknown Column
