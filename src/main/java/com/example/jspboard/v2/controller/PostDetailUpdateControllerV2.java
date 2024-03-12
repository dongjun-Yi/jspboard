package com.example.jspboard.v2.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PostDetailUpdateControllerV2 implements ControllerV2 {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            String id = req.getParameter("id");
            String title = req.getParameter("title");
            String content = req.getParameter("content");

            req.setAttribute("id", id);
            req.setAttribute("title", title);
            req.setAttribute("content", content);

            String viewPath = "/WEB-INF/post-detail-update.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
            dispatcher.forward(req, resp);
        }
    }
}
