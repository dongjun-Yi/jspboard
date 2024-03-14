package com.example.jspboard.v4;

import com.example.jspboard.MyView;
import com.example.jspboard.v4.controller.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "FrontControllerV4", urlPatterns = "/v4/*")
public class FrontControllerV4 extends HttpServlet {
    private HashMap<String, ControllerV4> controllerV4Map = new HashMap<>();

    public FrontControllerV4() {
        controllerV4Map.put("/v4", new HomeControllerV4());
        controllerV4Map.put("/v4/login", new LoginControllerV4());
        controllerV4Map.put("/v4/logout", new LogoutControllerV4());
        controllerV4Map.put("/v4/post-add", new PostAddControllerV4());
        controllerV4Map.put("/v4/post-delete", new PostDeleteControllerV4());
        controllerV4Map.put("/v4/post-detail", new PostDetailControllerV4());
        controllerV4Map.put("/v4/post-detail-update", new PostDetailUpdateControllerV4());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        ControllerV4 controller = controllerV4Map.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = createModel(req);

        String viewName = controller.process(paramMap, model);
        MyView myView = viewResolver(viewName);
        myView.render(model, req, resp);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/v4/" + viewName + ".jsp");
    }

    private Map<String, Object> createModel(HttpServletRequest req) {
        Map<String, Object> model = new HashMap<>();
        model.put("method", req.getMethod());
        model.put("session", req.getSession());
        return model;
    }

    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();

        req.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }
}
