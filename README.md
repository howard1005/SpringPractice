## ETC
- 과제의 방향성을 간단한 Spring 프레임워크를 구현하여 이전 과제의 코드를 가져다 쓰는것으로 해볼까 한다.
- 과제의 구조 및 HTTP 학습도 하면서 Spring에 대한 깊은 고찰도 할 수 있을것 같다.
- 의존성 주입 위해 Google 오픈소스의 Reflections와 view를 위해 Mustach 엔진을 쓸 생각이다.

## ETC
- 커스텀 스프링 구현을 어느정도 완성하여 이전 Spring 과제를 그대로 포팅하여 동작 가능했습니다.
- Component와 Bean기능을 구현하였습니다.
- Interceptor와 HandlerMethodArgumentResolver기능 구현 하였습니다.
- BasicDataSource를 이용하여 JdbpTemplate를 구현하였습니다.
- RestController 및 ResponseBody와 같이 REST API 기능을 구현하였습니다.
- WebConfig에 DataSource관련 Bean설정을추가 하였습니다. (application.properties에서 세팅하던것을 WebConfig로 옮김)
- Java와 Spring 동작과정에 대해 전반적으로 공부할 수 있었고 기존 Spring 프레임워크가 어떻게 짜여져 있는지도 비교를 할 수 있었습니다.
- 추가로 어노테이션 프로세서를 통해 Java Compile단 까지 간섭할 수 있다는것도 배웠습니다.
- 리팩토링 및 테스트, 예외처리 Http keep-alive기능등 더 할것들이 있지만 일단 다음 챕터인 서버 배포, 로그 수집, 스트레스 테스트, GC 모니터링 먼저 진행하고자 합니다.