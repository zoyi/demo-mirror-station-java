# demo-mirror-station-java


## 환경설정

Main 클래스 코드를 참조하여 아래 인증 정보를 입력 합니다.

```java
opts.query = "user_email=" + "[메일주소]" + "&user_token=" + "[인증토큰]" + "&shop_id=" + "[샵번호]";
```

인증토큰은 http://walkinsights.com/apidoc#api-User_Session-SignInUser API를 호출하면 'authentication_token' 항목을 통해 얻어올 수 있습니다.


## 성고 예

접속에 성공하면 해당 샵의 ZOYI Square로 부터 수집된 신호들이 실시간으로 미러 됩니다.
접속 이후의 신호만 미러되며 이전 데이터를 열람하고자 하는 경우, REST API를 이용해야 합니다.

```
{"square_mac":"f4fd2b102606","ts":1438324960285,"device_id":"e89fa04fea3056ce59bd94d0684ecbe2","rssi":-64}
{"square_mac":"f4fd2b102606","ts":1438325027784,"device_id":"e89fa04fea3056ce59bd94d0684ecbe2","rssi":-59}
...
```
