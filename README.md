# demo-station-java


## 환경설정

각 데모 클래스 코드를 참조하여 아래 인증 정보를 입력 합니다.

```java
opts.query = "user_email=" + "[메일주소]" + "&user_token=" + "[인증토큰]" + "&shop_id=" + "[샵번호]";
```

인증토큰은 http://walkinsights.com/apidoc#api-User_Session-SignInUser API를 호출하면 'authentication_token' 항목을 통해 얻어올 수 있습니다.


## 성공 예

접속에 성공하면 해당 샵의 ZOYI Square로 부터 수집된 신호들이 실시간으로 미러 됩니다.
접속 이후의 신호만 미러되며 이전 데이터를 열람하고자 하는 경우, REST API를 이용해야 합니다.

```
{"square_mac":"f4fd2b102606","ts":1438324960285,"device_id":"e89fa04fea3056ce59bd94d0684ecbe2","rssi":-64}
{"square_mac":"f4fd2b102606","ts":1438325027784,"device_id":"e89fa04fea3056ce59bd94d0684ecbe2","rssi":-59}
...
```


## ShopMirroringDemo

해당 샵의 모든 시그널을 받는 API

## ShopMonitoringDemo

해당 샵의 특정(target)맥 어드레스만 받는 API
ShopMirroringDemo와 다르게 특정 맥어드레스만 필터링 되지 않은 실시간 데이터를 받게됩니다.
opts query는 아래와 같이 3개 필드 모두 설정 되어야 합니다.
```
opts.query = "user_email=" + "" +
             "&user_token=" + "" +
             "&shop_id=" + "1";
```

접속에 성공한 후, 모니터링을 하고자 하는 타켓 디바이스의 맥어드레스를 보내면 됩니다.
필요에 따라 복수개 보내실 수 있습니다.

```
socket.emit("join", "001122334455");
```
맥어드레스 포맷은 ':'을 제외, 소문자 형태입니다.

## SquareMonitoringDemo

해당 스퀘어의 특정(target)맥 어드레스만 받는 API
ShopMonitoring과 다르게 지정한 square에서만 특정 맥어드레스만 실시간 데이터를 받게됩니다.
opts query는 아래와 같이 4개 필드 모두 설정 되어야 합니다.
```
opts.query = "user_email=" + "" +
             "&user_token=" + "" +
             "&square_mac=" + "f4fd2b100000";
```

접속에 성공한 후, 모니터링을 하고자 하는 타켓 디바이스의 맥어드레스를 보내면 됩니다.
필요에 따라 복수개 보내실 수 있습니다.

```
socket.emit("join", "001122334455");
```
맥어드레스 포맷은 ':'을 제외, 소문자 형태입니다.
