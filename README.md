# 웹 게시판 서버 아키텍처 개선하기

### V1 : MVC패턴에 따라 뷰 랜더링, 비즈니스 로직, 데이터를 분리

  * Model은 클래스(POJO), View는 jsp, Controller는 servlet로 구성.
  * Controller인 servlet에서 요청 경로마다 **공통의 비즈니스 로직 중복** 작성되는 문제 발생.
  * ex) 모든 서블릿에서 jsp로 forward하는 메서드의 중복 구성
   ```
   RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/post-list.jsp");
   dispatcher.forward(req, resp);
   ```

### V2 : Front Controller 패턴 도입
  * 공통된 비즈니스 로직을 하나의 서블릿으로 모든 클라이언트의 요청 처리
  * 중복되는 viewPath으로 만약 jsp의 코드가 변경되면 viewPath를 변경해야 하는 문제 발생.
   ```
   String viewPath = "/WEB-INF/post-list.jsp"
   ```

### V3 : View 분리
  * 하드코딩된 viewPath를 별도로 처리하기 위한 View 객체 생성
  * MyView 클래스 ```render()```에 viewPath로 forward하는 로직을 구성하여 jsp로 forward하는 중복의 코드를 제거.
   ```
   MyView view = controller.process(req, resp);
   view.render(req, resp);
   ```
