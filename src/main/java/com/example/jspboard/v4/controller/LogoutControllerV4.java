package com.example.jspboard.v4.controller;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

public class LogoutControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        HttpSession method = (HttpSession) model.get("method");
        if (method.equals("POST"))
            return "login-form";
        return null;
    }
}
