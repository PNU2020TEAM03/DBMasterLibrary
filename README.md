# DBMasterLibrary

----

## changelog
* 2020.05.10 첫 등록

----

## DB 서버 연결
네트워크 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.


----


**입력 값 **

* 사용자 ID
* 사용자 PassWord

> 사용법

    ConnectionServer connectionServer = new ConnectionServer();
    connectionServer.connectionRequest("userId", "userPw");



----
** 응답 결과 **
  

> 연결 성공
    

    Connection Success


>PW가 틀린경우


    Wrong PW



>ID, PW가 틀린경우


    Wrong ID
