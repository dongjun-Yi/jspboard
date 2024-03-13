package com.example.jspboard.v3.controller;

import com.example.jspboard.v3.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV3 {
    MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
