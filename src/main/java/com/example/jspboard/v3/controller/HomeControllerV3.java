package com.example.jspboard.v3.controller;

import com.example.jspboard.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HomeControllerV3 implements ControllerV3 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET"))
            return new MyView("/WEB-INF/v3/login-form.jsp");
        return null;
    }
}
