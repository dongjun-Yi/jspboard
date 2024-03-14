package com.example.jspboard.v3.controller;

import com.example.jspboard.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PostDetailUpdateControllerV3 implements ControllerV3 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            String id = req.getParameter("id");
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            req.setAttribute("id", id);
            req.setAttribute("title", title);
            req.setAttribute("content", content);

            return new MyView("/WEB-INF/v3/post-detail-update.jsp");
        }
        return null;
    }
}
