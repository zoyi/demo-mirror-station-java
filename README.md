# demo-mirror-station-java


## 환경설정

Main 클래스 코드를 참조하여 아래 인증 정보를 입력 합니다.

```java
opts.query = "user_email=" + "[메일주소]" + "&user_token=" + "[인증토큰]" + "&shop_id=" + "[샵번호]";
```

인증토큰은 http://walkinsights.com/apidoc#api-User_Session-SignInUser API를 호출하면 'authentication_token' 항목을 통해 얻어올 수 있습니다.

