# 웹 게시판 서버 아키텍처 개선하기

### V1 : MVC 패턴에 따라 뷰 랜더링, 비즈니스 로직, 데이터를 저장하는 역할을 나눔

  * Model은 클래스(POJO), View는 jsp, Controller는 servlet로 구성.
  * Controller인 servlet에서 요청 경로마다 **공통의 비즈니스 로직 중복** 작성되는 문제 발생.
  * ex) 모든 서블릿에서 jsp로 forward하는 메서드의 중복 구성
   ```
   RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/post-list.jsp");
   dispatcher.forward(req, resp);
   ```

### V2 : Front Controller 패턴 도입
  * 공통된 비즈니스 로직을 하나의 서블릿으로 모든 클라이언트의 요청 처리하도록 FrontController 도입.
  * 중복되는 viewPath으로 만약 jsp의 코드가 변경되면 viewPath를 변경해야 하는 문제 발생.
  * ex) 모든 controller에 구성된 viewPath의 물리적인 주소
   ```
   String viewPath = "/WEB-INF/post-list.jsp"
   ```

### V3 : View 분리
  * 하드코딩된 viewPath를 별도로 처리하기 위한 View 객체 생성
  * MyView 클래스 ```render()```에 viewPath로 forward하는 로직을 구성하여 Controller마다 jsp로 forward하는 중복의 코드를 제거.
  * FrontController 로직
   ```
   MyView view = controller.process(req, resp);
   view.render(req, resp);
   ```
  * MyView의 render()
   ```
    public void render(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }
   ```

### V4 : Model 추가 + viewResolver 추가
  * Controller마다 서블릿에 대한 종속성을 제거하기 위해 FrontController에서 model을 생성해 실행하는 Controller에게 전달하도록 구현.
  * ViewResolver는 실제 viewPath 경로를 만들어 MyView객체를 반환.
  * FrontController 로직
   ```
    Map<String, String> paramMap = createParamMap(req); // request 객체의 속성들을 paramMap에 보관
    Map<String, Object> model = createModel(req); // request 객체에서 필요한 정보들 보관

    String viewName = controller.process(paramMap, model);
    MyView myView = viewResolver(viewName);
    myView.render(model, req, resp);
   ```
  * ViewResolver 로직
   ```
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/v4/" + viewName + ".jsp");
    }
   ```

### V5 : adapter 패턴 도입
  * 서로 다른 Controller 인터페이스의 구현체 사용을 유연하게 도입하기 위해 Adapter 패턴으로 Controller 사용
 1. 핸들러(Controller)를 찾기
 2. 핸들러(Controller)에 알맞는 어댑터를 찾기
 3. 찾은 어댑터를 통해 핸들러 실행
 4. 핸들러(Controller)가 반환한 ModelView 객체를 통해 viewResolver에게 전달해 MyView 객체 생성
 5. MyView에서 jsp로 forward 로직 수행
 * FrontController 로직

   ```
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);  // 1

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler); // 2
        ModelView mv = adapter.handle(req, resp, handler); // 3

        MyView view = viewResolver(mv.getViewName()); //4
        view.render(mv.getModel(), req, resp); //5
    }
   ```
   
