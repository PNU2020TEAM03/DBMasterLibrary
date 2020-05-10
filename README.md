# DBMasterLibrary
# DB Master Library

----

## changelog
* 2020.05.10 첫 등록

----

## DB 서버 연결
Network 통신이 필요하며 메인 쓰레드가 아닌 다른 쓰레드에서 사용해야합니다.


----

### input data

* 사용자 ID
* 사용자 PassWord

>example

    ConnectionServer connectionServer = new ConnectionServer();
    connectionServer.connectionRequest("userId", "userPw");
  
----
### response

* 성공

>example
    
    Connection Success

* PW가 틀린경우

>example
    
    Wrong PW



* ID, PW가 틀린경우

>example

    Wrong ID

