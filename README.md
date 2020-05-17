# DBMasterLibrary

----

## changelog
* 2020.05.10 첫 등록

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
  

> 연결 성공
    

    Connection Success


>PW가 틀린경우


    Wrong PW



>ID, PW가 틀린경우


    Wrong ID
    
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
  

>테이블 생성  성공
    

    create success


>테이블 이름이 이미 존재하는 경우


    table already exists


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
    

    success


>아이디 중복으로 회원가입 실패


    failure : Duplicate ID


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
    

    available


>서버에 ID가 이미 존재하는 경우


    duplicate


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
