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

    ConnectionServer connectionServer = new ConnectionServer();
    connectionServer.connectionRequest("userId", "userPw");



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
