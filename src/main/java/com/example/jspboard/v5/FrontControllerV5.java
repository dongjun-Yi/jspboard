package com.example.jspboard.v5;

import com.example.jspboard.ModelView;
import com.example.jspboard.MyView;
import com.example.jspboard.v4.controller.*;
import com.example.jspboard.v5.controller.ControllerV4HandlerAdapter;
import com.example.jspboard.v5.controller.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FrontControllerV5", urlPatterns = "/v5/*")
public class FrontControllerV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList();

    public FrontControllerV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/v5", new HomeControllerV4());
        handlerMappingMap.put("/v5/login", new LoginControllerV4());
        handlerMappingMap.put("/v5/logout", new LogoutControllerV4());
        handlerMappingMap.put("/v5/post-add", new PostAddControllerV4());
        handlerMappingMap.put("/v5/post-delete", new PostDeleteControllerV4());
        handlerMappingMap.put("/v5/post-detail", new PostDetailControllerV4());
        handlerMappingMap.put("/v5/post-detail-update", new PostDetailUpdateControllerV4());
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object handler = getHandler(req);

        if (handler == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(req, resp, handler);

        MyView view = viewResolver(mv.getViewName());
        view.render(mv.getModel(), req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/v5/" + viewName + ".jsp");
    }

    private Object getHandler(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler))
                return adapter;
        }
        throw new IllegalArgumentException("handler Adapter를 찾을 수 없습니다. handler = " + handler);
    }
}
