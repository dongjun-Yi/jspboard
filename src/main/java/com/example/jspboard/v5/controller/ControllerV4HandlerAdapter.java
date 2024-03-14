package com.example.jspboard.v5.controller;

import com.example.jspboard.ModelView;
import com.example.jspboard.v4.controller.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(req);
        Map<String, Object> model = createModel(req);

        String viewName = controller.process(paramMap, model);
        modelToRequestAttribute(model, req);
        ModelView modelView = new ModelView(viewName);
        return modelView;
    }

    private void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest req) {
        model.forEach((key, value) -> req.setAttribute(key, value));
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
