package com.example.jspboard.v4.controller;

import java.util.Map;

public class HomeControllerV4 implements ControllerV4 {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "login-form";
    }
}
