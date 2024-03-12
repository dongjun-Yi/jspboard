# 서블릿으로 서버 아키텍처 개선하기

* V1 : MVC패턴에 따라 뷰 랜더링, 비즈니스 로직, 데이터를 분리

  * Model은 클래스(POJO), View는 jsp, Controller는 servlet로 구성.
  * Controller인 servlet에서 요청 경로마다 **공통의 비즈니스 로직 중복** 작성되는 문제 발생.
  * ex) 모든 서블릿에서 jsp로 forward하는 메서드와 하드코딩된 viewPath 코드
    <img width="535" alt="image" src="https://github.com/dongjun-Yi/jspboard/assets/90665186/8055de43-d61f-4859-959d-1b08178fb220">
  

* V2 : Front Controller 패턴 도입
  * 공통된 비즈니스 로직을 하나의 서블릿으로 모든 클라이언트의 요청 처리 
