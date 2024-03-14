package com.example.jspboard.v4.controller;

import java.util.Map;

public class LogoutControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        String method = model.get("method").toString();
        if (method.equals("POST"))
            return "login-form";
        return null;
    }
}
