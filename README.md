# 서블릿으로 서버 아키텍처 개선하기

### V1 : MVC패턴에 따라 뷰 랜더링, 비즈니스 로직, 데이터를 분리

  * Model은 클래스(POJO), View는 jsp, Controller는 servlet로 구성.
  * Controller인 servlet에서 요청 경로마다 **공통의 비즈니스 로직 중복** 작성되는 문제 발생.
  * ex) 모든 서블릿에서 jsp로 forward하는 메서드의 중복 구성
  
      <img width="537" alt="스크린샷 2024-03-12 오후 9 55 33" src="https://github.com/dongjun-Yi/jspboard/assets/90665186/c0e34d94-6c60-4524-999e-c98966cbfb94">



### V2 : Front Controller 패턴 도입
  * 공통된 비즈니스 로직을 하나의 서블릿으로 모든 클라이언트의 요청 처리
  * 중복되는 viewPath으로 만약 jsp의 변경시 전체코드를 변경해야 하는 문제 발생.

    <img width="352" alt="스크린샷 2024-03-12 오후 4 23 50" src="https://github.com/dongjun-Yi/jspboard/assets/90665186/a7173191-26e4-4175-b60a-c0b3fe5fa132">

### V3 : View 분리
  * 하드코딩된 viewPath를 별도로 처리하기 위한 View 객체 생성 
